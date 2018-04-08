# SpringBoot接口API系统实战

集成了：
* 拦截器
* 日志处理
* mysql
* mybatis
* oauth2.0,
* spring secutity等功能

已投入生产线上使用


# 接口服务系统，包括以下内容：


* 通用组件服务 yfax-common
* 客户端api系统 yfax-htt-api
* 任务系统（集成微信企业支付、支付宝企业支付） yfax-task
* 主爬虫系统 yfax-spider
* 爬虫监控系统 yfax-spider-monitor
* 工具类 yfax-utils

### yfax-common 

通用服务，比如发短信，推送等

### yfax-htt-api

客户端api系统，提供了通用功能，如注册、登入登出、微信登录集成、用户信息等功能

涉及技术：springboot，spring security，oauth2，高并发之乐观锁，接口加密校验，防刷机制等

注：首次下载运行前，需进入yfax-htt-api/libs目录下，安装一下两个jar的依赖包，用法详见其pom.xml文件

同时，项目需要配置tomcat运行（如没有，先下载tomcat，建议版本为tomcat9）


### yfax-task
任务系统，集成了微信企业支付、支付宝企业支付功能等

注：首次下载运行前，需进入yfax-task/libs目录下，安装一下jar的依赖包，
用法详见其pom.xml文件（已安装的同名jar，则可忽略）


### yfax-spider
基于webmagic框架二次开发，爬取今日头条新闻数据，并写入es存储


### yfax-spider-monitor
监控爬虫数据情况，发短信、短信报警


### yfax-utils
提供常用工具类，如AES对称加密算法类，RSA非对称加密算法类，唯一主键生成类，订单号生成类，8位邀请码生成类，图形验证码生成类，md5加密类，网络http请求工具类等


## [关于我](http://heminit.com/about/)


欢迎交流问题，可加QQ469580884，一起探讨交流问题


[我的博客地址](http://blog.csdn.net/hemin1003)


[个人域名](http://heminit.com)
