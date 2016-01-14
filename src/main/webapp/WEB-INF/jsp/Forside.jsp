<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>


<main>
    <br>
    <br>
    
    <div id='calendar'></div>
    
    <ul class="soking" style="list-style-type: none; margin: 0; padding: 0;">
            <li style="display: inline-block"><button id="leggTilKnapp"><span class="fontawesome-plus"></span></button></li>
            <li style="display: inline-block"><button id="slettKnapp"><span class="fontawesome-trash"></span></button></li>
            <li style="display: inline-block"><button id="søkeKnapp"><span class="fontawesome-search"></span></button></li>
            <li style="display: inline-block">
                <a class="button" id="btn-search" href="#openInfoWindow"><span class="fontawesome-info-sign"></span></a>
                    <div id="openInfoWindow" class="modalDialog">
                        <div><a href="#close" title="Close" class="close">X</a>
                            <h2>Informasjon</h2>
                                Denne kalenderen gir deg oversikt over dine forelesninger og øvinger. Du kan knytte dine personlige hendelser
                                    til denne kalenderen, og abonnere på andre. Håper den kommer i bruk!
                        </div>
                    </div>

            </li>
        </ul>
    
    <form>
        <ul style="list-style-type: none; margin: 0; padding: 0;">
            <li>
               <input id="background-color1" type="color" value="#00BFFF" style="background-color: #FFFFFF; width: 3em "disabled="true"/>
               <label for="checkbox_1">Forelesning</label>
               <input type="checkbox" name="checkbox1" class="kalenderCheckB" id="1" value="background-color1"  style="width: 15px; cursor: pointer" checked>
            </li>
            <li>
               <input id="background-color2" type="color" value="#00FF7F" style="background-color: #FFFFFF; width: 3em" disabled="true"/>
               <label for="checkbox_2">Øving</label>
               <input type="checkbox" name="checkbox2" class="kalenderCheckB" id="2" value="background-color2" style="width: 15px; cursor: pointer" checked>
            </li>
            <li>
               <input id="background-color3" type="color" value="#FFA500" style="background-color: #FFFFFF; width: 3em" disabled="true"/>
               <label for="checkbox_3">Rom reservasjon</label>
               <input type="checkbox" name="checkbox3" class="kalenderCheckB" id="3" value="background-color3"  style="width: 15px; cursor: pointer" checked>
            </li>
            <li>
               <input id="background-color4" type="color" value="#FFFF00" style="background-color: #FFFFFF; width: 3em" disabled="true"/>
               <label for="checkbox_4">Privat hendelse</label>
               <input type="checkbox" name="checkbox4" class="kalenderCheckB" id="4" value="background-color4"  style="width: 15px; cursor: pointer" checked>
            </li>
        </ul>
    </form>
</main>

    
<style>
    
@import url(http://weloveiconfonts.com/api/?family=fontawesome);

a[class*="fontawesome-"]:before{
}
span[class*="fontawesome-"]:before {
	font-family: 'FontAwesome', sans-serif;
	-webkit-font-smoothing: antialiased;
}

.soking{
    text-align: right;
}

.soking button, a::-moz-focus-inner {
	border: 0;
	padding: 0;
}

.soking button, .button, .close {
        cursor:pointer;
	background: #00BFFF;
	color: #FFFFFF;
	padding: .2em 1.2em;
        border: 0;
        font-size: 100%;
        text-transform: none;
        text-decoration: none;
        border:none; 
        outline:none; 
        button:focus(border:none);
}

.soking a:hover{
    background: #3598db;
    font-size:105%;
}
.soking button:hover {
	background: #3598db;
        font-size:105%;
}

</style>

<style>
.modalDialog {
	position: fixed;
	font-family: Microsoft YaHei Light;
	top: 0;
	right: 0;
	bottom: 0;
	left: 0;
	background: rgba(0,0,0,0.8);
	z-index: 99999;
	opacity:0;
	-webkit-transition: opacity 400ms ease-in;
	-moz-transition: opacity 400ms ease-in;
	transition: opacity 400ms ease-in;
	pointer-events: none;
}
        

.modalDialog:target {
	opacity:1;
	pointer-events: auto;
}

.modalDialog > div {
	width: 400px;
	position: relative;
	margin: 10% auto;
	padding: 5px 20px 13px 20px;
	border-radius: 10px;
	background: #fff;
	background: -moz-linear-gradient(#fff, #999);
	background: -webkit-linear-gradient(#fff, #999);
	background: -o-linear-gradient(#fff, #999);
}

.close {
	color: #FFFFFF;
	line-height: 10px;
	position: absolute;
	right: -12px;
	text-align: center;
	top: -10px;
	width: 15px;
	text-decoration: none;
	font-weight: bold;
	-webkit-border-radius: 12px;
	-moz-border-radius: 12px;
	border-radius: 12px;
	-moz-box-shadow: 1px 1px 3px #000;
	-webkit-box-shadow: 1px 1px 3px #000;
	box-shadow: 1px 1px 3px #000;
}
</style>

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



