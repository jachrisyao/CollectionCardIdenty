$(document).ready(function () {
    $(".btn-pay").click(function () {
        var html = "<div class=\"updateInfo\" style='width:370px;margin:20px auto;'>" +
            "<div class=\"form-group\">" +
            "<h5>请选择支付渠道</h5>" +
            "</div>" +
            "<hr />" +
            "<div class=\"form-group payway\">" +
            "<div class=\"col-sm-5\">" +
            "<a href=\"#\">" +
            "<img src=\"../../Content/img/alipay.png\" />" +
            "</a>" +
            "</div>" +
            "<div class=\"col-sm-5\">" +
            " <a href=\"#\">" +
            " <img src=\"../../Content/img/paypal.png\" />" +
            "</a>" +
            "</div>" +
            "<div class=\"clearfix\"></div>" +
            "</div>" +
            "<div class=\"form-group\">" +
            "<button type=\"button\" class=\"btn btn-primary btn-register\">确 认</button>" +
            "</div>" +
            " </div>";
        var param = {
            html: html,
            href: null,
            opacity: 0.5,
            onComplete: null,
            width: 450,
            height: 250,
            overlayClose: false,
            close: "x"
        };
        $.colorbox(param);
    });

    $(".waymark .label").hover(function () {
        $(this).parent().find(".tips").show();
    }, function () {
        $(this).parent().find(".tips").hide();
    });


});

function chekcedAll(obj) {
    if (obj.checked == true) {
        $("input[name='ids']").prop("checked", true);
    } else {
        $("input[name='ids']").prop("checked", false);
    }
}

function cinformUpdate() {
    var html = "<div class=\"updateInfo\">" +
        "<div class=\"form-group\">" +
        "<span class=\"warn\">!</span>" +
        "</div>" +
        "<div class=\"form-group\">" +
        "<p>您需要修改支付宝的信息，请输入您的登录密码进行再次确认！</p>" +
        "</div>" +
        "<div class=\"form-group\">" +
        "<input class=\"form-control\" name=\"Password\" size=\"30\" type=\"password\" value=\"\">" +
        "</div>" +
        "<div class=\"form-group\">" +
        "<input class=\"btn btn-primary btn-register\" name=\"commit\" type=\"button\" onclick=\"postForm('registerForm',this)\" value=\"确认\" />" +
        "</div>" +
        "</div>";
    var param = {
        html: html,
        href: null,
        opacity: 0.5,
        onComplete: null,
        width: 440,
        height: 320,
        overlayClose: false,
        close: "x"
    };
    $.colorbox(param);
}

//投诉建议
function cinComplaintAdvice() {
    var html = "<div class=\"colorbox-html\">" +
        "<div class=\"text-center c-title\">投诉建议</div>" +
        "<div class=\"c-img-box\">" +
        " <img src=\"/Content/img/web.png\" />" +
        " </div>" +
        "<div class=\"c-text\">" +
        " <p>若您与交易方沟通无果（包括打电话），并且已经查阅<a href=\"/market/rules#buyer_rule\" target=\"_blank\">《卡淘市场交易流程及规则》</a>，请移步平台网页端<a href=\"http://oldkazuworld.cardhobby.com/forum/forum.php\" target=\"_blank\">《卡淘论坛》</a>纠纷投诉版块发帖反馈。</p>" +
        " </div>" +
        "<div class=\"c-text\">" +
        " <p>联系我们：</p>" +
        "<p>电话：400-821-5220</p>" +
        "<p>客服QQ：2137535264</p>" +
        " </div>" +
        "</div>"
    var param = {
        html: html,
        title: true,
        href: null,
        opacity: 0.5,
        onComplete: null,
        width: 500,
        height: 350,
        overlayClose: false,
        close: "x"
    };
    $.colorbox(param);
}

