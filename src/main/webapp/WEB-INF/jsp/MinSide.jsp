<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html lang="no">
    <head>
        <link rel="stylesheet" href="<c:url value="/resources/css/TheStyle.css"/>">
        <meta charset="UTF-8">
        <title>Min Side</title>
    </head>

    <body>
        <a href="Forside"><img id="logo" src="<c:url value="/resources/images/LogoTeam1.png"/>"></a><br>
        <td><nav class="dropdownmenu">
            <ul>
                <li><a href="Forside">Forside</a></li>
                <li><a href="MinSide">Min side</a></li>
                <li><a href="#">Valg</a>
                    <ul id="submenu">
                        <li><a href="VelgRom">Bestill rom</a></li>
                        <li><a href="Forside">Finn rom</a></li>
                        <li><a href="Forside">Søk</a></li>
                    </ul>
                </li>
                <li><a href="Forside">FAQ</a></li>
                <li><a href="Forside">Kontakt oss</a></li>
            </ul>
        </nav>

        <form action="MinSideRed" id="formen">
            <fieldset>
                <legend>Personopplysninger</legend>
                <dl>
                    Fornavn:<br>
                    <dd><input type="text" name="fornavn" placeholder="Ola" readonly></dd>

                    Etternavn:<br>
                    <dd><input type="text" name="etternavn" placeholder="Nordmann" readonly></dd>

                    Telefonnummer:<br>
                    <dd><input id="telefon" name="telefon" type="tel" pattern="\d{8}$" readonly></dd>

                    E-post:<br>
                    <dd><input type="email" name="email1" readonly></dd>

                    Fødselsdato:<br>
                    <dd><input type="date" name="date" readonly></dd>

                    Klasse:<br>
                    <dd><input type="text" name="ref"  list="ref-list1" readonly>
                        <datalist id="ref-list1">
                            <option value="Dataingeniør">
                            <option value="Drift av datasystemer">
                            <option value="IT-støttet bedriftsutvikling">
                        </datalist>

                </dl>
                <input type="submit" value="Endre opplysninger">
            </fieldset>


        </form>


    <footer>
        <h4>NTNU - Norges Teknisk-naturvitenskapelige Universitet</h4>
        <a class="footerLenke" href="Forside">Hjem</a>
        ·
        <a class="footerLenke" href="MinSide">Min side</a>
        ·
        <a class="footerLenke" href="VelgRom">Velg rom</a>
        ·
        <a class="footerLenke" href="Forside">Om oss</a>
        ·
        <a class="footerLenke" href="Forside">FAQ</a>
        ·
        <a class="footerLenke" href="Forside">Contact</a>
        <h4>Study Easy © 2016</h4>
    </footer>

    </body>
</html>