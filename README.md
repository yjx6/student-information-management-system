# student-information-management-system
# 学生信息管理系统

## 系统概述
这是一个用 Java 语言编写的学生信息管理系统，具备基本的增删改查功能和其他创新功能。

## 主要功能
- 登录界面
- ![这是一个登录界面](https://github.com/user-attachments/assets/ad5391c2-c506-484c-be26-1db1d7dde129)
- 信息显示界面
- ![这是一个信息显示界面](https://github.com/user-attachments/assets/f938b667-35ca-4042-aff9-e401bcc11df4)
- 信息维护界面
- ![这是一个信息维护界面](https://github.com/user-attachments/assets/c53e1068-e310-46c4-9c39-eaefb982e883)


## 技术栈
- Java
- MySQL

## 安装与使用
1. 克隆仓库到本地。
2. 配置数据库连接。
3. 运行项目。
这是一个用Java语言编写的学生信息管理系统，具备基本的增删改查功能和其他的创新功能。

## 数据库表单设计
### admin表

|字段|类型|是否主键|是否为空|中文描述|备注|
|----|----|----|----|----|----|
|id|INT|是|否|用户id|无|
|username|VARCHAR(15)|否|否|用户名|无|
|password|VARCHAR(15)|否|否|密码|无|
### class表

|字段|类型|是否主键|是否为空|中文描述|备注|
|----|----|----|----|----|----|
|classteacher|VARCHAR(10)|是|否|班主任|无|
|classname|VARCHAR(10)|否|否|班级名称|无|
|grade|VARCHAR(10)|否|否|年级|无|
|number|INT|否|否|人数|无|
|college|VARCHAR(15)|否|否|学院|无|
### course表

|字段|类型|是否主键|是否为空|中文描述|备注|
|----|----|----|----|----|----|
|cno|VARCHAR(10)|是|否|课程号|无|
|cname|VARCHAR(10)|否|否|课程名|无|
|teacher|VARCHAR(10)|否|否|授课老师|无|
|time|VARCHAR(10)|否|否|上课时间|无|
|place|varchar(10)|否|否|上课地点|无|

### studentinfo表

|字段|类型|是否主键|是否为空|中文描述|备注|
|----|----|----|----|----|----|
|id|VARCHAR(15)|是|否|学号|无|
|name|VARCHAR(10)|否|否|姓名|无|
|sex|VARCHAR(2)|否|否|性别|无|
|age|INT|否|否|年龄|无|
|class|VARCHAR(15)|否|否|班级|无|
|address|VARCHAR(30)|否|否|家庭地址|无|
|facial|VARCHAR(10)|否|否|政治面貌|无|
|time|VARCHAR(10)|否|否|入学时间|无|
### teacher表

|字段|类型|是否主键|是否为空|中文描述|备注|
|----|----|----|----|----|----|
|t_id|VARCHAR(15)|是|否|id|无|
|name|VARCHAR(10)|否|否|姓名|外键|
|sex|VARCHAR(10)|否|否|性别|无|
|academic|VARCHAR(10)|否|否|学历|无|
|phone|VARCHAR(11)|否|否|手机号|无|
|facial|VARCHAR(10)|否|否|政治面貌|无|
|class|VARCHAR(10)|否|否|所带班级|无|
|password|VARCHAR(15)|否|否|密码|无|

## 附加说明
src文件中存放所有该信息系统所对应的Java界面代码，并且lib文件中存放着相关的依赖驱动，下载即可用。
代码中有些界面的图片可能加载不出来，因为该项目在写的时候使用的是绝对路径而不是相对路径，所以需要把所引用图片文件下载到本地后重新在代码中插入图片。
通过登录界面输入数据库对应的账号和密码即可进入学生信息系统进行你想要的操纵。管理员和老师将会有不同的操作权限。
