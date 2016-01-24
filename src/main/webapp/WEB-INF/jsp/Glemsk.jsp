<%-- 
    Document   : Glemsk
    Created on : Nov 17, 2015, 8:43:54 PM
    Author     : Stein-Erik
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<main>
        <div id="boks">
            <img id="loggInnBilde2" alt="Test" src="resources/images/Study_Easy_Login1.png"/>
            <form:form action="sendNyttPassord" modelAttribute="bruker">
                <div id="inputFelt">
                <form:input type="text" id="epostGlemtPassord" path="epost" placeholder="Din e-post adresse"/>
                </div>
                <input class="defaultKnapp" id="sendNyttPassordGlemt" type="submit" value="Send nytt passord"/>
            </form:form>
        </div>
    <c:set var="meldingS" value="${melding}"></c:set>
    <c:if test="${not empty meldingS}">
        <spring:message code="${melding}" />
    </c:if>
</main>