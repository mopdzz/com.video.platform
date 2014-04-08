package com.video.platform.web;

import com.video.platform.entity.Bill;
import com.video.platform.entity.User;
import com.video.platform.service.AccountService;
import com.video.platform.service.BillService;
import com.video.platform.service.ShiroDbRealm.ShiroUser;
import com.video.platform.entity.BillCondition;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import java.util.List;

/**
 * User: yangl
 * Date: 13-7-27 下午10:26
 */

@Controller
@RequestMapping(value = "/bill")
public class BillController {

    @Autowired
    private BillService billService;
    @Autowired
    private AccountService accountService;

    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model, ServletRequest request) {
        queryBill(model, request);

        return "bill/cpList";
    }

    @RequestMapping(value = "admin", method = RequestMethod.GET)
    public String adminList(Model model, ServletRequest request) {
        queryBill(model, request);

        return "bill/adminList";
    }

    private void queryBill(Model model, ServletRequest request) {
        BillCondition billCondition = new BillCondition();
        ShiroUser user = getCurrentUser();
        if (user.getParentId() == 0) {
            billCondition.setParentId(user.getParentId());
        } else {
            billCondition.setCpId(user.getCpId());
        }

        String cpId = request.getParameter("cpId");
        if (StringUtils.isNotBlank(cpId)) {
            billCondition.setCpId(Integer.parseInt(cpId));
        }

        String btime = request.getParameter("btime");
        String etime = request.getParameter("etime");
        if (StringUtils.isBlank(btime) || StringUtils.isBlank(etime)) {
            DateTime dateTime = new DateTime();
            String time = dateTime.toString("yyyy-MM-dd");
            btime = time;
            etime = time;
        }
        billCondition.setBtime(btime);
        billCondition.setEtime(etime);

        List<Bill> bills = billService.find(billCondition);
        model.addAttribute("bills", bills);
        model.addAttribute("billCondition", billCondition);

        if (user.getParentId() <= 0){
            model.addAttribute("trUser", accountService.getTrUser());
        }
    }

    /**
     * 取出Shiro中的当前用户Id.
     */
    private Long getCurrentUserId() {
        ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
        return user.id;
    }

    /**
     * 取出Shiro中的当前用户
     */
    private ShiroUser getCurrentUser() {
        return (ShiroUser) SecurityUtils.getSubject().getPrincipal();
    }
}
