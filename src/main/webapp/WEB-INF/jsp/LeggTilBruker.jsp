<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<main>
    <fieldset style = "width:20%">
        <legend>Legge til bruker</legend>
        <dl>
            <form:form action="LeggTilBruker" modelAttribute="bruker">
                
            <dt><label for="fornavn">Fornavn:<em>*</em></label></dt>
            <dd><form:input id="fornavn" type="text" name="fornavn" placeholder="Ola" autofocus="true" required="true" path="fornavn"/></dd>

            <dt><label for="etternavn">Etternavn:<em>*</em></label></dt>
            <dd><form:input id="etternavn" type="text" name="etternavn" placeholder="Nordmann" required="true" path="etternavn"/></dd>

            <dt><label for="epost">E-post:<em>*</em></label></dt>
            <dd><form:input type="email" id="epost" placeholder="Skriv inn e-post" required="true" path="epost"/></dd>

            <dt><label for="type">Type:<em>*</em></label></dt>
            <dd><form:input id="type" type="text" list="red-list1" name="type" required="true" path="type"/></dd>
            
                <datalist id="ref-list1">
                    <option value="Elev">
                    <option value="Ansatt">
                    <option value="Timeplansansvarlig">
                </datalist>
            </dd>
        </dl>
        <p><input type="submit" value="Legg til" onclick="behandle()"></p>
        </form:form>
    </fieldset>
</main>