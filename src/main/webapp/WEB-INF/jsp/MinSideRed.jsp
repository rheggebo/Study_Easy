<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<!DOCTYPE html>
<html lang="no">
<head>
    <link rel="stylesheet" href="<c:url value="/resources/css/Hovedstilen.css"/>">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Min Side</title>
</head>

<body>
<header>
    <a href="Forside"><img id="logo" src="<c:url value="/resources/images/LogoTeam1.png"/>"></a>
    NTNU - Norges Teknisk-naturvitenskapelige Universitet · Study Easy © 2016<br>
    <nav class="dropdownmenu">
        <ul>
            <li><a href="Forside">Forside</a></li>
            <li><a href="Forside">Kontakt</a></li>
            <li><a href="#">Romvalg</a>
                <ul id="submenu">
                    <li><a href="VelgRom">Bestill rom</a></li>
                    <li><a href="FinnRom">Finn rom</a></li>
                </ul>
            </li>
            <li><a href="SokeSide">Søk</a></li>
            <li><a href="#">Min side</a>
                <ul id="submenu">
                    <li><a href="MinSide">Min informasjon</a></li>
                    <li><a href="loggUt">Logg ut</a></li>
                </ul>
        </ul>
    </nav>
</header>
<main>
    <form action="MinSide.html" id="formen" autocomplete="on" method="post" onsubmit="return checkEmail(this);">
        <fieldset style = "width:20%">
            <legend>Personopplysninger</legend>
            <dl>
                <form:form modelAttribute="bruker">
                Fornavn:<em>*</em><br>
                <dd><form:input type="text" name="fornavn" placeholder="Ola" autofocus="true" required="true" path="navn"/></dd>

                Etternavn:<em>*</em><br>
                <dd><input type="text" name="etternavn" placeholder="Nordmann" required></dd>

                <dt><label for="telefon">Telefonnummer<em>*</em></label></dt>
                <dd><form:input id="telefon" name="telefon" type="tel" pattern="\d{8}$" placeholder="Skriv inn ditt telefonnummer" required="true" path="telefonnummer"/></dd>


                E-post:<em>*</em><br>
                <dd><form:input type="email" id="epost0" placeholder="Skriv inn din e-post" required="true" path="epost"/></dd>
                <input type="hidden" name="epost1" id="epost1"/>
                
                Gjenta E-post:<em>*</em><br>
                <dd><input type="email" name="epost2" placeholder="Gjenta din e-post" required="true"/></dd>

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
                

            <p><input type="submit" value="Lagre" onclick="behandle()"></p>
            </form:form>
        </fieldset>


    </form>
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


<!--Footer-->
<div class="space"></div>
<!--Må være med for å funke i Chrome-->

<footer>
    <h4>NTNU - Norges Teknisk-naturvitenskapelige Universitet</h4>
    <a class="footerLenke" href="Forside">Hjem</a>
    ·
    <a class="footerLenke" href="MinSide">Min side</a>
    ·
    <a class="footerLenke" href="VelgRom">Bestill rom</a>
    ·
    <a class="footerLenke" href="Forside">Finn rom</a>
    ·
    <a class="footerLenke" href="SokeSide">Søk</a>
    ·
    <a class="footerLenke" href="Forside">Kontakt</a>
    <h4>·Study Easy © 2016·</h4>
</footer>


</body>
</html>