//支付提示
function cinComplaintPay(tf, id, tp) {
    //    <div class="colorbox-html" style="width:400px;">
    //        <div class="c-text text-center">
    //            <p>确认后，将从您的发布点余额中扣除</p>
    //            <p>XXX点，支付订单</p>
    //        </div>
    //    <div class="c-text text-center">
    //       <button type="button" class="t-btn-def">取消</button>
    //       <button type="button" class="t-btn-def t-btn-btn1">确定</button>
    //    </div>
    //</div>
    var tfhtml = ""
    if (tf == 2) {
        tfhtml = "<p>是否确认已线下支付完成</p>" +
            "<p>￥" + tp + "，支付订单</p>";
    } else {
        tfhtml = "<p>确认后，将从您的发布点余额中扣除</p>" +
            "<p>" + tp + "点，支付订单</p>";
    }
    var html = "<div class=\"colorbox-html\">" +
        "<div class=\"text-center c-title\">提示</div>" +
        "<div class=\"c-text text-center\">" +
        " </div>" +
        "<div class=\"c-text text-center\">" +
        tfhtml +
        " </div>" +
        "<div class=\"c-text text-center\">" +
        "<button type=\"button\" class=\"t-btn-def\" onclick=\"closeColorbox()\">取消</button>" +
        "<button type=\"button\" class=\"t-btn-def t-btn-btn1\" onclick=\"getSubmitPay(" + tf + "," + id + ")\">确定</button>" +
        " </div>" +
        "</div>"
    var param = {
        html: html,
        title: false,
        href: null,
        opacity: 0.5,
        onComplete: null,
        width: 300,
        height: 230,
        overlayClose: false,
        close: null
    };
    $.colorbox(param);
}


function closeColorbox() {
    $.colorbox.close();
}

//点击支付调用弹框
function orderTypePay(ts, ty, tf, tp) {//支付ts当前触发对象，ty=1发布点ty=0现金，tf=2线下,其他线上，tp金额

    var id = $(ts).data("id");

    $.get("/Order/GetOrderState/?orderid=" + id, function (data) {

        if (data == "1") {

            Tips("订单已经支付无需重复支付！");

            setTimeout(function () {
                location.reload();
            }, 1500);

        } else {

            if (tf == 2) {
                //线下
                cinComplaintPay(tf, id, tp);
            } else {
                //线上
                if (ty == 1) {
                    //发布点  确认后，将从您的发布点余额中扣除XXX点，支付订单
                    cinComplaintPay(tf, id, tp);
                } else if (tf == 5) {
                    //微信支付
                    location.href = '/pay/order/?id=' + id;
                } else {
                    //现金
                    location.href = '/pay/order/?id=' + id;
                }
            }
        }
    });


}

//提交申请

function ApplyAccumulate() {

    var id = $(ts).data("id");

    $.post("/Order/ApplyAccumulate/" + id, function (data) {
        if (data == "1") {
            Tips("申请成功！");
            setTimeout(function () {
                location.reload();
            }, 1500);
        } else {
            Tips(data);
        }
    })
}


//支付提交
function getSubmitPay(tf, id) {
    if (tf == 2) {
        Tips("正在提交您的请求，请稍后……");
        $.post("/Order/OfflinePay/" + id, function (data) {
            if (data == "1") {
                Tips("确认支付成功！");
                setTimeout(function () {
                    location.reload();
                }, 1500);
            } else {
                Tips(data);
            }
        })
    } else {

        var param = {
            html: null,
            href: null,
            opacity: 0.5,
            onClosed: null,
            width: 340,
            height: "auto",
            overlayClose: false,
            close: "x"
        };
        param.html = "<div class='description'>正在提交您的请求，请稍后……</div>";
        $.colorbox(param);
        $.post("/Pay/Order/" + id, function (data) {
            if (data == "1") {
                Tips("确认支付成功！");
                setTimeout(function () {
                    location.reload();
                }, 1500);
            } else {
                param.html = "<div class='description'>" + data + "</div>";
                $.colorbox(param);

            }
        })
    }
}


function postForm(formid, obj) {
    var form = $("#" + formid);
    var params = form.serialize();
    $(obj).html("正在提交中……").attr("disabled", true);
    var url = form.attr("action");
    $.post(url, params, function (data) {
        var result = data.split("|");
        if (result[0] == "1") {
            $(obj).html("提交成功");
            setTimeout(function () {
                window.location.href = result[1];
            }, 1000)
        } else {
            $(obj).html(data);
        }
    });
}

