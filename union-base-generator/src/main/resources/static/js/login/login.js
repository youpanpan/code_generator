$(document).ready(function() {
    layui.use(['form', 'layer'], function() {
        var $ = layui.jquery,
            form = layui.form,
            layer = layui.layer;

        var USER_NAME = "USER_NAME";
        var PASSWORD = "PASSWORD";
        var REMBER = "REMBER";

        /**
         * 初始化登录信息
         * */
        function init() {
            var userName = cookieUtil.getCookie(USER_NAME);
            var password = cookieUtil.getCookie(PASSWORD);
            var rember = cookieUtil.getCookie(REMBER);
            $("input[name='userName']").val(userName);
            $("input[name='password']").val(password);
            if (rember == '1') {
                $("input[name='rember']").prop("checked", true);
            }
            form.render();
        }
        init();


        // 登录
        form.on('submit(login-filter)', function(data){
            var index = layer.load(2);
            var rember = data.field.rember == 'on';
            var userName = data.field.userName;
            var password = data.field.password;

            $.ajax({
                url: '/login/do-login',
                type: 'POST',
                data: data.field,
                dataType: 'json',
                success: function(data) {
                    layer.close(index);
                    if (data.success) {
                        layer.msg(LOGIN_SUCCESS, {icon: 6});
                        if (rember) {
                            cookieUtil.setCookie(USER_NAME, userName, Number.MAX_VALUE);
                            cookieUtil.setCookie(PASSWORD, password, Number.MAX_VALUE);
                            cookieUtil.setCookie(REMBER, rember?1:0, Number.MAX_VALUE);
                        } else {
                            cookieUtil.removeCookie(USER_NAME);
                            cookieUtil.removeCookie(PASSWORD);
                            cookieUtil.removeCookie(REMBER);
                        }
                        window.location.href = "/";
                    } else {
                        layer.msg(data.message, {icon: 5});
                    }
                },
                error: function() {
                    layer.close(index);
                    layer.alert(NETWORK_TIMEOUT, {icon: 5});
                }
            });

            return false;
        });

        $(document).on('click', '.toolbar a[data-target]', function (e) {
            e.preventDefault();
            var target = $(this).data('target');
            $('.widget-box.visible').removeClass('visible');//hide others
            $(target).addClass('visible');//show target
        });

        $('#btn-login-dark').on('click', function (e) {
            $('body').attr('class', 'login-layout');
            $('#id-text2').attr('class', 'white');
            $('#id-company-text').attr('class', 'blue');

            e.preventDefault();
        });
        $('#btn-login-light').on('click', function (e) {
            $('body').attr('class', 'login-layout light-login');
            $('#id-text2').attr('class', 'grey');
            $('#id-company-text').attr('class', 'blue');

            e.preventDefault();
        });
        $('#btn-login-blur').on('click', function (e) {
            $('body').attr('class', 'login-layout blur-login');
            $('#id-text2').attr('class', 'white');
            $('#id-company-text').attr('class', 'light-blue');

            e.preventDefault();
        });

    });
});