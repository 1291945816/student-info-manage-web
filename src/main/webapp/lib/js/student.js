layui.use('element', function(){
    let element = layui.element; //导航的hover效果、二级菜单等功能，需要依赖element模块

    //监听导航点击
    element.on('nav(demo)', function(elem){

        layui.use('layer',function () {
            layer.msg(elem.text());
        })
    });
});


function show_info() {
    $.ajax({
        type: "post",
        dataType: "json",
        url: './userservlet?action=query_studentInfo',
        data: '',
        success: function (info) {
            let html = "<h1 style='text-align: center'>个人信息</h1>"
                + "<table class='layui-table' lay-even lay-skin='nob' lay-size='lg' >"
                + "<tbody style='font-size: 30px'>"
                + "<tr>" + "<td>姓名: </td>" + "<td>" + info.sname + "</td>" + "</tr>"
                + "<tr>" + "<td>学号: </td>" + "<td>" + info.sno + "</td>" + "</tr>"
                + "<tr>" + "<td>性别: </td>" + "<td>" + info.ssex + "</td>" + "</tr>"
                + "<tr>" + "<td>出生日期: </td>" + "<td>" + info.birthday + "</td>" + "</tr>"
                + "<tr>" + "<td>班别: </td>" + "<td>" + info.clno + "</td>" + "</tr></tbody></table>";
            $('#content').get(0).innerHTML=html;




        }
    })
}
function show_department() {

    $.ajax({
        type: "post",
        dataType: "json",
        url: "./userservlet?action=query_departmentInfo",
        data: '',
        success: function (department) {
            let html1 = "<h1 style='text-align: center'>部门信息</h1>"
                + "<table class='layui-table' lay-even lay-skin='nob' lay-size='lg' >"
                + "<tbody style='font-size: 30px'>"
                + "<tr>" + "<td>部门编号: </td>" + "<td>" +department.dno+ "</td>" + "</tr>"
                + "<tr>" + "<td>部门名称: </td>" + "<td>" + department.dname + "</td>" + "</tr>"
                + "<tr>" + "<td>部门负责人: </td>" + "<td>" + department.dleader+ "</td>" + "</tr></tbody></table><hr />";
            $('#content').get(0).innerHTML=html1;
        }
    });



}

function show_classInfo() {
    $.ajax({
        type: "post",
        dataType: "json",
        url: "./userservlet?action=query_classInfo",
        data: '',
        success: function (classInfo) {
            let class_info = classInfo.class_;
            let students_info = classInfo.students;
            let teacher=classInfo.teacher;
            let html1 = "<h1 style='text-align: center'>班级信息</h1>"
                + "<table class='layui-table' lay-even lay-skin='nob' lay-size='lg' >"
                + "<tbody style='font-size: 30px'>"
                + "<tr>" + "<td>班级: </td>" + "<td>" + class_info.clno + "</td>" + "</tr>"
                + "<tr>" + "<td>部门: </td>" + "<td>" + class_info.dno + "</td>" + "</tr>"
                + "<tr>" + "<td>班主任: </td>" + "<td>" + teacher + "</td>" + "</tr></tbody></table><hr />";
            let html2 = "<h1 style='text-align: center'>班级成员</h1>"
                + "<table  lay-filter='data_parse'  >"
                +"<thead>"
                + "<tr>" +
                "<th lay-data=\"{field:'id',width: 300,sort: true}\" >" +"学号 </th>"
                +"<th lay-data=\"{field:'sname',width: 200}\">" +"姓名</th>"
                +"<th lay-data=\"{field:'ssex',width: 200}\">" +"性别</th>"
                +"</tr>"
                +"</thead><tbody>";
            students_info.forEach(function (student_info) {
                let id = student_info.sno;
                console.log(student_info.sname)
                html2=html2
                    +"<tr><td>"+id+"</td>"
                    +"<td>"+student_info.sname+"</td>"
                    +"<td>"+student_info.ssex+"</td>"
                    + "</tr>";
            });
            html2=html2+"</tbody></table>";

            $('#content').get(0).innerHTML=html1+html2;
            table_init();

        }


    })

}
function exit() {
    layui.use('layer',function (){
        layer.confirm('你确定现在就要退出吗?', {title: '提示',icon: 3,scrollbar: false},function(index){
            window.location.href='./exit';
            layer.close(index);
        });
    });
}

