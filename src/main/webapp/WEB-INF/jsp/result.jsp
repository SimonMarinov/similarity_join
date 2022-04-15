<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.marinsim.similarity_join.backEnd.Pair" %>
<%@ page import="com.marinsim.similarity_join.backEnd.MyImage" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html>
<head>
    <title>results</title>
</head>
<body style="white-space:nowrap">
<h3>
    <label>Similarity Join</label>
</h3>

<%

    for (Pair<MyImage, List<Pair<MyImage, Double>>> imgRes : (List<Pair<MyImage, List<Pair<MyImage, Double>>>>) request.getAttribute("result") ) {

        out.println(imgRes.first.getName() + "<hr><br>");

        out.println("<table><tr>");

        out.println("<td><br><img src=\"tmp/images/" + imgRes.first.getName()+ "\" width=\"200\" height=\"200\"></td>");
        //print splitter
        out.println("<td><br><img src=\"black.png\"width=\"15\" height=\"200\"></td>");
        //print similiar images
        for(Pair<MyImage, Double> similarImg :imgRes.second){
            out.println("<td>" + similarImg.second);
            out.println("<br><img src=\"tmp/images/" + similarImg.first.getName() + "\" width=\"200\" height=\"200\"></td>");
            out.println("<td><br><img src=\"black.png\"width=\"10\" height=\"200\"></td>");

        }
        out.println("</tr></table>");
    }

%>


<br>
</body>
</html>