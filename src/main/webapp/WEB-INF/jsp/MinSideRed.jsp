<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<main>
    <fieldset class="fieldsetDefault" style = "width:20%">
        <legend>Personopplysninger</legend>
        <dl>
        <form:form action="MinSideRedLagre" modelAttribute="bruker">
            Fornavn:<em>*</em><br>
            <dd><form:input type="text" name="fornavn" placeholder="Ola" autofocus="true" required="true" path="fornavn" disabled="true"/></dd>

            Etternavn:<em>*</em><br>
            <dd><form:input type="text" name="etternavn" placeholder="Nordmann" required="true" path="etternavn" disabled="true"/></dd>

            E-post:<em>*</em><br>
            <dd><form:input type="email" id="epost0" placeholder="Skriv inn din e-post" required="true" disabled="true" path="epost"/></dd>

            Klasse:<br>
            <dd><input type="text" name="ref"  list="ref-list1"/></dd>
            <datalist id="ref-list1">
                <option value="Dataingeniør">
                <option value="Drift av datasystemer">
                <option value="IT-støttet bedriftsutvikling">
            </datalist>
            </dl>
            <p><input type="submit" class="defaultKnapp" value="Lagre"></p>
        </form:form>
    </fieldset>
</main>



<script>  
    window.onload = function(){
        document.getElementById('epost0').addEventListener('change', function() {
        var s = getElementById('epost1');
        s.value = document.getElementById('epost0');
    });
    };
</script>   
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
