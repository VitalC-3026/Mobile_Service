<%@ page import="com.mobile_service.entity.Consumption" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: 麦子
  Date: 2019/12/18
  Time: 15:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="div" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>consumptionRecord</title>


<body>
<div style="color:#4d91ff;font-family: 微软雅黑,cursive;text-align: center">
    <h1>嗖嗖移动业务大厅竭诚为您服务</h1>
</div>
<br><br><br>
    <div style="font-size: smaller;text-align: right;font-family: 微软雅黑,monospace">
    <label style="text-align: right">单位:  通话:min; 流量:MB; 短信:条</label>
    </div><br>
<fieldset style="text-align: center">
    <pre>
        <%
            List<Consumption> consumptionList = (List<Consumption>) session.getAttribute("consumptionList");
            out.println("<legend style=\"text-align: center;font-size: larger;font-family: 微软雅黑,monospace\">用户"+consumptionList.get(0).getTelephone()+"的消费记录</legend>" +
                    "<table align=\"center\" style=\"text-align: center\">\n" +
                    "        <tr>\n" +
                    "            <td>消费类型</td>\n" +
                    "            <td>消费量</td>\n" +
                    "            <td>消费时间</td>\n" +
                    "        </tr>\n");
            for(Consumption consumption: consumptionList){
                out.println(
                        "     <tr>\n" +
                        "         <td>"+consumption.getMode()+"</td>\n" +
                        "         <td>"+consumption.getFlow()+"</td>\n" +
                        "         <td>"+consumption.getSave_time()+"</td>\n" +
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
