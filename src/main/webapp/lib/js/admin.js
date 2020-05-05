layui.use('element', function(){
    let element = layui.element; //导航的hover效果、二级菜单等功能，需要依赖element模块

    //监听导航点击
    element.on('nav(demo)', function(elem){

        layui.use('layer',function () {
            layer.msg(elem.text());
        })
    });
});

function exit() {
    layui.use('layer',function (){
        layer.confirm('你确定现在就要退出吗?', {title: '提示',icon: 3,scrollbar: false},function(index){
            window.location.href='./exit';
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
        success: function (info) {
            let html = "<h1 style='text-align: center'>个人信息</h1>"
                + "<table class='layui-table' lay-even lay-skin='nob' lay-size='lg' >"
                + "<tbody style='font-size: 30px'>"
                + "<tr>" + "<td>id: </td>" + "<td>" + info.tno + "</td>" + "</tr>"
                + "<tr>" + "<td>name: </td>" + "<td>" + info.tname + "</td>" + "</tr>"
                + "<tr>" + "<td>sex: </td>" + "<td>" + info.tsex+ "</td>" + "</tr>"
                + "<tr>" + "<td>birthday: </td>" + "<td>" + info.birthday + "</td>" + "</tr>"
                + "<tr>" + "<td>job: </td>" + "<td>" + info.president + "</td>" + "</tr>"
                + "<tr>" + "<td>department: </td>" + "<td>" + info.dno + "</td>" + "</tr></tbody></table>";
            $('#content').get(0).innerHTML=html;

        }
    })
}

function show_courseInfo() {
   let  html_table1="<table id='table1' lay-filter='test'></table>"
    $('#content').get(0).innerHTML=html_table1;
    layui.use('table',function () {
        var table=layui.table;
        table.render({
            elem: '#table1'
            ,page: true,
            limit: 5,
            limits: [5,10,15,20]
            ,height: 315,
            url: './adminservlet',
            cols: [[ //表头
                {type: 'checkbox', fixed: 'left'},
                {field: 'cno', title: '课号', width:200, sort: true, fixed: 'left'}
                ,{field: 'ccode', title: '课程代码', width:200}
                ,{field: 'startdate', title: '授课时间', width:200}
            ]],
            where: {'action': 'query_courseInfo'}

        })

    })

}
