package com.video.platform.service;

import com.google.common.base.Joiner;
import com.video.platform.entity.Bill;
import com.video.platform.entity.User;
import com.video.platform.repository.BillDao;
import com.video.platform.repository.UserDao;
import com.video.platform.entity.BillCondition;
import org.apache.commons.io.IOUtils;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.joda.time.DateTime;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: yangl
 * Date: 13-7-28 下午4:45
 */
@Service
@Transactional
public class BillService {
    private static final Logger logger = LoggerFactory.getLogger(BillService.class);

    private static final String loginUrl = "http://211.151.131.71:8090/channel/user.do?method=login";
    private static final String dataUrl = "http://211.151.131.71:8090/channel/stat.do?method=getDataQuery&bookId=0";

    @Autowired
    private UserDao userDao;
    @Autowired
    private BillDao billDao;

    public List<Bill> find(BillCondition condition){
        List<Bill> list = billDao.find(condition);
        int count = 0;
        int cpCount = 0;
        for(Bill bill : list){
            count += bill.getUsers();
            cpCount += bill.getCpUsers();
        }

        Bill bill = new Bill();
        bill.setDate("<b>&nbsp;总计</b>");
        bill.setParentId(0);
        bill.setCpId(0);
        bill.setCpName("");
        bill.setUsers(count);
        bill.setCpUsers(cpCount);
        list.add(bill);

        return list;
    }

    public void process() throws IOException {
        DateTime dateTime = new DateTime().minusDays(1);
        String date = dateTime.toString("yyyy-MM-dd");
        process(date, date);
    }

    public void process(String btime, String etime) throws IOException {
        logger.info("开始时间:{}, 结束时间:{}", btime, etime);
        List<User> users = userDao.search(null);
        Map<Integer, User> userMap = new HashMap<Integer, User>();
        for (User user : users) {
            if (user.getCpId() > 0) {
                userMap.put(user.getCpId(), user);
            }
        }

        User user;
        List<Bill> bills = getBillFromSp(btime, etime);
        logger.info("共取回:{} 条数据...", bills.size());

        for (Bill bill : bills) {
            user = userMap.get(bill.getCpId());
            bill.setCpUsers(bill.getUsers());
            if (user != null) {
                int cpUsers = bill.getUsers() * (100 - user.getReduce()) / 100;
                if (cpUsers > 0) {
                    bill.setCpUsers(cpUsers);
                }
                bill.setCpName(user.getName());
            }
            logger.info("数据库保存一条数据{}", bill.toString());
            if(billDao.update(bill) == 0){
                billDao.save(bill);
            }
        }
    }

    public List<Bill> getBillFromSp(String btime, String etime) throws IOException {
        DefaultHttpClient httpclient = new DefaultHttpClient();

        HttpPost httpost = new HttpPost(loginUrl);

        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("name", "turuixiangmu"));
        nvps.add(new BasicNameValuePair("password", "turuixiangmu"));

        httpost.setEntity(new UrlEncodedFormEntity(nvps, Consts.UTF_8));
        HttpResponse response = httpclient.execute(httpost);
        HttpEntity entity = response.getEntity();

        System.out.println("Login form get: " + response.getStatusLine());
        EntityUtils.consume(entity);

        System.out.println("Post logon cookies:");
        List<Cookie> cookies = httpclient.getCookieStore().getCookies();
        if (cookies.isEmpty()) {
            System.out.println("None");
        } else {
            for (int i = 0; i < cookies.size(); i++) {
                System.out.println("- " + cookies.get(i).toString());
            }
        }
        System.out.println(Joiner.on(";").join(cookies));

        HttpGet httpGet = new HttpGet(dataUrl + "&begintime=" + btime + "&endtime=" + etime);
        httpGet.setHeader("cookie", Joiner.on(";").join(cookies));
        response = httpclient.execute(httpGet);

        entity = response.getEntity();
        String body = IOUtils.toString(entity.getContent());

        // 解析数据
        Document doc = Jsoup.parse(body);
        List<Element> list = doc.getElementsByTag("tr");
        List<Bill> result = new ArrayList<Bill>();
        String[] strs;
        Bill bill;
        for (Element element : list) {
            if (element.text().startsWith("201")) {
                strs = element.text().split(" ");
                if (strs != null) {
                    bill = new Bill();
                    bill.setCpId(Integer.parseInt(strs[2]));
                    bill.setParentId(Integer.parseInt(strs[1]));
                    bill.setCpName(strs[3]);
                    bill.setUsers(Integer.parseInt(strs[4]));
                    bill.setDate(strs[0]);
                    result.add(bill);
                }
            }
        }
        return result;
    }
}
