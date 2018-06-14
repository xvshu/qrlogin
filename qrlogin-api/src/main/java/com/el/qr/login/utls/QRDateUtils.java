package com.el.qr.login.utls;

import java.text.SimpleDateFormat;
import java.util.Date;

public class QRDateUtils {

    private static String formatStr = "yyyyMMdd";
    public static String getDataString(){
        SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
        return sdf.format(new Date());
    }

}
