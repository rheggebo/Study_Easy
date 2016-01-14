<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<main>
    <fieldset style = "width:20%">
        <legend>Personopplysninger</legend>
        <dl>
        <form:form action="MinSideRedLagrer" modelAttribute="bruker">
            Fornavn:<em>*</em><br>
            <dd><form:input type="text" name="fornavn" placeholder="Ola" autofocus="true" required="true" path="fornavn"/></dd>

            Etternavn:<em>*</em><br>
            <dd><form:input type="text" name="etternavn" placeholder="Nordmann" required="true" path="etternavn"/></dd>

            <dt><label for="telefon">Telefonnummer<em>*</em></label></dt>
            <dd><form:input id="telefon" name="telefon" type="tel" pattern="\d{8}$" placeholder="Skriv inn ditt telefonnummer" required="true" path="telefonnummer"/></dd>

            E-post:<em>*</em><br>
            <dd><form:input type="email" id="epost0" placeholder="Skriv inn din e-post" required="false" path="epost" disabled="true"/></dd>

            Fødselsdato:<em>*</em><br>
            <dd><form:input type="date" name="dato1" maks="2010-01-01" min="1930-01-01" required="true" path="fodedato"/></dd>

            Klasse:<br>
            <dd><form:input type="text" name="ref"  list="ref-list1" path="klasse"/></dd>
            <datalist id="ref-list1">
                <option value="Dataingeniør">
                <option value="Drift av datasystemer">
                <option value="IT-støttet bedriftsutvikling">
            </datalist>

            </dl>
            <p><input type="submit" value="Lagre"></p>
        </form:form>
    </fieldset>
</main>
  
<script>
    function elementSupportsAttribute(element,attribute) {
        return (attribute in document.createElement(element));
    }

    // Sjekker om et element har en klasse.
    function hasClass(element, cls) {
        return (' ' + element.className + ' ').indexOf(' ' + cls + ' ') > -1;
    }
    //Funksjon for å sjekke om emailene stemmer overens
    function checkEmail(formen) {
        if (formen.epost1.value != formen.epost2.value)
        {
            alert('E-postene samsvarer ikke!');
            return false;
        } else {
            return true;
        }
    }
    //Validerer utfyllingen
    function behandle(){

        var inputs = [document.getElementById('fornavn'),document.getElementById('etternavn'),
            document.getElementById('telefon'), document.getElementById('epost1'),
            document.getElementById('epost2'), document.getElementById('dato1')];

        var error;

        for(var i = 0; i<inputs.length; i++)
        {
            if(inputs[i].value == '')
            {
                error = 'Fullfør alle felt';
                alert(error);
                return false;
                break;
            }
        }


    }
</script>
