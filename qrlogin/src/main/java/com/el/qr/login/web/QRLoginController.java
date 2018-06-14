/*
 * Powered By [eloancn-generator]
 * Author:qinxf
 * Since 2017 - 2018
 */
 
package com.el.qr.login.web;

import com.el.qr.login.service.RedisQRService;
import com.el.qr.login.utls.HexUtils;
import com.el.qr.login.utls.QRCodeEncryptUtils;
import com.el.qr.login.utls.QRKeyUtils;
import com.google.common.util.concurrent.RateLimiter;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
/**
 * <>
 * @author qinxf
 * @version 1.0
 * @Time 2018-06-04 09:13:22
 */


/**
 * 首页控制器
 *
 * @author xvshu
 */
@Controller
@RequestMapping("/qr")
public class QRLoginController {
    private static final Logger logger = LoggerFactory.getLogger(QRLoginController.class);
    private RateLimiter limiter = RateLimiter.create(100); // 每秒不超过10个任务被提交

    @Autowired
    private RedisQRService redisQRService;

    @RequestMapping(value = "/login")
    public ModelAndView index(HttpServletRequest request) {
        ratelimit();
        ModelAndView mav = new ModelAndView("qrlogin/qrlogin");
        String codeMark = QRKeyUtils.createQRKey(request.getSession().getId());
        mav.addObject("code_mark", QRCodeEncryptUtils.encrypt(codeMark));
        redisQRService.initQRKey(codeMark);
        return mav;
    }

    @RequestMapping(value = "/login/success_check")
    public ModelAndView success_check(String code_mark) {
        ratelimit();
        ModelAndView mav = new ModelAndView("qrlogin/qrsuccess");
        mav.addObject("code_mark",code_mark);
        return mav;
    }

    @RequestMapping(value = "/login/main")
    public ModelAndView main(String userId) {
        ratelimit();
        ModelAndView mav = new ModelAndView("qrlogin/qrmain");
        mav.addObject("userId",userId);
        return mav;
    }


    @RequestMapping(value = "/login/encrypt_decrypt")
    public ModelAndView encrypt_decrypt(String code_mark) {
        ratelimit();
        ModelAndView mav = new ModelAndView("qrlogin/qrencrypt");
        return mav;
    }

    @RequestMapping(value = "/login/success")
    @ResponseBody
    public String success(String code_mark,String userID) {
        ratelimit();
        String result = "fail";

        if(StringUtils.isNotEmpty(code_mark)&&StringUtils.isNotEmpty(userID)&&!QRKeyUtils.isTimeOur(QRCodeEncryptUtils.decrypt(code_mark))){
            code_mark=QRCodeEncryptUtils.decrypt(code_mark);
            redisQRService.setQRKey(code_mark,userID);
            logger.info("=/login/success=>code_mark:{} userID:{}",code_mark,userID);
            result="success";
        }
        return result;
    }


    @RequestMapping(value = "/login/check")
    @ResponseBody
    public String check(String code_mark) {
        ratelimit();
        String result = "nouser";
        code_mark=QRCodeEncryptUtils.decrypt(code_mark);
        String qr_value = redisQRService.getQRValue(code_mark);
        logger.info("=/login/check=>code_mark:{} qr_value:{}",code_mark,qr_value);
        if(StringUtils.isNotEmpty(qr_value)){
            result=qr_value;
        }
        return result;
    }

    @RequestMapping(value = "/login/refreshQRKey")
    @ResponseBody
    public String refreshQRKey(HttpServletRequest request) {
        ratelimit();
        String codeMark = QRKeyUtils.createQRKey(request.getSession().getId());
        redisQRService.initQRKey(codeMark);
        return QRCodeEncryptUtils.encrypt(codeMark);
    }

    private void ratelimit(){
//        boolean isOk = limiter.tryAcquire(); // 请求RateLimiter, 超过permits会被阻塞
//        if(!isOk){
//
//        }
    }

    @RequestMapping(value = "/login/encrypt")
    @ResponseBody
    public String encrypt(String code) {
        return QRCodeEncryptUtils.encrypt(code);
    }

    @RequestMapping(value = "/login/decrypt")
    @ResponseBody
    public String decrypt(String code) {
        return QRCodeEncryptUtils.decrypt(code);
    }


}
