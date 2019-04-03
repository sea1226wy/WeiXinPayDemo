<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!doctype html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; chartset=UTF-8">
        <title>支付页面测试</title>
    </head>
    <body>
    商品信息:xxxx
    <img src="loadPayImage">


        <script type="text/javascript">
            function orderStatus() {
                $.post("loadPayState",{},function(data){
                    if(data==1){
                        window.location.href="index.jsp";
                    }
                })
            }
            setInterval("orderStatus()",3000);//每隔3秒 去访问支付是否成功
        </script>
    </body>
</html>