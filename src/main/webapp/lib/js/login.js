$(function (){
    $("img#checkcode").click(
        function () {
            var img=$('#checkcode').get(0);
            img.src="./checkcode?"+new Date().getTime();
        })});


