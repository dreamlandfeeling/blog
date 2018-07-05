<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>登录</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <style>
        html, body {
            width: 100%;
            height: 100%;
        }

        body {
            background-image: url("img/loginBack.jpg");
            background-size: cover; /*放大图片占满整个屏幕 */
            overflow-x: hidden; /*去除滚动条 */
            overflow-y: hidden; /*去除滚动条 */
        }

        .login_form {
            width: 300px;
            height: 300px;
            padding: 20px;
        }

        .wrap {
            width: 300px;
            height: 300px;
            margin: auto;
            background: white;
            position: absolute;
            top: 0;
            left: 0;
            right: 0;
            bottom: 0;
        }
        .title{
            text-align: center;
            font-size: 24px;
        }
    </style>
    <script>
        function login(){
            $.post('/login',$('#login_form').serialize(),function (data) {
                if(data.status==200){
                    window.location.href = "/";
                }else {
                    $('#error').html(data.message);
                }
            },'json');
        }
    </script>
</head>
<body>
<div class="wrap">
    <form id="login_form" class="login_form">
        <div class="title">
            用户登录
        </div>
        <div class="input-group mb-3">
            <div class="input-group-prepend">
                <span class="input-group-text">帐号</span>
            </div>
            <input type="text" class="form-control" value="test" name="username" id="title">
        </div>
        <div class="input-group mb-3">
            <div class="input-group-prepend">
                <span class="input-group-text">密码</span>
            </div>
            <input type="password" class="form-control" value="test" name="password">
        </div>
        <div id="error" style="color:red"></div>
        <a class="btn btn-primary btn-block" title="登录" onclick="login()">登录</a>
    </form>
</div>


</body>
</html>