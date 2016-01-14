<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>


<main>
    <div id='calendar'></div>
    <form>
        <ul style="list-style-type: none">
            <li>
               <label for="background-color1"></label>
               <input id="background-color1" type="color" value="#00BFFF" style="width: 20px; background-color: white" disabled="true"/>
               <label for="checkbox_1">Forelesning</label>
               <input type="checkbox" name="checkbox1" id="checkbox_1" value="background-color1"  style="width: 15px" checked>
            </li>
            <li>
               <label for="background-color2"></label>
               <input id="background-color2" type="color" value="#00FF7F" style="width: 20px; background-color: white" style="background-color: #ffffff" disabled="true"/>
               <label for="checkbox_2">Øving</label>
               <input type="checkbox" name="checkbox2" id="checkbox_2" value="background-color2"  style="width: 15px" checked>
            </li>
            <li>
               <label for="background-color3"></label>
               <input id="background-color3" type="color" value="#FFA500" style="width: 20px; background-color: white" style="background-color: #ffffff" disabled="true"/>
               <label for="checkbox_3">Rom reservasjon</label>
               <input type="checkbox" name="checkbox3" id="checkbox_3" value="background-color3"  style="width: 15px" checked>
            </li>
            <li>
               <label for="background-color4"></label>
               <input id="background-color4" type="color" value="#FFFF00" style="width: 20px; background-color: white" style="background-color: #ffffff" disabled="true"/>
               <label for="checkbox_4">Privat hendelse</label>
               <input type="checkbox" name="checkbox4" id="checkbox_4" value="background-color4"  style="width: 15px" checked>
            </li>
        </ul>
    </form>
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



