<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<main>
    <div display="inline block">
    <fieldset style = "width:20%; position: absolute" class="fieldsetDefault">
        <legend>Legge Til Bruker</legend>
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
            </dd>
        </dl>
     </fieldset>
     </div>
    <div display="inline block">
     <fieldset style = "width:30%; float: right;" class="fieldsetDefault">       
        <legend>Legg Til Fag i Klasse</legend>
            <br>
            <dt><label for="valgKlasse">Velg klasse:<em>*</em></label></dt>
            <dd><select name="valgKlasse" id="valgKlasse" required="true">
                    <option value="Klasse 1">Klasse 1</option>
                    <option value="Klasse 2">Klasse 2</option>
                    <option value="Klasse 3">Klasse 3</option>
                    <option id="timeplansansvarligOption" name="timeplansansvarligOption" var="timeplansansvarligOption" value="Timeplansansvarlig">Timeplansansvarlig</option>
                </select>
            </dd>
            <br>
            <dt><label for="leggTilFag">Legg til fag i klasse:<em>*</em></label></dt>
            <dd><select name="leggTilFag" id="leggTilFag" required="true">
                    <option value="Fag 1">Fag 1</option>
                    <option value="Fag 2">Fag 2</option>
                    <option value="Fag 3">Fag 3</option>
                    <option id="timeplansansvarligOption" name="timeplansansvarligOption" var="timeplansansvarligOption" value="Timeplansansvarlig">Timeplansansvarlig</option>
                </select>
            
        <p><input id="leggTilFag" class="defaultKnapp" type="submit" value="Legg til" onclick=""></p>
    </fieldset>   
    </div>
   
    <c:set var="meldingS" value="${melding}"></c:set>
    <c:if test="${not empty meldingS}">
        <spring:message code="${melding}" />
    </c:if>
</main>
