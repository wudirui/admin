layui.use(['form','layer','jquery'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer
        $ = layui.jquery;
    var  user;
    $(".loginBody .seraph").click(function(){
        layer.msg("这只是做个样式，至于功能，你见过哪个后台能这样登录的？还是老老实实的找管理员去注册吧",{
            time:5000
        });
    })

    //登录按钮
    form.on("submit(login)",function(data){
      //  $(this).text("登录中...").attr("disabled","disabled").addClass("layui-disabled");
        $.post("user/login",{
            userName : $("#userName").val(),  //登录名
            passWord : $("#password").val()  //密码

        },function(res){

            if(res.code == -1){ // 登录失败
                layer.msg(res.msg);
                // var index = layer.open({
                //     type : 2,
                //     title : "登录异常",
                //     maxmin : true,
                //     offset: '100px',
                //     area : [ '600px', '500px' ],
                //     content :'page/403.html'// iframe的url
                // });
            }else {
                debugger;
                var  userName = res.data.loginUser.realName;
                var roleName = res.data.role.name;
                window.sessionStorage.setItem("userName",userName);
                window.sessionStorage.setItem("roleName",roleName);
                window.location.href = "/index.html";

            }
        })

        return false;
    })

    //表单输入效果
    $(".loginBody .input-item").click(function(e){
        e.stopPropagation();
        $(this).addClass("layui-input-focus").find(".layui-input").focus();
    })
    $(".loginBody .layui-form-item .layui-input").focus(function(){
        $(this).parent().addClass("layui-input-focus");
    })
    $(".loginBody .layui-form-item .layui-input").blur(function(){
        $(this).parent().removeClass("layui-input-focus");
        if($(this).val() != ''){
            $(this).parent().addClass("layui-input-active");
        }else{
            $(this).parent().removeClass("layui-input-active");
        }
    })
})
