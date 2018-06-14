package com.el.qr.login.base.simple;


import com.el.qr.login.utls.HexUtils;
import com.el.qr.login.utls.WebToolUtils;

public class SimpleTest
{
    public static void main(String[] args) {
        String data = "192.168.56.1_1CFE964E1E8C98946AA88860095ADEE5_1528357110221_EL";
        String mi = HexUtils.str2HexStr(data);
        String yuan = HexUtils.hexStr2Str(mi);
        System.out.println(mi);
        System.out.println(yuan);
    }
}
