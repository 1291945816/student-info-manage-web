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
                + "<tr>" + "<td>name: </td>" + "<td>" + info.sname + "</td>" + "</tr>"
                + "<tr>" + "<td>id: </td>" + "<td>" + info.sno + "</td>" + "</tr>"
                + "<tr>" + "<td>sex: </td>" + "<td>" + info.ssex + "</td>" + "</tr>"
                + "<tr>" + "<td>birthday: </td>" + "<td>" + info.birthday + "</td>" + "</tr>"
                + "<tr>" + "<td>class: </td>" + "<td>" + info.clno + "</td>" + "</tr></tbody></table>";
            $('#content').get(0).innerHTML=html;




        }
    })
}

function show_classInfo() {
    $.ajax({
        type: "post",
        dataType: "json",
        url: "/test/userservlet?action=query_classInfo",
        data: '',
        success: function (classInfo) {
            let class_info = classInfo.class_;
            let students_info = classInfo.students;
            let teacher=classInfo.teacher;
            let html1 = "<h1 style='text-align: center'>班级信息</h1>"
                + "<table class='layui-table' lay-even lay-skin='nob' lay-size='lg' >"
                + "<tbody style='font-size: 30px'>"
                + "<tr>" + "<td>classId: </td>" + "<td>" + class_info.clno + "</td>" + "</tr>"
                + "<tr>" + "<td>department: </td>" + "<td>" + class_info.dno + "</td>" + "</tr>"
                + "<tr>" + "<td>classTeacher: </td>" + "<td>" + teacher + "</td>" + "</tr></tbody></table><hr />";
            let html2 = "<h1 style='text-align: center'>班级成员</h1>"
                + "<table  lay-filter='data_parse'  >"
                +"<thead>"
                + "<tr>" +
                "<th lay-data=\"{field:'id',width: 300,sort: true}\" >" +"id </th>"
                +"<th lay-data=\"{field:'name',width: 200}\">" +"name</th>"
                +"<th lay-data=\"{field:'name',width: 200}\">" +"sex</th>"
                +"</tr>"
                +"</thead><tbody>";
            students_info.forEach(function (student_info) {
                let id = student_info.sno;
                html2=html2
                    +"<tr><td>"+id+"</td>"
                    +"<td>"+student_info.sname+"</td>"
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
        url: "/test/userservlet?action=query_allCourse",
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
                    "<th lay-data=\"{field:'courseId ',width: 300,sort: true}\" >" +"courseId </th>"
                    +"<th lay-data=\"{field:'name',width: 300}\">" +"name</th>"
                    +"<th lay-data=\"{field:'startDate ',width: 200,sort:true}\">" +"startDate </th>"
                    +"<th lay-data=\"{field:'credit',width: 300,sort:true}\">" +"credit </th>"
                    +"<th lay-data=\"{field:'teacher',width: 290}\">" +"teacher </th>"
                    +"</tr>"
                    +"</thead><tbody>";
                courses.forEach(function (item) {
                    html=html+"<tr><td>"+item.courseId+"</td>"
                        +"<td>"+item.name+"</td>"
                        +"<td>"+item.startDate+"</td>"
                        +"<td>"+item.credit+"</td>"
                        +"<td>"+item.teacher+"</td></tr>";
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
        url: '/test/userservlet?action=query_allCourseGrade',
        dataType: 'json',
        success: function (item) {
            if(item.code === 500){
                layui.use('layer',function () {
                    layer.msg('未查询到相关的成绩信息', {icon: 0});
                })
            }else
            {
                let html1="<h1 style='text-align: center'>统计信息</h1>"
                    + "<table class='layui-table' lay-even lay-skin='nob' lay-size='lg' >"
                    + "<tbody style='font-size: 30px'>"
                    + "<tr>" + "<td>学分绩: </td>" + "<td style='color: red'>" +item.creditGrade+ "</td>" + "</tr>"
                    + "<tr>" + "<td>优秀门数: </td>" + "<td style='color: red'>" + item.count + "</td>" + "</tr>"
                    + "<tr>" + "<td>及格门数: </td>" + "<td style='color: red'>" + item.passCount + "</td>" + "</tr>"
                    + "<tr>" + "<td>不及格门数: </td>" + "<td style='color: red'>" + item.failCount + "</td>" + "</tr>"
                    + "<tr>" + "<td>平均分: </td>" + "<td style='color: red'>" + item.avgGrade+ "</td>" + "</tr></tbody></table>";
                let grades=item.grades;

                let htm2="<h1 style='text-align: center'>各科成绩</h1>"
                    + "<table  lay-filter='data_parse'  >"
                    +"<thead>"
                    + "<tr>" +
                    "<th lay-data=\"{field:'courseId ',width: 300,sort: true}\" >" +"courseId </th>"
                    +"<th lay-data=\"{field:'name',width: 300}\">" +"name</th>"
                    +"<th lay-data=\"{field:'daygrade',width: 200,sort:true}\">" +"daygrade </th>"
                    +"<th lay-data=\"{field:'examgrade',width: 200,sort:true}\">" +"examgrade </th>"
                    +"<th lay-data=\"{field:'grade',width: 290}\">" +"grade </th>"
                    +"<th lay-data=\"{field:'credit',width: 100}\">" +"credit </th>"
                    +"</tr>"
                    +"</thead><tbody>";
                grades.forEach(function (info_grade) {

                    htm2=htm2+"<tr><td>"+info_grade.courseId+"</td>"
                        +"<td>"+info_grade.name+"</td>"
                        +"<td>"+info_grade.daygrade+"</td>"
                        +"<td>"+info_grade.examgrade+"</td>"
                        +"<td>"+info_grade.grade+"</td>"
                        +"<td>"+info_grade.credit+"</td></tr>";
                });
                htm2+="</tbody></table>";
                $('#content').get(0).innerHTML=html1+htm2;
                table_init();
            }

        }
    })

}