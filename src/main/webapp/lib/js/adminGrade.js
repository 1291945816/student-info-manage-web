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
    };
        html+="</select> " +
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
    let sno=$("input[name=sno]").get(0).value;
    let daygrade=$("input[name=daygrade]").get(0).value;
    let examgrade=$("input[name=examgrade]").get(0).value;

    if(daygrade != null && examgrade != null && sno!=null && select_value != null){
        console.log(121)
        let data={
            selectccode:select_value,
            sno:sno,
            daygrade:daygrade,
            examgrade:examgrade
        };
        $(function () {
            $.ajax({
                type: "post",
                dataType: "json",
                url: './gradeController?action=update_studentgrade',
                data: data,
                success: function (info) {
                    if(info.code="200"){
                        layer.msg("录入成功!",{icon:1});
                    }else
                    {
                        layer.msg("录入失败!,请检查录入的信息",{icon:2});
                    }
                }
            });

        });





    }else {
        layer.msg("输入不能为空",{icon:2});
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
        "<button class='layui-btn'   onclick='query()' >查询</button>" +
        "</div></div>";
    $('#content').get(0).innerHTML = html;

}

function query() {
    let value = $("#selectcode option:selected").val();
    $(function () {
        $.ajax({

        })

    })

}


