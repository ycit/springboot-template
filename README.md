# Spring Boot Basic项目

## 介绍
该项目是基于spring boot框架开发，其中主要包含了权限控制、应用配置管理及菜单管理功能。
      
## 项目使用技术
1.加入了swagger api管理，swagger会管理所有包含@ApiOperation注解的控制器方法，同时，可利用@ApiImplicitParams注解标记接口中的参数
  
  ```
  @ApiOperation("查询所有重点人员统计数据")
  @ApiImplicitParams({@ApiImplicitParam(name = "data", value = "数据", required = true, paramType = "query", dataType = "String")})
  ```
  具体用法参考：[swagger官网](https://swagger.io/)
  

2.shiro权限控制，Apache Shiro是一个强大且易用的Java安全框架,执行身份验证、授权、密码学和会话管理，其三大核心组件：Subject, SecurityManager 和 Realms极大的方便了开发。  

3.Mybatis-Plus（简称MP）是一个 Mybatis 的增强工具，在 Mybatis 的基础上只做增强不做改变，为简化开发、提高效率。具体使用方法参考官网：[Mybatis-Plus官网](http://mp.baomidou.com)

4.Lombok插件可以简化java bean的代码，通过注解可以将get、set等方法省略不写，代码编译的ide需要下载对应的插件，不然ide工具代码编译报错。

## 项目部署
我们可以通过Gradle工具将项目打包，通过配置可以将项目打包为war包或者jar包，只需要在build.gradle文件中配置
```
apply plugin: 'war'
```
默认打包结果是一个jar包，当加入war插件配置后就会打包成war包，war包可以在外部的Tomcat中部署，jar包可以直接使用java命令执行（spring boot中嵌入了tomcat）。
