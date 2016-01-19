<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<main>
        <fieldset style = "width:20%">
            <legend>Endre passord</legend>
            <dl>
            <form:form modelAttribute="passord" action="EndrePassord" onsubmit="return checkEmail(this)" id="formen">
                <form:input id="gPassord" type="password" placeholder="Gammelt passord" path="passord" style="width: 420px"/>
                <form:input id="nPassord" type="password" placeholder="Nytt passord" path="passord1" style="width: 420px"/>
                <form:input id="gnPassord" type="password" placeholder="Gjenta nytt passord" path="passord2" style="width: 420px"/>
            </dl>

            <p><input id="endreGammeltPassord" type="submit" value="Endre passord" onclick="behandle()"></p>
            <div id="tekst">
                <c:set var="meldingS" value="${melding}"></c:set>
                <c:if test="${not empty meldingS}">
                    <spring:message code="${melding}" />
                </c:if>
                <form:errors path="passord"/>
                <form:errors path="passord1"/>
                <form:errors path="passord2"/>
            </div>
            </form:form>
        </fieldset>
</main>
