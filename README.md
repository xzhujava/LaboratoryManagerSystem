# 实验室管理系统
整体采用MVC三层架构方式构建的JSP+Servlet一套简洁清新的管理系统。
## 环境
JDK1.8、Mysql8.0、Tomcat9.0;开发工具使用IntelliJ IDEA（同样也可使用eclipse等）
## 后端架构
使用DBHelper对数据库连接，dao层基于JDBC使用反射+泛型构建baseDao;<br>
service层实现对业务逻辑的处理；servlet则是暴露接口用与前端请求；<br>
SysFilter实现Filter接口对所有请求进行拦截处理(设置编码格式，对未登录或session过期的请求直接重定向到登录页重新登陆)
## 前端结构
使用bootstrap各种组件优化页面布局(*虽然使用bootstrap但页面并非完全响应式);<br>
使用jsp及jstl标签库对页面构造
## 项目简介
### 数据库说明
1 项目分析：项目角色分为管理员和学生 --> **角色表**、**用户表**，通过角色表关联用户表实现不同角色的登录；系统功能主要为学生对实验室的预约，教师管理实验成绩,管理员管理学生和教师信息 -->**实验室表** 、**实验室预约记录表**。<br/>
2 项目分析得出系统大致分为角色表、用户表、菜单表、实验室表、实验室预约记录表，而其中不同用户的不同角色关系由用户角色中间表关联，不同角色显示不同菜单由菜单角色中间表关联，不同学生的实验室预约信息由实验室预约记录表与实验室和用户表关联<br/>
3 系统数据表有：菜单表、角色表、实验室表，用户数据相关表有：用户表、实验室预约记录表，数据关联表有：角色菜单表、用户角色表
### 系统模块说明
#### 学生模块
##### 注册(学生使用自己的学号注册)
1 注册模块系统设计为只允许学生注册，而管理员为系统默认保留一名管理员<br/>
2 学生依次输入学生姓名、学生学号、学生登录密码、学生性别以及学生备注信息，学生姓名必须不为空，学号必须为纯数字，密码长度必须大于等于6位方可注册。servlet获取前端request请求数据封装为注册用户对象,学生输入的密码在service层使用MD5加密处理，保证用户安全性，dao层使用jdbc对用户信息的持久化做处理<br>
3 注册完成后自动跳转到欢迎页，学生可执行查看实验室信息，及预约实验室操作等 
##### 登录
1 分为管理员和学生登录 <br>
2 用户依次输入工号或学号(这里前端提示信息为请填写您的学号，而管理员可直接输入正确的工号和密码进入系统)，servlet获取登录请求的用户信息后传值到service，在service根据学号或工号获取用户表中的对应用户信息，如果用户表不存在对应学号或工号用户则直接返回前端登录失败的信息，前端提示用户
用户名或密码输入错误；如果用户正确输入学号或工号及对应密码则验证通过把该用户信息存入session中，通过后查询该用户对应的角色表查出该用户的角色，然后通过角色菜单关联关系查出该用户的菜单信息，把菜单信息存入session，然后返回前端登录成功的消息，跳转到欢迎页以执行后续操作。
##### 查询实验室信息
1 学生登录后可根据目录导航，点击实验室信息可查询系统中的实验室信息包括实验室名称、实验室地址、实验室状态<br>
2 其中实验室状态分为可预约和预约中，点击可预约按钮可以对改实验室进行预约操作，而预约中则表示该实验室已经有同学预约，可以点击预约中按钮查询预约信息
##### 预约实验室
学生登录后点击实验室信息表中最后一列的可预约按钮可以对该实验室进行预约操作，预约实验室必须填入预约信息，这些信息包括预约时间、备注信息，其中实验室名称通过js自动获取填充，而真正传值到serlet的是改实验室id，预约时间，备注信息，在servlet把这些信息封装成预约信息记录最后存入数据库来持久化
#### 管理员模块
##### 查询学生信息
管理员在登陆系统后，点击导航菜单的学生管理可查询所有系统中注册的学生信息
##### 查询学生的预约信息
管理员在登陆系统后，点击导航菜单的预约实验室管理可查询所有学生的实验室预约信息
##### 维护实验室信息
管理员在登陆系统后，点击导航菜单的实验室管理可查询所有状态为已预约的实验室预约信息，管理员可根据预约时间对比当前时间来改变预约信息的状态(把预约信息改为已完成使用，这里系统后台会把该实验室的状态改为可预约)，来达到维护实验室的目的
## 说明
1 本系统并未包含教师管理实验成绩<br/>
2 维护实验室信息中逻辑为：这里并没有真正的去查询实验室具体信息，而是直接查找还在进行中的实验(实验预约记录)，如果尚未存在实验预约记录此目录下不会有数据;也就是说这里显示的是预约记录管理的一部分，如果没有预约记录，那么实验室则不需要维护