package com.el.qr.login.utls;

public class QRKeyUtils {
    public static String  createQRKey(String sessionId){
        StringBuilder sb= new StringBuilder();
        sb.append(WebToolUtils.getLocalIP());
        sb.append("_");
        sb.append(sessionId);
        sb.append("_");
        sb.append(System.currentTimeMillis());
        sb.append("_EL");
        return sb.toString();
    }

    public static boolean isTimeOur(String oldCode){
        boolean isOut = true;
        String[] codes = oldCode.split("_");
        String old_time_str=codes[2];
        long old_time = Long.parseLong(old_time_str);
        long now_time = System.currentTimeMillis();
        if(now_time>old_time && (now_time-old_time)<180000){
            isOut=false;
        }
        return isOut;
    }
}
