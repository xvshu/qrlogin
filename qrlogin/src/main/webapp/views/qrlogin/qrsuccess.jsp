
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
<!DOCTYPE html>

<html lang="en">
<head>
    <title>翼龙贷APP授权</title>
    <script type="text/javascript" src="../../common/js/jquery-3.3.1.min.js" ></script>

    <script type="text/javascript" >
        var code_mark='${code_mark}';
        var userid="xvshu_test_001";

        function qr_login(){
            $.get("/qr/login/success?code_mark="+code_mark+"&userID="+userid,function(data,status){
                if(status=="success"&&data=="success"){
                    window.location.href="/qr/login/main?userId=xvshu_test_001";
                }else{
                    alert("授权失败，请重试！");
                }
            });
        }
    </script>
    <style>

        .dow{display:block;width:302px;height:82px; line-height:52px; text-align:center; font-weight:bold; font-size:35px; background:cornflowerblue;color:white; text-decoration:none; border: 0px solid cornflowerblue; cursor:pointer}
        .dow:hover{background:cornflowerblue;}
        .dow:active{background:cornflowerblue;}

    </style>

</head>

<body style="margin: 0">


    <div style="width: 100%;height: 30%;background-color:red;text-align: center">
        <div style="width: 100%;height: 25%;background-color:red;text-align: center"></div>
        <h1 style="font-size: 35px;color: white;margin:auto;">翼龙贷模拟APP授权</h1>
        <p></p>
        <div style="color:white;text-align: center;">测试用户:xvshu_test_001</div>
    </div>

    <div style="width:100%;height:78%;text-align: center;background-color:white;text-align: center">
        <div style="width: 100%;height: 25%;background-color:white;text-align: center"></div>
        <p></p>
        <button  style="margin:auto;"  class="dow" onclick="qr_login()">登&nbsp;&nbsp;&nbsp;&nbsp;录</button>
    </div>


</body>

</html>


