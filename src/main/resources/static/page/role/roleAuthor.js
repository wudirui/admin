layui.use(['form','layer'],function(){
    var form = layui.form
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery;


    var functional=""; // 所有选中的 资源id
    form.on("submit(addUser)",function(data){

        //获取复选框选中的值
        var groupCheckbox=$("input[name='per']");

        for( var i=0;i<groupCheckbox.length;i++){
            if(groupCheckbox[i].checked){
                var val =groupCheckbox[i].value;
                if(functional.length>0){
                    functional+=","+val;
                }else{
                    functional+=val;
                }

            }
        }

        //弹出loading
        var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});

        // 实际使用时的提交信息
        $.post("../../role/roleauthor",{
            id : $("#roleId").val(),  //登录名
            permissionIds:functional
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

        ///



        ///


        return false;
    })

 //  动态渲染 复选框
    var resultData;
    var htmls = ''; //全局变量
    var p1="用户中心---",p2="系统设置---",p3="设备管理---",p4="使用文档---";
    $.ajax({
        url: '../../role/getallpermission',
        type: "post",
        dataType : "json",
        contentType : "application/json",
        async: false,//这得注意是同步
        success: function (result) {
            resultData = result ;
           debugger;
            for(var i=0;i<resultData.length;i++){
             var re =   resultData[i] ;
             if(re.pid ==1){
                 p1  +=   '<input type="checkbox" name="per" value="'+ re.id+'" title="'+ re.name+'"  lay-skin="switch">'+ re.name;
             }
                if(re.pid ==2){
                    p2  +=    '<input type="checkbox" name="per" value="'+ re.id+'" title="'+ re.name+'"  lay-skin="switch">'+ re.name;
                }
                if(re.pid ==3){
                    p3  +=   '<input type="checkbox" name="per" value="'+ re.id+'" title="'+ re.name+'"  lay-skin="switch">'+ re.name;
                }
                if(re.pid ==4){
                    p4  +=   '<input type="checkbox" name="per" value="'+ re.id+'" title="'+ re.name+'"  lay-skin="switch">'+ re.name;
                }

            }


            $("#permission1").html(p1);
            $("#permission2").html(p2);
            $("#permission3").html(p3);
            $("#permission4").html(p4);
            form.render('checkbox');//需要渲染一下
        }
    });
    // 实际使用时的提交信息
    $.post("../../role/getpbyrid",{
        id : $("#roleId").val() //登录名

    },function(result){
        debugger;
        //获取复选框选
        var groupCheckbox=$("input[name='per']");
        var functional="";
        for( var i=0;i<result.length;i++){
            // 根据id 绑定 check
            $("input[name='per'][value='"+result[i].id+"']").prop("checked", "checked");
        //    $("input[name='per'][value='"+result[i].id+"']").attr("checked", true);
            console.log(result[i].id);

        }

        form.render('checkbox');//需要渲染一下
    })



 // eletree
 //
 //    var el3=eleTree.render({
 //        elem: '.ele3',
 //      //  data: data,
 //       url: "../../../eleTree/eleTree/tree.json",
 //        showCheckbox: true,
 //        defaultExpandAll: true,
 //        draggable: true,
 //        contextmenuList: ["copy","add","edit","remove"]
 //    });
 //    eleTree.on("nodeClick(data3)",function(d) {
 //        console.group("节点点击nodeClick:")
 //        console.log(d.data);    // 点击节点对于的数据
 //        console.log(d.event);   // event对象
 //        console.log(d.node);    // 点击的dom节点
 //        console.log(this);      // 与d.node相同
 //        console.groupEnd();
 //
 //    })
 //    eleTree.on("nodeChecked(data3)",function(d) {
 //        console.group("节点选中nodeChecked:")
 //        console.log(d.data);    // 点击节点对于的数据
 //        console.log(d.isChecked);   // input是否被选中
 //        console.log(d.node);    // 点击的dom节点
 //        console.log(this);      // input对于的dom
 //        console.groupEnd();
 //    })
 //    eleTree.on("nodeContextmenu(data3)",function(d) {
 //        console.group("节点右键nodeContextmenu:")
 //        console.log(d.data);    // 点击节点对于的数据
 //        console.log(d.event);   // event对象
 //        console.log(d.node);    // 点击的dom节点
 //        console.log(this);      // 与d.node相同
 //        console.groupEnd();
 //    })
 //    eleTree.on("nodeDrag(data3)",function(d) {
 //        console.group("节点拖拽nodeDrag:")
 //        // d.stop();           // 取消拖拽
 //        console.log(d.current);    // 起始节点对应的dom和数据
 //        console.log(d.target);   // 鼠标落点对应的dom和数据
 //        console.log(this);      // 鼠标落点对应的dom
 //        console.groupEnd();
 //    })
 //
 //    eleTree.on("nodeAppend(data3)",function(d) {
 //        console.group("添加子节点nodeAppend:")
 //        console.log(d.data);    // 点击节点对于的数据
 //        console.log(d.node);    // 点击的dom节点
 //        console.log(this);      // 与d.node相同
 //        // d.stop();            // 取消添加
 //        // d.setData({          // 自定义数据
 //        //     id: 666,
 //        //     label: "aaa"
 //        // })
 //        console.log(d.newData); // 新增加的节点数据
 //        console.groupEnd();
 //    })
 //    eleTree.on("nodeInsertBefore(data3)",function(d) {
 //        console.group("添加节点之前nodeInsertBefore:")
 //        console.log(d.data);    // 点击节点对于的数据
 //        console.log(d.node);    // 点击的dom节点
 //        console.log(this);      // 与d.node相同
 //        // d.stop();            // 取消添加
 //        // d.setData({          // 自定义数据
 //        //     key: 666,
 //        //     label: "aaa"
 //        // })
 //        console.log(d.newData); // 新增加的节点数据
 //        console.groupEnd();
 //    })
 //    eleTree.on("nodeInsertAfter(data3)",function(d) {
 //        console.group("添加节点之后nodeInsertAfter:")
 //        console.log(d.data);    // 点击节点对于的数据
 //        console.log(d.node);    // 点击的dom节点
 //        console.log(this);      // 与d.node相同
 //        // d.stop();            // 取消添加
 //        // d.setData({          // 自定义数据
 //        //     key: 666,
 //        //     label: "aaa"
 //        // })
 //        console.log(d.newData); // 新增加的节点数据
 //        console.groupEnd();
 //    })
 //    eleTree.on("nodeEdit(data3)",function(d) {
 //        console.group("编辑节点nodeEdit:")
 //        console.log(d.data);        // 点击节点对于的数据
 //        console.log(d.node);        // 点击的dom节点
 //        console.log(d.value);       // 新输入的值
 //        console.log(this);          // 与d.node相同
 //        // d.stop();                // 取消编辑
 //        console.groupEnd();
 //    })
 //    eleTree.on("nodeRemove(data3)",function(d) {
 //        console.group("删除nodeRemove:")
 //        console.log(d.data);        // 点击节点对于的数据
 //        console.log(d.node);        // 点击的dom节点
 //        // d.stop();                // 取消删除
 //        console.groupEnd();
 //    })
 //




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