<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>


<main>
    <form>
        <ul style="list-style-type: none">
            <li>
               <label for="background-color1"></label>
               <input id="background-color1" type="color" value="#00BFFF" disabled="true"/>
               <input type="checkbox" name="color" value="#00BFFF" checked>Forelesning</li>
            <li>
               <label for="background-color2"></label>
               <input id="background-color2" type="color" value="#00FF7F" disabled="true"/>
               <input type="checkbox" name="color" value="00FF7F" checked>Øving</li>
            <li>
               <label for="background-color3"></label>
               <input id="background-color3" type="color" value="#FFA500" disabled="true"/>
               <input type="checkbox" name="color" value="FFA500" checked>Rom reservasjon</li>
            <li>
               <label for="background-color4"></label>
               <input id="background-color4" type="color" value="#FFFF00" disabled="true"/>
               <input type="checkbox" name="color" value="#FFFF00" checked>Privat hendelse</li>
        </ul>
    </form>
    <div id='calendar'></div>
</main>


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



