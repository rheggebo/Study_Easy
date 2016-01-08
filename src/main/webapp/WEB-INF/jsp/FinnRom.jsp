<%-- 
    Document   : FinnRom
    Created on : 08.jan.2016, 14:47:36
    Author     : Ane
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" href="<c:url value="/resources/css/TheStyle.css"/>">
    <meta charset="UTF-8">
    <title>Finn rom</title>

    <script src="http://code.jquery.com/jquery-1.8.2.min.js"></script>
    <script src="modernizr.custom.39755.js"></script>

    <script src="http://afarkas.github.com/webshim/demos/js-webshim/minified/polyfiller.js"></script>

    <script>
        //implement all unsupported features || call polyfill before DOM-Ready to implement everything as soon and as fast as possible
        $.webshims.polyfill();
    </script>

</head>

<body>
<header>
    <a href="Forside.html"><img id="logo" src="LogoTeam1.png"></a>
    Norges teknisk-naturvitenskapelige universitet<br>
    <nav class="dropdownmenu">
        <ul>
            <li><a href="Forside.html">Forside</a></li>
            <li><a href="MinSide.html">Min side</a></li>
            <li><a href="#">Romvalg</a>
                <ul id="submenu">
                    <li><a href="VelgRom.html">Bestill rom</a></li>
                    <li><a href="Forside.html">Finn rom</a></li>
                </ul>
            </li>
            <li><a href="Forside.html">Søk</a></li>
            <li><a href="Forside.html">Logg ut</a></li>
        </ul>
    </nav>
</header>

<main>
        <aside>
        <form action="" method="post" onsubmit="return validate();">

            <legend>Velg ønsket spesifikasjoner rommet skal ha:</legend>
            <dl>
                Romtype:<em>*</em><br>
                <dd><select name="romtype" required >
                    <option value="forelesingssal">Forelesingssal</option>
                    <option value="møterom">Møterom</option>
                    <option value="grupperom">Grupperom</option>
                    <option value="klasserom">Klasserom</option>
                </select>

                </dd>
               <!-- <dd><input type="text" name="romtype" list="ref-romtype" placeholder="Nedtrekksliste"></dd>
                <datalist id="ref-romtype">
                </datalist> -->

                Antall stolplasser:<em>*</em><br>
                <dd><input type="number" name="num-stoler" min="1" max="150" value="1" required></dd>

                Størrelse(m2):<br>
                <dd><input id="str" name="størrelse" type="number" min="5" max="200" value="5"></dd>

                Utstyr:<br>
                <dd><input type="checkbox" id="skjerm" value="Skjerm">PC-skjerm <br>
                <input type="checkbox" id="tavle" value="tavle">Tavle <br>
                <input type="checkbox" id="stikkontakter" value="stikkontakter">Stikkontakter <br>
                <input type="checkbox" id="prosjektor" value="prosjektor">Prosjektor <br></dd>



                Dato:<em>*</em><br>
                <dd><input type="date" name="date" required></dd>

                </dl>
                <input type="submit" value="Finn ledig rom">
        </form>
        </aside>

        <section class="info">
            <p>Resultatet kan inn her</p>
        </section>

</main>


<!--Footer-->
<div class="space"></div>
<!--Må være med for å funke i Chrome-->

<footer>
    <h4>NTNU - Norges Teknisk-naturvitenskapelige Universitet</h4>
    <a class="footerLenke" href="Forside.html">Hjem</a>
    ·
    <a class="footerLenke" href="MinSide.html">Min side</a>
    ·
    <a class="footerLenke" href="VelgRom.html">Velg rom</a>
    ·
    <a class="footerLenke" href="Forside.html">Om oss</a>
    ·
    <a class="footerLenke" href="Forside.html">FAQ</a>
    ·
    <a class="footerLenke" href="Forside.html">Contact</a>
    <h4>Study Easy © 2016</h4>
</footer>

</body>
</html>
