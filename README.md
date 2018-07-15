## 项目简介

### 博客管理系统

技术：springboot+mybatis+freemarker+druid

开发环境: jdk1.8、idea、maven、mysql

## 功能介绍
1.  发表文章，对文章的管理
2.  游客对文章的评论和回复功能，博主可以审核评论，对评论进行管理
3.  后端代码一键生成(controller/service/model/mapper/mapperXml)增删改查分页和批量删除
暂时只支持mysql，配置文件:generator.properties

## 部分项目图
![Image text](https://github.com/dreamlandfeeling/blog/blob/master/img-folder/admin.png)
![Image text](https://github.com/dreamlandfeeling/blog/blob/master/img-folder/blog.png)
![Image text](https://github.com/dreamlandfeeling/blog/blob/master/img-folder/blog_article.png)

## 项目部署
1.	导入yxblog.sql
2.	修改application.properties 的数据库配置
3.	运行application类或者在项目根目录使用命令行mvn spring-boot:run
4.	访问路径从http://localhost:8080/blog/  开始比较好

   

## 其他
该项目前后端都使自己写的，主后端开发的所以前端部分有点不足
