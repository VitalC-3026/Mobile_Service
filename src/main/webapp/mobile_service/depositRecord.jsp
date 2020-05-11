<%@ page import="com.mobile_service.entity.Deposit" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: 麦子
  Date: 2019/12/15
  Time: 20:45
  To change this template use File | Settings | File Templates.
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>DepositRecord</title>
</head>
<body>
<div style="color:#4d91ff;font-family: 微软雅黑,monospace;text-align: center">
    <h1>嗖嗖移动业务大厅竭诚为您服务</h1>
</div>
<br><br><br>

<fieldset style="text-align: center">
    <pre>
        <%
            List<Deposit> depositList = (List<Deposit>) session.getAttribute("depositList");
            out.println("<legend style=\"text-align: center;font-size: larger;font-family: 微软雅黑,monospace\">用户"+depositList.get(0).getTelephone()+"的充值记录</legend>" +
                    "<table align=\"center\" style=\"text-align: center\">\n" +
                    "        <tr>\n" +
                    "            <td>充值/元</td>\n" +
                    "            <td>充值时间</td>\n" +
                    "        </tr>\n");
            for(Deposit deposit: depositList){
                out.println(
                        "     <tr>\n" +
                                "         <td>"+deposit.getMoney()+"</td>\n" +
                                "         <td>"+deposit.getDepositTime()+"</td>\n" +
                        "     </tr>\n" );
            }
            out.println("</table>");
        %>
    </pre>
</fieldset>
<br>
<a href="useService.jsp" style="text-align: left;font-size: smaller;font-family: 微软雅黑,monospace">返回</a>
</body>
</html>
