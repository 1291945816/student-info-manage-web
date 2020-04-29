layui.use('element', function(){
    var element = layui.element; //导航的hover效果、二级菜单等功能，需要依赖element模块

    //监听导航点击
    element.on('nav(demo)', function(elem){
        //console.log(elem)

        layer.msg(elem.text());
    });
});
function query_grade() {
    var a=document.getElementById("content");
    test="\n";
    for (let i = 0;i<10;i++){
        test=test+"test\n";
    }
    a.innerText=test;
    alert("已经点击");

}
function show_info() {
    $.ajax({
        type: "post",
        dataType: "json",
        url: '/test/userservlet?action=query_studentInfo',
        data: '',
        success: function (info) {
            var html = "<table class='layui-table' lay-even lay-skin='nob' lay-size='lg' >"
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
