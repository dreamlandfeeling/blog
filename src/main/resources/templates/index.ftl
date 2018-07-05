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
    <link rel="stylesheet" href="css/my.css?v=2">
    <script src="js/jquery.min.js"></script>
    <script src="js/jquery.validate.min.js"></script>
    <script src="js/popper.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/bootstrap-table.min.js"></script>
    <script src="js/bootstrap-table-zh-CN.min.js"></script>
    <script src="js/plugins/summernote/summernote.min.js"></script>
    <script src="js/plugins/summernote/summernote-zh-CN.min.js"></script>
    <script src="js/plugins/layer/layer.min.js"></script>

    <script src="js/blog/list.js?v=1"></script>
    <script>

        $(function() {
            $('.children-menu-item .conent-href').click(function() {
                $('#home').load($(this).attr("href"));
                return false;
            })

        })

        Date.prototype.Format = function(formatStr) {
            var str = formatStr;
            var Week = ['日', '一', '二', '三', '四', '五', '六'];
            str = str.replace(/yyyy|YYYY/, this.getFullYear());
            str = str.replace(/yy|YY/, (this.getYear() % 100) > 9 ? (this.getYear() % 100).toString() : '0' + (this.getYear() % 100));
            str = str.replace(/MM/, (this.getMonth() + 1) > 9 ? (this.getMonth() + 1).toString() : '0' + (this.getMonth() + 1));
            str = str.replace(/M/g, (this.getMonth() + 1));
            str = str.replace(/w|W/g, Week[this.getDay()]);
            str = str.replace(/dd|DD/, this.getDate() > 9 ? this.getDate().toString() : '0' + this.getDate());
            str = str.replace(/d|D/g, this.getDate());
            str = str.replace(/hh|HH/, this.getHours() > 9 ? this.getHours().toString() : '0' + this.getHours());
            str = str.replace(/h|H/g, this.getHours());
            str = str.replace(/mm/, this.getMinutes() > 9 ? this.getMinutes().toString() : '0' + this.getMinutes());
            str = str.replace(/m/g, this.getMinutes());
            str = str.replace(/ss|SS/, this.getSeconds() > 9 ? this.getSeconds().toString() : '0' + this.getSeconds());
            str = str.replace(/s|S/g, this.getSeconds());
            return str
        }
    </script>
</head>

<body>
<div class="container-fluid">
    <div id="left-menu" class="col-2">
        <div id="menu-head">
            <span><img class="rounded-circle" src="img/default_head.jpg" height="60" width="60" ></span>
            <h4 style="color: white;">后台管理系统</h4>
        </div>
        <div id="menu-item">
            <ul>
                <li>
                    <a href="#"><i class="fa fa-rss-square"></i>博客管理</a>
                    <ul>
                        <li class="children-menu-item">
                            <a class="conent-href" href="blog/add">发表文章</a>
                        </li>
                        <li class="children-menu-item">
                            <a class="conent-href" href="blog/list">文章列表</a>
                        </li>
                    </ul>
                </li>
                <li>
                    <a href="#"><i class="fa fa-rss-square"></i>系统工具</a>
                    <ul>
                        <li class="children-menu-item">
                            <a class="conent-href" href="/generator/">代码生成</a>
                        </li>
                    </ul>
                </li>
            </ul>
        </div>

    </div>
    <div id="right-content" class="col-10" >
        <nav class="top-nav">
            <ul>
                <li>
                    <a href="#"><i class="fa fa-id-card"></i>个人</a>
                </li>
                <li>
                    <a href="#"><i class="fa fa-rss-square"></i>博客</a>
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
                    <h3>HOME</h3>
                    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</p>
                </div>
            </div>
        </div>
    </div>
</body>
</html>