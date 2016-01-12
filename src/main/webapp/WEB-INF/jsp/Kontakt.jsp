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
    <title>Kontakt</title>
</head>

<body>
<header>
    <a href="Forside"><img id="logo" src="<c:url value="/resources/images/LogoTeam1.png"/>"></a>
    NTNU - Norges Teknisk-naturvitenskapelige Universitet · Study Easy © 2016<br>
    <nav class="dropdownmenu">
        <ul>
            <li><a href="Forside">Forside</a></li>
            <li><a href="Kontakt">Kontakt</a></li>
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
<main>  <details>
                    <summary><p1><b>Systemansvarlig <br> Stein-Erik Bjørnnes</b></p1></summary>
                    <p2><dt>
                        <br><b>Kontaktinformasjon</b><br>
                        Tlf:    994 75 118<br>
                        E-post: steinerikbjornnes@gmail.com<br>
                    </dt></p2>
                </details>

                <br><br>

                <details>
                    <summary><p1><b>Databaseansvarlig <br> Arne Røen</b></p1></summary>
                    <p2><dt>
                        <br><b>Kontaktinformasjon</b><br>
                        Tlf:    994 75 118<br>
                        E-post: steinerikbjornnes@gmail.com<br>
                    </dt></p2>
                </details>
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
    <a class="footerLenke" href="Kontakt">Kontakt</a>
    <h4>·Study Easy © 2016·</h4>
</footer>

</body>
</html>