function Tips(str, envent, url, width, height) {
    var param = {
        html: null,
        href: null,
        opacity: 0.5,
        onClosed: null,
        width: 340,
        height: "auto",
        overlayClose: false,
        close: "x"
    };
    if (url == null) {
        param.html = "<div class='description'>" + str + "</div>";
    } else {
        param.href = url;
    }
    if (width != null) {
        param.width = width;
    }
    if (height != null) {
        param.height = height;
    }
    if (envent != null) param.onClosed = envent;
    $.colorbox(param);
    setTimeout(function () {
        $.colorbox.close();
    }, 5000)
}


function uploadImage(id, imageType, showDiv, isOnly, imageUrlId, beginNumber, isCarousel, height, width, mark, imageCount, ntype) {

    var postUrl = "http://www.cardhobby.com/upload/UploadImage?type=" + imageType
    $('#' + id).fileinput({
        language: 'zh', //设置语言
        uploadUrl: postUrl, //上传的地址
        allowedFileExtensions: ['jpg', 'jpeg', 'png', 'gif', 'bmp'],//接收的文件后缀
        previewFileType: ['jpg', 'jpeg', 'png', 'gif', 'bmp'],//预览文件类型
        showUpload: false, //是否显示上传按钮
        uploadAsync: false,
        showRemove: false,
        showCaption: false,//是否显示标题
        browseClass: "btn btn-primary", //按钮样式
        //initialPreview: img,
        showPreview: false,//是否显示生成的图片框
        //dropZoneEnabled: false,//是否显示拖拽区域
        //minImageWidth: 50, //图片的最小宽度
        //minImageHeight: 50,//图片的最小高度
        //maxImageWidth: 1000,//图片的最大宽度
        //maxImageHeight: 1000,//图片的最大高度
        maxFileSize: 4096,//单位为kb，如果为0表示不限制文件大小
        //minFileCount: 0,
        maxFileCount: 1, //表示允许同时上传的最大文件个数
        enctype: 'multipart/form-data',
        validateInitialCount: true,
        previewFileIcon: "<i class='glyphicon glyphicon-king'></i>",
        msgFilesTooMany: "选择上传的文件数量({n}) 超过允许的最大数值{m}！"
    }).on("filebatchselected", function (event, files) {
        if (files.length > 0) {
            $(this).fileinput("upload");
        } else {
            Tips("文件格式不正确或文件过大");
        }

    }).on("filebatchuploadsuccess", function (event, data, previewId, index) {
        var arr = data.response;
        var arry = [arr.result, arr.data, arr.type];
        if ($('input[name="Url"]').length < 5) {
            if (arr.result == "0") {
                //判断是否最新上传
                if (ntype) {
                    var html = NewcreateImageDiv(arry, beginNumber, id, imageUrlId, isCarousel);
                } else {
                    var html = createImageDiv(arry, beginNumber, id, imageUrlId, isCarousel);
                }

                if (isOnly) {
                    $('#' + showDiv).html(html);
                } else {
                    $('#' + showDiv).html($('#' + showDiv).html() + html);
                }
                ++beginNumber;
            } else {
                Tips(arr.data);
            }
        } else {
            Tips("最多可上传" + imageCount + "张图片！");
        }

    }).on("filebatchuploaderror", function (event, data, msg) {
        console.log(data.id);
        console.log(data.index);
        console.log(data.file);
        console.log(data.reader);
        console.log(data.files);
        // get message
        console.log(msg);
    })
    //20170829 注释更换长传控件
    //$('#' + id).uploadify({
    //    buttonText: '请选择上传文件',
    //    swf: '/Scripts/uploadify-v3.1/uploadify.swf"',
    //    uploader: postUrl,
    //    formData: { height: height, width: width, mark: mark },
    //    uploadLimit: (imageCount ? imageCount : 9999),
    //    multi: false,
    //    fileTypeDesc: '*.jpg;*.jpeg;*.gif;*.png;*.bmp',
    //    fileTypeExts: '*.jpg;*.jpeg;*.gif;*.png;*.bmp',
    //    removeCompleted: true,
    //    removeTimeout: 1,
    //    fileSizeLimit: '5MB',
    //    onUploadSuccess: function (file, data, response) {
    //        var arr = data.split(',');
    //        if (arr[0] == "0") {
    //            var html = createImageDiv(arr, beginNumber, id, imageUrlId, isCarousel);
    //            if (isOnly) {
    //                $('#' + showDiv).html(html);
    //            }
    //            else {
    //                $('#' + showDiv).html($('#' + showDiv).html() + html);
    //            }
    //            ++beginNumber;
    //            //var title = $("input[name='setTitleIMG']");
    //            //if (title.length == 1) {
    //            //    title[0].click();
    //            //}
    //            //imgMasonry();
    //        }
    //        else {
    //            Tips(arr[1]);
    //        }
    //    },
    //    overrideEvents: ['onSelectError', 'onDialogClose'],
    //    //返回一个错误，选择文件的时候触发
    //    onSelectError: function (file, errorCode, errorMsg) {
    //        switch (errorCode) {
    //            case -100:
    //                Tips("最多可上传" + imageCount + "张图片！");
    //                break;
    //            case -110:
    //                Tips("文件 [" + file.name + "] 大小超出系统限制的10MB大小！");
    //                break;
    //            case -120:
    //                Tips("文件 [" + file.name + "] 大小异常！");
    //                break;
    //            case -130:
    //                Tips("文件 [" + file.name + "] 类型不正确！");
    //                break;
    //        }
    //        return false;
    //    },
    //    onFallback: function () {
    //        Tips("您未安装FLASH控件，无法上传图片！请安装FLASH控件后再试。");
    //    }
    //});
}

