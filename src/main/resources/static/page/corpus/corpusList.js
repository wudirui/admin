layui.use(['form', 'layer', 'table', 'laytpl'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laytpl = layui.laytpl,
        table = layui.table;

    //用户列表
    var tableIns = table.render({
        elem: '#corpusList',
        //  url : '../../json/userList.json',
        url: '../../../corpus/getlist',
        cellMinWidth: 95,
        page: true,
        height: "full-125",
        limits: [5, 10, 15, 20, 25],
        limit: 10,
        id: "corpusListTable",
        toolbar: '#toolbar',
        cols: [[
            {type: "checkbox", fixed: "left", width: 50},
            {field: 'sentence', title: '语句', minWidth: 100, align: "center"},
            {field: 'recorder_name', title: '录音人姓名', minWidth: 50, align: "center"},
            {field: 'sex', title: '录音人性别', minWidth: 50, align: "center"},
            {field: 'age', title: '录音人年龄', minWidth: 30, align: "center"},
            {field: 'dialect', title: '对应方言', minWidth: 50, align: "center"},
            {field: 'audio', title: '录音', minWidth: 100, align: "center"},
            {field: 'status', title: '审核结果', minWidth: 50, align: "center"},
            {field: 'create_time', title: '录制日期', minWidth: 50, align: "center"},
            {title: '操作', minWidth: 175, templet: '#corpusListBar', fixed: "right", align: "center"}
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

    form.on('submit(corpusSearch)', function (data) {
        //alert(data.field);
        table.reload("corpusListTable", {
            page: {
                curr: 1 //重新从第 1 页开始
            },
            where: {
                sentence: data.field['sentence'],
                recorderName: data.field['recorderName'],
                dialect: data.field['dialect'],
                sex: data.field['sex'],
                status: data.field['status']
            }
        })
    });


    //批量删除
    $(".delAll_btn").click(function () {
        var checkStatus = table.checkStatus('corpusListTable'),
            data = checkStatus.data
        var ids = "";
        if (data.length > 0) {
            for (var i in data) {
                ids = ids + "," + data[i].id
            }
            console.log(ids)
            layer.confirm('确定删除选中的数据？', {icon: 3, title: '提示信息'}, function (index) {
                $.post("../../corpus/delAll", {
                    ids: ids //将需要删除的newsId作为参数传入
                }, function (data) {
                    // tableIns.reload();
                    layer.close(index);
                    window.location.reload()
                })
            })
        } else {
            layer.msg("请选择需要删除的语料");
        }

    })

    //列表操作
    table.on('tool(corpusList)', function (obj) {
        var layEvent = obj.event,
            data = obj.data;
        if (layEvent === 'usable') { //启用禁用
            var _this = $(this),
                usableText = "是否确定禁用此设备？",
                btnText = "已禁用";
            if (_this.text() == "已禁用") {
                usableText = "是否确定启用此设备？",
                    btnText = "已启用";
            }
            layer.confirm(usableText, {
                icon: 3,
                title: '系统提示',
                cancel: function (index) {
                    layer.close(index);
                }
            }, function (index) {
                _this.text(btnText);
                layer.close(index);
            }, function (index) {
                layer.close(index);
            });
        } else if (layEvent === 'del') { //删除
            layer.confirm('确定删除此设备？', {icon: 3, title: '提示信息'}, function (index) {
                $.post("../../corpus/del", {
                    id: data.id  //将需要删除的newsId作为参数传入
                }, function (data) {
                    tableIns.reload();
                    layer.close(index);
                })
            });
        } else if (layEvent === 'check') {
            layer.open({
                title: '审核这条录音'
                , content: '合格请选择通过，不合格选择不通过'
                , btn: ['通过', '不通过', '取消']
                , btn1: function (index, layero) {
                    $.post("../../corpus/check", {
                        id: data.id,
                        status: 1
                    }, function (data) {
                        tableIns.reload();
                        layer.close(index);
                    })
                    parent.layer.close(index);
                }
                , btn2: function (index, layero) {
                    $.post("../../corpus/check", {
                        id: data.id,
                        status: 2
                    }, function (data) {
                        tableIns.reload();
                        layer.close(index);
                    })
                }
                , cancel: function () {
                }
            });
        }
    });

})
