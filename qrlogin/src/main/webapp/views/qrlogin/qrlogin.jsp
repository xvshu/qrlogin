
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">

<!DOCTYPE html>

<html lang="en">
<head>
    <title>翼龙贷扫码登录</title>

    <script type="text/javascript" src="../../common/js/jquery-3.3.1.min.js" ></script>
    <script type="text/javascript" src="../../common/js/utf.js" ></script>
    <script type="text/javascript" src="../../common/js/jquery.qrcode.js" ></script>


    <script type="text/javascript" >

        var int=self.setInterval("check_qrlogin()",2000);

        $(function() {
            var code_mark='${code_mark}';
            $("#code_mark").val(code_mark);
            create_qrcode();
            img_logo();
            setTimeout(function(){alert("二维码失效,请刷新页面重新扫码！");clean_qrcode()},30000);

        });

        function check_qrlogin(){
            var code_mark='${code_mark}';
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

        function clean_qrcode(){
            $("#code").html("<h1 style='color:red'>二维码失效,请刷新页面重新扫码！</h1>");
            int=window.clearInterval(int);
        }

    </script>


</head>

<body style="margin: 0">

    <div style="width: 100%;height: 30%;background-color:red;text-align: center">
        <div style="width: 100%;height: 25%;background-color:red;text-align: center"></div>
        <h1 style="font-size: 35px;color: white;margin:auto;">翼龙贷扫码授权平台</h1>
        <p></p>
        <div style="color:white;text-align: center;">二维码30秒后失效，请尽快扫码登录</div>
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
    </div>



</body>

</html>


