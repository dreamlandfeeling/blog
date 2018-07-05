<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>出错了</title>
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <script src="/js/jquery.min.js"></script>
    <script src="/js/bootstrap.min.js"></script>
    <style>
        html, body {
            width: 100%;
            height: 100%;
        }

        body {
            background-image: url("/img/error.jpg");
            background-size: cover; /*放大图片占满整个屏幕 */
            overflow-x: hidden; /*去除滚动条 */
            overflow-y: hidden; /*去除滚动条 */
        }


        .wrap {
            width: 100px;
            height: 100px;
            margin: auto;
            background: white;
            position: absolute;
            top: 0;
            left: 0;
            right: 0;
            bottom: 0;
        }
        a {
            text-decoration: none;
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
    <h4><a href="/">主页</a></h4>
</div>


</body>
</html>