function uploadImagebuy(id, imageType, showDiv, isOnly, imageUrlId, beginNumber, isCarousel, height, width, mark, imageCount, ntype) {

    var postUrl = "http://www.cardhobby.com/upload/UploadImage?type=" + imageType
    $('#' + id).fileinput({
        language: 'zh', //设置语言
        uploadUrl: postUrl, //上传的地址
        allowedFileExtensions: ['jpg', 'jpeg', 'png', 'gif', 'bmp'],//接收的文件后缀
        previewFileType: ['jpg', 'jpeg', 'png', 'gif', 'bmp'],//预览文件类型
        showUpload: false, //是否显示上传按钮
        uploadAsync: false,
        showRemove: false,
        showCaption: false,//是否显示标题
        browseClass: "btn btn-primary", //按钮样式
        //initialPreview: img,
        showPreview: false,//是否显示生成的图片框
        //dropZoneEnabled: false,//是否显示拖拽区域
        //minImageWidth: 50, //图片的最小宽度
        //minImageHeight: 50,//图片的最小高度
        //maxImageWidth: 1000,//图片的最大宽度
        //maxImageHeight: 1000,//图片的最大高度
        maxFileSize: 4096,//单位为kb，如果为0表示不限制文件大小
        //minFileCount: 0,
        maxFileCount: 1, //表示允许同时上传的最大文件个数
        enctype: 'multipart/form-data',
        validateInitialCount: true,
        previewFileIcon: "<i class='glyphicon glyphicon-king'></i>",
        msgFilesTooMany: "选择上传的文件数量({n}) 超过允许的最大数值{m}！"
    }).on("filebatchselected", function (event, files) {
        if (files.length > 0) {
            $(this).fileinput("upload");
        } else {
            layer.msg("文件格式不正确或文件过大");
        }

    }).on("filebatchuploadsuccess", function (event, data, previewId, index) {
        var arr = data.response;
        var arry = [arr.result, arr.data, arr.type];
        if ($('input[name="ImageUrl"]').length < 6) {
            if ($('input[name="ImageUrl"]').length == 5) {
                $("#addImagesWarrp").hide();
            }
            if (arr.result == "0") {
                //判断是否最新上传
                if (ntype) {
                    var html = NewcreateBuyImageDiv(arry, beginNumber, id, imageUrlId, isCarousel);
                } else {
                    var html = createImageDiv(arry, beginNumber, id, imageUrlId, isCarousel);
                }

                if (isOnly) {
                    $('#' + showDiv).html(html);
                } else {
                    $('#' + showDiv).html($('#' + showDiv).html() + html);
                }
                ++beginNumber;
            } else {
                layer.msg(arr.data);
            }
        } else {
            layer.msg("最多可上传" + imageCount + "张图片！");
        }

    }).on("filebatchuploaderror", function (event, data, msg) {
        console.log(data.id);
        console.log(data.index);
        console.log(data.file);
        console.log(data.reader);
        console.log(data.files);
        // get message
        console.log(msg);
    })

}

