
function select_course() {
    let html_table1 = "<h1 style='text-align: center'>选课</h1>" +
        "<table id='table1' lay-filter='test' width='800px'></table>";
    $('#content').get(0).innerHTML = html_table1;

    layui.use('table', function () {
        var table = layui.table;


        table.render({
            elem: '#table1',
            page: true,
            limit: 5,
            limits: [5, 10, 15, 20],
            height: 250,
            url: './userSelectedCourse',
            cols: [
                [ //表头
                    {field: 'cno', title: '课号', width: 200, sort: true, fixed: 'left'}, {
                    field: 'ccode',
                    edit: 'text',
                    title: '课程代码',
                    width: 200
                }, {field: 'cname', edit: 'text', title: '课程名称', width: 200},
                    {field: 'tname', edit: 'text', title: '教师', width: 200},
                    {field: 'cnum', edit: 'text', title: '数量', width: 200},
                    {fixed: 'right', title: '操作', width: 200, align: 'center', toolbar: '#con'}
                ]
            ],
            where: {'action': 'selectCourse'}

        });
    });

}


