layui.use(['form','layer'],function(){
    var form = layui.form
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery;

    form.on("submit(addUser)",function(data){
        //弹出loading
        var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});

        // 实际使用时的提交信息
        $.post("../../role/add",{
            id : $("#roleId").val(),  //登录名
            name : $("#roleName").val(),  //
            description : $("#description").val(),  //
        },function(res){
            // 请求成功 刷新页面
            setTimeout(function(){
                top.layer.close(index);
                top.layer.msg("添加成功！");
                layer.closeAll("iframe");
                //刷新父页面
                parent.location.reload();
            },500);
        })

        return false;
    })
// fan返回上页
//     form.on("submit(cancel)",function(data){
//         window.history.back(-2);
//         // setTimeout(function(){
//         //     top.layer.close(index);
//         //   //  top.layer.msg("用户添加成功！");
//         //     layer.closeAll("iframe");
//         //     //刷新父页面
//         //     parent.location.reload();
//         // },2000);
//         return false;
//     })
    /*
    先给select赋值：$("#...").val(val);
然后根据自己的情况渲染下
form.render(); //更新全部
form.render('select'); //刷新select选择框渲染
form.render(null, 'test1'); //更新 lay-filter="test1" 所在容器内的全部表单状态
form.render('select', 'test2'); //更新 lay-filter="test2" 所在容器内的全部 select 状态
     */
    // var resultData;
    //
    // var htmls = '<option value="-1">请选择</option>'; //全局变量
    // $.ajax({
    //     url: '../../role/getlist',
    //     type: "post",
    //     dataType : "json",
    //     contentType : "application/json",
    //     async: false,//这得注意是同步
    //     success: function (result) {
    //         resultData = result ;
    //        debugger;
    //         for(var x in resultData){
    //             htmls += '<option value = "' + resultData[x].id + '">' + resultData[x].name + '</option>'
    //         }
    //         $("#roles").html(htmls);
    //     }
    // });
    // $("#roles").val(    $("#roleId").val()  );
    // form.render('select');//需要渲染一下


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