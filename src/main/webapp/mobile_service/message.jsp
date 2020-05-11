<%--
  Created by IntelliJ IDEA.
  User: 麦子
  Date: 2019/12/18
  Time: 0:42
  To change this template use File | Settings | File Templates.
--%>

<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<jsp:useBean id="message" scope="session" type="java.lang.String"/>
<span style="font-size:18px;">

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<meta http-equiv='refresh' content='3;url=homepage.jsp'>
    <!--content='页面停留时间(这里单位是秒);url=跳转至另一页面'  -->
<script type='text/javascript'>
    var i=3;
    function getTime(){
        document.getElementById('num').innerHTML="<span style='color:red'>" + i + "</span>";
        i-=1;
        var x=setTimeout('getTime()',1000);//1000毫秒=1秒
        if(i<=0){
            clearTimeout(x);
        }
    }
    window.onload=getTime;//开始执行倒计时
</script>
<TITLE>messagePage</TITLE></HEAD>
<BODY>
	<br>
	<div style="text-align: center;">
		<h2>
			<%=session.getAttribute("message")%><br>
			注意!!页面将在<span id='num' style='display:inline'>3</span>秒后跳转至主页
		</h2>
	</div>
</BODY>
</HTML> </span>

