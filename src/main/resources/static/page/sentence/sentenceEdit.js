layui.use(['form', 'layer'], function () {
    form = layui.form;
    $ = layui.jquery;
    var layer = parent.layer === undefined ? layui.layer : top.layer

    form.on("submit(editSentence)", function (data) {
        //弹出loading
        var index = top.layer.msg('数据提交中，请稍候', {icon: 16, time: false, shade: 0.8});

        // 实际使用时的提交信息
        $.post("../../sentence/add", {
            id: $("#id").val(),  //
            sentence: $("#sentence").val(),  //
        }, function (res) {

        })
        setTimeout(function () {
            top.layer.close(index);
            top.layer.msg("用户添加成功！");
            layer.closeAll("iframe");
            //刷新父页面
            parent.location.reload();
        }, 2000);
        return false;
    })

})