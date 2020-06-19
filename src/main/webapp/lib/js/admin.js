layui.use('element', function() {
    let element = layui.element; //导航的hover效果、二级菜单等功能，需要依赖element模块

    //监听导航点击
    element.on('nav(demo)', function(elem) {

        layui.use('layer', function() {
            layer.msg(elem.text());
        })
    });
});

function exit() {
    layui.use('layer', function() {
        layer.confirm('你确定现在就要退出吗?', { title: '提示', icon: 3, scrollbar: false }, function(index) {
            window.location.href = './exit';
            layer.close(index);
        });
    });
}

function show_info() {
    $.ajax({
        type: "post",
        dataType: "json",
        url: './adminservlet?action=query_teacherInfo',
        data: '',
        success: function(info) {
            let html = "<h1 style='text-align: center'>个人信息</h1>" +
                "<table class='layui-table' lay-even lay-skin='nob' lay-size='lg' >" +
                "<tbody style='font-size: 30px'>" +
                "<tr>" + "<td>id: </td>" + "<td>" + info.tno + "</td>" + "</tr>" +
                "<tr>" + "<td>name: </td>" + "<td>" + info.tname + "</td>" + "</tr>" +
                "<tr>" + "<td>sex: </td>" + "<td>" + info.tsex + "</td>" + "</tr>" +
                "<tr>" + "<td>birthday: </td>" + "<td>" + info.birthday + "</td>" + "</tr>" +
                "<tr>" + "<td>job: </td>" + "<td>" + info.president + "</td>" + "</tr>" +
                "<tr>" + "<td>department: </td>" + "<td>" + info.dno + "</td>" + "</tr></tbody></table>";
            $('#content').get(0).innerHTML = html;

        }
    })
}

function show_courseInfo() {


    let html_table1 = "<h1 style='text-align: center'>课程计划</h1>" +
        "<table id='table1' lay-filter='test' width='800px'></table>" +
        "<hr /><h1 style='text-align: center'>课程</h1>" +
        "<table id='table2' lay-filter='test2' width='800px'></table>";
    $('#content').get(0).innerHTML = html_table1;
    layui.use('table', function() {
        var table = layui.table;


        table.render({
            elem: '#table1',
            page: true,
            limit: 5,
            limits: [5, 10, 15, 20],
            height: 250,
            url: './adminservlet',
            cols: [
                [ //表头
                    { field: 'cno', title: '课号', width: 200, sort: true, fixed: 'left' }, { field: 'ccode', edit: 'text', title: '课程代码', width: 200 }, { field: 'startdate', edit: 'text', title: '授课时间', width: 200 },
                    { fixed: 'right', title: '操作', width: 200, align: 'center', toolbar: '#con' }
                ]
            ],
            where: { 'action': 'query_courseInfo' }

        });




        table.render({
            elem: '#table2',
            page: true,
            limit: 5,
            limits: [5, 10, 15, 20],
            height: 250,
            url: './adminservlet',
            cols: [
                [ //表头
                    { field: 'ccode', title: '课程代码', width: 200, sort: true, fixed: 'left' }, { field: 'cname', edit: 'text', title: '课程名称', width: 200 }, { field: 'credit', edit: 'text', title: '学分', width: 200 },
                    { fixed: 'right', title: '操作', width: 200, align: 'center', toolbar: '#con' }
                ]
            ],
            where: { 'action': 'query_courseDetail' }

        });
        table.on('tool(test2)', function(obj) {
            const data_ = obj.data,
                layEvent = obj.event; //获取操作、数据
            if (layEvent === 'del') {
                layer.confirm('真的删除行么', function(index) {
                    layer.close(index);
                    $.ajax({
                        type: 'post',
                        data: data_,
                        url: './adminservlet?action=update_deleteCourse',
                        dataType: 'json',
                        success: function(status) {
                            if (status.code === "200") {
                                layui.use('layer', function() {
                                    layer.msg('删除成功', { icon: 1 });
                                })
                                obj.del(); //删除对应行（tr）的DOM结构
                            } else {
                                layui.use('layer', function() {
                                    layer.msg('删除失败，该课程已归入课程计划', { icon: 0 });
                                })
                            }
                        }
                    })

                });
            } else if (layEvent === 'store') {
                $.ajax({
                    type: 'post',
                    data: data_,
                    url: './adminservlet?action=update_updateCourse',
                    dataType: 'json',
                    success: function(status) {
                        if (status.code === "200") {
                            layui.use('layer', function() {
                                layer.msg('修改成功', { icon: 1 });
                            });
                        } else {
                            layui.use('layer', function() {
                                layer.msg('修改失败', { icon: 0 });
                            })
                        }
                    }
                })
            }
        });







        table.on('tool(test)', function(obj) {
            const data_ = obj.data,
                layEvent = obj.event; //获取操作、数据
            if (layEvent === 'del') {
                layer.confirm('真的删除行么', function(index) {
                    layer.close(index);
                    $.ajax({
                        type: 'post',
                        data: data_,
                        url: './adminservlet?action=update_deleteCourseplan',
                        dataType: 'json',
                        success: function(status) {
                            if (status.code === "200") {
                                layui.use('layer', function() {
                                    layer.msg('删除成功', { icon: 1 });
                                })
                                obj.del(); //删除对应行（tr）的DOM结构
                            } else {
                                layui.use('layer', function() {
                                    layer.msg('删除失败，该课号已在开课', { icon: 0 });
                                })
                            }
                        }
                    })

                });
            } else if (layEvent === 'store') {
                $.ajax({
                    type: 'post',
                    data: data_,
                    url: './adminservlet?action=update_updateCourseplan',
                    dataType: 'json',
                    success: function(status) {
                        if (status.code === "200") {
                            layui.use('layer', function() {
                                layer.msg('修改成功', { icon: 1 });
                            });
                        } else {
                            layui.use('layer', function() {
                                layer.msg('修改失败，该课程不存在/请增加课程', { icon: 0 });
                            })
                        }
                    }
                })
            }
        });




    });





}

