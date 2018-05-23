<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/4/22
  Time: 21:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body onload="onTime()">
 <h5>大兄弟，必须要添加商家名,也可能商家名已经被占用了呀，<br>再见！</h5>
 <span id="second"></span>
 <%
     response.setHeader("refresh","3;URL=addModifyPage.jsp");
 %>
<script src="js/Modify.js"></script>
</body>
</html>
