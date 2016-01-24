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
            <form:form action="sendNyttPassord" modelAttribute="bruker">
                <div id="inputFelt">
                <form:input type="text" path="epost" placeholder="Epost" style="
                                width: 455px;
                                height: 50px;
                                border-top-width: 2px;
                                border-left-width: 2px;
                                padding-bottom: 1px;
                                font-size: 18px;
                                border-radius: 8px;"/>
                </div>
                <input id = "passordInnlogging" type="submit" value="Send nytt passord" style="
                    width: 460px;
                    padding: 15px 10px;
                    position: absolute;
                    top: 215%;
                    left: 50%;
                    margin-left: -230px;
                    cursor: pointer;
                    margin-top: -240px;
                    background-color: #00BFFF;
                    color: white;
                    border-radius: 12px;"/>
            </form:form>
        </div>
    <c:set var="meldingS" value="${melding}"></c:set>
    <c:if test="${not empty meldingS}">
        <spring:message code="${melding}" />
    </c:if>
<main>