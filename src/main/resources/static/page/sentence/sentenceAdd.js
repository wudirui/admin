layui.use(["jquery","element", "laypage", "layer", "upload"], function () {
    var element = layui.element;
    var laypage = layui.laypage;
    var layer = layui.layer;
    var upload = layui.upload;//主要是这个
    var $ = layui.$ //重点处

    upload.render({
        elem: '.fileUpload'
        , url: '../../sentence/fileUpload'
        , accept: 'file' //普通文件
        , exts: 'xls|xlsx' //允许上传的文件后缀  |xlsx |
        , multiple: true
        , auto: false
        , bindAction: '#addSentence'
        , done: function (ret, index) {
            setTimeout(function () {
                top.layer.close(index);
                top.layer.msg("导入成功！");
                layer.closeAll("iframe");
                //刷新父页面
                parent.location.reload();
            }, 2000);
        }
    });
    $("#cancel").click(function(){
        var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
        parent.layer.close(index); //再执行关闭
    })
})