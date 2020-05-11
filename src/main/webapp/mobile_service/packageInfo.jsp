<%@ page import="com.mobile_service.entity.PackageInfo" %><%--
  Created by IntelliJ IDEA.
  User: 麦子
  Date: 2019/12/19
  Time: 21:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>packageInfoPage</title>
</head>
<body>
<div style="color:#4d91ff;font-family: 微软雅黑,cursive;text-align: center">
    <h1>嗖嗖移动套餐一览无余</h1>
</div>
<fieldset style="text-align: center;font-family: 微软雅黑,monospace;font-size: large">
    <table border="0">
        <tr>
            <td>套餐种类</td>
            <td>通话时长/min</td>
            <td>流量/MB</td>
            <td>短信数/条</td>
            <td>资费/元</td>
        </tr>
        <tr>
            <td>话痨套餐</td>
            <td><%=((PackageInfo)session.getAttribute("talkingTooMuch")).getCall()%></td>
            <td><%=((PackageInfo)session.getAttribute("talkingTooMuch")).getData()%></td>
            <td><%=(int)((PackageInfo)session.getAttribute("talkingTooMuch")).getText()%></td>
            <td><%=((PackageInfo)session.getAttribute("talkingTooMuch")).getFee()%></td>
        </tr>
        <tr>
            <td>网虫套餐</td>
            <td><%=((PackageInfo)session.getAttribute("netWorm")).getCall()%></td>
            <td><%=((PackageInfo)session.getAttribute("netWorm")).getData()%></td>
            <td><%=(int)((PackageInfo)session.getAttribute("netWorm")).getText()%></td>
            <td><%=((PackageInfo)session.getAttribute("netWorm")).getFee()%></td>
        </tr>
        <tr>
            <td>超人套餐</td>
            <td><%=((PackageInfo)session.getAttribute("superman")).getCall()%></td>
            <td><%=((PackageInfo)session.getAttribute("superman")).getData()%></td>
            <td><%=(int)((PackageInfo)session.getAttribute("superman")).getText()%></td>
            <td><%=((PackageInfo)session.getAttribute("superman")).getFee()%></td>
        </tr>
        <tr>
            <td>超出套餐计费</td>
            <td><%=((PackageInfo)session.getAttribute("out")).getCall()%></td>
            <td><%=((PackageInfo)session.getAttribute("out")).getData()%></td>
            <td><%=((PackageInfo)session.getAttribute("out")).getText()%></td>
            <td>0</td>
        </tr>

    </table>
</fieldset>
<br><br>
<a href="register.jsp" style="text-align: left;font-size: smaller;font-family: 微软雅黑,monospace">返回</a>
</body>
</html>
