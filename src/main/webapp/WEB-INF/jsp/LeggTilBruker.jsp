<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<main>
    <fieldset style = "width:20%">
        <legend>LeggeTilBruker</legend>
        <dl>
            <form:form action="LeggTilBrukerLagre" modelAttribute="nyBruker">
                
            <dt><label for="fornavn">Fornavn:<em>*</em></label></dt>
            <dd><form:input id="fornavnLeggTilBruker" type="text" name="fornavn" placeholder="Ola" autofocus="true" required="true" path="fornavn"/></dd>

            <dt><label for="etternavn">Etternavn:<em>*</em></label></dt>
            <dd><form:input id="etternavnLeggTilBruker" type="text" name="etternavn" placeholder="Nordmann" required="true" path="etternavn"/></dd>

            <dt><label for="epost">E-post:<em>*</em></label></dt>
            <dd><form:input type="email" id="epostLeggTilBruker" placeholder="Skriv inn e-post" required="true" path="epost"/></dd>

            <dt><label for="tilgangniva">Type:<em>*</em></label></dt>
            <dd><select id="tilgangnivaLeggTilBruker" name="tilgangniva" path="tilgangniva" required>
                    <option value="Elev">Elev</option>
                    <option value="L?rer">L�rer</option>
                    <option value="Timeeplansansvarlig">Timeplanansvarlig</option>
                </select>
            </dd>
        </dl>
        <p><input type="submit" value="Legg til" onclick=""></p>
        </form:form>
    </fieldset>
</main>