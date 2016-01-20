<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>


<main>
    
    <br>
    <br>
    
    <div id='calendar'></div>
    
    <ul class="ikonForside" style="list-style-type: none; margin: 0; padding: 0;">
        <li style="display: inline-block"><a href="OpprettHendelse"><button class="knappForside" id="leggTilKnappForside" name="leggTilKnapp"><span class="fontawesome-plus"></span></button></a></li>
        <li style="display: inline-block"><button class="knappForside" id="slettKnappForside" name="slettKnappForside"><span class="fontawesome-trash"></span></button></li>
        <li style="display: inline-block"><button class="knappForside" id="søkeKnappForside" name="søkeKnappForside" onclick="location.href='SokeSide'"><span class="fontawesome-search"></span></button></li>
        <li style="display: inline-block">
            <a class="infoKnappForside" href="#infoVindu"><span class="fontawesome-info-sign"></span></a>
                <div id="infoVindu" class="infoVinduForside">
                    <div><a href="#lukk" title="Lukk" class="lukk">X</a>
                        <h2>Informasjon</h2>
                        <p>Denne kalenderen gir deg oversikt over dine forelesninger og øvinger. Du kan knytte dine personlige hendelse til denne kalenderen, og abonnere på andre. Håper den kommer i bruk!</p>
                    </div>
                </div>
        </li>
    </ul>
    
    <form action="kalenderEventCheck">
        <ul style="list-style-type: none; margin: 0; padding: 0;">
            <li>
                <input type="text" class="fargeTekst" disabled="true" style="background-color: #00BFFF;">
                <label for="checkbox_1">Forelesning</label>
                <input type="checkbox" class="checkBoxForside" id="checkBoxForside1" value="background-color1"  style="width: 15px; cursor: pointer" checked>
            </li>
            <li>
                <input type="text" class="fargeTekst" disabled="true" style="background-color: #00FF7F;">
                <label for="checkbox_2">Øving</label>
                <input type="checkbox" class="checkBoxForside" id="checkBoxForside2" value="background-color2" style="width: 15px; cursor: pointer" checked>
            </li>
            <li>
                <input type="text" class="fargeTekst" disabled="true" style="background-color: #FFA500;">
                <label for="checkbox_3">Rom reservasjon</label>
                <input type="checkbox" class="checkBoxForside" id="checkBoxForside3" value="background-color3"  style="width: 15px; cursor: pointer" checked>
            </li>
            <li>
                <input type="text" class="fargeTekst" disabled="true" style="background-color: #FFFF00;">
                <label for="checkbox_4">Privat hendelse</label>
                <input type="checkbox" class="checkBoxForside" id="checkBoxForside4" value="background-color4"  style="width: 15px; cursor: pointer" checked>
            </li>
        </ul>
    </form>
</main>

    <style>@import url(http://weloveiconfonts.com/api/?family=fontawesome);</style>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
    <script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
    <script src="<c:url value="/resources/fullcalendarJS/moment.min.js"/>"></script>
    <script src="<c:url value="/resources/fullcalendarJS/fullcalendar.js"/>"></script>
    <script src ="<c:url value="/resources/fullcalendarJS/jquery.qtip.js"/>"></script>
    <script src="<c:url value="/resources/fullcalendarJS/nb.js"/>"></script>

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



