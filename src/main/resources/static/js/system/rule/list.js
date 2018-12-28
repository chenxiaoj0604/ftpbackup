var prefix = "/rule/"
$(function () {
    load();
});

/**
 * 加载
 */
function load() {
    $("#ruleTable").bootstrapTable({
        method: 'post', // 服务器数据的请求方式 get or post
        url: prefix + 'list', // 服务器数据的加载地址
        toolbar: '#toolbar', //工具按钮用哪个容器
        iconSize: 'outline',
        striped: true, // 设置为true会有隔行变色效果
        dataType: 'json', // 服务器返回的数据类型
        pagination: true, // 设置为true会在底部显示分页条
        singleSelect: false, // 设置为true将禁止多选
        pageSize: 10, // 如果设置了分页，每页数据条数
        pageNumber: 1, // 如果设置了分布，首页页码
        pageList: [], //可供选择的每页的行数
        showColumns: false, // 是否显示内容下拉框（选择显示的列）
        sidePagination: 'server', // 设置在哪里进行分页，可选值为"client" 或者
        contentType: "application/x-www-form-urlencoded",
        search: false, //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
        strictSearch: true,
        showColumns: false, //是否显示所有的列
        showRefresh: false, //是否显示刷新按钮
        minimumCountColumns: 2, //最少允许的列数
        clickToSelect: false, //是否启用点击选中行
        // height: 500, //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
        uniqueId: "id", //每一行的唯一标识，一般为主键列
        showToggle: false, //是否显示详细视图和列表视图的切换按钮
        cardView: false, //是否显示详细视图
        detailView: false, //是否显示父子表
        queryParamsType: "undefined",
        dataField: 'list',
        queryParams: function (params) {
            return {
                // 说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
                pageNumber: params.pageNumber,
                pageSize: params.pageSize,
                taskName: $("#searchName").val()

            };
        },
        columns: [

            {
                field: 'taskName',
                title: '任务名称',
                align: 'center'
            },
            {
                field: 'taskArea',
                title: '任务分组',
                align: 'center'
            },
            {
                field: 'sourceFileName',
                title: '文件名',
                align: 'center'
            },
            {
                field: 'getDays',
                title: '备份天数',
                align: 'center'
            },
            {
                field: 'saveDays',
                title: '保存天数',
                align: 'center'
            },
            {
                field: 'fileNum',
                title: '文件数量',
                align: 'center'
            },
            {
                field: 'regex',
                title: '正则表达式',
                align: 'center'
            },
            {
                field: 'backupTimeCron',
                title: 'Cron表达式',
                align: 'center'
            },
            {
                field: 'taskDetail',
                title: '描述',
                align: 'center'
            },
            {
                field: 'taskStatus',
                title: '状态',
                align: 'center',
                formatter:function (value,row,index) {
                    if (value != null) {
                        if (value == "0") {
                            return '<span class="label label-danger">停用</span>';
                        } else {
                            return '<span class="label label-primary">启用</span>';
                        }
                    }
                }
            },
            {
                field: 'lastExecuteStatus',
                title: '上次执行状态',
                align: 'center',
                formatter: function (value,row) {
                    if(value != null){
                        if (value == "1"){
                            return  '<span class="label label-primary">'+'成功'+'</span>';
                        } else if(value == "0") {
                            return  '<span class="label label-danger">'+'失败'+'</span>';
                        }
                    }
                }
            },
            {
                field: 'id',
                title: '操作',
                align: 'center',
                formatter: function (value, row, index) {
                    var open = '<a class="btn btn-primary btn-sm ' + '" href="#" title="开启" onclick="changeStatus(\'' + row.id + '\',\'' + row.taskStatus + '\')" mce_href="#"><i class="glyphicon glyphicon-play"></i></a> ';
                    var stop = '<a class="btn btn-primary btn-sm ' + '" href="#" title="停用" onclick="changeStatus(\'' + row.id + '\',\'' + row.taskStatus + '\')"  mce_href="#"><i class="glyphicon glyphicon-unchecked"></i></a> ';

                    var q;
                    if (row.taskStatus == "0"){
                        q = open;
                    } else {
                        q = stop;
                    }

                    var l = '<a class="btn btn-info btn-sm ' + '" href="#" title="立即执行" onclick="runJob(\'' + row.id + '\',\'' + row.taskStatus + '\')"  mce_href="#"><i class="glyphicon glyphicon-step-forward"></i></a> ';
                    var e = '<a class="btn btn-success btn-sm ' + '" href="#" title="编辑" onclick="edit(\'' + row.id + '\')" mce_href="#"><i class="fa fa-edit"></i></a> ';
                    var d = '<a class="btn btn-warning btn-sm ' + '" href="#" title="删除" onclick="del(\'' + row.id + '\')"  mce_href="#"><i class="fa fa-remove"></i></a> ';
                    return q + l + e + d;
                }
            }
        ]
    });
}


/**
 * 立即执行
 * @param id
 */
function runJob(id,status) {
    layer.confirm("确定要执行吗？", {btn: ["确定", "取消"]}, function () {
        $.ajax({
            type: "post",
            url: prefix + "runJob",
            data: {"id": id,"taskStatus":status},
            success: function (r) {
                if (0 == r.code) {
                    layer.msg(r.msg);
                    reload();
                } else {
                    layer.msg(r.msg);
                }
            }
        });
    });
}



function changeStatus(id, status) {
    var actCh;
    if (status == "0") {
        actCh = "确认要开启任务吗？";
    } else {
        actCh = "确认要停止任务吗？";
    }
    layer.confirm(actCh, {
        btn: ['确定', '取消']
    }, function () {
        $.ajax({
            url: prefix + "/changeStatus",
            type: "post",
            data: {
                'id': id,
                'taskStatus': status
            },
            success: function (r) {
                if (r.code == 0) {
                    layer.msg(r.msg);
                    reload();
                } else {
                    layer.msg(r.msg);
                }
            }
        });
    })
}


/**
 * 刷新
 */
function reload() {
    $("#ruleTable").bootstrapTable("refresh");
}

/**
 * 编辑
 * @param id
 */
function edit(id) {
    var url = prefix + "edit";
    var title = "";
    if (id == null) {
        title = "新增任务";
    } else {
        url = url + "?id=" + id;
        title = "编辑任务";
    }
    layer.open({
        type: 2,
        title: title,
        maxmin: true,
        shadeClose: false,
        area: ["800px", "400px"],
        content: url
    });
}

/**
 * 删除
 * @param id
 */
function del(id) {
    layer.confirm("确定要删除选中任务？", {btn: ["确定", "取消"]}, function () {
        $.ajax({
            type: "post",
            url: prefix + "del",
            data: {"id": id},
            success: function (r) {
                if (0 == r.code) {
                    layer.msg(r.msg);
                    reload();
                } else {
                    layer.msg(r.msg);
                }
            }
        });
    });
}

/**
 * 查看日志
 * @param id
 */
function showLog(rwfz,rwmc) {
    window.location.href="/rule/logList?jkmc=" + rwfz + "_" + rwmc;
}
