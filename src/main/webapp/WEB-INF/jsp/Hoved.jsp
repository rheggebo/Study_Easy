<%-- 
    Document   : Hoved
    Created on : Jan 5, 2016, 9:28:41 AM
    Author     : Stein-Erik
    TEST
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
        <!-- this script got from www.htmlbestcodes.com-Coded by: Krishna Eydat -->
<!doctype html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <title>jQuery UI Datepicker - Default functionality</title>
  <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
  <script src="//code.jquery.com/jquery-1.10.2.js"></script>
  <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
  <link rel="stylesheet" href="/resources/demos/style.css">
  <script>
    $(function() {
      $( "#datepicker" ).datepicker({
          showOtherMonths: true,
          selectOtherMonths: true
      });
      
    });
  </script>
</head>
    <body>
 
        <p>Date: <input type="text" id="datepicker"></p>
 
        <iframe src="https://calendar.google.com/calendar/embed?src=steinerikbjornnes%40gmail.com&ctz=Europe/Oslo" 
                style="border: 0" width="800" height="600" frameborder="0" scrolling="no"></iframe>
        
        
    </body>
</html>
