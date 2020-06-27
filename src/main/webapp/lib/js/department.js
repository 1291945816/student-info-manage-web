function addpartdemt() {
    let html = "<h1>增加部门</h1>" +
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
    let dno = $('input[name=dno]').get(0).value;
    let dleader = $('input[name=dleader]').get(0).value;
    let dname = $('input[name=dname]').get(0).value;
    var req=new RegExp("^[0-9]*$");
    if(! req.test(dno) )
    {
        layer.msg("部门编号只能是数字！！！", {icon: 2});
        return false;
    }


    if (dleader === "" || dno === "" || dname === "" ) {

        layer.msg("输入项不能为空!", {icon: 2});
        return false;
    } else {
        const data = {
            dleader: dleader,
            dname: dname,
            dno: dno
        };
        $(function () {
            $.ajax({
                    type: "post",
                    dataType: "json",
                    url: './dep?action=adddepartment',
                    data: data,
                    success: function (data) {
                        if (data.code === "200") {
                            layer.msg("添加成功", {icon: 1});
                        } else {
                            layer.msg("添加失败", {icon: 2});
                        }

                    }
                }
            )

        })
        return true;
    }
}

function query_dep() {
    $('#content').get(0).innerHTML = "<h1 style='text-align: center'>部门</h1>" +
        "<table id='table1' lay-filter='table_dep' width='800px'></table>";

    layui.use('table', function () {
        var table = layui.table;


        table.render({
            elem: '#table1',
            page: true,
            limit: 5,
            limits: [5, 10, 15, 20],
            height: 250,
            url: './dep',
            cols: [
                [ //表头
                    {field: 'dno', title: '部门编号', width: 200, sort: true, fixed: 'left'},
                    {
                        field: 'dleader',
                        title: '部门负责人',
                        width: 200
                    }, {field: 'dname', title: '部门名称', width: 200},
                    {fixed: 'right', title: '操作', width: 200, align: 'center', toolbar: '#con2'}
                ]
            ],
            where: {'action': 'query_department_info'}

        });

        table.on('tool(table_dep)', function (obj) {
            const data_ = obj.data;
            layEvent = obj.event; //获取操作、数据
            if (layEvent === "del") {
                layer.confirm('真的要删除部门吗?请慎重!!!', function (index) {
                    layer.close(index);
                    $.ajax({
                        type: 'post',
                        data: data_,
                        url: './dep?action=delete_department',
                        dataType: 'json',
                        success: function (status) {
                            if (status.code === "200") {
                                layui.use('layer', function () {
                                    layer.msg(status.msg, {icon: 1});
                                });
                                obj.del(); //删除对应行（tr）的DOM结构
                            } else {
                                layui.use('layer', function () {
                                    layer.msg(status.msg, {icon: 0});
                                })
                            }
                        }
                    });

                });
            }
        });
    });
}
