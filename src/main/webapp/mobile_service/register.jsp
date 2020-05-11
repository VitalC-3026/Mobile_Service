<%--
  Created by IntelliJ IDEA.
  User: 麦子
  Date: 2019/12/9
  Time: 10:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>RegisterPage</title>
</head>
<body style="text-align: center">
<div style="font-size: xx-large;font-family: 微软雅黑,cursive;text-align: center;color:#4d91ff">
    <h2>终于等到您，欢迎注册嗖嗖移动服务</h2>
</div>
<pre><%
    if(session.getAttribute("error") != null){
        out.println("<fieldset style=\"text-align: center\">\n" +
                "        <legend>错误信息</legend><br>" +
                session.getAttribute("error") +
                "</fieldset><br>");
        session.setAttribute("error", null);
    }
%></pre>
    <form action="/Java12_war_exploded2/loginPage" method="post">
        <fieldset style="text-align: center;font-size: large;font-family: 微软雅黑,monospace">
            <legend style="font-size: larger;font-family: 微软雅黑,monospace">请完成信息的填写和手机卡的注册</legend><br>
                用户名:
                <input type="text" name="username" required>
            <br>
                密码:
                <input type="password" name="password" required><br>
                请再输入一遍:
                <input type="password" name="password2" required>
           <br><br><br>
                <!--如何显示随机生成的手机号码-->
                移动服务为您精挑细选了9个电话号码，请您选择您心仪的手机号码的序号<br><br>
                <input type="radio" name="Telephone" value="0">1. <%=((String[])session.getAttribute("Cards"))[0]%>
                <input type="radio" name="Telephone" value="1">2. <%=((String[])session.getAttribute("Cards"))[1]%>
                <input type="radio" name="Telephone" value="2">3. <%=((String[])session.getAttribute("Cards"))[2]%><br><br>
                <input type="radio" name="Telephone" value="3">4. <%=((String[])session.getAttribute("Cards"))[3]%>
                <input type="radio" name="Telephone" value="4">5. <%=((String[])session.getAttribute("Cards"))[4]%>
                <input type="radio" name="Telephone" value="5">6. <%=((String[])session.getAttribute("Cards"))[5]%><br><br>
                <input type="radio" name="Telephone" value="6">7. <%=((String[])session.getAttribute("Cards"))[6]%>
                <input type="radio" name="Telephone" value="7">8. <%=((String[])session.getAttribute("Cards"))[7]%>
                <input type="radio" name="Telephone" value="8">9. <%=((String[])session.getAttribute("Cards"))[8]%><br><br><br>
                嗖嗖移动服务为您提供了三种套餐<br><br>
                <table border="1" align="center" width="50%" style="width:80%">
                    <tr>
                        <td>套餐种类</td>
                        <td>月资费</td>
                        <td>选择</td>
                    </tr>
                    <tr>
                        <td>话痨套餐</td>
                        <td>￥58</td>
                        <td><input type="radio" name="PackageName" value="1"></td>
                    </tr>
                    <tr>
                        <td>网虫套餐</td>
                        <td>￥68</td>
                        <td><input type="radio" name="PackageName" value="2"></td>
                    </tr>
                    <tr>
                        <td>超人套餐</td>
                        <td>￥78</td>
                        <td><input type="radio" name="PackageName" value="3"></td>
                    </tr>
                </table>

                <%--<input type="submit" name="packageInfo" onclick="this.form.submit.value='1'" style="font-size: smaller">&nbsp;&nbsp;--%>
            <a href="http://localhost:8080/Java12_war_exploded2/packageInfoDisplay" >套餐详细信息</a><br>
            <br><br>
                充值:
                <input type="number" name="deposit" min="100" required><br>
            <div style="font-size: x-small;color: rgba(83,80,79,0.73)">
                请至少充值100元并输入整数。
            </div>
            <br><br>
            <input type="hidden" id="submit" name="submit" value="0">
            <input type="submit" name="submit" value="注册" onclick="this.form.submit.value = '1'">&nbsp;
            <input type="reset" value="重置" id="reset" name="reset">
            <input type="submit" name="submit" value="退出" onclick="window.location='http://localhost:8080/Java12_war_exploded2/mobile_service/homepage.jsp'">
        </fieldset>
    </form>




</body>
</html>
