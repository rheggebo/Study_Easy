<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<html>
<head>

<script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
<link rel="stylesheet" href="<c:url value="/resources/css/fullcalendar.css"/>">
<link rel="stylesheet" href="<c:url value="/resources/css/jquery.qtip.css"/>">

<script src="<c:url value="/resources/fullcalendarJS/moment.min.js"/>"></script>
<script src="<c:url value="/resources/fullcalendarJS/fullcalendar.js"/>"></script>
<script src ="<c:url value="/resources/fullcalendarJS/jquery.qtip.js"/>"></script>



<script>
       $(document).ready(function() {
                       var date = new Date();
            var d = date.getDate();
            var m = date.getMonth();
            var y = date.getFullYear();
          $.getJSON('vacation/getVacation', function (data) {
            var calendar = $('#calendar').fullCalendar({
                header: {
                    left: 'prev,next today',
                    center: 'title',
                    right: 'month,agendaWeek,agendaDay'
                },
                editable: true,
                events:data
            });
         });
        });
        
</script>
<style>
	body {
		margin: 40px 10px;
		padding: 0;
		font-family: "Lucida Grande",Helvetica,Arial,Verdana,sans-serif;
		font-size: 14px;
	}
	#calendar {
		max-width: 900px;
		margin: 0 auto;
	}
</style>
</head>
<body>

	<div id='calendar'></div>

</body>
</html>