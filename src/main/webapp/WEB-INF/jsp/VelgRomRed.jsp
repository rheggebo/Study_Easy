<%-- 
    Document   : VelgRomRed
    Created on : 20.jan.2016, 10:54:43
    Author     : Ingvild
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<!DOCTYPE html>

<main>
    
    <div id="RomInfoRed">
        <fieldset>
            <legend>Rominformasjon:</legend>
                <form action="VelgRomRed" id="velgRomForm1" method="post">    
                    <table>
                        <tr>
                            <td>Rom-id:</td>
                            <td><label id="rom"> </td>
                        </tr>
                        <tr>
                            <td>Rom navn:</td>
                            <td><label id="okRomNavn"></label></td>
                        </tr>
                        <tr>
                            <td>Etasje:</td>
                            <td><label id="okEtasje"></label></td>
                        </tr>
                        <tr>
                            <td>Plasser:</td>
                            <td><label id="okPlasser"></label></td>
                        </tr>
                        <tr>
                            <td>Utstyr:</td>
                            <td><label id="okUtstyr"></label></td>
                        </tr>
                        <tr>
                            <td>Dato:</td>
                            <td><label id="okDato"></label></td>
                        </tr>
                        <tr>
                            <td>Tid fra:</td>
                            <td><label id="okTidFra"></label></td>
                        </tr>
                        <tr>
                            <td>Tid til</td>
                            <td><label id="okTidTil"></label></td>
                        </tr>
                    </table>
                    <input type="submit" id="resRomVelgRom" value="Reserver rom">
                    
                </form>
        </fieldset>
    </div>
    
    
    
    
</main>