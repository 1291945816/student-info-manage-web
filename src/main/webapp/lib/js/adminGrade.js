function qury_grade() {
    $(function () {
        $.ajax({
            type: "post",
            dataType: "json",
            url: './gradeController?action=query_selected_course',
            data: "",
            success: function (info) {
                query_index(info.data);
            }
        });

    });

}

function insert() {
    $(function () {
        $.ajax({
            type: "post",
            dataType: "json",
            url: './gradeController?action=query_selected_course',
            data: "",
            success: function (info) {
                insert_grade(info.data)
            }
        });

    });

}

function insert_grade(data) {
    let html = "<h1>录入成绩</h1>" +
        "<div class='layui-form-item'>" +
        "<label class='layui-form-label'>课号:</label>" +
        "<div class='layui-input-block'>" +
        "<select name='cno' id='selectcode' lay-verify='required' > " +
        "<option value=''>请选择您要录入成绩的课号</option>";
    console.log(data);
    if (data != null) {
        data.forEach(function (info) {
            html += "<option value='" + info + "'>" + info + "</option>";
        });
    }
    ;
    html += "</select> " +
        "</div>" +
        "</div>";
    html +=
        "<div class='layui-form-item'>" +
        "<label class='layui-form-label'>学号:</label>" +
        "<div class='layui-input-block'>" +
        "<input type='text' name='sno'  class='layui-input' lay-verify='required' placeholder='请输入学生的学号' > " +
        "</div></div>" +
        "<div class='layui-form-item'>" +
        "<label class='layui-form-label'>平时成绩(20%):</label>" +
        "<div class='layui-input-block'>" +
        "<input type='text' name='daygrade'  class='layui-input' lay-verify='required' placeholder='请输入学生的平时成绩' > " +
        "</div></div>" +
        "<div class='layui-form-item'>" +
        "<label class='layui-form-label'>考试成绩(80%):</label>" +
        "<div class='layui-input-block'>" +
        "<input type='text' name='examgrade'  class='layui-input'  lay-verify='required' placeholder='请输入学生的考试成绩' > " +
        "</div></div>" +
        "<div class='layui-form-item'>" +
        "<div class='layui-input-block'>" +
        "<button class='layui-btn'   onclick='add_grade()' >录入</button>" +
        "</div></div>";
    $('#content').get(0).innerHTML = html;


}

function add_grade() {
    let select_value = $("#selectcode option:selected").val();
    let sno = $("input[name=sno]").get(0).value;
    let daygrade = $("input[name=daygrade]").get(0).value;
    let examgrade = $("input[name=examgrade]").get(0).value;
    if(!daygrade.match(/^[0-9]+([.]{1}[0-9]+){0,1}$/) || !examgrade.match(/^[0-9]+([.]{1}[0-9]+){0,1}$/)){
        layer.msg("请勿输入非法字符!", {icon: 2});
        return;
    }

    if ( select_value === ""  || sno === "" || examgrade === "" || daygrade === "")
    {
        layer.msg("输入不能为空", {icon: 2});
        return;
    }
    console.log(sno);


    if (daygrade !== null && examgrade !== null && sno !== null && select_value !== null ) {
        let data = {
            selectccode: select_value,
            sno: sno,
            daygrade: daygrade,
            examgrade: examgrade
        };
        $(function () {
            $.ajax({
                type: "post",
                dataType: "json",
                url: './gradeController?action=update_studentgrade',
                data: data,
                success: function (info) {
                    if (info.code === "200") {
                        layer.msg("录入成功!", {icon: 1});
                    } else {
                        layer.msg("录入失败!,请检查录入的信息", {icon: 2});
                    }
                }
            });

        });


    } else {
        layer.msg("输入不能为空", {icon: 2});
    }


}


function query_index(data) {
    let html = "<h1>查询课程成绩</h1>" +
        "<div class='layui-form-item'>" +
        "<label class='layui-form-label'>课号:</label>" +
        "<div class='layui-input-block'>" +
        "<select name='cno' id='selectcode' lay-verify='required' > " +
        "<option value=''>请选择您要查询的课号</option>";

    console.log(data);
    if (data != null) {
        data.forEach(function (info) {

            html += "<option value='" + info + "'>" + info + "</option>";
        });
    }
    html += "</select> " +
        "</div>" +
        "</div>" + "<div class='layui-form-item'>" +
        "<div class='layui-input-block'>" +
        "<button class='layui-btn'   onclick='show_courseGrade()' >查询</button>" +
        "</div></div>";
    $('#content').get(0).innerHTML = html;

}


function show_courseGrade() {
    let value = $("#selectcode option:selected").val();
    data_ = {value: value};
    if (value === '' || value === null) {
        layer.msg("请选择一个课号进行查询!!", {icon: 2});
        return;
    }
    $(function () {
        $.ajax({
            type: "post",
            dataType: "json",
            url: "./gradeController?action=query_courseGrade",
            data: data_,
            success: function (gradeInfo) {
                if (gradeInfo.code === "200") {
                    let failNum = gradeInfo.failNum;
                    let courseName = gradeInfo.courseName;
                    let avggrade = gradeInfo.avggrade;
                    let data = gradeInfo.data;
                    let greatNum = gradeInfo.greatNum;

                    let html1 = "<h1 style='text-align: center'>" + courseName + "</h1>"
                        + "<table class='layui-table' lay-even lay-skin='nob' lay-size='lg' >"
                        + "<tbody style='font-size: 30px'>"
                        + "<tr>" + "<td>优秀人数: </td>" + "<td>" + greatNum + "</td>" + "</tr>"
                        + "<tr>" + "<td>挂科人数: </td>" + "<td>" + failNum + "</td>" + "</tr>"
                        + "<tr>" + "<td>平均分: </td>" + "<td>" + avggrade + "</td>" + "</tr></tbody></table><hr />";
                    let html2 = "<h1 style='text-align: center'>成员</h1>"
                        + "<table  lay-filter='data_parse'  >"
                        + "<thead>"
                        + "<tr>" +
                        "<th lay-data=\"{field:'id',width: 200,sort: true}\" >" + "学号</th>"
                        + "<th lay-data=\"{field:'name',width: 200}\">" + "姓名</th>"
                        + "<th lay-data=\"{field:'daygrade',width: 200,sort: true}\">" + "平时成绩</th>"
                        + "<th lay-data=\"{field:'examgrade',width: 200,sort: true}\">" + "考试成绩</th>"
                        + "<th lay-data=\"{field:'totalgrade',width: 200,sort: true}\">" + "总评成绩</th>"
                        + "</tr>"
                        + "</thead><tbody>";
                    data.forEach(function (data_info) {

                        html2 = html2
                            + "<tr><td>" + data_info.sno + "</td>"
                            + "<td>" + data_info.sname + "</td>"
                            + "<td>" + data_info.daygrade + "</td>"
                            + "<td>" + data_info.examgrade + "</td>"
                            + "<td>" + data_info.totalgrade + "</td>"
                            + "</tr>";
                    });
                    html2 = html2 + "</tbody></table>";

                    $('#content').get(0).innerHTML = html1 + html2;
                    table_init();

                }else
                    {
                        layer.msg("查询失败,该课目前无人选...",{icon:2});

                }
            }
        })

    });


}

function table_init() {
    layui.use('table', function () {
        var table = layui.table;
        table.init('data_parse', {
            limit: 10,
            height: 450,
            page: true,

        })

    })
}
