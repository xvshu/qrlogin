package com.el.qr.login.service;

import com.el.qr.login.utls.QRDateUtils;
import com.el.qr.login.web.QRLoginController;
import com.eloancn.common.utils.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Service
public class RedisQRService {
    private static final Logger logger = LoggerFactory.getLogger(QRLoginController.class);


    @Resource(name = "codisTemplate")
    private HashOperations hashOperations;

    @Resource(name = "codisTemplate")
    private RedisOperations redisOperations;

    private int DAY_LOSS=2;


    private String QR_KEY="qrlogin_";

    public void initQRKey(String qrKey){
        if(!hashOperations.hasKey(initKey(),initKey()+qrKey)){
            hashOperations.put(initKey(),initKey()+qrKey,"");
            logger.info("=RedisQRService.setQRKey=>key:{} file:{} value:{}",initKey(),initKey()+qrKey,"");
            expire(initKey());
        }
    }


    public void setQRKey(String qrKey,String value){
        if(hashOperations.hasKey(initKey(),initKey()+qrKey)&& StringUtils.isEmpty(getQRValue(qrKey))){
            hashOperations.put(initKey(),initKey()+qrKey,value);
            logger.info("=RedisQRService.setQRKey=>key:{} file:{} value:{}",initKey(),initKey()+qrKey,value);
        }
    }


    public String getQRValue(String qrKey){
        return (String)hashOperations.get(initKey(),initKey()+qrKey);
    }

    private String initKey(){
        return QR_KEY+QRDateUtils.getDataString();
    }

    private boolean expire(String key){
        return redisOperations.expire(key,DAY_LOSS, TimeUnit.DAYS);
    }

}
