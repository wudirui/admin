layui.use(['form','layer','table','laytpl','upload'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laytpl = layui.laytpl,
        table = layui.table;
        upload = layui.upload;
    //用户列表
    var tableIns = table.render({
        elem: '#userList',
      //  url : '../../json/userList.json',
        url : '../../../user/getuserlist',
        cellMinWidth : 95,
        page : true,
        height : "full-125",
        limits : [10,15,20,25],
        limit : 10,
        id : "userListTable",
        cols : [[


            {type: "checkbox", fixed:"left", width:50},
         //   {field: 'uid', title: '用户id', minWidth:100, align:"center"},
            {field: 'userName', title: '用户名', minWidth:100, align:"center"},
        //    {field: 'passWord', title: '密码', minWidth:100, align:"center"},
            {field: 'realName', title: '姓名', minWidth:100, align:"center"},
            {field: 'sex', title: '性别',  align:'center',templet:function(d){
                return d.sex == "0" ? "男" : "女";
            }},
            {field: 'phone', title: '电话', minWidth:100, align:"center"},
            {field: 'dept', title: '所属单位', minWidth:100, align:"center"},
            {field: 'address', title: '地址', minWidth:100, align:"center"},

            {field: 'rName', title: '用户级别', minWidth:100, align:"center"},
            // {field: 'passWord', title: '用户邮箱', minWidth:200, align:'center',templet:function(d){
            //     return '<a class="layui-blue" href="mailto:'+d.userEmail+'">'+d.userEmail+'</a>';
            // }},
            // {field: 'userSex', title: '用户性别', align:'center'},
            // {field: 'userStatus', title: '用户状态',  align:'center',templet:function(d){
            //     return d.userStatus == "0" ? "正常使用" : "限制使用";
            // }},
            // {field: 'userGrade', title: '用户等级', align:'center',templet:function(d){
            //     if(d.userGrade == "0"){
            //         return "注册会员";
            //     }else if(d.userGrade == "1"){
            //         return "中级会员";
            //     }else if(d.userGrade == "2"){
            //         return "高级会员";
            //     }else if(d.userGrade == "3"){
            //         return "钻石会员";
            //     }else if(d.userGrade == "4"){
            //         return "超级会员";
            //     }
            // }},
            // {field: 'userEndTime', title: '最后登录时间', align:'center',minWidth:150},
            {title: '操作', minWidth:175, templet:'#userListBar',fixed:"right",align:"center"}
        ]]
    });

    // 页面准备完毕 就绑定 上传插件
    upload.render({
        elem: '#uploadExcel'
        ,url: '../../user/import'
        ,accept: 'file' //普通文件
        ,exts: 'xls|xlsx' //允许上传的文件后缀  |xlsx |
        ,multiple: true
        ,done: function(res){
            console.log(res);
        }
    });

    //搜索【此功能还没完成】
    $(".search_btn").on("click",function(){
        if($(".searchVal").val() != ''){
            table.reload("newsListTable",{
                page: {
                    curr: 1 //重新从第 1 页开始
                },
                where: {
                    key: $(".searchVal").val()  //搜索的关键字
                }
            })
        }else{
            layer.msg("请输入搜索的内容");
        }
    });

    //添加用户
    function addUser(edit){
        var index = layui.layer.open({
            title : "添加用户",
            type : 2,
            content : "userAdd.html",
            success : function(layero, index){
                var body = layui.layer.getChildFrame('body', index);
                if(edit){
                    body.find(".userId").val(edit.uid);  //登录名
                    body.find(".userName").val(edit.userName);  //登录名
                //    body.find("#email").val(edit.email);  //邮箱
                    body.find(".userSex input[value="+edit.sex+"]").prop("checked","checked");  //性别
                    body.find("#roleId").val(edit.rid);  //邮箱
                    body.find("#phone").val(edit.phone);  //邮箱
                    body.find("#address").val(edit.address);  //邮箱
                    body.find("#dept").val(edit.dept);  //邮箱
                    body.find("#realName").val(edit.realName);  //邮箱
                  //  body.find("#roleId").val(edit.rid);  //邮箱
/*

$("#atype").val($('#typename').val());//atype是select的id
form.render('select');
 */
                    form.render();
                }
                setTimeout(function(){
                    layui.layer.tips('点击此处返回用户列表', '.layui-layer-setwin .layui-layer-close', {
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
    $(".addNews_btn").click(function(){
        addUser();
    })
    // 到处Excel       import_btn
    $(".export_btn").click(function(){
        // 导出
        var form = $('<form></form>');
        // 设置属性
        form.attr('action', '../../user/export');//  请求后台的URL
        form.attr('method', 'POST');
        form.attr('target', '_blank');
        form.css('display','none');
        var param = getParams();
        if(param) {
            for(var i in param) {
                var input = $('<input type="text" name="'+i+'" value="'+param[i]+'" />');
                form.append(input);
            }
        }
        form.appendTo("body")
        // 提交表单
        form.submit();
        //删除表单
        form.remove();
        //
        // 实际使用时的提交信息
        // $.post("../../user/export",{
        //     id : $("#userId").val(),  //登录名
        //     userName : $("#userName").val(),  //登录名
        //     realName : $("#realName").val(),  //登录名
        //
        // },function(res){
        //
        //     //
        //
        //     setTimeout(function(){
        //
        //         top.layer.msg("用户导出成功！");
        //         layer.closeAll("iframe");
        //         //刷新父页面
        //         parent.location.reload();
        //     },1000);
        // })

    })



    // 获取参数
    function getParams() {
        var queryParam = {
         //   branchReqOrderNumber : branchReqOrderNumber,
           // reqOrderName : reqOrderName,

        }
        return queryParam;
    }

    //批量删除
    $(".delAll_btn").click(function(){
        var checkStatus = table.checkStatus('userListTable'),
            data = checkStatus.data,
            userIds = [];
        if(data.length > 0) {
            for (var i in data) {
                userIds.push(data[i].uid);
            }
            layer.confirm('确定删除选中的用户？', {icon: 3, title: '提示信息'}, function (index) {

                console.log(userIds);
                $.post("../../user/batchdel",{
                    ids : userIds.join(',') //将需要删除的userIds作为参数传入
                },function(data){
                    tableIns.reload();
                    layer.close(index);
                })

            })
        }else{
            layer.msg("请选择需要删除的用户");
        }
    })

    //列表操作
    table.on('tool(userList)', function(obj){
        var layEvent = obj.event,
            data = obj.data;

        if(layEvent === 'edit'){ //编辑
            addUser(data);
        }else if(layEvent === 'usable'){ //启用禁用
            var _this = $(this),
                usableText = "是否确定解除此用户的设备？",
                btnText = "已解除";
            // if(_this.text()=="已禁用"){
            //     usableText = "是否确定启用此用户？",
            //     btnText = "已启用";
            // }
            layer.confirm(usableText,{
                icon: 3,
                title:'系统提示',
                cancel : function(index){
                    layer.close(index);
                }
            },function(index){
                $.post("../../facility/untying",{  //解绑此用户的  设备
                    id : data.uid  //将 Id作为参数传入
                },function(data){
                 //   tableIns.reload();
                    // layer.close(index);

                    _this.text(btnText);
                    layer.close(index);
                })


            } );
        }else if(layEvent === 'del'){ //删除
            layer.confirm('确定删除此用户？',{icon:3, title:'提示信息'},function(index){
                 $.post("../../user/del",{
                     id : data.uid  //将需要删除的newsId作为参数传入
                 },function(data){
                    tableIns.reload();
                    layer.close(index);
                 })
            });
        }
    });

})
