var form, $,areaData;
layui.config({
    base : "../../js/"
}).extend({
    "address" : "address"
})
layui.use(['form','layer','upload','laydate',"address"],function(){
    // var form = layui.form
    //     layer = parent.layer === undefined ? layui.layer : top.layer,
    //         laydate=layui.laydate,
    //         upload = layui.upload;
    //     $ = layui.jquery;

    form = layui.form;
    $ = layui.jquery;
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        upload = layui.upload,
        laydate = layui.laydate,
        address = layui.address;

    var imgUrl;
    //上传头像
    upload.render({
        elem: '.userFaceBtn',
        url: '../../facility/savefile',
      //  method : "get",  //此处是为了演示之用，实际使用中请将此删除，默认用post方式提交
        done: function(res, index, upload){
        //    var num = parseInt(4*Math.random());  //生成0-4的随机数，随机显示一个头像信息
            $('#userFace').attr('src',res.data.src);
            imgUrl = res.data.src;
            window.sessionStorage.setItem('userFace',res.data.src);// 全局变量？？？？

        }
    });


    //执行一个laydate实例
    laydate.render({
        elem: '#leaveDate', //指定元素
        type:'datetime'
    });
    //

    form.on("submit(addUser)",function(data){
        //弹出loading
        var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});

        // 实际使用时的提交信息
        $.post("../../facility/add",{
            id:$("#facilityId").val(),  //
            name : $("#facilityName").val(),  //
            type : $("#facilityType").val(),  //
            leaveDate : $("#leaveDate").val(),  //
            uid:$("#roles").val(),
            imgUrl:imgUrl

        },function(res){

        })
        setTimeout(function(){
            top.layer.close(index);
            top.layer.msg("用户添加成功！");
            layer.closeAll("iframe");
            //刷新父页面
            parent.location.reload();
        },2000);
        return false;
    })

    //
    var resultData;

    var htmls = '<option value="-1">请选择</option>'; //全局变量
    $.ajax({
        url: '../../user/getalluser',
        type: "post",
        dataType : "json",
        contentType : "application/json",
        async: false,//这得注意是同步
        success: function (result) {
            resultData = result ;
            for(var x in resultData){
                htmls += '<option value = "' + resultData[x].id + '">' + resultData[x].realName + '</option>'
            }
            $("#roles").html(htmls);
        }
    });
    $("#roles").val(    $("#userId").val()  );
    form.render('select');//需要渲染一下
    //
    //格式化时间
    function filterTime(val){
        if(val < 10){
            return "0" + val;
        }else{
            return val;
        }
    }
    //定时发布
    var time = new Date();
    var submitTime = time.getFullYear()+'-'+filterTime(time.getMonth()+1)+'-'+filterTime(time.getDate())+' '+filterTime(time.getHours())+':'+filterTime(time.getMinutes())+':'+filterTime(time.getSeconds());

})