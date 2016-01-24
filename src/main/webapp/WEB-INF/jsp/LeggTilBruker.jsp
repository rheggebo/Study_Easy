<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<main>
    <div>
        <div>
        <fieldset class="fieldsetDefault">
            <legend>Legg til bruker</legend>
            <form:form action="LeggTilBrukerLagre" modelAttribute="nyBruker">
                <table>
                    <tr>
                        <td><label for="fornavn">Fornavn:<em>*</em> </label></td>
                        <td><form:input id="fornavn" type="text" name="fornavn" placeholder="Ola" autofocus="true" required="true" path="fornavn"/></td>
                    </tr>
                    <tr>
                        <td><label for="etternavn">Etternavn:<em>*</em></label></td>
                        <td><form:input id="etternavn" type="text" name="etternavn" placeholder="Nordmann" required="true" path="etternavn"/></td>
                    </tr>

                    <tr>
                        <td><label for="epost">E-post:<em>*</em></label></td>
                        <td><form:input type="email" id="epost" placeholder="Skriv inn e-post" required="true" path="epost"/></td>
                    </tr>
                    <tr>
                        <td><label for="tilgangnivaa">Type:<em>*</em></label></td>
                        <td>
                            <select name="tilgangnivaa" id="tilgangnivaa" required="true">
                                <option value="Elev">Elev</option>
                                <option value="Lærer">Lærer</option>
                                <option id="timeplansansvarligOption" name="timeplansansvarligOption" var="timeplansansvarligOption" value="Timeplansansvarlig">Timeplansansvarlig</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td></td>
                        <td><input id="leggTil" class="defaultKnapp" type="submit" value="Legg til" onclick=""></td>
                    </tr>
                </table>
            </form:form>
        </fieldset>
        </div>
        
        <div>
        <fieldset class="fieldsetDefault">       
            <legend>Legg til nytt fag</legend>
            <form:form action="NyttFag" modelAttribute="nyttFag">
                <table>
                    <tr>
                        <td>FagID<em>* </em></td>
                        <td>
                           <form:input required="true" placeholder="FagID" path="fagID"/>
                        </td>
                    </tr>
                    <tr>
                        <td>Fagnavn:<em>* </em></td>
                        <td>
                            <form:input equired="true" placeholder="Fagnavn" path="navn"/>
                        </td>
                    </tr>
                    <tr>
                        <td></td>
                        <td><input id="leggTilFag" class="defaultKnapp" type="submit" value="Legg til" onclick=""></td>
                    </tr>
                </table>
                <p></p>
            </form:form>
        </fieldset>     
        </div>
        <div>
        <fieldset class="fieldsetDefault">       
            <legend>Legg til ny klasse</legend>
            <form:form action="NyKlasse" modelAttribute="nyKlasse">
                <table>
                    <tr>
                        <td>KlasseID:</td>
                        <td>
                           <form:input required="true" placeholder="KlasseID" path="navn"/>
                        </td>
                        <td>
                            <em>Velg et fag* (En klasse må ha minst ett fag)</em>
                        </td>
                    </tr>
                    <tr>
                        <td></td>
                        <td>
                            <form:select name="leggTilFag" id="leggTilFag" required="true" path="fagID">
                                <form:options items="${fagListe}"/>
                            </form:select>
                        </td>
                        <td></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td><input id="leggTilFag" class="defaultKnapp" type="submit" value="Legg til" onclick=""></td>
                        <td></td>
                    </tr>
                </table>
                <p></p>
            </form:form>
        </fieldset>     
        </div>
        <div>
        <fieldset class="fieldsetDefault">       
            <legend>Legg til fag i klasse</legend>
            <form:form action="LeggTilFagLagre" modelAttribute="leggTilFagKlasse">
                <table>
                    <tr>
                        <td>Velg klasse<em>* </em></td>
                        <td>
                           <form:select name="valgKlasse" id="valgKlasse" placeholder="KlasseID" required="true" path="klasseID">
                                <form:options items="${leggTilFagKlasse.getKlasseListe()}"/>
                            </form:select>
                        </td>
                    </tr>
                    <tr>
                        <td>Legg til fag i klasse:<em>* </em></td>
                        <td>
                            <form:select name="leggTilFag" id="leggTilFag" placeholder="FagID" required="true" path="fagID">
                                <form:options items="${fagListe}"/>
                            </form:select>
                        </td>
                    </tr>
                    <tr>
                        <td></td>
                        <td><input id="leggTilFag" class="defaultKnapp" type="submit" value="Legg til" onclick=""></td>
                    </tr>
                </table>
                <p></p>
            </form:form>
            <c:set var="meldingS" value="${melding}"></c:set>
            <c:if test="${not empty meldingS}">
                <p>
                    <spring:message code="${melding}" />
                </p>
            </c:if>
        </fieldset>   

        </div>
    </div>
</main>