<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>文章列表</title>
    <link rel="stylesheet" href="../css/bootstrap.min.css">

    <script src="../js/jquery.min.js"></script>
    <style>
        html {
            height: 100%;
            width: 100%;
            padding: 0px;
            overflow:auto;
        }
        body {
            background-image: url(../img/blog.jpg);
            position: fixed;
            height: 100%;
            width: 100%;
            padding: 0px;
            margin: 0px;
            overflow:auto;
        }

        #top {
            width: 100%;
            padding-right: 50px;
            height: 250px;
        }
        #top a {
            padding: 20px;
            float: right;
            line-height: 20px;
            font-size: 18px;
        }
        a:hover{
            text-decoration: none;
        }
        .article_title{
            font-weight:bold;
        }
    </style>
    <script>
        $(function () {

        })


    </script>
</head>
<body>
<div class="container">
    <div id="top">
        <a href="/login">登录</a>
        <a href="/blog/">主页</a>
    </div>
    <div id="article_list">
        <#list page.rows as article>
            <h4 class="article_title"><div><a href="/common/${article.id}">${article.title!}</a></div></h4>
            <p>作者: ${article.author!} <span style="margin-left: 20px">发表时间:${article.updatetime!?string('yyyy.MM.dd HH:mm:ss')}</span></p>
            <hr/>
        </#list>
    </div>
    <span>共${page.total!0}条数据</span>
    <#if (currentPage-1>=0)>
        <a class="btn btn-link" href="/blog/?currentPage=${currentPage-1!0}" role="button">上一页</a>
    <#else >
        <a class="btn btn-link disabled" href="/blog/?currentPage=${currentPage-1!0}" role="button">上一页</a>
    </#if>
    <a class="btn btn-link" href="/blog/?currentPage=${currentPage+1!0}" role="button">下一页</a>
</div>

</body>
</html>