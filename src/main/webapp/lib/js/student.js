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