function addcourse() {

    let courseform = "<h1>增加课程</h1>" +
        "<div class='layui-form-item'>" +
        "<label class='layui-form-label'>课程代码</label>" +
        "<div class='layui-input-block'>" +
        "<input type='text' name='ccode'  class='layui-input'  placeholder='请输入课程代码' autocomplete='off'> " +
        "</div></div>" +
        "<div class='layui-form-item'>" +
        "<label class='layui-form-label'>课程名称</label>" +
        "<div class='layui-input-block'>" +
        "<input type='text' name='cname'  class='layui-input'  placeholder='请输入课程名称' autocomplete='off'> " +
        "</div></div>" +
        "<div class='layui-form-item'>" +
        "<label class='layui-form-label'>课程学分</label>" +
        "<div class='layui-input-block'>" +
        "<input type='text' name='credit'  class='layui-input'  placeholder='请输入课程学分' autocomplete='off'> " +
        "</div></div>" +
        "<div class='layui-form-item'>" +
        "<div class='layui-input-block'>" +
        "<button class='layui-btn'  id='submitdata' onclick='add(\"addcourse\")' >增加</button>" +
        "</div></div>";
    $('#content').get(0).innerHTML = courseform;

}



function addcourseplan() {

    let courseform = "<h1>增加课程计划</h1>" +
        "<div class='layui-form-item'>" +
        "<label class='layui-form-label'>课号</label>" +
        "<div class='layui-input-block'>" +
        "<input type='text' name='cno'  class='layui-input'  placeholder='请输入课号' > " +
        "</div></div>" +
        "<div class='layui-form-item'>" +
        "<label class='layui-form-label'>课程代码</label>" +
        "<div class='layui-input-block'>" +
        "<input type='text' name='ccode'  class='layui-input'  placeholder='请输入课程代码' > " +
        "</div></div>" +
        "<div class='layui-form-item'>" +
        "<label class='layui-form-label'>授课时间</label>" +
        "<div class='layui-input-block'>" +
        "<input type='text' name='startdate'  class='layui-input'  placeholder='请输入授课时间' > " +
        "</div></div>" +
        "<div class='layui-form-item'>" +
        "<div class='layui-input-block'>" +
        "<button class='layui-btn'  id='submitdata' onclick='add(\"addcourseplan\")' >增加</button>" +
        "</div></div>";
    $('#content').get(0).innerHTML = courseform;

}

