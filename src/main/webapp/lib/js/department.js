function addpartdemt() {
    let html = "<h1>增加部门</h1>"+
        "<div class='layui-form-item'>" +
        "<label class='layui-form-label'>部门编号:</label>" +
        "<div class='layui-input-block'>" +
        "<input type='text' name='dno'  class='layui-input'  placeholder='请输入部门编号' > " +
        "</div></div>" +
        "<div class='layui-form-item'>" +
        "<label class='layui-form-label'>主管领导:</label>" +
        "<div class='layui-input-block'>" +
        "<input type='text' name='dleader'  class='layui-input'  placeholder='请输入部门主管领导姓名' > " +
        "</div></div>" +
        "<div class='layui-form-item'>" +
        "<label class='layui-form-label'>部门名称:</label>" +
        "<div class='layui-input-block'>" +
        "<input type='text' name='dname'  class='layui-input'  placeholder='请输入部门名称' > " +
        "</div></div>" +
        "<div class='layui-form-item'>" +
        "<div class='layui-input-block'>" +
        "<button class='layui-btn'   onclick='addde_btn()' >增加</button>" +
        "</div></div>";

    $('#content').get(0).innerHTML = html;

}
function addde_btn() {
    let dno=$('input[name=dno]').get(0).value;
    let dleader=$('input[name=dleader]').get(0).value;
    let dname=$('input[name=dname]').get(0).value;
    if(dleader === "" || dno === "" || dname===""){

        layer.msg("输入项不能为空!",{icon:2});
        return false;
    }else {
        const data = {
            dleader:dleader,
            dname:dname,
            dno:dno
        };
        $(function () {
            $.ajax({
                    type: "post",
                    dataType: "json",
                    url: './dep?action=adddepartment',
                    data: data,
                    success:function (data) {
                        if(data.code === "200"){
                            layer.msg("添加成功",{icon: 1});
                        }else
                        {
                            layer.msg("添加失败",{icon: 2});
                        }

                    }
            }
            )

        })
        return true;
    }





}