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
            <dl>
                    <dt><label for="datoFinnRom">Dato:<em>*</em></label></dt>
                    <dd><input id="datoFinnRom" type="date" name="fraDato" required></dd>
                    <dt><label for="fraTid">Tid fra:<em>*</em></label></dt>
                    <dd><select id="fraTid" name="fraTid" required>
                    <option value="1">06.00</option>
                    <option value="2">07.00</option>
                    <option value="3">08.00</option>
                    <option value="4">09.00</option>
                    <option value="5">10.00</option>
                    <option value="6">11.00</option>
                    <option value="7">12.00</option>
                    <option value="8">13.00</option>
                    <option value="9">14.00</option>
                    <option value="10">15.00</option>
                    <option value="11">16.00</option>
                    <option value="12">17.00</option>
                    <option value="13">18.00</option>
                    <option value="14">19.00</option>
                    <option value="15">20.00</option>
                    <option value="16">21.00</option>
                    <option value="17">22.00</option>
                    </select>
                    </dd>

                    <dt><label for="tilTid">Varighet:<em>*</em></label></dt>
                    <dd><select id="tilTid" name="varighet" required>
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                    </select></dd>

                </dl>
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