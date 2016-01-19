<%-- 
    Document   : SokeSide.jsp
    Created on : 08.jan.2016, 15:27:40
    Author     : Sigrid
--%>
<%@page import="verktøy.Funksjoner"%>
<%@page import="java.util.ArrayList"%>
<%@page import="static java.lang.System.out"%>
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
    <ul><form action="search" method="POST"  role="search" modelAttribute="soke">
        <li><div>
            <input id="searchFormSokeSide" class="searchForm" type=search name="sokeord" size="30">
            <input id="searchButtonSokeSide" class="searchButton" value="Søk" type="submit"></div>
            <br>
            <div>              
                    <input class="checkboxes" type="checkbox" id="ansattSokeSide" name="Spes" value="Ansatt">Ansatt
                    <br>
                    <input class="checkboxes" type="checkbox" id="studentSokeSide" name="Spes" value="Student">Student
                    <br>
                    <input class="checkboxes" type="checkbox" id="fagSokeSide" name="Spes" value="Fag">Fag
                    <br>
                    <input class="checkboxes" type="checkbox" id="romSokeSide" name="Spes" value="Rom">Rom
                    <br>
                    <input class="checkboxes" type="checkbox" id="klasseSokeSide" name="Spes" value="Klasse">Klasse</form></div>
        </li></form>               
    </ul></div>
    
<div class="searchInfo">
   
    <table>

            <c:forEach var="liste" items="${liste}">
                <tr>
                    <td><c:out value="${liste}"></c:out>
                        <form:form modelAttribute="bruker" action="BrukerOversikt">
                        <form:input type="hidden" path="etternavn" value="${liste}"/>
                        <%--<input class=oversikt type='submit' name='knappTilOversikt' value='Se oversikt'/>--%>
                        <input class="oversikt" id="abonKnapp" type='submit' name='knappTilAbonnement' value='Abonner'/>
                        </form:form>
                    </td>
                </tr>                    
                          
            </c:forEach>                      
    </table>
</div>         
</body>
</html>
