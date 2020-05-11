<%--
  Created by IntelliJ IDEA.
  User: 麦子
  Date: 2019/12/9
  Time: 21:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>LoginPage</title>
</head>
<body style="text-align: center">
<div style="color:#4d91ff;font-size: xx-large;font-family: 微软雅黑,cursive">
    <h1>欢迎登录嗖嗖移动服务大厅</h1>
</div><br><br><br/>
    <form action="/Java12_war_exploded2/loginCheck" method="post" style="font-size: large;font-family: 微软雅黑,monospace">
        电话号码:
        <input type="text" name="Telephone"><br><br>
        密码:
        <input type="password" name="password"><br><br>
        <input type="hidden" id="submit" name="submit" value="1">
        <input type="submit" value="登录" onclick="this.form.submit.value='1'">&nbsp;&nbsp;
        <input type="reset" name="reset" id="reset" value="重置" onclick="this.form.submit.value='2'">&nbsp;&nbsp;
        <input type="submit" value="忘记密码" onclick="this.form.submit.value='3'"><br>

        <pre>
            <%
                if(session.getAttribute("resetPassword") != null){
                    out.println("电话号码:" +
                            "        <input type=\"text\" name=\"Telephone\"><br>" +
                            "        验证码:" +
                            "        <input type=\"text\" name=\"checkCode\"><br>" +
                            "        新置密码:" +
                            "        <input type=\"password\" name=\"password1\"><br>" +
                            "        再输一遍:" +
                            "        <input type=\"password\" name=\"password2\"><br>" +
                            "        <input type=\"submit\" value=\"确认\" onclick=\"this.form.submit.value='4'\">&nbsp;&nbsp;" +
                            "        <input type=\"reset\" name=\"reset\" id=\"reset1\" value=\"重置\" >&nbsp;&nbsp;");
                    session.setAttribute("resetPassword", null);
                }
            %>
        </pre>
    </form>

</body>
</html>