function headUploadImg(id, url) {
    $(id).fileinput({
        uploadUrl: url,
        allowedFileExtensions: ['jpg', 'png'],
        overwriteInitial: false,
        maxFileSize: 4096,
        maxFilesNum: 10,
        uploadAsync: false,
        showRemove: false,
        showCaption: false//是否显示标题

    }).on("filebatchselected", function (event, files) {

        if (files.length > 0) {
            $(this).fileinput("upload");
        } else {
            Tips("文件格式不正确或文件过大");
        }

    }).on("filebatchuploadsuccess", function (event, data, previewId, index) {
        var arr = data.response;
        if (arr.result == "0") {

            $("#PortraitImg").prop("src", data.response.data);
            $("#thumbnail").val(data.response.data);
            postImgForm();
        } else {
            Tips(arr.data);
        }


    }).on("filebatchuploaderror", function (event, data, msg) {
        console.log(data.id);
        console.log(data.index);
        console.log(data.file);
        console.log(data.reader);
        console.log(data.files);
        // get message
        console.log(msg);
    });
}

function createImageDiv(arr, i, uploadId, imageUrlId, isCarousel) {

    var html = "<div class='col-sm-6 col-md-4 item' id='imgDiv" + imageUrlId + i + "'>";

    var length = $("input[name='setTitleIMG']").length;

    html += "<div class='thumbnail " + (length == 0 ? "img-selected" : "") + "'>";
    html += "<img src=" + arr[1] + " class='' alt='" + imageUrlId + "'>";
    html += "<div class='caption'>";
    html += "<input type='hidden' name='Url' value='" + arr[1] + "' />";
    html += "<input type='hidden' name='Type' value='" + (length == 0 ? "3" : arr[2]) + "' />";
    if (isCarousel) html += "<label class='checkbox-inline'><input type='checkbox' name='IsCarousel' value='" + url + "'> 轮播 </label>";
    html += "<label class=\"control-label\"><input type=\"radio\" name=\"setTitleIMG\" class=\"title-radio\" onclick=\"setTitleImg(this)\" " + (length == 0 ? "checked" : "") + "/> 标题图</label>";
    html += "<a href='javascript:void(0);' class='btn btn-danger pull-right' role='button' onclick=\"deleteImageDiv('imgDiv" + imageUrlId + i + "','" + uploadId + "')\">移除</a>";
    html += "</div>";
    html += "</div>";
    html += "</div>";
    return html;
}

//发布卡片上传
function NewcreateImageDiv(arr, i, uploadId, imageUrlId, isCarousel) {

    var html = "<div class='pull-left item' id='imgDiv" + imageUrlId + i + "' style='margin-right:10px;margin-bottom:10px;position: relative;'>";

    var length = $("input[name='setTitleIMG']").length;

    html += "<div class='thumbnail' style='width:92px; height:92px;'>";
    html += "<img src=" + arr[1] + " class='' alt='" + imageUrlId + "' style='max-width:82px;max-height:62px;'>";
    html += "<div class='caption' style='position: absolute;bottom: 15px;'>";
    html += "<input type='hidden' name='Url' value='" + arr[1] + "' />";
    html += "<input type='hidden' name='Type' value='" + (length == 0 ? "3" : arr[2]) + "' />";
    if (isCarousel) html += "<label class='checkbox-inline'><input type='checkbox' name='IsCarousel' value='" + url + "'> 轮播 </label>";
    html += "<label class=\"control-label\" style='margin-bottom:0px;padding:0px;'><input type=\"radio\" name=\"setTitleIMG\" class=\"title-radio\" onclick=\"setTitleImg(this)\" " + (length == 0 ? "checked" : "") + "/> 标题图</label>";
    html += "</div>";
    html += "<a href='javascript:void(0);' style='position: absolute;top:-4px;right:-4px;' class='' role='button' onclick=\"deleteImageDiv('imgDiv" + imageUrlId + i + "','" + uploadId + "')\"><img src='http://www.cardhobby.com/Content/img/unchecked.gif' /></a>";

    html += "</div>";
    html += "</div>";
    return html;
}

