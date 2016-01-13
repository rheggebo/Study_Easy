
<main>
    <form action="MinSideRed" id="formen">
        <fieldset>
            <legend>Personopplysninger</legend>
            <dl>
                <form:form modelAttribute="bruker">
                Fornavn:<br>
                    <dd><form:input type="text" name="fornavn" path="fornavn" readonly="true"/></dd>
                Etternavn:<br>
                <dd><form:input type="text" name="etternavn" placeholder="Nordmann" path="etternavn" readonly="true"/></dd>

                Telefonnummer:<br>
                <dd><form:input id="telefon" name="telefon" type="tel" pattern="\d{8}$" readonly="true" path="telefonnummer"/></dd>

                E-post:<br>
                <dd><form:input type="email" name="email1" readonly="true" path="epost"/></dd>

                Fødselsdato:<br>
                <dd><form:input type="date" name="date" readonly="true" path="fodedato"/></dd>

                Klasse:<br>
                <dd><form:input type="text" name="ref"  list="ref-list1" readonly="true" path="klasse"/>
                    <datalist id="ref-list1">
                        <option value="Dataingeniør">
                        <option value="Drift av datasystemer">
                        <option value="IT-støttet bedriftsutvikling">
                    </datalist>

            </dl>
            <input type="submit" value="Endre opplysninger"/>
            </form:form>
            <br>
            <form:form modelAttribute="bruker" action="EndrePassordRed">
                <input type="submit" value="Endre passord" style="width: 130px"/>
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