function show_allCourseInfo() {
    $.ajax({
        type: 'post',
        data: '',
        dataType: 'json',
        url: "./userservlet?action=query_allCourse",
        success: function (result) {
            let code = result.code;
            if(code === '500'){
                layui.use('layer',function () {
                    layer.msg('未查询到相关的课程信息', {icon: 0});
                })
            }else
            {
                let courses=result.courses;
                let html ="<h1 style='text-align: center'>课程信息</h1>"
                    + "<table  lay-filter='data_parse'  >"
                    +"<thead>"
                    + "<tr>" +
                    "<th lay-data=\"{field:'courseId ',width: 300,sort: true}\" >" +"课号 </th>"
                    +"<th lay-data=\"{field:'name',width: 300}\">" +"课程名称</th>"
                    +"<th lay-data=\"{field:'startDate ',width: 200,sort:true}\">" +"开课学期 </th>"
                    +"<th lay-data=\"{field:'credit',width: 300,sort:true}\">" +"学分 </th>"
                    +"<th lay-data=\"{field:'teacher',width: 290}\">" +"教师 </th>"
                    +"</tr>"
                    +"</thead><tbody>";
                courses.forEach(function (item) {
                    html=html+"<tr><td>"+item.cno+"</td>"
                        +"<td>"+item.cname+"</td>"
                        +"<td>"+item.startdate+"</td>"
                        +"<td>"+item.credit+"</td>"
                        +"<td>"+item.tname+"</td></tr>";
                });
                html+="</tbody></table>";
                $('#content').get(0).innerHTML=html;
                table_init();
            }
        }

    })
}
function table_init() {
    layui.use('table', function () {
        var table = layui.table;
        table.init('data_parse', {
            limit: 10,
            height: 450,
            page: true,

        })

    })
}

function show_grade() {
    $.ajax({
        type: "post",
        data: '',
        url: './userservlet?action=query_allCourseGrade',
        dataType: 'json',
        success: function (item) {
            if(item.code === "500"){
                layui.use('layer',function () {
                    layer.msg('未查询到相关的成绩信息', {icon: 0});
                })
            }else
            {
                let html1="<h1 style='text-align: center'>统计信息</h1>"
                    + "<table class='layui-table' lay-even lay-skin='nob' lay-size='lg' >"
                    + "<tbody style='font-size: 30px'>"
                    + "<tr>" + "<td>学分绩: </td>" + "<td style='color: red'>" +item.creditGrade+ "</td>" + "</tr>"
                    + "<tr>" + "<td>优秀门数: </td>" + "<td style='color: red'>" + item.greatCount + "</td>" + "</tr>"
                    + "<tr>" + "<td>及格门数: </td>" + "<td style='color: red'>" + item.passCount + "</td>" + "</tr>"
                    + "<tr>" + "<td>不及格门数: </td>" + "<td style='color: red'>" + item.failCount + "</td>" + "</tr>"
                    +"</tbody></table>";
                let grades=item.grades;

                let htm2="<h1 style='text-align: center'>各科成绩</h1>"
                    + "<table  lay-filter='data_parse'  >"
                    +"<thead>"
                    + "<tr>" +
                    "<th lay-data=\"{field:'cno ',width: 300,sort: true}\" >" +"课号 </th>"
                    +"<th lay-data=\"{field:'cname',width: 300}\">" +"课程名称</th>"
                    +"<th lay-data=\"{field:'daygrade',width: 200,sort:true}\">" +"平时成绩 </th>"
                    +"<th lay-data=\"{field:'examgrade',width: 200,sort:true}\">" +"考试成绩 </th>"
                    +"<th lay-data=\"{field:'grade',width: 290}\">" +"成绩 </th>"
                    +"</tr>"
                    +"</thead><tbody>";
                grades.forEach(function (info_grade) {

                    htm2=htm2+"<tr><td>"+info_grade.cno+"</td>"
                        +"<td>"+info_grade.cname+"</td>"
                        +"<td>"+info_grade.daygrade+"</td>"
                        +"<td>"+info_grade.examgrade+"</td>"
                        +"<td>"+info_grade.grade+"</td>"
                });
                htm2+="</tbody></table>";
                $('#content').get(0).innerHTML=html1+htm2;
                table_init();
            }

        }
    })

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
    let data;
    if(name === 'changebirthday'){
        let birthday = $('input[name=birthday]').get(0).value;
        if(birthday === ""){
            layer.msg("输入不能为空",{icon:2});
            return false;
        }
        data = {birthday: birthday};
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
            data = {password: password};
        }
    }
    $(function () {
        $.ajax({
            type: "post",
            dataType: "json",
            url: './userInfoChange?action=' + name,
            data: data,
            success: function (data) {
                if (data.code === 200) {
                    layer.msg("修改成功,5秒后会跳转到登陆页面...", {icon: 1});
                    setTimeout(function () {
                        window.location.href = "http://localhost:8080/login.jsp";

                    }, 5000)
                } else {
                    layer.msg("修改失败", {icon: 2});
                }
            }
        });

    })




}