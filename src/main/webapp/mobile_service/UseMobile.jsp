<%--
  Created by IntelliJ IDEA.
  User: 麦子
  Date: 2019/12/18
  Time: 21:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>使用服务</title>
</head>
<body>
<div style="color:#4d91ff;font-family: 微软雅黑,cursive">
    <h1>嗖嗖移动伴你左右</h1>
</div>
<pre><%
    String message = (String) session.getAttribute("message");
    if("登录成功".equals(message)){
        out.println("<form action=\"/UserScene\" method=\"post\">\n" +
                "    <input type=\"hidden\" id=\"submit\" name=\"submit\" value=\"1\">\n" +
                "    <fieldset style=\"text-align: left;font-size: medium\">\n" +
                "        <legend>使用场景</legend>\n" +
                        (String)session.getAttribute("UserScene")+"\n" +
" <br>\n" +
" <input type=\"submit\" value=\"确认场景\" onclick=\"this.form.submit.value='1'\">\n" +
" <input type=\"submit\" value=\"更换场景\" onclick=\"this.form.submit.value='2'\">\n" +
" <br>\n" +
"    </fieldset>\n" +
" <fieldset style=\"text-align: left;font-size: medium\">\n" +
    " <legend>自定义场景</legend>\n" +
    " <input type=\"radio\" name=\"scene\" value=\"1\">通话\n" +
    " <input type=\"radio\" name=\"scene\" value=\"2\">上网\n" +
    " <input type=\"radio\" name=\"scene\" value=\"3\">短信\n" +
    " <br><br>\n" +
    " To: <input type=\"text\" name=\"toTelephone\">\n" +
    " 通话时长: <input type=\"number\" name=\"Call\"> 分钟\n" +
    " 上网流量: <input type=\"number\" name=\"Surf\"> MB\n" +
    " 短信条数: <input type=\"number\" name=\"Text\"> 条\n" +
    " <input type=\"submit\" value=\"确认\" onclick=\"this.form.submit.value='3'\">\n"+
    " <input type=\"reset\" id=\"reset\" name=\"reset\" value=\"重置\" onclick=\"this.form.submit.value='4'\">\n"+
    " </fieldset>\n" + "</form>");
        if(session.getAttribute("ServiceInfo")!=null){
            out.println("<fieldset style=\"text-align: left;font-size: medium\">\n" +
                    "    <legend>信息</legend><br><br>\n" +
                    "    session.getAttribute(\"ServiceInfo\")\n" +
                    "    <br><br> </fieldset>");
        }
        else{
            out.println("<fieldset style=\"text-align: left;font-size: medium\">\n" +
                    "    <legend>信息</legend><br><br> \n" +
                    "    这里空空如也！\n" +
                    "    <br><br> </fieldset>");
        }

    }
    else{
        out.println(
            "<fieldset style=\"text-align: left;font-size: medium\">\n" +
                    "    <legend>信息</legend><br><br>\n" +
                    "    登录失败!请重新输入电话号码!<br>\n" +
                    "    仔细回想，嗖嗖一直等你!\n" +
                    "    <br><br> </fieldset>");
    }

%></pre>

<fieldset style="text-align: left;font-size: medium">
    <legend>信息</legend><br><br>
    session.getAttribute("ServiceInfo")
    <br><br> </fieldset>

</body>
</html>
