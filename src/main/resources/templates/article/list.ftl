<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>文章列表</title>
    <script>
        $(function () {
            initTable();
        })

        function initTable() {
            $('#article_table').bootstrapTable({
                url: 'blog/article/list',         //请求后台的URL（*）
                method: 'get',                      //请求方式（*）
                // toolbar: '#toolbar',                //工具按钮用哪个容器
                striped: true,                      //是否显示行间隔色
                cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
                pagination: true,                   //是否显示分页（*）
                sortable: false,                     //是否启用排序
                sortOrder: "asc",                   //排序方式
                queryParams: function (params) { // 请求服务器数据时发送的参数，可以在这里添加额外的查询参数，返回false则终止请求
                    return {
                        limit: params.limit, // 每页要显示的数据条数
                        offset: params.offset, // 每页显示数据的开始行号
                    }
                },
                sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
                pageNumber: 1,                       //初始化加载第一页，默认第一页
                pageSize: 10,                       //每页的记录行数（*）
                pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
                // search: true,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
                // strictSearch: true,
                showColumns: false,                  //是否显示所有的列
                // showRefresh: true,                  //是否显示刷新按钮
                minimumCountColumns: 2,             //最少允许的列数
                clickToSelect: true,                //是否启用点击选中行
                height: 500,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
                uniqueId: "ID",                     //每一行的唯一标识，一般为主键列
                // showToggle: true,                    //是否显示详细视图和列表视图的切换按钮
                cardView: false,                    //是否显示详细视图
                detailView: false,                   //是否显示父子表
                columns: [{
                    checkbox: true
                },{
                    field: 'id',
                    title: '',
                    visible : false,
                }, {
                    field: 'title',
                    title: '标题'
                }, {
                    field: 'author',
                    title: '作者'
                }, {
                    field: 'updatetime',
                    title: '最近修改时间',
                    formatter: function (value, row, index) {
                        var date = new Date(Date.parse(value));
                        return date.Format('YYYY-MM-dd HH:mm:ss');
                    }
                }, {
                    field: 'status',
                    title: '状态',
                    align: 'center',
                    formatter: function (value, row, index) {
                        if (value == 0) {
                            return '<h5><span class="badge badge-warning">草稿</span></h5>';
                        } else if (value == 1) {
                            return '<h5><span class="badge badge-success">发布</span></h5>';
                        }
                    }
                }, {
                    field: '',
                    title: '操作',
                    align: 'center',
                    formatter: function (value, row, index) {
                        var edit = "<a class='btn btn-primary' onclick='editArticle(" + row.id + ")' title='编辑'><i class='fa fa-edit'></i></a>";
                        var remove = "<a class='btn btn-warning' onclick='removeArticle(" + row.id + ")' title='删除'><i class='fa fa-remove'></i></a>";
                        var preview = "<a class='btn btn-success' onclick='previewArticle(" + row.id + ")' title='预览'><i class='fa fa-rocket'></i></a>";
                        return edit + remove + preview;
                    }
                }]
            });
        }






    </script>
</head>
<body>
<a class="btn btn-danger" title="批量删除" style="margin: 20px 0 20px 0" onclick="batchRemove()">批量删除</a>

<table id="article_table"></table>
</body>
</html>