function add(name) {
    let data;
    if (name === 'addcourse') {
        var ccode = $('input[name=ccode]').get(0).value;
        var cname = $('input[name=cname]').get(0).value;
        var credit = $('input[name=credit]').get(0).value;
        data = {
            ccode: ccode,
            cname: cname,
            credit: credit
        };
        $(function () {
            $.ajax({
                type: "post",
                dataType: "json",
                url: './adminservlet?action=addcourse',
                data: data,
                success: function (data) {
                    if(data.code === "200"){
                        layer.msg("添加成功",{icon:1});
                    }else
                        layer.msg("添加失败",{icon:2});
                }

            })

        })
    } else if (name === "addcourseplan") {
        var cno = $('input[name=cno]').get(0).value;
        var ccode = $('input[name=ccode]').get(0).value;
        var startdata = $('input[name=startdate]').get(0).value;
        data = {
            cno: cno,
            ccode: ccode,
            startdata: startdata
        };

        //增加课程计划
        $(function () {
            $.ajax({
                type: "post",
                dataType: "json",
                url: './adminservlet?action=addcourseplan',
                data: data,
                success: function (data) {
                    if(data.code === "200"){
                        layer.msg("添加成功",{icon:1});
                    }else
                        layer.msg("添加失败",{icon:2});
                }

            })

        })

    }
}

function changeprofile() {
    let html = "<h1>修改个人信息</h1>"+
        "<div class='layui-form-item'>" +
        "<label class='layui-form-label'>生日:</label>" +
        "<div class='layui-input-block'>" +
        "<input type='date' name='birthday'  class='layui-input'  placeholder='请输入生日' > " +
        "</div></div>" +
        "<div class='layui-form-item'>" +
        "<div class='layui-input-block'>" +
        "<button class='layui-btn'  id='submitdata' onclick='changeinfoButton(\"changebirthday\")' >修改</button>" +
        "</div></div>"+
        "<div class='layui-form-item'>" +
        "<label class='layui-form-label'>新密码:</label>" +
        "<div class='layui-input-block'>" +
        "<input type='password' name='password'  class='layui-input'  placeholder='请输入新密码' > " +
        "</div></div>" +
        "<div class='layui-form-item'>" +
        "<label class='layui-form-label'>重复密码:</label>" +
        "<div class='layui-input-block'>" +
        "<input type='password' name='repassword'  class='layui-input'  placeholder='请再次输入密码' > " +
        "</div></div>" +
        "<div class='layui-form-item'>" +
        "<div class='layui-input-block'>" +
        "<button class='layui-btn'  id='submitdata' onclick='changeinfoButton(\"changepassword\")' >修改</button>" +
        "</div></div>";

    $('#content').get(0).innerHTML = html;

}
function changeinfoButton(name) {
 let data,flag=false;
 if(name === 'changebirthday'){
     let birthday = $('input[name=birthday]').get(0).value;
     if(birthday === ""){
         layer.msg("输入不能为空",{icon:2});
         return false;
     }
     data={birthday:birthday};

 }else if(name === 'changepassword'){
     let password =  $('input[name=password]').get(0).value;
     let repassword =  $('input[name=repassword]').get(0).value;

     if(password === "" || repassword === ""){
         layer.msg("密码不能为空",{icon:2});
         return false;
     }else if(password !== repassword){
         layer.msg("两次输入的密码不一致",{icon:2});
         return false;
     }else if (password === repassword) {
         data={password:password};
     }
 }
 $(function () {
     $.ajax({
         type: "post",
         dataType: "json",
         url: './admininfo?action='+name,
         data:data,
         success:function (data) {
             if (data.code === "200"){
                 layer.msg("修改成功,5秒后会跳转到登陆页面...",{icon:1});
                 setTimeout(function () {
                     window.location.href="http://localhost/test/login.jsp";

                 },5000)
             }else {
                 layer.msg("修改失败",{icon:2});
             }
         }
     });

 })
}