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

<main>
<div class="searchNav">
        <ul class="tekst"><form action="search" method="POST"  role="search" modelAttribute="soke">
                <li>
                    <div>
                        <input id="searchFormSokeSide" class="searchForm" type=search name="sokeord" size="30" placeholder="Søk her...">
                        <button id="searchButtonSokeSide" class="defaultKnapp" type="submit"><span class="fontawesome-search"></span></button>
                        
                        <br>
                        <br>
                        
                    <div class="tekst">              
                            <input class="checkboxes" type="checkbox" id="ansattSokeSide" name="Spes" value="Ansatt">Ansatt
                            <br>
                            <input class="checkboxes" type="checkbox" id="studentSokeSide" name="Spes" value="Student">Student
                            <br>
                            <input class="checkboxes" type="checkbox" id="fagSokeSide" name="Spes" value="Fag">Fag
                            <br>
                            <input class="checkboxes" type="checkbox" id="romSokeSide" name="Spes" value="Rom">Rom
                            <br>
                            <input class="checkboxes" type="checkbox" id="klasseSokeSide" name="Spes" value="Klasse">Klasse
                    </div>
                        <br>
                </li>
            </form>               
        </ul>
    </div>
                       <c:set var="meldingS" value="${melding}"></c:set>
                <c:if test="${not empty meldingS}">
                    <spring:message code="${melding}" />
                </c:if>
<div class="searchInfo">
    <table>
            <c:forEach var="liste" items="${liste}">
                <tr>
                    <td><c:out value="${liste}"></c:out>
                        <form:form modelAttribute="resultat">
                        <form:input type="hidden" path="resultat" value="${liste}"/>
                        <c:if test="${liste.getClass().simpleName=='Bruker' or liste.getClass().simpleName=='Fag' or liste.getClass().simpleName=='Klasse'}"> 
                            <button class="defaultKnappMin" id="abonKnapp" type='submit' name='knappTilAbonnement' formaction="abonnere" value='Abonner'></button>
                        </c:if>
                        <c:if test="${liste.getClass().simpleName=='Rom'}">
                            <button class="defaultKnappMin" id="abonKnapp" type='submit' name='knappTilKart' formaction="sekart" value='Se kart'></button>
                        </c:if>
                        </form:form>
                    </td>
                </tr>                    
                          
            </c:forEach>                      
    </table>
</div>         
</main>
