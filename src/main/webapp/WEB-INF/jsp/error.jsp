<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>
  <label>ERROR</label>
</h1>
<%
  out.println(request.getAttribute("error"));
%>
</body>
</html>
