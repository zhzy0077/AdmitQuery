<%--
  Created by IntelliJ IDEA.
  User: Zheng
  Date: 16/7/8
  Time: 15:24
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>南京邮电大学本科生录取查询系统</title>
    <meta http-equiv="X-UA-Compatible" content="chrome=1,IE=edge"/>
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta name="robots" content="noindex,nofollow">
    <link href="<c:url value="/css/index.css"/>" rel="stylesheet"/>
    <link href="http://libs.baidu.com/bootstrap/3.0.3/css/bootstrap.min.css" rel="stylesheet">
    <style>
        #login_btn_wraper {
            text-align: center;
        }

        #login_btn_wraper .tips_success {
            background-color: #57c556;
        }

        #login_btn_wraper .tips_error {
            background-color: #FF5252;
        }

        #login_btn_wraper button:focus {
            outline: none;
        }
    </style>
    <script>
        if (window.parent !== window.self) {
            document.write = '';
            window.parent.location.href = window.self.location.href;
            setTimeout(function () {
                document.body.innerHTML = '';
            }, 0);
        }
    </script>

</head>

<body>
<div class="logo">
    <img src="<c:url value="/images/logo.png"/>">
</div>
<div class="wrap">
    <div class="login">
        <h1>本科生录取查询系统</h1>
        <form>
            <div class="login">
                <ul>
                    <li>
                        <input class="input" id="studentId" required name="studentId" type="text" placeholder="考生号"/>
                    </li>
                    <li>
                        <input class="input" id="idCard" type="text" required name="idCard" placeholder="身份证"/>
                    </li>
                    <li class="verifycode-wrapper">
                        <img src="<c:url value="/captcha.jpg"/>"
                             style="width:50%;margin:0 auto;cursor:pointer; " alt="验证码" id="verifyimg">
                    </li>
                    <li>
                        <input class="input" type="text" name="verify" required placeholder="验证码" id="verify"/>
                    </li>
                </ul>
                <div id="login_btn_wraper">
                    <button type="button" class="btn js-ajax-submit" id="search" data-toggle="modal"
                            data-target=".bs-example-modal-sm">查询
                    </button>
                </div>
                <div class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog"
                     aria-labelledby="myModalLabel" aria-hidden="true">
                    <div class="modal-dialog modal-sm" style="width:340px;">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close"
                                        data-dismiss="modal" aria-hidden="false">
                                </button>
                                <h4 class="modal-title" id="myModalLabel">
                                    南邮本科录取查询结果
                                </h4>
                            </div>
                            <div class="modal-body">
                                查询中...
                            </div>
                        </div><!-- /.modal-content -->

                    </div>
                </div>
            </div>
        </form>
    </div>
</div>
</body>
<script src="http://cdn.bootcss.com/jquery/3.0.0/jquery.min.js"></script>
<script src="http://libs.baidu.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        $("#verifyimg").click(function () {
            $(this).attr("src", "<c:url value="/captcha.jpg"/>?" + Math.random());
        });
        $("#search").click(function () {
            var studentId = $('#studentId').val();
            var idCard = $('#idCard').val();
            var verify = $('#verify').val();
            $("#search").html("正在查询……");
            $.post("<c:url value="/student"/>",
                    {"studentId": studentId, "idCard": idCard, "captcha": verify},
                    function (back) {
                        if (back.error_code == 0) {
                            if (back.data) {
                                if (back.data.bell == 1) {
                                    back.data.bell = "是";
                                } else {
                                    back.data.bell = "否";
                                }
                                $("#search").html("恭喜您被录取了！");
//                                $("#search").addClass("tips_success");
                                if (!back.data.ems) {
                                    $(".modal-body").html("<h3 style='color:red'>恭喜您！您已被南京邮电大学录取！</h3><p>姓名：" + back.data.name + "</p><p>录取专业：" + back.data.major + "</p><p>是否具有贝尔英才学院选拔资格：" + back.data.bell + "</p><p>录取通知书尚未寄出，请过段时间再次登录该页面查询是否寄出</p>");
                                } else {
                                    $(".modal-body").html("<h3 style='color:red'>恭喜您！您已被南京邮电大学录取！</h3><p>姓名：" + back.data.name + "</p><p>录取专业：" + back.data.major + "</p><p>是否具有贝尔英才学院选拔资格：" + back.data.bell + "</p><p>录取通知书已经寄出，EMS编号为<strong>" + back.data.ems + "</strong>，可登录EMS官方网站<a target='_blank' href='http://www.ems.com.cn'>www.ems.com.cn</a>查询邮件状态</p>");
                                }
                            }

                        } else if (back.error_code == 1) {
                            $("#search").html("未查询到录取信息！");
                            $(".modal-body").html("<h4>对不起！库中无匹配信息，可能原因如下：</h4><p>1、录取信息尚未入库</p><p>2、您输入的信息有误</p><p>3、您未被南京邮电大学录取</p>");
//                            $("#search").addClass("tips_error");
                        } else if (back.error_code == 2) {
                            $("#search").html("验证码错误，请重试！");
                            $(".modal-body").html("验证码错误，请重试！");
                            $("#search").html("正在查询……");
                        } else {
                            $("#search").html("未知错误，请稍后重试");
//                            $("#search").addClass("tips_error");
                        }
                        $("#search").html("查询");
                        $("#verifyimg").attr("src", "<c:url value="/captcha.jpg"/>?" + Math.random());
                    });
//            window.location.reload(true);

        });
    });
</script>
</html>