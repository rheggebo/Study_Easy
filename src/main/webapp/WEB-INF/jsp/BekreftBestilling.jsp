<%-- 
    Document   : BekreftBestilling
    Created on : Jan 15, 2016, 11:43:53 PM
    Author     : Stein-Erik
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<script src="http://code.jquery.com/jquery-1.8.2.min.js"></script>
<script src="modernizr.custom.39755.js"></script>
<script src="http://afarkas.github.com/webshim/demos/js-webshim/minified/polyfiller.js"></script>
<script>
    //implement all unsupported features || call polyfill before DOM-Ready to implement everything as soon and as fast as possible
    $.webshims.polyfill();
</script>

<main>
        <form:form action="bekreftBestilling" modelAttribute="rom">
            <fieldset>
                <legend>Bekreft din bestilling:</legend>
                    <dt><label for="romNavn">Romnavn:</label></dt>
                    <dd><form:label id="romNavn" path="romNavn"></form:label></dd>
                    <br>
                    <dt><label for="datoFinnRom">Dato:<em>*</em></label></dt>
                    <dd><input id="datoFinnRom" type="date" name="date" required></dd>
                    <br>
                    <dt><label for="fraTid">Fra:</label></dt>
                    <dd><label id="fraTid"></label></dd>
                    <br>
                    <dt><label for="tilTid">Til:</label></dt>
                    <dd><label id="tilTid"></label></dd>
                    <br>
                    <dt><label for="sitteplasser">Sitteplasser:</label></dt>
                    <dd><form:label id="sitteplasser" path="antStolplasser"></form:label></dd>
                    <br>
                    <dt><label for="storrelse">Størrelse:</label></dt>
                    <dd><form:label id="storrelse" path="storrelse"></form:label><label>kvm</label></dd>
                    <br>
                    <dt><label for="innhold">Innhold:</label></dt>
                    <dd><form:label id="innhold" path="innhold"></form:label>
                    <br>
                </dl>
                <input type="submit" class="defaultKnapp" value="Bekreft bestilling">
            </fieldset>
        </form:form>
</main>