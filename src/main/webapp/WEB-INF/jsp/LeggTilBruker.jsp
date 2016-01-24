<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<main>
    <div>
    <fieldset style = "width:20%; position: absolute" class="fieldsetDefault">
        <legend>Legg til bruker</legend>
        <dl>
            <form:form action="LeggTilBrukerLagre" modelAttribute="nyBruker">
                
            <dt><label for="fornavn">Fornavn:<em>*</em></label></dt>
            <dd><form:input id="fornavn" type="text" name="fornavn" placeholder="Ola" autofocus="true" required="true" path="fornavn"/></dd>

            <dt><label for="etternavn">Etternavn:<em>*</em></label></dt>
            <dd><form:input id="etternavn" type="text" name="etternavn" placeholder="Nordmann" required="true" path="etternavn"/></dd>

            <dt><label for="epost">E-post:<em>*</em></label></dt>
            <dd><form:input type="email" id="epost" placeholder="Skriv inn e-post" required="true" path="epost"/></dd>

            <dt><label for="tilgangnivaa">Type:<em>*</em></label></dt>
            <dd><select name="tilgangnivaa" id="tilgangnivaa" required="true">
                    <option value="Elev">Elev</option>
                    <option value="Lærer">Lærer</option>
                    <option id="timeplansansvarligOption" name="timeplansansvarligOption" var="timeplansansvarligOption" value="Timeplansansvarlig">Timeplansansvarlig</option>
                </select>
            </dd>
            <p><input id="leggTil" class="defaultKnapp" type="submit" value="Legg til" onclick=""></p>
            </form:form>
        </dl>
     </fieldset>
     </div>
    <div>
     <fieldset style = "width:30%; float: right;" class="fieldsetDefault">       
        <legend>Legg til fag i klasse</legend>
        <form:form action="LeggTilFagLagre" modelAttribute="leggTilFagKlasse">
            <table>
                <tr>
                    <td>Velg klasse<em>* </em></td>
                    <td>
                       <form:select name="valgKlasse" id="valgKlasse" required="true" path="klasseID">
                            <form:options items="${leggTilFagKlasse.getKlasseListe()}"/>
                        </form:select>
                    </td>
                </tr>
                <tr>
                    <td>Legg til fag i klasse:<em>* </em></td>
                    <td>
                        <form:select name="leggTilFag" id="leggTilFag" required="true" path="fagID">
                            <form:options items="${fagListe}"/>
                        </form:select>
                    </td>
                </tr>
                <tr>
                    <td><input id="leggTilFag" class="defaultKnapp" type="submit" value="Legg til" onclick=""></td>
                    <td></td>
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
    <div>
     <fieldset style = "width:30%; float: right;" class="fieldsetDefault">       
        <legend>Legg til nytt fag</legend>
        <form:form action="NyttFag" modelAttribute="nyttFag">
            <table>
                <tr>
                    <td>FagID<em>* </em></td>
                    <td>
                       <form:input required="true" path="fagID"/>
                    </td>
                </tr>
                <tr>
                    <td>Fagnavn:<em>* </em></td>
                    <td>
                        <form:input equired="true" path="navn"/>
                    </td>
                </tr>
                <tr>
                    <td><input id="leggTilFag" class="defaultKnapp" type="submit" value="Legg til" onclick=""></td>
                    <td></td>
                </tr>
            </table>
            <p></p>
        </form:form>
    </fieldset>     
    </div>
    <div>
     <fieldset style = "width:30%; float: right;" class="fieldsetDefault">       
        <legend>Legg til ny klasse</legend>
        <form:form action="NyKlasse" modelAttribute="nyKlasse">
            <table>
                <tr>
                    <td>KlasseID<em>* </em></td>
                    <td>
                       <form:input required="true" path="navn"/>
                    </td>
                </tr>
                <tr>
                    <td>:<em>Velg et fag* (En klasse må ha minst ett fag)</em></td>
                    <td>
                        <form:select name="leggTilFag" id="leggTilFag" required="true" path="fagID">
                            <form:options items="${fagListe}"/>
                        </form:select>
                    </td>
                </tr>
                <tr>
                    <td><input id="leggTilFag" class="defaultKnapp" type="submit" value="Legg til" onclick=""></td>
                    <td></td>
                </tr>
            </table>
            <p></p>
        </form:form>
    </fieldset>     
    </div>
        
   
    
</main>