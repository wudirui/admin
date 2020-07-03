layui.use(['form', 'layer'], function () {
    let form = layui.form;
    let $ = layui.jquery;
    let layer = parent.layer === undefined ? layui.layer : top.layer;
    let baseUrl = '../../../';
    form.on("submit(edit)", function (data) {
        //弹出loading
        let index = top.layer.msg('数据提交中，请稍候', {icon: 16, time: false, shade: 0.8});
        // 实际使用时的提交信息
        $.post(baseUrl+"menu/add", {
            id: $("#menuId").val(),
            menuName: $("#name").val(),
            menuType: $("#type").val(),
            parentId: $("#parentName").val(),
            menuUrl: $("#menuUrl").val(),
        }, function (res) {
            setTimeout(function () {
                top.layer.close(index);
                top.layer.msg("用户更新成功！");
                layer.closeAll("iframe");
                //刷新父页面
                parent.location.reload();
            }, 2000);
        })
        return false;
    })

})