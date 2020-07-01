layui.use(['form', 'layer', 'table', 'laytpl'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table,
        baseUrl = '../../../';
    //用户列表
    var tableIns = table.render({
        elem: '#tableList',
        url: baseUrl + 'menu/getlist',
        cellMinWidth: 95,
        page: true,
        height: "full-125",
        limits: [5, 10, 15, 20, 25],
        limit: 10,
        id: "listTable",
        toolbar: '#tableToolbar',
        cols: [[
            {type: "checkbox", fixed: "left", width: 50},
            {field: 'name', title: '菜单名称', minWidth: 100, align: "center"},
            {field: 'type', title: '菜单类型', minWidth: 50, align: "center"},
            {field: 'menu_url', title: '菜单路径', minWidth: 50, align: "center"},
            {field: 'parent_id', title: '父菜单', minWidth: 50, align: "center"},
            {title: '操作', minWidth: 175, templet: '#listBar', fixed: "right", align: "center"}
        ]],
        done: function (res, curr, count) {
            $("[data-field='sex']").children().each(function () {
                if ($(this).text() == '1') {
                    $(this).text("女")
                }
                if ($(this).text() == '0') {
                    $(this).text("男")
                }

            });
            $("[data-field='status']").children().each(function () {
                if ($(this).text() == '1') {
                    $(this).text("通过")
                } else if ($(this).text() == '2') {
                    $(this).text("未通过")
                } else {
                    $(this).text("未审核")
                }

            });

            $("[data-field='audio']").children().each(function () {
                if ($(this).text() != "录音" && $(this).text()) {
                    $(this).html("<audio style='height: 28px;width: 100px' controls='controls' src='" + $(this).text() + "' ></audio>");
                }
            });
        }
    });

    //搜索
    form.on('submit(menuSearch)', function (data) {
        // alert(data.field.menuName);
        table.reload("listTable", {
            page: {
                curr: 1 //重新从第 1 页开始
            },
            where: {
                menuName: data.field['menuName']
            }
        })
    });


    //批量删除
    $(".delAll_btn").click(function () {
        let checkStatus = table.checkStatus('listTable'),
            data = checkStatus.data,
            ids = "";
        if (data.length > 0) {
            for (let i in data) {
                ids = ids + "," + data[i].id
            }
            layer.confirm('确定删除选中的数据？', {icon: 3, title: '提示信息'}, function (index) {
                $.post(baseUrl + "/menu/delAll", {
                    ids: ids //将需要删除的newsId作为参数传入
                }, function (data) {
                    tableIns.reload();
                    layer.close(index);
                })
            })
        } else {
            layer.msg("请选择需要删除的选项");
        }
    })

    //编辑
    function edit(edit){
        var index = layui.layer.open({
            title : "编辑菜单",
            type : 2,
            content : "menuEdit.html",
            success : function(layero, index){
                var body = layui.layer.getChildFrame('body', index);
                body.find("#id").val(edit.id);
                body.find("#name").val(edit.name);  //设备名称
                body.find("#type").val(edit.type);  //设备名称
                body.find("#parent_id").val(edit.parent_id);  //设备名称
                //设备名称
                form.render();
                setTimeout(function(){
                    layui.layer.tips('点击此处返回列表', '.layui-layer-setwin .layui-layer-close', {
                        tips: 3
                    });
                },500)
            }
        })
        layui.layer.full(index);
        window.sessionStorage.setItem("index",index);
        //改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
        $(window).on("resize",function(){
            layui.layer.full(window.sessionStorage.getItem("index"));
        })
    }

    //列表操作
    table.on('tool(tableList)', function (obj) {
        const layEvent = obj.event, data = obj.data;
        if (layEvent === 'del') { //删除
            layer.confirm('确定删除此选项？', {icon: 3, title: '提示信息'}, function (index) {
                $.post(baseUrl + "menu/del", {
                    id: data.id  //将需要删除的newsId作为参数传入
                }, function (data) {
                    tableIns.reload();
                    layer.close(index);
                })
            });
        }
        if (layEvent === 'edit') { //编辑
            edit(data);
        }

    });

})
