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
    <title>Endre Passord</title>
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
<main>
        <fieldset style = "width:20%">
            <legend>Endre passord</legend>
            <dl>
            <form:form modelAttribute="passord" action="EndrePassord" onsubmit="return checkEmail(this)" id="formen" autocomplete="on">
                <form:input type="password" placeholder="Gammelt passord" path="passord" style="width: 420px"/>
                <form:input type="password" placeholder="Nytt passord" path="passord1" style="width: 420px"/>
                <form:input type="password" placeholder="Gjenta nytt passord" path="passord2" style="width: 420px"/>
                <form:errors path="passord"></form:errors>
            </dl>
                

            <p><input type="submit" value="Endre passord" onclick="behandle()"></p>
            </form:form>
        </fieldset>
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
