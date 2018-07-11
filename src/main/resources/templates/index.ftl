<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>后台管理</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="shortcut icon" href="favicon.ico">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/bootstrap-table.min.css">
    <link rel="stylesheet" href="css/font-awesome.min.css">
    <link href="css/plugins/summernote/summernote.css" rel="stylesheet">
    <link rel="stylesheet" href="css/my.css?v=3">
    <script src="js/jquery.min.js"></script>
    <script src="js/jquery.validate.min.js"></script>
    <script src="js/popper.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/bootstrap-table.min.js"></script>
    <script src="js/bootstrap-table-zh-CN.min.js"></script>
    <script src="js/plugins/summernote/summernote.min.js"></script>
    <script src="js/plugins/summernote/summernote-zh-CN.min.js"></script>
    <script src="js/plugins/layer/layer.min.js"></script>

    <script src="js/blog/list.js?v=2"></script>
    <script src="js/comment/list.js?v=2"></script>
    <script src="js/dateFormat.js?v=2"></script>
    <script>

        $(function () {
            $('#menu-item .conent-href').click(function () {
                $('#home').load($(this).attr("href"));
                return false;
            })

        })


    </script>
</head>

<body>
<div class="container-fluid">
    <div id="left-menu" class="col-2">
        <div id="menu-head">
            <span><img class="rounded-circle" src="img/default_head.jpg" height="60" width="60"></span>
            <h4 style="color: white;">后台管理系统</h4>
        </div>
        <div id="menu-item">
            <ul>
                <li>
                    <a href="#"><i class="fa fa-rss-square"></i>博客管理</a>
                    <ul>
                        <a class="conent-href" href="blog/article/add">
                            <li class="children-menu-item">
                                发表文章
                            </li>
                        </a>
                        <a class="conent-href" href="blog/article/">
                            <li class="children-menu-item">
                                文章列表
                            </li>
                        </a>
                        <a class="conent-href" href="blog/comment/2">
                            <li class="children-menu-item">
                                评论审核
                            <#if (checkCommentCount!>0)>
                                <span class="badge badge-info" style="margin-left: 50px;">${checkCommentCount}</span>
                            </#if>
                            </li>
                        </a>
                        <a class="conent-href" href="blog/comment/1">
                            <li class="children-menu-item">
                                评论管理
                            </li>
                        </a>
                    </ul>
                </li>
                <li>
                    <a href="#"><i class="fa fa-rss-square"></i>系统工具</a>
                    <ul>
                        <a class="conent-href" href="/generator/">
                            <li class="children-menu-item">
                                代码生成
                            </li>
                        </a>
                    </ul>
                </li>
            </ul>
        </div>

    </div>
    <div id="right-content" class="col-10">
        <nav class="top-nav">
            <ul>
                <li>
                    <a href="blog/"><i class="fa fa-rss-square"></i>博客</a>
                </li>
                <li>
                    <a href="/layout"><i class="fa fa-sign-out"></i>退出</a>
                </li>
            </ul>
        </nav>
        <div id="content-nav">
            <ul class="nav nav-tabs" role="tablist">
                <li class="nav-item">
                    <a class="nav-link active" data-toggle="tab" href="#home">Home</a>
                </li>
            </ul>

            <!-- Tab panes -->
            <div class="tab-content">
                <div id="home" class="container tab-pane active"><br>
                    <h3>首页</h3>
                    <p>github:<a href="https://github.com/dreamlandfeeling/blog" target="_blank">https://github.com/dreamlandfeeling/blog</a></p>
                </div>
            </div>
        </div>
    </div>
</body>
</html>