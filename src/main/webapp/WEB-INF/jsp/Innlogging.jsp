<%--
  Created by IntelliJ IDEA.
  User: Stein-Erik
  Date: 23.09.2015
  Time: 22:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<html lang="en">
<head>
    <link rel="shortcut icon" href="<c:url value="/resources/images/BBIkon2.png"/>">
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/Login3.css"/>">
  <meta charset="UTF-8">
  <title>Study Easy</title>
</head>
<body>

<div id="ILBoks">
  <img src="resources/images/Study_Easy_Login1.png" style="border-radius: 8px; padding: 10px; top: 10%; margin-left: 78px"/>
  <form:form action="logInSjekk" modelAttribute="bruker">
    <div id="section">
      <div id="inputwrap">
        <form:input type="text" placeholder="Epost" path="epost" style="width: 420px"/><hr>
        <form:input type="password" placeholder="Passord" path="passord" style="width: 420px"/>
      </div>

    </div>
    <input type="submit" value="Logg inn" style="
    width: 460px;
    padding: 15px 10px;
    position: absolute;
    top: 145%;
    left: 50%;
    margin-left: -230px;
    margin-top: -277px;
    background-color: dodgerblue;
    color: white;
    border-radius: 12px;"><br>


    <a href="glemtPassord" name="glemsk" style="
    position: absolute;
    top: 93%;
    left: 75%;">Glemt passord?</a>
    

  </form:form>
  
</div>
    <div id="feilmelding">
        <c:set var="meldingS" value="${melding}"></c:set>
    <c:if test="${not empty meldingS}">
        <spring:message code="${melding}" />
    </c:if>
    </div>
</body>
</html>