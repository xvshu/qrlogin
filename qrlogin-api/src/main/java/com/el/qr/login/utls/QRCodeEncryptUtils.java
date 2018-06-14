package com.el.qr.login.utls;

public class QRCodeEncryptUtils {
    public static String encrypt(String data) {
        String hex = data;
        try {
            String encrypt = DesUtil.encrypt(data);
            hex = HexUtils.str2HexStr(encrypt);
        }catch (Exception ex){
            hex = data;
            ex.printStackTrace();
        }
        return hex;
    }

    public static String decrypt(String data) {
        String decrypt=data;
        try{
            String hex_y=HexUtils.hexStr2Str(data);
            decrypt =DesUtil.decrypt(hex_y);
        }catch (Exception ex){
            decrypt=data;
            ex.printStackTrace();
        }
        return decrypt;

    }
}
