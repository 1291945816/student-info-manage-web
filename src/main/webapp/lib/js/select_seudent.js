
function select_course() {
    let html_table1 = "<h1 style='text-align: center'>选课</h1>" +
        "<table id='table1' lay-filter='table_s' width='800px'></table>";
    $('#content').get(0).innerHTML = html_table1;

    layui.use('table', function () {
        var table = layui.table;


        table.render({
            elem: '#table1',
            page: true,
            limit: 5,
            limits: [5, 10, 15, 20],
            height: 450,
            url: './userSelectedCourse',
            cols: [
                [ //表头
                    {field: 'cno', title: '课号', width: 200, sort: true, fixed: 'left'}, {
                    field: 'ccode',
                    sort: true,
                    title: '课程代码',
                    width: 200
                }, {field: 'cname', title: '课程名称', width: 200},
                    {field: 'tname', title: '教师', width: 200},
                    {field: 'cnum', title: '数量', width: 200},
                    {fixed: 'right', title: '操作', width: 200, align: 'center', toolbar: '#con'}
                ]
            ],
            where: {'action': 'selectCourse'}

        });

        table.on('tool(table_s)', function (obj) {
            const data_ = obj.data;
            layEvent = obj.event; //获取操作、数据
            if (layEvent === "store") {
                layer.confirm('确定要选它吗?', function (index) {
                    layer.close(index);
                    $.ajax({
                        type: 'post',
                        data: data_,
                        url: './userSelectedCourse?action=selectCourseforstudent',
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


