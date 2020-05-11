<%@ page import="java.util.Map" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="form1" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 麦子
  Date: 2019/12/9
  Time: 21:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>UseServicePage</title>
</head>
<body>
<div style="color:#4d91ff;font-family: 微软雅黑,cursive;text-align: center">
    <h2>欢迎使用嗖嗖移动服务</h2><br><br><br/>
</div>


<form action="/Java12_war_exploded2/useCheck" method="post">
    <input type="hidden" id="submit" name="submit" value="1">
    <div style="font-size:large;font-family: 微软雅黑,monospace;text-align: center">
        电话号码:
        <input type="text" name="Telephone">
        <input type="submit" value="使用">
        <input type="submit" value="返回" onclick="this.form.submit.value='0'">
    </div>
</form>

<pre><%
    String message = (String) session.getAttribute("message");

    if("登录成功".equals(message)){
        Map userScene = (Map)session.getAttribute("setScene");
        String scene = (String) userScene.get("scene");
        out.println("<form action=\"/Java12_war_exploded2/UserScene\" method=\"post\">" +
                "    <input type=\"hidden\" id=\"submit\" name=\"submit\" value=\"1\">" +
                "    <fieldset style=\"font-size:large;font-family: 微软雅黑,monospace;text-align: center\">" +
                "        <legend style=\"font-size:larger;font-family: 微软雅黑,monospace;text-align: center\">使用场景</legend>" +
                scene +
                " <br>" +
                " <input type=\"submit\" value=\"确认场景\" onclick=\"this.form.submit.value='1'\">&nbsp;" +
                " <input type=\"submit\" value=\"更换场景\" onclick=\"this.form.submit.value='2'\">" +
                " <br>" +
                "    </fieldset><br>\n" +
                " <fieldset style=\"font-size:large;font-family: 微软雅黑,monospace;text-align: center\">" +
                " <legend style=\"font-size:larger;font-family: 微软雅黑,monospace;text-align: center\">自定义场景</legend>" +
                " <input type=\"radio\" name=\"scene\" value=\"1\">通话" +
                " <input type=\"radio\" name=\"scene\" value=\"2\">上网" +
                " <input type=\"radio\" name=\"scene\" value=\"3\">短信" +
                " <br><br>" +
                " To: <input type=\"text\" name=\"toTelephone\">" +
                " 通话时长: <input type=\"number\" name=\"Call\"> 分钟" +
                " 上网流量: <input type=\"number\" name=\"Surf\"> MB" +
                " 短信条数: <input type=\"number\" name=\"Text\"> 条" +
                "<br><br><br>" +
                " <input type=\"submit\" value=\"确认\" onclick=\"this.form.submit.value='3'\">"+
                " <input type=\"reset\" id=\"reset\" name=\"reset\" value=\"重置\" onclick=\"this.form.submit.value='4'\">"+
                " </fieldset>" +
                " </form>");
        if(session.getAttribute("ServiceInfo")!=null){
            out.println("<fieldset style=\"font-size:large;font-family: 微软雅黑,monospace;text-align: center\">" +
                    "    <legend style=\"font-size:larger;font-family: 微软雅黑,monospace;text-align: center\">信息</legend><br><br>" +
                         session.getAttribute("ServiceInfo") +
                    "    <br><br> </fieldset>");
        }
        else{
            out.println("<fieldset style=\"font-size:large;font-family: 微软雅黑,monospace;text-align: center\">" +
                    "    <legend style=\"font-size:larger;font-family: 微软雅黑,monospace;text-align: center\">信息</legend>" +
                    "    这里空空如也!" +
                    "    <br><br> </fieldset>");
        }
    }
    else if("登录失败".equals(message)){
        out.println(
                "<fieldset style=\"font-size:large;font-family: 微软雅黑,monospace;text-align: center\">" +
                        "    <legend style=\"font-size:larger;font-family: 微软雅黑,monospace;text-align: center\">信息</legend>" +
                        "    登录失败!请重新输入电话号码!<br>" +
                        "    仔细回想，嗖嗖一直等你!" +
                        "    <br><br> </fieldset>");
    }
%></pre>

</body>
</html>
