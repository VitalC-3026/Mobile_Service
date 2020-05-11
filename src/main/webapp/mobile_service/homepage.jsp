<%--
  Created by IntelliJ IDEA.
  User: 麦子
  Date: 2019/12/9
  Time: 8:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>HomePage</title>
    </head>
    <script>
    </script>
    <body style="text-align: center">

    <div style="color:#4d91ff;font-size: xx-large;font-family: 微软雅黑,cursive">
        <h1>嗖嗖移动业务大厅欢迎您</h1>
    </div>
        <br><br><br>
        <b2>请选择</b2>
    <br><br>
        <form action="/Java12_war_exploded2/homepageRedirect" method="get">
            <input type="submit" name = "choice" value="注册" />
            <input type="submit" name = "choice" value="登录" />
            <input type="submit" name = "choice" value="使用移动" />
        </form>
    <br><br><br>
    <pre><%
        if(session.getAttribute("message") != null){
            if(session.getAttribute("userInfo") != null) {
                out.print("<fieldset style=\"text-align: center;font-size: large;font-family: 微软雅黑,monospace\">\n" +
                    "        <legend style=\"font-size: larger;font-family: 微软雅黑,monospace\">成功注册信息</legend>" +
                    (String)session.getAttribute("userInfo") +
                    "</fieldset><br><br><br>");
                session.setAttribute("userInfo", null);
            }

        }
        if(session.getAttribute("exception") != null){
            out.print("<fieldset style=\"text-align: center;font-size: large;font-family: 微软雅黑,monospace\">\n" +
                    "        <legend style=\"text-align: center;font-size: larger;font-family: 微软雅黑,monospace\">这里出现了一些小故障</legend>" +
                    (String)session.getAttribute("exception") +
                    "</fieldset><br><br><br>");
            session.setAttribute("exception",null);
        }
    %></pre>
    </body>
</html>
