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

<main>
    
    <div id="RomInfoRed">
        <fieldset class="fieldsetDefault">
            <legend><b>Rominformasjon:</b></legend>
                <form:form modelAttribute="redRomForm" method="POST" action="redigerRomLagre">
                    <table>
                        
                        <tr>
                            <td>Rom-id:</td>
                            <td><form:input class="redRomForm" id="redRomId" type="text" value="${rom.getRomID()}" path="romID" /></td>
                        </tr>
                        
                        <tr>
                            <td>Rom navn:</td>
                            <td><form:input class="redRomForm" id="redRomNavn" type="text" value="${rom.getRomNavn()}" path="romNavn" /></td>
                        </tr>
                        
                        <tr>
                            <td>Rom type:</td>
                            <td><form:input class="redRomForm" id="redRomType" type="number" min="1" max="3" value="${rom.getType()}" path="romType" /></td>
                        </tr>
                        <tr>
                            <td>Romstørrelse m<sup>2</sup>:</td>
                            <td><form:input class="redRomForm" id="redRomStr" type="number" min="${rom.getStorrelse()}" max="${rom.getStorrelse()}" value="${rom.getStorrelse()}" path="romStr"/></td>
                        </tr>
                        <tr>
                        <td>Antall sitteplasser:</td>
                            <td><form:input class="redRomForm" id="redRomPlasser" type="number" value="${rom.getAntStolplasser()}" path="antSittePl" /></td>
                        </tr>
                         <tr>
                            <td>Antall skjermer</td>
                            <td><form:input class="redRomForm" id="redRomId" type="number" value="${rom.getRomID()}" path="romID" /></td>
                        </tr>
                        <tr>
                            <td>Antall tavler:</td>
                            <td><form:input class="redRomForm" id="redRomId" type="number" value="${rom.getRomID()}" path="romID" /></td>
                        </tr>
                        <tr>
                            <td>Antall prosjektorer:</td>
                            <td><form:input class="redRomForm" id="redRomId" type="number" value="${rom.getRomID()}" path="romID" /></td>
                        </tr>
                        
                           
                        
                    </table>
                        <input type="submit" class="defaultKnapp" id="resRomVelgRom" formaction="EndreRom" value="Lagre informasjon">
                    
                </form:form>
        </fieldset>
    </div>
    
</main>