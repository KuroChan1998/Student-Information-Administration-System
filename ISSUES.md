# 项目问题大全

## 关于可视化功能报错，无法显示

有的同学反反映可视化图表功能模块报错，无法显示。

### 原因

我的mysql存储过程定义者为\`root\`@\`%\`，其中%表示所有主机可调用，而有的同学中mysql的root用户的host字段不是%而是localhost。

```sql
CREATE DEFINER=`root`@`%` PROCEDURE `count_stu_percent`(IN type_ varchar(50), IN college_name_ varchar(50), IN major_name_ varchar(50))
```

### 解决

查看数据库*mysql.user*表中host和user字段信息
mysql> select host, user from mysql.user;
如果root用户的host字段值为localhost或者不是%，则与我的init.sql中的定义冲突，采用如下方式解决

#### 解决方法一

in file init.sql

- line 288:

  ```sql
  CREATE DEFINER=`root`@`%` PROCEDURE `count_stu_percent`(IN type_ varchar(50), IN college_name_ varchar(50), IN major_name_ varchar(50))
  ```

  更改\`root\`@\`%\`为\`root\`@\`localhost\`

- line 328:

  ```sql
  CREATE DEFINER=`root`@`%` PROCEDURE `count_tea_percent`(IN type_ varchar(50), IN college_name_ varchar(50), IN major_name_ varchar(50))
  ```

  更改\`root\`@\`%\`为\`root\`@\`localhost\`

- 最后删除数据库mydatabase2，重新导入脚本init.sql

#### 解决方法二

更改mysql.user表
在mysql命令行中只执行如下命令
`mysql> use mysql;`
`mysql> update user set host = '%' where user = 'root'; `
`mysql> select host, user from user; `
`mysql> flush privileges;`
重启项目