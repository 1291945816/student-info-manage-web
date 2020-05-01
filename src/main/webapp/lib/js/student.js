layui.use('element', function(){
    var element = layui.element; //导航的hover效果、二级菜单等功能，需要依赖element模块

    //监听导航点击
    element.on('nav(demo)', function(elem){
        //console.log(elem)

        layui.use('layer',function () {
            layer.msg(elem.text());
        })
    });
});


function show_info() {
    $.ajax({
        type: "post",
        dataType: "json",
        url: '/test/userservlet?action=query_studentInfo',
        data: '',
        success: function (info) {
            var html ="<h1 style='text-align: center'>个人信息</h1>"
                +"<table class='layui-table' lay-even lay-skin='nob' lay-size='lg' >"
                +"<tbody style='font-size: 30px'>"
                +"<tr>" +"<td>name: </td>" +"<td>"+info.name+"</td>" + "</tr>"
                +"<tr>" +"<td>id: </td>" +"<td>"+info.id+"</td>" + "</tr>"
                +"<tr>" +"<td>sex: </td>" +"<td>"+info.sex+"</td>" + "</tr>"
                +"<tr>" +"<td>birthday: </td>" +"<td>"+info.birthday+"</td>" + "</tr>"
                +"<tr>" +"<td>class: </td>" +"<td>"+info.class_+"</td>" + "</tr>"
                +"<tr>" +"<td>department: </td>" +"<td>"+info.department+"</td>" + "</tr></tbody></table>"
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
            var class_info=classInfo.Class;
            var students_info=classInfo.students;
            var html1 ="<h1 style='text-align: center'>班级信息</h1>"
                +"<table class='layui-table' lay-even lay-skin='nob' lay-size='lg' >"
                +"<tbody style='font-size: 30px'>"
                +"<tr>" +"<td>classId: </td>" +"<td>"+class_info.id+"</td>" + "</tr>"
                +"<tr>" +"<td>classTeacher: </td>" +"<td>"+class_info.classTeacher+"</td>" + "</tr></tbody></table><hr />";
            let html2 = "<h1 style='text-align: center'>班级成员</h1>"
                + "<table  lay-filter='data_parse'  >"
                +"<thead>"
                + "<tr>" +
                "<th lay-data=\"{field:'id',width: 300,sort: true}\" >" +"id </th>"
                +"<th lay-data=\"{field:'name',width: 200}\">" +"name</th>"
                +"<th lay-data=\"{field:'department',width: 300}\">" +"department </th>"
                +"</tr>"
                +"</thead>";
            students_info.forEach(function (student_info) {
                var id = student_info.id;
               html2=html2
                   +"<tr><td>"+id+"</td>"
                   +"<td>"+student_info.name+"</td>"
                   +"<td>"+student_info.department+"</td>"
                   + "</tr>";
            });
            html2=html2+"</tbody></table>";

            $('#content').get(0).innerHTML=html1+html2;
            table_init();

        }


    })

}
function table_init() {
    layui.use('table', function () {
        var table = layui.table;
        table.init('data_parse', {
            limit: 10,
            height: 315,
            page: true,



        })

    })
}

