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
                            <li><a href="Forside">Finn rom</a></li>
                        </ul>
                    </li>
                    <li><a href="SokeSide">Søk</a></li>
                    <li><a href="MinSide">Min side</a>
                        <ul id="submenu">
                            <li><a href="MinSideRed">Endre instillinger</a></li>
                            <li><a href="Forside">Logg ut</a></li>
                        </ul>
                </ul>
            </nav>
        </header>

        <main>

            <form action="MinSideRed.html" id="formen">
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