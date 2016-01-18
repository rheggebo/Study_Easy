<%--
  Created by IntelliJ IDEA.
  User: Stein-Erik
  Date: 23.09.2015
  Time: 22:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>


<div id="ILBoks">
    <img id="loggInnBilde" alt="Test" src="resources/images/Study_Easy_Login1.png"/>
    <form:form action="logInSjekk" modelAttribute="bruker">
        <div id="section">
            <div id="inputwrap">
              <form:input type="text" id="epostInnlogging" placeholder="E-post" path="epost" style="width: 420px"/><hr>
              <form:input type="password" id="passordInnlogging" placeholder="Passord" path="passord" style="width: 420px"/>
            </div>
        </div>
    <div id="feilmelding">
    <c:set var="meldingS" value="${melding}"></c:set>
    <c:if test="${not empty meldingS}">
        <spring:message code="${melding}" />
    </c:if>
    </div>
    <a id="loggInnGlemtPassord" href="glemtPassord" name="glemsk">Glemt passord?</a>
    <input id="loggInnKnapp" type="submit" value="Logg inn"><br>
  </form:form>
</div>
<script>
    $(document).scroll(function(){
    localStorage['page'] = document.URL;
    localStorage['scrollTop'] = $(document).scrollTop();
    });
    
    $(document).ready(function(){
    if (localStorage['page'] == document.URL) {
        $(document).scrollTop(localStorage['scrollTop']);
    }
});
</script>
