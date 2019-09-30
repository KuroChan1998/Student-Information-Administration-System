## Brief Introduction
这个项目是一个大学生信息管理系统,提供用户级别的登录注册资料管理,信息查询,信息修改（管理员权利），简单的数据可视化分析等功能，也有基本的安全性保障。

该项目为master项目的分支项目，系统的后端是由`SpringBoot`+`Mybatis`实现，功能上与主支SSM项目相同。

* SpringMVC+Spring+Mybatis主支：[https://github.com/KuroChan1998/Student-Information-Administration-System/tree/master](https://github.com/KuroChan1998/Student-Information-Administration-System/tree/master)

## Release Notes

### v1.0.0 - 2019.9.30

*该版本功能上与主支SSM项目的[v1.2.0](https://github.com/KuroChan1998/Student-Information-Administration-System/tree/v1.2.0)版本对应。*

*版本1.0.0，更新如下内容 :*

* 这里前端我没有进行修改，还是使用jsp，接口与SSM项目相同，所以前端资源在webapp目录下；需要注意的是：springboot官方强调不推荐使用jsp，可以使用*freemaker*、*thymeleaf*代替，这些模板文件应该放在resources的static和templates目录下。
* layui和springboot整合时会出现layui一些图标无法显示问题，这里通过在pom.xml文件修改maven编译时对layui资源的拦截解决。
* springmvc拦截器的定义有原来的spring-mvc.xml中改为在config包下的配置类*SpringmvcConfig*完成；redis的配置等也同样改成springboot风格即配置类完成。
* 在原先的*com.jzy.controller.OtherController*中`"/error"`请求与springboot内部定义的出错请求相同，产生冲突，因而改成`"/myError"`



## Technologies Used
### 前端
* 前端框架 : `layui`
* 数据可视化框架 : `echarts`

### 后端
* MVC&IOC框架 : `SpringBoot`
* ORM框架 : `Mybatis`
* 缓存技术：`Redis`
* 数据库：`Mysql`
* 日志框架 : `Log4j`

## Project Structure
```
├─database                          // 数据库相关文件
│  ├─design				                  // 数据库设计
│  │  └─1
│  └─sql                            // 数据库初始化脚本文件
├─git_screenshot                    // 存放README.md 中的图片
├─src                               // 项目源代码目录
│  ├─main                           //源代码目录
│  │  ├─java
│  │  │  └─com
│  │  │      └─jzy          // java代码目录
│  │  │          ├─config			//springboot配置类
│  │  │          ├─controller       // 控制层
│  │  │          ├─dao              // 持久层
│  │  │          ├─dto              // 传输对象
│  │  │          ├─entity           // 实体类
│  │  │          ├─interceptor      // 拦截器
│  │  │          ├─log              // 日志管理
│  │  │          ├─service          // 服务层
│  │  │          │  └─impl          // 服务层接口实现
│  │  │          └─util             // 工具方法
│  │  ├─resources                   // 资源文件目录
│  │  │  └─com
│  │  │  │   └─jzy
│  │  │  │       └─mapper           // mybatis对dao接口的xml实现
│  │  │  ├─static                   // springboot默认静态资源目录
│  │  │  └─templates				// springboot默认页面模板目录
│  │  └─webapp                      // tomcat前端文件目录
│  │      ├─static                  // 静态资源
│  │      │  ├─custom               // 自定义静态资源
│  │      │  └─plugins              // 插件类静态资源
│  │      └─WEB-INF
│  │          └─page                // jsp页面目录 
│  └─test                           // 测试代码目录
├─README.md                         // help
└─pom.xml                           // maven依赖
```


## Quick Start
### 1 : 项目开发环境
- IDE : `IntelliJ IDEA 2018.1.7`
- 项目构建工具 : `Maven 3.x`
- 数据库 : `Mysql 8.0.13`
- Redis：`Redis server 3.2.100`
- JDK版本 : `jdk 1.8`
- Tomcat版本 : `Tomcat 8.x`


### 2 : 项目的初始构建
1. *在你的Mysql中，运行我提供的database/sql/init.sql 文件（注意mysql版本与sql脚本中部分代码的兼容性）, 成功会创建名为mydatabase2的数据库，以及user、student、teacher、class、major、college、title、grade八个表*

*数据库物理模型如下 :*

![Snipaste_2019-07-17_09-48-36](git_screenshot/Snipaste_2019-07-17_09-48-36.jpg)

2. *进入src/main/resources修改application.properties配置文件*

   * 修改mysql连接信息

     ```properties
     #JDBC
     #你的mysql连接url，localhost(本机)，端口：3306（默认），数据库：mydatabase2（上一步完成创建）
     spring.datasource.url=jdbc:mysql://localhost:3306/mydatabase2?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8&useSSL=false
     #你的mysql用户名
     spring.datasource.username=root
     #你的mysql密码
     spring.datasource.password=123
     ```

   * 修改redis连接信息

     ```properties
     #redis
     #你的redis主机地址
     spring.redis.host = localhost
     #你的redis端口
     spring.redis.port = 6379
     #你的redis密码
     spring.redis.password = 123
     ```

   * 如果有必要可以修改当前项目部署端口和部署路径（可选）

     ```properties
     #端口
     server.port= 80
     #项目部署路径
     server.context-path= /
     ```

3. *进入src/main/resources查看log4j.properties，如果有必要可以修改日志输出路径，目前在D盘下，你可选择不修改跳过此步*

4. *使用 IntelliJ IDEA 导入项目，选择Maven项目选项，一路点击next，即可将项目所需依赖导入。若有无法引入的依赖，可能是因为maven版本不同或是该依赖已过时不存在于现有maven仓库中，请前往maven官网映入最新的该类型依赖*

![Snipaste_2019-07-17_08-47-37](git_screenshot/Snipaste_2019-07-17_08-47-37.jpg)

![Snipaste_2019-07-17_08-49-48](git_screenshot/Snipaste_2019-07-17_08-49-48.jpg)

5. *运行com.jzy.App，进入用户登录页面*


![Snipaste_2019-05-04_08-02-50](git_screenshot/Snipaste_2019-05-04_08-02-50.jpg)


7. *登录账户/密码(你也可以自行注册一个账户登录哟)*
  - 管理员账户：000000000000/admin1
  - 学生账户：516030910429/123456
  - 教师账户：1000000001/123456



## Detailed Functions
### 用户服务
* 登录：如上文图所示

* 注册

  ![Snipaste_2019-05-04_08-11-21](git_screenshot/Snipaste_2019-05-04_08-11-21.jpg)

* 忘记密码后的重置密码（含发送邮箱验证码）

  ![Snipaste_2019-05-04_08-12-27](git_screenshot/Snipaste_2019-05-04_08-12-27.jpg)

* 登录进入主页

  ![Snipaste_2019-09-12_10-35-03](git_screenshot/Snipaste_2019-09-12_10-35-03.jpg)

* 修改基本资料

  ![Snipaste_2019-07-04_08-19-12](git_screenshot/Snipaste_2019-07-04_08-19-12.jpg)

* 修改密码

  ![Snipaste_2019-07-17_09-23-03](git_screenshot/Snipaste_2019-07-17_09-23-03.jpg)

* 修改绑定邮箱

  ![Snipaste_2019-07-17_09-30-38](git_screenshot/Snipaste_2019-07-17_09-30-38.jpg)


### 信息查询
* 学生信息查询

  * 查询所有信息

    ![Snipaste_2019-07-17_09-31-05](git_screenshot/Snipaste_2019-07-17_09-31-05.jpg)

  * 根据登录用户的用户名（应以学号注册）查询当前个人的学籍信息，若注册时未以真实学号注册，则无法查询到。

    ![Snipaste_2019-07-17_09-32-12](git_screenshot/Snipaste_2019-07-17_09-32-12.jpg)

  * 模糊查询搜索

    ![Snipaste_2019-07-17_09-32-47](git_screenshot/Snipaste_2019-07-17_09-32-47.jpg)

* 教师信息查询：类似学生信息查询，图略

* 班级信息查询：类似学生信息查询，图略

* 专业&学院信息查询：类似学生信息查询，图略


### 信息修改
* 学生信息修改

  * 此功能必须以管理员用户身份登录，否则会跳转至异常页面

    ![Snipaste_2019-05-04_08-32-13](git_screenshot/Snipaste_2019-05-04_08-32-13.jpg)

  * 编辑信息

    ![Snipaste_2019-07-17_09-33-51](git_screenshot/Snipaste_2019-07-17_09-33-51.jpg)

  * 添加

    ![Snipaste_2019-07-17_09-34-24](git_screenshot/Snipaste_2019-07-17_09-34-24.jpg)

  * 单条、多条删除

    ![Snipaste_2019-07-17_09-34-56](git_screenshot/Snipaste_2019-07-17_09-34-56.jpg)

* 教师信息修改：类似学生信息修改，图略

* 班级信息修改：类似学生信息修改，图略

* 专业&学院信息修改：类似学生信息修改，图略


### 拓展功能
* 学生男女比可视化

![Snipaste_2019-07-17_09-36-37](git_screenshot/Snipaste_2019-07-17_09-36-37.jpg)

* 学生人数比可视化

![Snipaste_2019-07-17_09-37-17](git_screenshot/Snipaste_2019-07-17_09-37-17.jpg)

![Snipaste_2019-07-26_17-10-43](git_screenshot/Snipaste_2019-07-26_17-10-43.jpg)

* 师资力量可视化

  ![Snipaste_2019-07-26_17-12-36](git_screenshot/Snipaste_2019-07-26_17-12-36.jpg)



## Contact me

- qq: 929703621
- wechat: Jzy_bb_1998
- e-mail: 929703621@qq.com
- soul: despacito

欢迎提出意见与建议~