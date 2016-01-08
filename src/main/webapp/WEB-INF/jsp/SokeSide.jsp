<%-- 
    Document   : SokeSide.jsp
    Created on : 08.jan.2016, 15:27:40
    Author     : Sigrid
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<html>
    <head>
        <link rel="stylesheet" href="<c:url value="/resources/css/Hovedstilen.css"/>">

        <title>SokeSide</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
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
                    <li><a href="MinSide">Min side</a>
                        <ul id="submenu">
                            <li><a href="MinSideRed">Endre instillinger</a></li>
                            <li><a href="Forside">Logg ut</a></li>
                        </ul>
                </ul>
            </nav>
        </header>

      <br><br><br>
      <main> 

        <div class="searchNav">
            <ul>
                <li><div><form action="/search" role="search"> 
                <input class="searchForm" type=search size="30">
                <input class="searchButton" value="Søk" type="submit"></form></div>
                
                <br>
                <div>
                <form class="checkboxes" action="">
                    <input type="checkbox" name="Spes" value="Ansatt">Ansatt
                    <br>
                    <input type="checkbox" name="Spes" value="Student">Student
                    <br>
                    <input type="checkbox" name="Spes" value="Fag">Fag
                    <br>
                    <input type="checkbox" name="Spes" value="Rom">Rom
                    <br>
                    <input type="checkbox" name="Spes" value="Klasse">Klasse</form></div>                
            </li>
           
            </ul></div>
        
        <section class="searchInfo">
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
