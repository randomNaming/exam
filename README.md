# 在线考试系统(JavaWeb)
## 数据库的设置：
    和数据库连接有关的代码位于application.properties 中，
    默认是私人的賬戶密碼，
    你可以通过MySQL自行创建自己的用户名密码，不建议直接使用root，
## 运行前操作
    1.附带SQL脚本，一个包含数据，一个只有表结构，自行选择(/src/main/resources/SQL...)
    2.当数据库用户创建好后，为该用户创建一个数据库并配置权限，本程序默认的数据库是"examdb",视图权限默认提供给'root'@'%'
    3.请记得更改application.properties中的鏈接、用户名、密码
        如果你想用自己创建的数据库，请随意，但是记得修改application.properties中的数据库名称变量
