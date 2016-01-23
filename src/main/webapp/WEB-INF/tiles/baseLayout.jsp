<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">
    <head>
        <link rel="shortcut icon" href="<c:url value="/resources/images/BBIkon2.png"/>">
        
        <link rel="stylesheet" href="<c:url value="/resources/css/hovedstilen.css"/>">
        <link rel="stylesheet" href="<c:url value="/resources/css/velgRom.css"/>">
        <link rel="stylesheet" href="<c:url value="/resources/css/minSide.css"/>">
        <link rel="stylesheet" href="<c:url value="/resources/css/sokeSide.css"/>">
        <link rel="stylesheet" href="<c:url value="/resources/css/finnRom.css"/>">
        
        <link rel="stylesheet" href="<c:url value="/resources/css/fullcalendar.css"/>">
        <link rel="stylesheet" href="<c:url value="/resources/css/jquery.qtip.css"/>">
        
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/Login3.css"/>">
        <style>@import url(http://weloveiconfonts.com/api/?family=fontawesome);</style>
        <title>Study Easy</title>
    </head>

    
        <table id="baseLayoutTable" align="center">
            <tr>
                <td height="0">
                    <tiles:insertAttribute name="header" />
                </td>
            </tr>
            <tr>
                <td height="0">
                     <tiles:insertAttribute name="menu" />
                </td>
            </tr>
            <tr>
                <td height="1200">
                    <tiles:insertAttribute name="body" />
                </td>
            </tr>
            <tr>
                <td height="0">
                    <tiles:insertAttribute name="footer" />
                </td>
            </tr>
        </table>
                
     
</html>
<script src="//cdn.jsdelivr.net/webshim/1.14.5/polyfiller.js"></script>
<script>
    webshims.setOptions('forms-ext', {types: 'date'});
    webshims.polyfill('forms forms-ext');
</script>