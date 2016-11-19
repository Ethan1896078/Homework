<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%
  String basePath = request.getContextPath();
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <title>发表文章</title>
  <script type="text/javascript" src="<%=basePath%>/js/jquery-1.7.1.js"></script>
</head>
<body>
<h1>request status</h1>
status = ${requestStatus.status}<br/>
errorCode = ${requestStatus.errorCode}<br/>
errorMsg = ${requestStatus.errorMsg}<br/>

<h1>article list</h1>
<div>
  <c:forEach items="${list}" var="item" varStatus="i">
    ${item}<br/>
  </c:forEach>
</div>
</body>
</html>