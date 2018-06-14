package com.el.qr.login.service;

import com.el.qr.login.api.QRLoginService;
import com.el.qr.login.dto.QRLoginDto;
import com.el.qr.login.utls.QRCodeEncryptUtils;
import com.el.qr.login.utls.QRKeyUtils;
import com.eloancn.framework.sevice.api.MessageStatus;
import com.eloancn.framework.sevice.api.ResultDTO;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("qRLoginServiceImpl")
public class QRLoginServiceImpl implements QRLoginService {

    @Autowired
    private RedisQRService redisQRService;

    @Override
    public ResultDTO<Boolean> initQRCode(QRLoginDto qrLoginDto) {
        ResultDTO<Boolean> resultDTO=new ResultDTO<Boolean>();
        if(ObjectUtils.allNotNull(qrLoginDto,qrLoginDto.getQrCode())
                &&qrLoginDto.getQrCode().trim().length()>0){

            //解密存储
            String qrcode = QRCodeEncryptUtils.decrypt(qrLoginDto.getQrCode().trim());
            boolean result = redisQRService.initQRKey(qrcode);
            resultDTO.setData(result);
            if(!result){
                resultDTO.setStatus(MessageStatus.FAIL.getValue());
                resultDTO.setMessage("Your code:["+qrLoginDto.getQrCode()+"] repeats in redis !");
            }else{
                resultDTO.setStatus(MessageStatus.SUCCESS.getValue());
            }
        }else{
            resultDTO.setStatus(MessageStatus.FAIL.getValue());
            resultDTO.setMessage("Your code is Null !");
        }
        return resultDTO;
    }

    @Override
    public ResultDTO<String> checkQRCodeBindUser(QRLoginDto qrLoginDto) {
        ResultDTO<String> resultDTO=new ResultDTO<String>();
        if(ObjectUtils.allNotNull(qrLoginDto,qrLoginDto.getQrCode())
                &&qrLoginDto.getQrCode().trim().length()>0) {
            //解密存储
            String qrcode = QRCodeEncryptUtils.decrypt(qrLoginDto.getQrCode().trim());
            String qr_value = redisQRService.getQRValue(qrcode);
            if(StringUtils.isNotEmpty(qr_value)){
                resultDTO.setData(qr_value.trim());
            }
            resultDTO.setStatus(MessageStatus.SUCCESS.getValue());
        }else{
            resultDTO.setStatus(MessageStatus.FAIL.getValue());
            resultDTO.setMessage("Your code is Null !");
        }

        return resultDTO;
    }

    @Override
    public ResultDTO<Boolean> bindQRCodeUser(QRLoginDto qrLoginDto) {
        ResultDTO<Boolean> resultDTO=new ResultDTO<Boolean>();
        if(ObjectUtils.allNotNull(qrLoginDto,qrLoginDto.getQrCode(),qrLoginDto.getQrUser())
                &&qrLoginDto.getQrCode().trim().length()>0
                &&qrLoginDto.getQrUser().trim().length()>0) {

            //解密存储
            String qrcode = QRCodeEncryptUtils.decrypt(qrLoginDto.getQrCode().trim());
            if(QRKeyUtils.isTimeOur(qrcode)){
                resultDTO.setStatus(MessageStatus.FAIL.getValue());
                resultDTO.setMessage("Your code:["+qrLoginDto.getQrCode()+"] is TimeOut !");
            }else{
                redisQRService.setQRKey(qrcode,qrLoginDto.getQrUser().trim());
                resultDTO.setStatus(MessageStatus.SUCCESS.getValue());
            }
        }else{
            resultDTO.setStatus(MessageStatus.FAIL.getValue());
            resultDTO.setMessage("Your code or user is Null !");
        }
        return resultDTO;
    }
}
