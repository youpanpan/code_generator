# 代码生成平台
## 项目组成结构
+ screenshots：截图
+ union-base-generator：代码生成平台主程序
+ union-commons-util：基础工具类
## 项目技术
+ 后端技术
   + SpringBoot	
   + Thymeleaf
   + FreeMarker
   + Mybatis
   + Mysql
+ 前端技术
   + Ace Admin页面框架
   + Jquery
   + Bootstrap
   + layui
## 项目运行
1.准备数据库,根据sql文件（/union-base-generator/文档/数据库sql/code_generate.sql）建立数据库   

2.编译打包项目union-commons-util  
`mvn clean install`  

3.修改连接的数据库配置  
/union-base-generator/src/main/resources/application-dev.properties  
/union-base-generator/src/main/resources/application-prodproperties  

4.编译项目union-base-generator  
`mvn clean package`  

5.运行打好的jar,进入union-base-generator/target目录下执行  
`java -jar union-base-generator-2.0.6.RELEASE.jar --spring-profiles.active=dev`  

6.访问[http://localhost:84](http://localhost:84)  

## 使用讲解
1.登录  
![登录界面](https://github.com/youpanpan/code_generator/blob/master/screenshots/%E7%99%BB%E5%BD%95%E7%95%8C%E9%9D%A2.png?raw=true)  
这里使用管理员登录  
2.登录后主界面  
![管理员主界面](https://github.com/youpanpan/code_generator/blob/master/screenshots/%E7%AE%A1%E7%90%86%E5%91%98%E7%9C%8B%E5%88%B0%E7%9A%84%E7%95%8C%E9%9D%A2.png?raw=true)  
这里可以看到系统具有的功能如下：  
+ 统计信息
这里统计系统的一些数量信息，包括模版引擎数量、上下文数量、项目数量、用户数量、模版数量、代码生成数量等  
+ 代码生成  
	+ 代码生成  
	根据模版生成代码  	
	+ 代码生成记录  
	代码生成的记录，可以用来下载生成的代码  
+ 模版管理  
上传自定义的模版，可以是单模版，也可以是组合模版（多个模版的组合）  
+ 模版引擎管理  
管理模版支持的引擎，目前支持两种：Thymeleaf、FreeMarker  
+ 上下文管理  
模版文件的内容提供者，用来产生模版中使用的一些变量，通过前端传入的参数来提供相应的数据  
+ 项目管理  
将用户进行整合到一个项目中，一个项目的模版可以进行公开，这样项目中所有的成员都可以看到该模版  
+ 权限管理  
管理用户、角色、URL，并设置角色所能访问的URL来管理权限  
3.管理模版引擎    
![管理模版引擎](https://github.com/youpanpan/code_generator/blob/master/screenshots/%E7%AE%A1%E7%90%86%E6%A8%A1%E7%89%88%E5%BC%95%E6%93%8E.png?raw=true)  
4.管理上下文  
这里分为3个功能  
（1）上下文实现配置  
管理上下文实现类：参数类是什么，实现类是什么  
![上下文实现配置](https://github.com/youpanpan/code_generator/blob/master/screenshots/%E4%B8%8A%E4%B8%8B%E6%96%87%E5%AE%9E%E7%8E%B0%E9%85%8D%E7%BD%AE.png?raw=true)  
（2）上下文参数配置  
配置上下文参数，用于传递参数到后台，与参数类字段对应  
![上下文参数配置](https://github.com/youpanpan/code_generator/blob/master/screenshots/%E4%B8%8A%E4%B8%8B%E6%96%87%E5%8F%82%E6%95%B0%E9%85%8D%E7%BD%AE.png?raw=true)  
（3）上下文参数实例管理  
为了将参数配置实例化，避免生成时多次输入参数值，从实例中选择即可  
![上下文参数实例管理](https://github.com/youpanpan/code_generator/blob/master/screenshots/%E4%B8%8A%E4%B8%8B%E6%96%87%E5%8F%82%E6%95%B0%E5%AE%9E%E4%BE%8B%E7%AE%A1%E7%90%86.png?raw=true)  
5.管理模版   
![模版管理](https://github.com/youpanpan/code_generator/blob/master/screenshots/%E6%A8%A1%E7%89%88%E7%AE%A1%E7%90%86.png?raw=true)  
![新增模版](https://github.com/youpanpan/code_generator/blob/master/screenshots/%E6%96%B0%E5%A2%9E%E6%A8%A1%E7%89%88.png?raw=true)  
新增模版时可以选择模版的类型，如果是单模版则需要上传模版文件，如果是组合模版则选择单模版即可；  
单模版需要选择使用的模版引擎是什么，同时需要指定依赖的上下文；  
模版的开发类型分为私有、项目组，如果选择项目组则同一项目组的所有用户都可以看到该模版并使用该模版。  

6.代码生成
![代码生成](https://github.com/youpanpan/code_generator/blob/master/screenshots/%E4%BB%A3%E7%A0%81%E7%94%9F%E6%88%90.png?raw=true)
选择要生成代码的模版，系统自动带出所依赖的上下文及上下文参数，输入上下文需要的参数值，点击保存即可生成代码
![代码生成记录](https://github.com/youpanpan/code_generator/blob/master/screenshots/%E4%BB%A3%E7%A0%81%E7%94%9F%E6%88%90%E8%AE%B0%E5%BD%95.png?raw=true)
代码生成记录可以看到已生成的代码，并通过代码文件进行下载
7.项目管理
![项目管理](https://github.com/youpanpan/code_generator/blob/master/screenshots/%E9%A1%B9%E7%9B%AE%E7%AE%A1%E7%90%86.png?raw=true)
8.权限管理
- 用户管理
管理系统用户，用户不允许注册，只能通过管理员添加
- 角色管理
系统已有的角色：项目管理员、开发人员、系统管理员
![角色管理](https://github.com/youpanpan/code_generator/blob/master/screenshots/%E8%A7%92%E8%89%B2%E7%AE%A1%E7%90%86.png?raw=true)
- URL管理
URL包括系统菜单URL、功能URL
![URL管理](https://github.com/youpanpan/code_generator/blob/master/screenshots/URL%E7%AE%A1%E7%90%86.png?raw=true)


## 二次开发  
### 模版引擎开发  
目前系统中有两个模版引擎  
FreeMarker：com.chengxuunion.generator.component.engine.impl.FreeMarkerEngineClient  
Thymeleaf：com.chengxuunion.generator.component.engine.impl.ThymeleafEngineClient  
通过实现com.chengxuunion.generator.component.engine.EngineClient接口  
或  
继承com.chengxuunion.generator.component.engine.impl.AbstractEngineClient抽象类  
来实现模版引擎  

AbstractEngineClient将对模版的非核心处理做了封装，具体的模版处理由实现类去做  
抽象出两个方法：  
- getTemplateProcess获取模版引擎处理对象   
- process处理模版引擎文件  
### 上下文开发  
目前系统实现了两个上下文，一个基础上下文，一个数据库表描述上下文  
用户可以通过继承com.chengxuunion.generator.component.context.AbstractDataContext来实现自己的上下文  
可以参考：  
com.chengxuunion.generator.component.context.basic.service.BasicDataContext  
com.chengxuunion.generator.component.context.database.service.DataBaseDataContext  
 
### 上下文参数  
上下文参数是上下文需要接收的对象，是一个Java Bean，非常简单，定义一个字段及字段的get/set方法  
