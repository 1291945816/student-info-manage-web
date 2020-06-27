$(function (){
    $("img#checkcode").click(
        function () {
            var img=$('#checkcode').get(0);
            img.src="./checkcode?"+new Date().getTime();
        })});

function aboutSystem() {
    layui.use('layer',function () {
        layer.msg("目前提供测试用例为:教师端:\n工号:13001 密码:admin\n\n学生端:\n学号:1800100100 密码:1234567\n");
    })

}


