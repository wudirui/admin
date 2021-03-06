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
            $("[data-field='type']").children().each(function () {
                if ($(this).text() == '1') {
                    $(this).text("一级菜单")
                }
                if ($(this).text() == '2') {
                    $(this).text("二级菜单")
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

    //添加
    $(".add_btn").click(function () {
        let index = layui.layer.open({
            title: "添加菜单",
            type: 2,
            content: "menuEdit.html",
        })
        layui.layer.full(index);
        window.sessionStorage.setItem("index", index);
        //改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
        $(window).on("resize", function () {
            layui.layer.full(window.sessionStorage.getItem("index"));
        })
    });

    //编辑
    function edit(edit) {
        let index = layui.layer.open({
            title: "编辑菜单",
            type: 2,
            content: "menuEdit.html",
            success: function (layero, index) {
                let body = layui.layer.getChildFrame('body', index);
                body.find("#menuId").val(edit.id);
                body.find("#name").val(edit.name);
                body.find("#type").val(edit.type);
                body.find("#menuUrl").val(edit.menu_url);
                body.find("#parentId").val(edit.parent_id);
                form.render();
                setTimeout(function () {
                    layui.layer.tips('点击此处返回列表', '.layui-layer-setwin .layui-layer-close', {
                        tips: 3
                    });
                }, 500)
            }
        })
        layui.layer.full(index);
        window.sessionStorage.setItem("index", index);
        //改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
        $(window).on("resize", function () {
            layui.layer.full(window.sessionStorage.getItem("index"));
        })
    }

    //列表操作
    table.on('tool(tableList)', function (obj) {
        let layEvent = obj.event, data = obj.data;
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
        if(layEvent==='add'){
            add();
        }

    });

})
