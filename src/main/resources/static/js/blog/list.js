function removeArticle(id) {
    layer.confirm("确认要删除本条记录？", {
        btn: ['确定', '取消']
    }, function () {
        $.ajax({
            url : "blog/article/"+id,
            type : "delete",
            data : {
                'id' : id
            },
            success : function(data) {
                $('#article_table').bootstrapTable('remove', {
                    field: 'id',
                    values: [parseInt(id)]
                })
                if (data.status == 200) {
                    layer.msg("删除成功");
                } else {
                    layer.msg(r.msg);
                }
            },
            dataType:'json'
        });
    });
}

function editArticle(id) {
    $('#home').load("blog/article/"+id);
}

function batchRemove() {
    layer.confirm("确认要删除本条记录？", {
        btn: ['确定', '取消']
    }, function () {
        var rows= $('#article_table').bootstrapTable('getSelections');
        var ids= new Array(rows.length);
        for (var i = 0,len = rows.length; i < len; i++) {
            ids[i] = rows[i].id;
        }
        $.ajax({
            url : "blog/article/batchRemove",
            type : "post",
            data : {
                ids : ids,
                _method:'delete'
            },
            success : function(data) {
                if (data.status == 200) {
                    for (var i = 0,len = rows.length; i < len; i++) {
                        $('#article_table').bootstrapTable('remove', {
                            field: 'id',
                            values: [parseInt(ids[i])]
                        })
                    }
                    layer.msg("删除成功");
                } else {
                    layer.msg(r.msg);
                }
            },
            dataType:'json'
        });
    });
}