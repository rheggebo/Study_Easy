<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<main>
    <div display="inline block">
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
    <div display="inline block">
     <fieldset style = "width:30%; float: right;" class="fieldsetDefault">       
        <legend>Legg til fag i klasse</legend>
        <form:form action="LeggTilFagLagre" modelAttribute="nyttFag">
            <br>
            <dt><label for="valgKlasse">Velg klasse:<em>*</em></label></dt>
            <dd><form:select name="valgKlasse" id="valgKlasse" required="true" path="klasseID">
                    <form:option value="" label="Velg"/>
                    <form:options items="${klasseList}"/>
                </form:select>
            </dd>
            <br>
            <dt><label for="leggTilFag">Legg til fag i klasse:<em>*</em></label></dt>
            <dd><form:select name="leggTilFag" id="leggTilFag" required="true" path="fagID">
                    <form:option value="" label="Velg"/>
                    <form:options items="${fagList}"/>
                </form:select>
        
            
        <p><input id="leggTilFag" class="defaultKnapp" type="submit" value="Legg til" onclick=""></p>
        </form:form>
    </fieldset>   
    </div>
   
    <c:set var="meldingS" value="${melding}"></c:set>
    <c:if test="${not empty meldingS}">
        <spring:message code="${melding}" />
    </c:if>
</main>