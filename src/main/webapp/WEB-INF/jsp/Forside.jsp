<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html lang="no">
<head>
    <link rel="stylesheet" href="<c:url value="/resources/css/Hovedstilen.css"/>">
    
    <link rel="stylesheet" href="<c:url value="/resources/css/fullcalendar.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/jquery.qtip.css"/>">
    <script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
    <script src="<c:url value="/resources/fullcalendarJS/moment.min.js"/>"></script>
    <script src="<c:url value="/resources/fullcalendarJS/fullcalendar.js"/>"></script>
    <script src ="<c:url value="/resources/fullcalendarJS/jquery.qtip.js"/>"></script>
    <script src="<c:url value="/resources/fullcalendarJS/nb.js"/>"></script>

    
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Fremside</title>
    
<script>
       $(document).ready(function() {
            var currentLangCode = 'en';
            var date = new Date();
            var d = date.getDate();
            var m = date.getMonth();
            var y = date.getFullYear();


          $.getJSON('events/getEvents', function (data) {
            var calendar = $('#calendar').fullCalendar({
                header: {
                    left: 'prev,next today',
                    center: 'title',
                    right: 'month,agendaWeek,agendaDay'
                },
                weekNumbers: true,
                editable: false,
                events:data,
                eventRender: function(event, element) {
                    element.qtip({
                        content: event.description,
                        hide: {
                            fixed: true,
                            delay: 300
                        }
                    });
                }
            });
         });
        });
        
</script>
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

    	<div id='calendar'></div>
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