<%-- 
    Document   : OpprettHendelse
    Created on : 19.jan.2016, 18:53:22
    Author     : henri
--%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<main>
    <fieldset style = "width:20%">
        <legend>Ny hendelse</legend>
        
            <form:form action="OpprettHendelse" modelAttribute="nyHendelse">
                
            <dt><label for="Navn">Navn:<em>*</em></label></dt>
            <dd><form:input id="navn" type="text" name="navn" placeholder="Skriv inn navn her" autofocus="true" required="true" path="tittel"/></dd>

            <dt><label for="privatOffentlig">Privat/Offentlig:<em>*</em></label></dt>
            <dd><select name="valg" id="valg" required="true">
                    <option value="Offentlig">Offentlig</option>
                    <option value="Privat">Privat</option>
                </select>
            </dd>
            <dt><label for="Notat">Notat:</label></dt>
            <textarea id="notat" placeholder="Skriv notat her" name="notat" autofocus="true" rows="10" cols="35" ></textarea>
            
        <p><input type="submit" value="Legg til" onclick=""></p>
        </form:form>
    </fieldset>
</main>