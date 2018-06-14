package com.el.qr.login.service;

import com.el.qr.login.utls.QRDateUtils;
import com.el.qr.login.web.QRLoginController;
import com.eloancn.common.utils.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Service
public class RedisQRService {
    private static final Logger logger = LoggerFactory.getLogger(RedisQRService.class);


    @Resource(name = "codisTemplate")
    private ValueOperations valueOperations;

    @Resource(name = "codisTemplate")
    private RedisOperations redisOperations;

    /**
     * 每个key，5分钟失效
     */
    private int DAY_LOSS=2;
    private int MINUTES_LOSS=5;


    private String QR_KEY="QRLogin_";

    public boolean initQRKey(String qrKey){
        boolean result = false;
        String key=createQRKey(qrKey);
        if(!redisOperations.hasKey(key)){
            valueOperations.set(key,"");
            logger.info("=RedisQRService.initQRKey=>key:{} value:{}",key,"");
            result=true;
            expire_short(key);
        }
        return result;
    }


    public boolean setQRKey(String qrKey,String value){
        boolean result =false;
        String key = createQRKey(qrKey);
        if(redisOperations.hasKey(key)&& StringUtils.isEmpty(getQRValue(qrKey))){
            valueOperations.set(key,value);
            result=true;
            expire_long(key);
            logger.info("=RedisQRService.setQRKey=>key:{} value:{}",key,value);
        }else{
            logger.info("=RedisQRService.setQRKey=>faile key:{} redis not have key or is not empty ",key);
        }
        return result;
    }


    public String getQRValue(String qrKey){
        String key=createQRKey(qrKey);
        return (String)valueOperations.get(key);
    }

    private String createQRKey(String oldkey){
        return QR_KEY+oldkey;
    }


    private boolean expire_short(String key){
        logger.info("=RedisQRService.expire_short=> key:{} MINUTES:{}",key,MINUTES_LOSS);
        return redisOperations.expire(key,MINUTES_LOSS, TimeUnit.MINUTES);
    }

    private boolean expire_long(String key){
        logger.info("=RedisQRService.expire_long=> key:{} DAYS:{}",key,DAY_LOSS);
        return redisOperations.expire(key,DAY_LOSS, TimeUnit.DAYS);
    }

}
