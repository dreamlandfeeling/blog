<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>文章列表</title>
    <link rel="stylesheet" href="../css/bootstrap.min.css">
<#--<link rel="stylesheet" href="../css/blog/article.css">-->

    <script src="../js/jquery.min.js"></script>
    <script src="../js/plugins/layer/layer.min.js"></script>
    <script src="../js/dateFormat.js?v=2"></script>

    <style>
        html {
            height: 100%;
            width: 100%;
            padding: 0px;
            overflow: auto;
        }

        body {
            background-image: url(../img/blog.jpg);
            position: fixed;
            height: 100%;
            width: 100%;
            padding: 0px;
            margin: 0px;
            overflow: auto;
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

        a:hover {
            text-decoration: none;
        }

        #article_info {
            padding-top: 120px;
            margin: 0 auto;
        }

        .native-wrap {
            padding-top: 100px;
            width: 1000px;
        }

        .user_head {
            float: left;
            width: 2.5rem;
            height: 2.5rem;
        }

        .comment_info {
            padding-left: 80px;
        }
    </style>
    <script>
        $(function () {
            initCommentInfo();

        });

        function initCommentInfo() {
            if (!localStorage) {
                return false;
            } else {
                var author = localStorage.getItem("author");
                var email = localStorage.getItem("email");
                $('#author').val(author);
                $('#email').val(email);
            }
        }

        function sendComment() {
            var author = $('#author').val();
            if(author==null || author.trim()==''){
                layer.msg("昵称不能为空");
                return false;
            }
            var email = $('#email').val();
            localStorage.setItem("author", author);
            localStorage.setItem("email", email);
            var newContent = $('#comment_content').val() + $('#content').val();
            $('#content').val(newContent);
            $.post('/common/newComment', $('#comment_form').serialize(), function (data) {
                if (data.status == 200) {
                    var comment = data.data;
                    var date = new Date(Date.parse(comment.createtime)).Format("yyyy-MM-dd HH:mm:ss");
                    var comment = "<div>" +
                            "<img class='rounded-circle user_head' src='../img/comment_user.jpg' alt='用户头像'>" +
                            "<div class='comment_info' >" +
                            "<div style='padding-bottom:10px; color: blue;'>" + comment.author + "</div>" +
                            "<div style='padding-bottom:10px;'>" + comment.content + "</div>" +
                            "<div style='padding-bottom:10px;'>" + date + " <a href='javascript:void(0)' onclick='replay(" + comment.id + ")'><span style=\'padding-left: 10px; color: red;\'>回复</span></a></div>" +
                            "</div><hr/> </div>";
                    $('#comment_list').prepend(comment);
                    initForm();
                }
            }, "json");
        }

        function initForm() {
            $('#cid').val("");
            $('#content').val("");
            $('#comment_content').val("");
        }

        function replay(id) {
            // var str = "//@<span style='color:blue'>"+author+"</span>:"+content;
            // $('#content').val(str);
            $('#cid').val(id);
            openReplayLayer();
        }

        function openReplayLayer() {
            layer.open({
                id: 1,
                type: 1,
                title: '回复',
                skin: 'layui-layer-rim',//样式类名  自定义样式
                area: ['450px', 'auto'],
                content: '<input id="replay_comment" type="text" class="form-control" placeholder="请输入评论内容">'
                ,
                btn: ['回复', '取消'],
                btn1: function (index, layero) {
                    var replay = $('#replay_comment').val();
                    $('#comment_content').val(replay);
                    layer.close(index);
                    sendComment();
                    return replay;
                },
                btn2: function (index, layero) {
                    layer.close(index);
                }

            });
        }
    </script>
</head>
<body>
<div class="container">
    <div id="top">
        <a href="/login">登录</a>
        <a href="/blog/">主页</a>
        <div id="article_info">

            <div style="font-size: 30px">${article.title!}</div>
            <div style="font-size: 24px">作者:${article.author!} <span
                    style="margin-left: 20px">${article.updatetime!?string('yyyy.MM.dd HH:mm:ss')}</span></div>
        </div>
    </div>
    <div style="height: auto;">
    ${article.content!}
    </div>
    <form id="comment_form">
        <div class="native-wrap">
            <input name="aid" type="hidden" value="${article.id!}">
            <input id="cid" name="cid" type="hidden">
            <input id="content" name="content" type="hidden">
            <input id="status" name="status" type="hidden" value="2">
            <div>
                <h5>参与评论</h5>
            </div>
            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <span class="input-group-text">昵称(必填)</span>
                </div>
                <input type="text" class="form-control" placeholder="nickname" name="author" id="author">
                <div class="input-group-prepend">
                    <span class="input-group-text">邮箱(选填)</span>
                </div>
                <input type="text" class="form-control" placeholder="email" name="email" id="email">
            </div>
            <textarea class="form-control" rows="3" placeholder="赶快来评论一下吧" id="comment_content"></textarea>
            <a class="btn btn-primary" role="button" style="margin-top: 15px" onclick="sendComment()">提交</a>
        </div>
    </form>
    <div style="padding-top: 40px;"><h5>评论:</h5></div>
    <div id="comment_list">
        <#list page.rows as comment>
            <#if comment.status!=0>
                <div>
                    <img class="rounded-circle user_head" src="../img/comment_user.jpg" alt="用户头像">
                    <div class="comment_info">
                        <div style="padding-bottom:10px; color: blue;">${comment.author}
                            <#if (comment.isAdmin!0)==1>
                                <span class="badge badge-info">博主</span>
                            </#if>
                        </div>
                        <div style="padding-bottom:10px;">${comment.content}</div>
                        <div style="padding-bottom:10px;">${comment.createtime?string("yyyy-MM-dd HH:mm:ss")} <a
                                href="javascript:void(0)" onclick="replay(${comment.id})"><span
                                style="padding-left: 10px; color: red;">回复</span></a></div>
                    </div>
                    <hr/>
                </div>
            </#if>
        </#list>
    </div>

</div>

</body>
</html>