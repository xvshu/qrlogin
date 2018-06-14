
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">

<!DOCTYPE html>

<html lang="en">
<head>
    <title>扫码登录平台</title>

    <script type="text/javascript" src="../../common/js/jquery-3.3.1.min.js" ></script>


    <script type="text/javascript" >
        function decrypt(){
            var code_mark_en_val = $("#code_mark_en").val();
            $.get("/qr/login/decrypt?code="+code_mark_en_val,function(data,status){
                $("#code_mark_en_de").html(data);
            });

        }

        function encrypt(){
            var code_mark_de_val = $("#code_mark_de").val();
            $.get("/qr/login/encrypt?code="+code_mark_de_val,function(data,status){
                $("#code_mark_de_en").html(data);
            });
        }
    </script>


</head>

<body style="margin: 0">

    <div style="width: 100%;height: 30%;background-color:red;text-align: center">
        <div style="width: 100%;height: 25%;background-color:red;text-align: center"></div>
        <h1 style="font-size: 35px;color: white;margin:auto;">扫码授权平台</h1>
        <p></p>
        <div style="color:white;text-align: center;">加密解密工具</div>
    </div>

    <div style="width:100%;height:78%;text-align: center;background-color:white;text-align: center">
        <div style="width: 100%;height: 5%;background-color:white;text-align: center"></div>
        <div style="width:100%;height:300px;text-align: center">
            <div style="text-align: center;"  >
                密文：<textarea  type="text" style="width:200px;height: 80px;" id="code_mark_en" /></textarea>
                &nbsp;&nbsp;&nbsp;&nbsp;
                <button style="width:200px;height: 30px"  onclick="decrypt()">解&nbsp;&nbsp;&nbsp;&nbsp;密</button>
                <p></p>
                <textarea id="code_mark_en_de" style="height: 60px;width: 500px;"></textarea>
            </div>
            <p></p>
            <div style="text-align: center;" >
                原文：<textarea  type="text" style="width:200px;height: 80px" id="code_mark_de" ></textarea>
                &nbsp;&nbsp;&nbsp;&nbsp;
                <button   style="width:200px;height: 30px"  onclick="encrypt()">加&nbsp;&nbsp;&nbsp;&nbsp;密</button>
                <p></p>
                <textarea id="code_mark_de_en" style="height: 60px;width: 500px;"></textarea>
            </div>
        </div>

    </div>



</body>

</html>


