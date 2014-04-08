import com.google.common.base.Joiner;
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
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * User: yangl
 * Date: 13-7-28 下午5:01
 */
public class HttpClientTest {
    private static final String loginUrl = "http://211.151.131.71:8090/channel/user.do?method=login";
    private static final String dataUrl = "http://211.151.131.71:8090/channel/stat.do?method=getDataQuery&bookId=0&begintime=2013-07-24&endtime=2013-07-24";

    @Test
    public void formLogin() throws IOException {
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

        HttpGet httpGet = new HttpGet(dataUrl);
        httpGet.setHeader("cookie", Joiner.on(";").join(cookies));
        response = httpclient.execute(httpGet);

        entity = response.getEntity();
        String body = IOUtils.toString(entity.getContent());
        System.out.println(body);
    }

    @Test
    public void testHtmlParse() throws IOException {
        Document doc = Jsoup.parse(new File("C:\\Users\\mopdgg\\Desktop\\new4.txt"), "utf-8");
        List<Element> list = doc.getElementsByTag("tr");
        List<String> result = new ArrayList<String>();
        for(Element element : list){
            System.out.println(element.text());
            if(element.text().startsWith("201")){
                result.add(element.text());
            }
        }

        System.out.println("----------------------------");
        for(String str : result){
            System.out.println(str.split(" ").length);
        }
    }
}
