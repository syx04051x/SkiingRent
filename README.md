#AlphaZ


# 前言
本工程使用springboot作为基础开发框架，使用gradle打包编译项目。项目支持多语言开发，推荐使用java与kotlin进行编码。

# 项目说明

## app
本项目下开发项目网页的应用层代码，例如controller，websocket等在此编码。网页的资源文件放在“resources/”中。项目的具体配置文件也存在于“resources/”中，具体参考注意点。

## api
本项目下开发应用层接口代码，提供移动端或其他系统调用，http接口统一使用restful规范。网页的资源文件放在“resources/”中。项目的具体配置文件也存在于“resources/”中，具体参考注意点。

## core
本项目下开发项目主要业务代码，包含主要的dao，service，POJO类。在POJO下，包含DO（数据库映射类），DTO（业务传输类），VO（视图类）。该项目下用到的资源文件（例如接口地址配置），请统一放在resources里，如文件较多时，请按文件夹分类。

## task
本项目下开发后台定时任务，批量处理等业务。使用spring task以及spring batch框架。资源文件放在“resources/”中。项目的具体配置文件也存在于“resources/”中，具体参考注意点。

## util
本项目下是一些基于java实现的工具类，部分实现在其他语言中会是鸡肋，请谨慎使用。

## plugins

### oatuh2
本项目下开发项目所需的基础服务，例如权限，认证，单点登录等基础设施。目前已完成权限与oauth2的认证服务。

### flow
业务流程化

## extra
拓展功能项目放置在此

### customentity
自定义实体


# 注意点
* 项目统一使用驼峰式命名。
* 应用层与业务层暴露的方法参数统一使用包装类，数据层方法推荐使用包装类。
* 项目配置统一在“application-*.properties”修改。其中\*表示具体运行环境，dev表示开发环境，test表示测试环境，prod表示生产环境。该配置应当在项目开发伊始完成。具体根据“application.properties”中配置使用哪个环境配置。打包各环境代码，请参考打包。
* 在项目中创建的包或者文件夹，请以该文件夹内具体处理问题命名，并将处理类型问题的类放在一起，例如统一处理exception的包内存放一些异常处理的类。
* 页面开发采用thymeleaf模板引擎，页面统一放在“resources/templates”，静态文件放在“resources/static”。
* 页面文件请按照页面结构分类存放，页例如页面通用head，foot等统一放在一个文件夹中，页面真正的内容另外存放。
* 静态文件，如js或者css也按照文件类型存放至文件夹中，例如，js则放在js相关的文件夹中，并且按照js的工程细分文件夹。
* service层的返回统一的数据结构，除非是暴露给service内调用，否则一律使用统一数据结构返回。
* 数据层采用JPA以及hibernate开发。简单的sql查询，例如单表CRUD可使用spring jpa提供的工具完成，复杂的查询也可通过spring jpa完成，也可通过hibernate完成。<font color="red">手动编写sql，请注意sql注入问题</font>
* 不使用spring jpa实现dao层方法，需要继承BaseDAO类，其中提供部分实现。
* 数据库映射类，统一继承BaseDTO，对象类型统一使用包装类。
* 在开发API的时候，API在系统中是无状态的，API不直接记录用户状态（例如通过非持久化session判断登录与否）。当前台需要得到当前用户状态，请通过持久化session实现。
* 视图解析统一放在IndexController中，IndexController类名上不应该注解路径。
* 其他controller的url根路径为此controller的类名除去controller，例如UserController的根路径为user，url统一小写
* 项目中一般状态量使用int表示，一般情况默认0为积极的状态，1为消极的状态，例如0表示通过，1表示未通过
* 由于使用querydsl，在使用IDEA时，请先运行gradle build生成querydsl的metadata。

# 数据库处理
* 一般建表统一增加默认字段，即默认继承BaseDTO
```
  `createby` bigint(11) DEFAULT NULL COMMENT '数据的创建人',
  `updateby` bigint(11) DEFAULT NULL COMMENT '数据的修改人',
  `createtime` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '数据更新时间',
  `updatetime` datetime ON UPDATE CURRENT_TIMESTAMP COMMENT '数据创建时间',
  `state` int(1) NOT NULL DEFAULT 0 COMMENT '数据状态，0为启用，1为禁用',
```
* 关系表可不用state字段

# TODO
* querydsl支持

# 发布

## 打包jar 
使用”gradle build“命令进行项目打包
之后在有“application.properties”配置文件的api，app，task项目的“build/libs”生成相应的jar执行包

## war
在对应需要生成war包的项目build.gradle文件中添加“apply plugin: 'war'”代码，则在打包时会同时生成war包

## 运行
将jar部署到服务器，通过命令行“java -jar  ***.jar --spring.profiles.active=? --server.port=9090”，问号用（dev,test,prod）替代，指向具体使用哪个环境运行。（war部署直接发布在tomcat或其他应用服务器中）

# 项目依赖

app->core,plugin/oauth

api->core,plugin/oauth

task->core

plugin/oauth->core

core->util
