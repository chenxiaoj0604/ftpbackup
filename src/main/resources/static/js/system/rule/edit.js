var prefix = "/rule/";

$(function () {
    validateRule();
    var val = $(this).find("option:selected").val();
    if(val == '0'){
        $(".type_2").hide();
        $(".type_0").show();
    }else if(val == '1'){
        $(".type_0").hide();
        $(".type_2").hide();
    }else if(val == '2'){
        $(".type_2").show();
        $(".type_0").hide();
    }
})


/**
 * 保存
 */
function save() {
    $.ajax({
        type: "post",
        url: prefix + "save",
        data: $("#ruleForm").serialize(),
        success: function (r) {
            if (0 == r.code) {
                parent.layer.msg(r.msg);
                parent.reload();
                parent.layer.close(parent.layer.getFrameIndex(window.name));
            } else {
                parent.layer.msg(r.msg);
            }
        }
    });

}

/**
 * 校验
 */
function validateRule() {
    var icon = "<i class='fa fa-times-circle'></i> ";
    $("#ruleForm").validate({
        rule: {
            taskName: {
                required: true,
                maxlength: 40
            }
        },
        messages: {
            taskName: {
                required: icon + "请输入任务名称",
                maxlength: icon + "任务名称过长",
            }
        },
        errorPlacement: function (error, element) {
            if (element.is(":radio") || element.is(":checkbox")) {
                error.appendTo(element.parent().parent());
            } else {
                error.insertAfter(element);
            }
        },
        submitHandler: function () {
            save();
        }
    });
}


$('#type').change(function(){
    var val = $(this).find("option:selected").val();
    if(val == '0'){
        $(".type_2").hide();
        $(".type_0").show();
    }else if(val == '1'){
        $(".type_0").hide();
        $(".type_2").hide();
    }else if(val == '2'){
        $(".type_2").show();
        $(".type_0").hide();
    }
})
