
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">

<!DOCTYPE html>

<html lang="en">
<head>
    <title>扫码登录平台</title>

    <script type="text/javascript" src="../../common/js/jquery-3.3.1.min.js" ></script>
    <script type="text/javascript" src="../../common/js/utf.js" ></script>
    <script type="text/javascript" src="../../common/js/jquery.qrcode.js" ></script>


    <script type="text/javascript" >

        var int=self.setInterval("check_qrlogin()",1000);
        var lost_time=self.setInterval("lost_time()",1000);
        $(function() {
            var code_mark='${code_mark}';
            $("#code_mark").val(code_mark);
            create_qrcode();
            img_logo();
            setTimeout(function(){clean_qrcode()},180000);

        });

        function check_qrlogin(){
            var code_mark=$("#code_mark").val();
            $.get("/qr/login/check?code_mark="+code_mark,function(data,status){
                if(status=="success"&&data!='nouser'){
                    int=window.clearInterval(int);
                    window.location.href="/qr/login/main?userId="+data;
                }
            });
        }

        function create_qrcode(){
            var code_mark=$("#code_mark").val();
            if(code_mark==null || code_mark==""){
                alert("随机码不能为空！");
                return;
            }
            var baseUrl= "http://172.30.53.250:8080/qr/login/success_check?code_mark=";
            $("#code").text("");
            $('#code').qrcode({
                render : "canvas",//也可以替换为table
                width : 300,
                height : 300,
                text : baseUrl+ code_mark ,  //二维码内置内容，如果时URL形式一般浏览器会自动加载
                background : "#ffffff",       //二维码的后景色
                foreground : "#000000" ,       //二维码的前景色
                id:"code_qrlogin"

            });

        }

        function img_logo(){
            var c= $("canvas")[0];
            var cxt=c.getContext("2d");
            var img=new Image();
            img.src="../../common/img/el_logo.png";
            img.onload = function() {
                cxt.drawImage(img,110,110,80,80);
            }
        }

        function qr_refresh(){

            $.get("/qr/login/refreshQRKey",function(data,status){
                if(status=="success"&&data!=''){
                    var code_mark=data;
                    $("#code_mark").val(code_mark);
                    create_qrcode();
                    img_logo();
                    setTimeout(function(){clean_qrcode()},180000);
                    int=self.setInterval("check_qrlogin()",1000);
                }
            });
        }

        function clean_qrcode(){
            $("#code").html("<h1 style='color:red'>二维码已失效</h1><pr></pr><pr></pr><pr></pr><pr></pr>" +
                "<button  style=\"margin:auto;\"  class=\"dow\" onclick=\"qr_refresh()\">刷&nbsp;新&nbsp;二&nbsp;维&nbsp;码</button>");
            int=window.clearInterval(int);
        }

        var lostCount=180;
        function lost_time(){
            if(lostCount>=0){
                $()
            }
        }
    </script>

    <style>
        .dow{display:block;width:302px;height:62px; line-height:42px; text-align:center; font-weight:bold; font-size:32px; background:cornflowerblue;color:white; text-decoration:none; border: 0px solid cornflowerblue; cursor:pointer}
        .dow:hover{background:cornflowerblue;}
        .dow:active{background:cornflowerblue;}

    </style>


</head>

<body style="margin: 0">

    <div style="width: 100%;height: 30%;background-color:red;text-align: center">
        <div style="width: 100%;height: 25%;background-color:red;text-align: center"></div>
        <h1 style="font-size: 35px;color: white;margin:auto;">扫码授权平台</h1>
        <p></p>
        <div style="color:white;text-align: center;">二维码180秒后失效，请尽快扫码登录</div>
    </div>

    <div style="width:100%;height:78%;text-align: center;background-color:white;text-align: center">
        <div style="width: 100%;height: 5%;background-color:white;text-align: center"></div>
        <div style="width:100%;height:300px;text-align: center">
            <div style="text-align: center;"  id="code">
            </div>
        </div>
        <p></p><p></p><p></p>
        <div style="width:100%;text-align: center">
            标识码：<input type="text" style="width:200px" id="code_mark" />
        </div>
        <p></p><p></p><p></p>
        <div style="width:100%;text-align: center">
            进行时间：<div id="lost_time"></div>
        </div>
    </div>



</body>

</html>