//求卡上传
function NewcreateBuyImageDiv(arr, i, uploadId, imageUrlId, isCarousel) {

    var html = "<div class='pull-left item' id='imgDiv" + imageUrlId + i + "' style='margin-right:10px;margin-bottom:10px;position: relative;'>";

    var length = $("input[name='setTitleIMG']").length;

    html += "<div class='thumbnail' style='width:80px; height:80px;margin-bottom:0px;'>";
    html += "<img src=" + arr[1] + " class='' alt='" + imageUrlId + "' style='max-width:70px;max-height:70px;'>";
    html += "<div class='caption' style='position: absolute;bottom: 15px;'>";
    html += "<input type='hidden' name='ImageUrl' value='" + arr[1] + "' />";
    html += "<input type='hidden' name='Type' value='" + (length == 0 ? "3" : arr[2]) + "' />";
    html += "</div>";
    html += "<a href='javascript:void(0);' style='position: absolute;top:-4px;right:-4px;' class='' role='button' onclick=\"deleteImageDiv('imgDiv" + imageUrlId + i + "','" + uploadId + "')\"><img src='http://www.cardhobby.com/Content/img/unchecked.gif' /></a>";
    html += "</div>";
    html += "</div>";
    return html;
}

function deleteImageDiv(id, uploadId) {
    if ($("#addImagesWarrp").length) {
        $("#addImagesWarrp").show();
    }
    $("div").remove("#" + id);
    var imageCount = $('#' + uploadId).uploadify('settings', 'uploadLimit');
    $('#' + uploadId).uploadify('settings', 'uploadLimit', (imageCount + 1));
    //imgMasonry();
}

function reoffer() {
    var isblock = $(".cardform").css("display");
    if (isblock == "block") {
        $(".cardform").hide();
        $(".cardform-bottom").show();
    } else {
        $(".cardform").show();
        $(".cardform-bottom").hide();
    }
}

function hide() {
    $(".cardform").hide();
    $(".cardform-bottom").show();
}

function allselelcts(name, ts) {
    var _this = $(ts);
    var ds = $("input[name='" + name + "']");
    if (_this.prop("checked") == true) {
        ds.prop("checked", true)
    } else {
        ds.prop("checked", false)
    }

}

function del() {
    if (confirm("确定要删除所有选中的卡片吗？")) {
        var url = "/member/del/";
        Tips("正在提交您的请求，请稍后……");
        var form = $("#myform").serialize();
        if (form.length > 1) {
            $.post(url, form, function (data) {
                if (data == "1") {
                    Tips("卡片已全部删除！");
                    setTimeout(function () {
                        location.reload();
                    }, 1500);
                } else Tips(data, function () {
                    location.reload();
                });

            })
        } else Tips("您必须选择需要处理的卡片！");
    }
}

function put(op) {
    if (confirm("确定要" + (op == "on" ? "上架" : "下架") + "所有选中的卡片吗？")) {
        var url = "/member/put?op=" + op;
        Tips("正在提交您的请求，请稍后……");
        var form = $("#myform").serialize();
        if (form.length > 1) {
            $.post(url, form, function (data) {
                if (data == "1") {
                    Tips("卡片已全部" + (op == "on" ? "上架" : "下架"));
                    setTimeout(function () {
                        location.reload();
                    }, 1500);
                } else Tips(data, function () {
                    location.reload();
                });

            })
        } else Tips("您必须选择需要处理的卡片！");
    }
}

function addFavorite() {
    var url = window.location;
    var title = document.title;
    var ua = navigator.userAgent.toLowerCase();
    if (ua.indexOf("360se") > -1) {
        alert("由于360浏览器功能限制，请按 Ctrl+D 手动收藏！");
    } else if (ua.indexOf("msie 8") > -1) {
        window.external.AddToFavoritesBar(url, title); //IE8
    } else if (document.all) {
        try {
            window.external.addFavorite(url, title);
        } catch (e) {
            alert('您的浏览器不支持,请按 Ctrl+D 手动收藏!');
        }
    } else if (window.sidebar) {
        window.sidebar.addPanel(title, url, "");
    } else {
        alert('您的浏览器不支持,请按 Ctrl+D 手动收藏!');
    }
}

function nextitem(obj) {
    var id = $(obj).data("id");
    $(".account-head li").removeClass("active");
    $(".account-head li").find("span").each(function (i, item) {
        var text = $(item).html();
        if (text == id) {
            $(item).closest("li").addClass("active");
        }
    });
    $(".account-info li").hide();
    $(".account-info li").eq(id - 1).show();
}