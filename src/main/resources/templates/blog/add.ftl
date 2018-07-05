<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>发布文章</title>
    <style>
        .blog-info div {
            float: left;
        }

        .input-group {
            width: 50%;
        }

        #summernote {
            border: 1 solid black;
        }
    </style>
    <script  >
        $(function () {
            $('#summernote').summernote({
                lang: 'zh-CN',
                height: '300px',
                placeholder: '请输入文章内容',
                callbacks: {
                    onImageUpload: function (files) {
                        uploadImages(files);
                    }
                }
            });

        })

        // summernote具体的上传图片方法
        function uploadImages(files) {
            $("#loadingText").html("正在上传图片...");
            // 上传图片的form
            var formData = new FormData();
            for (f in files) {
                formData.append("file", files[f]);
            }
            $.ajax({
                data: formData,
                type: "POST",
                url: "upload",
                cache: false,
                contentType: false,
                processData: false,
                success: function (data) {
                    if (data.status === 200) {
                        $("#summernote").summernote('insertImage', data.data, 'image name');
                        layer.alert('上传成功');
                    } else {
                        layer.alert('上传失败');
                    }
                },
                dataType: "json"
            });
            $("#loadingText").html("");
        }

        function getContent() {
            var content = $('#summernote').summernote('code');
            $('#content').val(content);
        }

        function saveArticle() {
            getContent();
            $('#status').val(1);
            $.post('/blog/article', $('#blog-form').serialize(), function (data) {
                if (data.status == 200) {
                    layer.alert("发布成功");
                } else {
                    layer.alert("发布失败");
                }
            }, "json");
        }

        function saveDraft() {
            getContent();
            $('#status').val(0);
            $.post('/blog/article', $('#blog-form').serialize(), function (data) {
                if (data.status == 200) {
                    layer.alert("存稿成功");
                } else {
                    layer.alert("存稿失败");
                }
            }, "json");
        }
    </script>
</head>




<body>
<div class="container-fluid">
    <h4 style="padding-top: 20px;">发布文章</h4>
    <form id="blog-form">
        <input id="content" name="content" type="hidden">
        <input id="status" name="status" type="hidden">
        <div class="input-group mb-3">
            <div class="input-group-prepend">
                <span class="input-group-text">标题(必需)</span>
            </div>
            <input type="text" class="form-control" placeholder="title" name="title" id="title">
        </div>
        <div class="input-group mb-3">
            <div class="input-group-prepend">
                <span class="input-group-text">作者(任意)</span>
            </div>
            <input type="text" class="form-control" placeholder="author" name="author">
        </div>
        <div class="form-group">
            内容(必需)：<span id="loadingText"></span>
            <div class="ibox-content no-padding">
                <div id="summernote"></div>
            </div>
        </div>
        <a class="btn btn-primary" onclick="saveArticle()">发布文章</a>
        <a class="btn btn-warning" onclick="saveDraft()">存为草稿</a>
    </form>
</div>
</body>

</html>