function pass(id) {
    $.ajax({
        url : "comment/"+id,
        type : "post",
        data : {
            'id' : id,
            'status':1,
            '_method':'put'
        },
        success : function(data) {
            $('#comment_table').bootstrapTable('remove', {
                field: 'id',
                values: [parseInt(id)]
            })
            if (data.status != 200) {
                layer.msg(data.message);
            }
        },
        dataType:'json'
    });
}

function reply(id,aid) {
    layer.open({
        id:1,
        type: 1,
        title:'回复',
        skin:'layui-layer-rim',//样式类名  自定义样式
        area:['450px', 'auto'],
        content: '<input id="replay_comment" type="text" class="form-control" placeholder="请输入评论内容">'
        ,
        btn:['回复','取消'],
        btn1: function (index,layero) {
            var replay = $('#replay_comment').val();
            layer.close(index);
            $.post('/comment/',{
                'content':replay,
                'cid':id,
                'aid':aid,
                'isAdmin':1,
                'status':1
            },function (data) {
                if(data.status!=200){
                    layer.msg(data.message);
                    return 0;
                }else{
                    return 1;
                }
            },"json");
        },
        btn2:function (index,layero) {
            layer.close(index);
        }
    });
}

function passAndReply(id,aid) {
    layer.open({
        id:1,
        type: 1,
        title:'回复',
        skin:'layui-layer-rim',//样式类名  自定义样式
        area:['450px', 'auto'],
        content: '<input id="replay_comment" type="text" class="form-control" placeholder="请输入评论内容">'
        ,
        btn:['回复','取消'],
        btn1: function (index,layero) {
            var replay = $('#replay_comment').val();
            layer.close(index);
            $.post('/comment/',{
                'content':replay,
                'cid':id,
                'aid':aid,
                'isAdmin':1,
                'status':1
            },function (data) {
                if(data.status!=200){
                    layer.msg(data.message);
                }else{
                    pass(id);
                }
            },"json");
        },
        btn2:function (index,layero) {
            layer.close(index);
        }
    });

}


function remove(id) {
    layer.confirm("确认要删除本条记录？", {
        btn: ['确定', '取消']
    }, function () {
        $.ajax({
            url : "comment/"+id,
            type : "delete",
            data : {
                'id' : id
            },
            success : function(data) {
                $('#comment_table').bootstrapTable('remove', {
                    field: 'id',
                    values: [parseInt(id)]
                })
                if (data.status == 200) {
                    layer.msg("删除成功");
                } else {
                    layer.msg(data.message);
                }
            },
            dataType:'json'
        });
    });
}


function batchRemove() {
    layer.confirm("确认要删除本条记录？", {
        btn: ['确定', '取消']
    }, function () {
        var rows= $('#comment_table').bootstrapTable('getSelections');
        var ids= new Array(rows.length);
        for (var i = 0,len = rows.length; i < len; i++) {
            ids[i] = rows[i].id;
        }
        $.ajax({
            url : "comment/batchRemove",
            type : "post",
            data : {
                ids : ids,
                _method:'delete'
            },
            success : function(data) {
                if (data.status == 200) {
                    for (var i = 0,len = rows.length; i < len; i++) {
                        $('#comment_table').bootstrapTable('remove', {
                            field: 'id',
                            values: [parseInt(ids[i])]
                        })
                    }
                    layer.msg("删除成功");
                } else {
                    layer.msg(data.message);
                }
            },
            dataType:'json'
        });
    });
}

