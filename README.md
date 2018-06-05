# 二维码登录
随着智能机的普及，在手持设备端做用户验证是一种较为高效，安全的手段，而在pc端利用已经鉴权过的手持设备扫码登录不仅方便，而且安全。

## redis数据说明

redis节点存储数据格式为hash结构：

  key：qrlogin_年月日
  field：qrlogin_唯一标识
  value：用户id
  失效时间：2天

## 概要流程

<img  src="https://github.com/xvshu/qrlogin/blob/master/doc/img/%E8%AE%BE%E8%AE%A11.png">

# 主要页面
## 生成二维码
PC：

<img width="400px" src="https://github.com/xvshu/qrlogin/blob/master/doc/img/main-1.jpg">

APP：

<img width="400px" src="https://github.com/xvshu/qrlogin/blob/master/doc/img/ap-main.jpg">

## 模拟扫码登录
APP：

<img width="400px" src="https://github.com/xvshu/qrlogin/blob/master/doc/img/aplogin.jpg">

## 登录成功
PC：

<img width="400px" src="https://github.com/xvshu/qrlogin/blob/master/doc/img/success1.jpg">

APP：

<img width="400px" src="https://github.com/xvshu/qrlogin/blob/master/doc/img/ap-success.jpg">

## 说明
二维码失效时间：30s
二维码登录页面与后台校验数据间隔时间3s
失效页面：
PC：

<img width="400px" src="https://github.com/xvshu/qrlogin/blob/master/doc/img/ap-loss.jpg">

# 总结
demo只完成了一个简单的二维码登录，此处有很多使用的还是测试数据，在生产环境对接生产数据，并做网关等安全处理之后就 可以使用其完成二维码登录了，因为全程使用的redis，性能上一般会非常不错。
后期要考虑ddos攻击等安全问题，一般从两个方面入手：

1. 生成二维码网关
在页面上对生成二维码有严格性能要求，同一ip同一时间只允许生成3次每秒，超过此限制，返回过于频繁，自动失败。

2. 授权登录（APP）网关
在页面上对授权有严格性能要求，同一ip同一时间只允许登录1次每分钟，超过此限制，返回过于频繁，自动失败。

多有不周，请大家指正。

相关博客地址

-[《二维码登录（一）概要设计》](https://blog.csdn.net/xvshu/article/details/80571897)

-[《二维码登录（二）生成二维码》](https://blog.csdn.net/xvshu/article/details/80572041)

-[《二维码登录（三）扫码登录》](https://blog.csdn.net/xvshu/article/details/80572372)





    
