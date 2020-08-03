layui.use(['form','layer','table','laytpl'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table,
        upload = layui.upload;

    //用户列表
    var tableIns = table.render({
        elem: '#sentenceList',
      //  url : '../../json/userList.json',
        url : '../../../sentence/getlist',
        cellMinWidth : 95,
        page : true,
        height : "full-125",
        limits : [5,10,15,20,25,500,1000],
        limit : 10,
        id : "sentenceListTable",
        toolbar: '#toolbar',
        cols : [[
            {type: "checkbox", fixed:"left", width:50},
            {field: 'sentence', title: '语句', minWidth:100, align:"center"},
            {field: 'create_time', title: '创建日期', minWidth:50, align:"center"},
            {title: '操作', minWidth:175, templet:'#sentenceListBar',fixed:"right",align:"center"}
        ]]
    });

    form.on('submit(sentenceSearch)',function (data) {
        //alert(data.field);
        table.reload("sentenceListTable", {
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

    //编辑
    function editSentence(edit){
        var index = layui.layer.open({
            title : "编辑语料",
            type : 2,
            content : "sentenceEdit.html",
            success : function(layero, index){
                var body = layui.layer.getChildFrame('body', index);
                body.find("#id").val(edit.id);
                body.find("#sentence").val(edit.sentence);  //设备名称
                  //设备名称
                form.render();
                setTimeout(function(){
                    layui.layer.tips('点击此处返回列表', '.layui-layer-setwin .layui-layer-close', {
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

    //添加
    function addSentence(){
        var index = layui.layer.open({
            title : "添加语料",
            type : 2,
            content : "sentenceAdd.html",
            success : function(layero, index){
                setTimeout(function(){
                    layui.layer.tips('点击此处返回列表', '.layui-layer-setwin .layui-layer-close', {
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
    $(".addSentence").click(function(){
        addSentence();
    })

    //批量删除
    $(".delAll_btn").click(function () {
        var checkStatus = table.checkStatus('sentenceListTable'),
            data = checkStatus.data
        var ids="";
        if (data.length > 0) {
            for (var i in data) {
                ids=ids+","+data[i].id
            }
            console.log(ids)
            layer.confirm('确定删除选中的数据？', {icon: 3, title: '提示信息'}, function (index) {
                $.post("../../sentence/delAll", {
                    ids: ids //将需要删除的newsId作为参数传入
                }, function (data) {
                    //tableIns.reload();
                    layer.close(index);
                    window.location.reload();
                })
            })
        } else {
            layer.msg("请选择需要删除的设备");
        }
    })

    //列表操作
    table.on('tool(sentenceList)', function(obj){
        var layEvent = obj.event,
            data = obj.data;

        if (layEvent==='add'){
            addSentence();
        }
        else if(layEvent === 'edit'){ //编辑
            editSentence(data);
        }else if(layEvent === 'usable'){ //启用禁用
            var _this = $(this),
                usableText = "是否确定禁用此设备？",
                btnText = "已禁用";
            if(_this.text()=="已禁用"){
                usableText = "是否确定启用此设备？",
                btnText = "已启用";
            }
            layer.confirm(usableText,{
                icon: 3,
                title:'系统提示',
                cancel : function(index){
                    layer.close(index);
                }
            },function(index){
                _this.text(btnText);
                layer.close(index);
            },function(index){
                layer.close(index);
            });
        }else if(layEvent === 'del'){ //删除
            layer.confirm('确定删除此设备？',{icon:3, title:'提示信息'},function(index){
                $.post("../../sentence/del",{
                    id : data.id  //将需要删除的newsId作为参数传入
                },function(data){
                    tableIns.reload();
                    layer.close(index);
                })
            });
        }else if(layEvent==='check'){
            layer.open({
                title: '审核这条录音'
                ,content: '合格请选择通过，不合格选择不通过'
                ,btn: ['通过', '不通过', '取消']
                ,yes: function(index, layero){
                    alert("通过")
                    //按钮【按钮一】的回调
                    return true;
                }
                ,btn2: function(index, layero){
                    //按钮【按钮二】的回调
                    alert("不通过")
                    //return false 开启该代码可禁止点击该按钮关闭
                }
                ,btn3: function(index, layero){
                    //按钮【按钮三】的回调
                    alert("取消")
                    //return false 开启该代码可禁止点击该按钮关闭
                }
                ,cancel: function(){
                    //右上角关闭回调

                    //return false 开启该代码可禁止点击该按钮关闭
                }
            });
        }
    });

})
