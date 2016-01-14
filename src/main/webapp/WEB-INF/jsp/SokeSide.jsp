<%-- 
    Document   : SokeSide.jsp
    Created on : 08.jan.2016, 15:27:40
    Author     : Sigrid
--%>
<%@page import="verktøy.Funksjoner"%>
<%@page import="java.util.ArrayList"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 

<%@page import="beans.Sok" %>

<jsp:useBean id="sokeord" class="beans.Sok"/>
<jsp:setProperty name="sokeord" property="*"/>


<!DOCTYPE html>
<html lang="no">
<body>
<div class="searchNav">
    <ul>
        <li><div><form action="search" method="POST"  role="search" modelAttribute="soke">
            <input class="searchForm" type=search name="sokeord" size="30">
            <input class="searchButton" value="Søk" type="submit"></form></div>

            <br>
            <div>
                <form class="checkboxes" action="">
                    <input type="checkbox" id="ansatt" name="Spes" value="Ansatt">Ansatt
                    <br>
                    <input type="checkbox" id="student" name="Spes" value="Student">Student
                    <br>
                    <input type="checkbox" id="fag" name="Spes" value="Fag">Fag
                    <br>
                    <input type="checkbox" id="rom" name="Spes" value="Rom">Rom
                    <br>
                    <input type="checkbox" id="klasse" name="Spes" value="Klasse">Klasse</form></div>
        </li>               
    </ul></div>

 
<div class="searchInfo">    
    <table>
            <c:forEach var="liste" items="${liste}">
                <tr>
                    <td><c:out value="${liste}"></c:out> </td>
                </tr>                              
            </c:forEach>                      
    </table>
</div>
</body>
</html>

    <script>
    var ansatt=$("#ansatt").attr("checked") ? 1 : 0;
    var student=$("#student").attr("checked") ? 1 : 0;
    var fag=$("#fag").attr("checked") ? 1 : 0;
    var rom=$("#rom").attr("checked") ? 1 : 0;
    var klasse=$("#klasse").attr("checked") ? 1 : 0;
   </script>