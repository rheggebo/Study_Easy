<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html lang="no">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="<c:url value="/resources/css/TheStyle.css"/>">
    <title>Velg rom</title>
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

    Her kommer kartet!


    <aside>
        <script>
            var date = new Date();

            var day = date.getDate();
            var month = date.getMonth() + 1;
            var year = date.getFullYear();

            if (month < 10) month = "0" + month;
            if (day < 10) day = "0" + day;

            var today = year + "-" + month + "-" + day;
            var maks = year+1 + "-" + month + "-" + day;

            document.getElementById('datoen').value = today;
        </script>

        <form>
            <p>Velg dato for rombestilling</p>
            <input type="date" name="date" id="datoen" required placeholder="ÅÅÅÅ-MM-DD">

            <p>Velg klokkeslett<em>(kan velge flere)</em></p>
            <select id="klokkeslett" multiple required name="klokkeslett">
                <option value="1">08.00 - 09.00</option>
                <option value="2">09.00 - 10.00</option>
                <option value="3">10.00 - 11.00</option>
                <option value="4">11.00 - 12.00</option>
                <option value="5">12.00 - 13.00</option>
                <option value="6">13.00 - 14.00</option>
                <option value="7">14.00 - 15.00</option>
                <option value="8">15.00 - 16.00</option>
                <option value="9">16.00 - 17.00</option>
                <option value="10">17.00 - 18.00</option>
            </select>
            <br>
            <p>Her skal ledige rom listes ut</p>
            <br>
            <input type="submit" value="Velg rom">
        </form>


    </aside>


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