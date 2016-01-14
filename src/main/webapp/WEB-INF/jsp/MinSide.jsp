<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<main>
    
        <fieldset>
            <legend>Personopplysninger</legend>
            <dl>
                <form:form modelAttribute="bruker">
                Fornavn:<br>
                    <dd><form:input type="text" name="fornavn" path="fornavn" readonly="true"/></dd>
                Etternavn:<br>
                <dd><form:input type="text" name="etternavn" placeholder="Nordmann" path="etternavn" readonly="true"/></dd>

                E-post:<br>
                <dd><form:input type="email" name="email1" readonly="true" path="epost"/></dd>

                Klasse:<br>
                <dd><form:input type="text" name="ref"  list="ref-list1" readonly="true" path="klasse"/>
                    <datalist id="ref-list1">
                        <option value="Dataingeniør">
                        <option value="Drift av datasystemer">
                        <option value="IT-støttet bedriftsutvikling">
                    </datalist>

            </dl>
            </form:form>
            <c:set var="tilgang" value="${bruker.getTilgangsniva()}"></c:set>
            <c:if test="${tilgang == 2}">
                <form action="MinSideRed" id="formen">
                    <input type="submit" value="Endre opplysninger"/>
                </form>
            </c:if>
            <br>
            <form:form action="EndrePassordRed">
                <input type="submit" value="Endre passord"/>
            </form:form>
        </fieldset>

        <fieldset>
            <legend>Fag</legend>
            <input type="submit" value="Endre opplysninger">
        </fieldset>

        <fieldset>
            <legend>Abonnement</legend>
            <input type="submit" value="Endre opplysninger">
        </fieldset>

    </form>
</main>
