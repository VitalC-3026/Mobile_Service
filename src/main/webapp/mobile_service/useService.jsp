<%--
  Created by IntelliJ IDEA.
  User: 麦子
  Date: 2019/12/10
  Time: 9:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>办理业务</title>
</head>
<body>
<div style="color:#4d91ff;font-family: 微软雅黑,monospace">
    <h1>嗖嗖移动业务大厅欢迎您</h1>
</div>
<form action="/Java12_war_exploded2/Service" method="post">
    <fieldset style="font-size:large;font-family: 微软雅黑,monospace;text-align: center">
        <legend style="font-size:larger;font-family: 微软雅黑,monospace;text-align: center">请选择您需要办理的业务种类:</legend>
        <input type="hidden" id="submit" name="submit" value="-1">
        主套餐:
        <input type="radio" name="MonthlyPackage" value="1">话痨套餐
        <input type="radio" name="MonthlyPackage" value="2">网虫套餐
        <input type="radio" name="MonthlyPackage" value="3">超人套餐
        <input type="reset" name="reset" value="重置" id="reset1">
        <br><br>
        流量包:
        <input type="radio" name="DataPack" value="1">1GB
        <input type="radio" name="DataPack" value="2">2GB
        <input type="radio" name="DataPack" value="3">3GB
        <input type="reset" name="reset" value="重置" id="reset2">
        <br><br>
        生活服务:
        <input type="radio" name="LifeService" value="1">视频VIP
        <input type="radio" name="LifeService" value="2">音乐VIP
        <input type="reset" name="reset" value="重置" id="reset3">
        <br><br>
        话费充值: <input type="text" name="deposit">  <input type="reset" name="reset" value="重置" id="reset4"><br><br>
        办理退网: <input type="submit" value="确认退网" onclick="this.form.submit.value='0'">
    </fieldset><br>
    <input type="submit" value="提交" style="text-align: left">
<br><br>

    <fieldset style="font-size:large;font-family: 微软雅黑,monospace;text-align: center">
        <legend style="font-size:larger;font-family: 微软雅黑,monospace;text-align: center">请选择您需要查询的业务种类:</legend>

        <table style="font-size:large;font-family: 微软雅黑,monospace" align="center">
            <tr>
                <td>本月账单</td>
                <td>本月套餐余量</td>
                <td>缴费记录</td>
                <td>消费记录</td>
            </tr>
            <tr>
                <td><input type="submit" value="查看" onclick="this.form.submit.value='1'"></td>
                <td><input type="submit" value="查看" onclick="this.form.submit.value='2'"></td>
                <td><input type="submit" value="查看" onclick="this.form.submit.value='3'"></td>
                <td><input type="submit" value="查看" onclick="this.form.submit.value='4'"></td>
            </tr>
        </table>
    </fieldset><br>
    <input type="submit" value="返回" onclick="this.form.submit.value='5'">
</form>
<br><br>
<fieldset style="font-size:large;font-family: 微软雅黑,monospace;text-align: center">
    <legend style="font-size:larger;font-family: 微软雅黑,monospace;text-align: center">您查询的信息如下:</legend>
    <pre><%
        String bill = (String)session.getAttribute("bill");
        String left = (String)session.getAttribute("left");
        if(bill!=null){
            out.println("用户本月账单信息:");
            out.println(bill);
            session.setAttribute("bill", null);
        }
        else if(left!=null){
            out.println("用户本月套餐余量:");
            out.println(left);
            session.setAttribute("left", null);
        }
        else{
            out.println("这里空空如也!");
        }
    %></pre>
</fieldset>
</body>
</html>
