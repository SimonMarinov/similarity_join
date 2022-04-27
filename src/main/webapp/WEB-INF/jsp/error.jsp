<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>error</title>
</head>
<body style="background-color:moccasin">
<h1>
  <label>ERROR</label>
</h1>
<%
  out.println(request.getAttribute("error"));
%>

<p>
    <a href="/">
        <input type="button" value="Go back" style="height:50px;font-size:30px"/>
    </a>
</p>

</body>
</html>
