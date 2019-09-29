## Brief Introduction
这个项目是一个大学生信息管理系统,提供用户级别的登录注册资料管理,信息查询,信息修改（管理员权利），简单的数据可视化分析等功能，也有基本的安全性保障



## Release Notes

### v1.0.0 - 2019.5.19

*StuInfoAdmin-v1.0.0 的一切准备工作似乎都已到位。发布之弦，一触即发。*
*不枉近百个日日夜夜与之为伴。因小而大，因弱而强。*

*无论它能走多远，抑或如何支撑？至少我曾倾注全心，无怨无悔*

Download: https://pan.baidu.com/s/1piVQnIFdz_BIoszIEzAJwQ

### v1.1.0 - 2019.7.27

*版本1.1.0，更新如下内容 :*

* 优化数据表结构，对原有的表的部分字段进行了修改，并增加了title和grade两个表
* 优化sql语句效率
* 优化前端查询界面及查询方式，使其更加全面，对用户友好
* 更新登录界面记住密码的cookie设置
* 更新邮箱验证码服务，增加了验证码有效时间
* 优化源代码结构，增强了规范性和可拓展性

Download: https://pan.baidu.com/s/1yHjrk7gAycHRFapU_Waf4g

### v1.2.0 - 2019.9.12 (Current Version)

*目前版本为v1.2.0，更新如下内容 :*

* 修复了前端编辑添加弹窗在不同分辨率客户机上的显示大小问题
* 新增Redis技术，用以缓存用户名密码，用户错误登录次数限制，邮箱验证码等等
* 新增连续输错用户名密码超过一定次数后的限制时间
* 更改了邮箱验证码有效时间的实现方式，由服务端java实现改为redis过期时间实现
* 提升了服务端的安全性和新增异常处理机制，用aop实现入参的校验，对不合法的请求及其参数值用日志记录，并抛出异常
* 优化了util包等源代码的结构，增强了可拓展性

对`Redis`和`SpringAOP`不太熟的同学，下载`v1.1.0`版本足够学习或完成课设了~

### v2.0.0  (//TODO)
*使用`SpringBoot`重构`SSM`,`Thymeleaf`重构`JSP`,提高项目的实用性及扩展性,尽情期待哟~*



## Technologies Used
### 前端
* 前端框架 : `layui`
* 数据可视化框架 : `echarts`

### 后端
* IOC容器 : `Spring`
* MVC框架 : `SpringMVC`
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
│  │  │      └─jzy
│  │  │          └─mapper           // mybatis对dao接口的xml实现
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

2. *进入src/main/resources修改dbconfig.properties配置文件,把数据库主机、端口、用户名和密码，改为你本地的*
3. *进入src/main/resources修改redis.properties配置文件,把数据库主机、端口、用户名和密码，改为你本地的*
4. *进入src/main/resources查看log4j.properties，如果有必要可以修改日志输出路径，目前在D盘下，你可选择不修改跳过此步*
5. *使用 IntelliJ IDEA 导入项目，选择Maven项目选项，一路点击next，即可将项目所需依赖导入。若有无法引入的依赖，可能是因为maven版本不同或是该依赖已过时不存在于现有maven仓库中，请前往maven官网映入最新的该类型依赖*

![Snipaste_2019-07-17_08-47-37](git_screenshot/Snipaste_2019-07-17_08-47-37.jpg)

![Snipaste_2019-07-17_08-49-48](git_screenshot/Snipaste_2019-07-17_08-49-48.jpg)

5. *在 IntelliJ IDEA 中，配置我们的 Tomcat， 然后把使用Maven构建好的项目添加到Tomcat中，操作比较简单，相关方法可以参考百度*

6. *运行项目，进入用户登录页面*

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