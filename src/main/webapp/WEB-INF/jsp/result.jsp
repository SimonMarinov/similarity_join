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
<body style="background-color:moccasin">
<h3>
    <label>Similarity Join Result</label>
</h3>

<p>
<a href="/">
    <input type="button" value="Go back" style="height:50px;font-size:30px"/>
</a>
</p>
<%

    for (Pair<MyImage, List<Pair<MyImage, Double>>> imgRes : (List<Pair<MyImage, List<Pair<MyImage, Double>>>>) request.getAttribute("result") ) {

        out.println("<br>");
        out.println(imgRes.first.getName() + "<hr>");

        out.println("<table><tr>");

        out.println("<td><br><img src=\"images/" + imgRes.first.getName().replaceAll("/","&") + "\" width=\"200\" height=\"200\"></td>");
        //print splitter
        out.println("<td><pre>      </pre></td>");
        //print similiar images
        for(Pair<MyImage, Double> similarImg :imgRes.second){
            out.println("<td>" + similarImg.second);
            out.println("<br><img src=\"images/" + similarImg.first.getName().replaceAll("/","&")+ "\" width=\"200\" height=\"200\"></td>");
            out.println("<td><pre>  </pre></td>");

        }
        out.println("</tr></table>");
        out.println("<br><br>");

    }

%>




<br>
</body>
</html>