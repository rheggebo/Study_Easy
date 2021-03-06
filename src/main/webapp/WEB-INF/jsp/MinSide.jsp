<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<main>
    
    <%-- setter og printer ut eventuell feilmelding her --%>
    <c:set var="slettFeilMelding" value="${slettFeilMelding}"></c:set>
        <c:if test="${not empty slettFeilMelding}">
            <fieldset id="feilmeldingSlett">
            <spring:message code="${slettFeilMelding}"/>

            </fieldset>
        </c:if>
            
    <fieldset class="fieldsetDefault">
        <legend><b>Personopplysninger</b></legend>   
        <dl>
            <form:form modelAttribute="bruker">
            <dt><label for="fornavnMinSide">Fornavn:</label></dt>
            <dd><form:input id="fornavnMinSide" type="text" name="fornavn" path="fornavn" readonly="true"/></dd>
            <dt><label for="etternavnMinSide">Etternavn:</label></dt>
            <dd><form:input type="text" name="etternavn" placeholder="Nordmann" path="etternavn" readonly="true"/></dd>

            <dt><label for="emailMinSide">E-post:</label></dt>
            <dd><form:input id="emailMinSide" type="email" name="email1" readonly="true" path="epost"/></dd>


            </form:form>
        </dl>
        <form:form action="EndrePassordRed">
            <input id="endrePassord" class="defaultKnapp" type="submit" value="Endre passord"/>
        </form:form>
    </fieldset>
    
    <fieldset class="fieldsetDefault">
        <legend><b>Fag</b> </legend>
        <div class="tabMinSide">
            <table>
                <c:forEach var="abliste" items="${abonemenntListe}">
                    <c:if test="${abliste.getType() == 1}">
                    <tr>
                        <td>
                            <form:form modelAttribute="resultat" action="slett">
                            <form:input type="hidden" path="resultat" value="${abliste}"/>
                            <c:out value ="${abliste}"></c:out> <%-- printer ut listeverdiene--%>

                            <%-- legger til knappene for slett fag --%>
                            <button class="slettknappMinSide" id="slettFag" type="submit" name="slettFagAbKnapp" value="Slett"/><span class="fontawesome-trash"></span></button>

                            </form:form>
                        </td>
                    </tr> 
                    </c:if>
                </c:forEach>
            </table>
        </div>
    </fieldset>
    
    <fieldset class="fieldsetDefault">
        <legend><b>Brukerabonnement</b></legend>
            <div class="tabMinSide">
                <table>
                <c:forEach var="abliste" items="${abonemenntListe}">
                    <c:if test="${abliste.getType() == 0}">
                        <tr>
                            <td>
                                <form:form modelAttribute="resultat" action="slett">
                                <form:input type="hidden" path="resultat" value="${abliste}"/>
                                <c:out value ="${abliste}"></c:out> <%-- printer ut listeverdiene--%>
                                <%-- legger til knappene for slett abonemennt --%>
                                <button class="slettknappMinSide" id="slettBrukerA" type="submit" name="slettBrukerAbKnapp" value="Slett"/><span class="fontawesome-trash"></span></button>
                                </form:form>
                            </td>
                        </tr>   
                    </c:if>
                </c:forEach>
                </table>
            </div>
    </fieldset>
    
    <fieldset class="fieldsetDefault">
        <legend><b>Romreservasjoner</b></legend>
        <section class="tabMinSide">
            <table>
                <c:forEach var="resliste" items="${reservasjonsliste}">
                    <form:form modelAttribute="event">
                    <tr>
                        <!--<script>
                            var bestilltTid = new Date();
                            bestilltTid.setTime("{resliste.getStartDato().getTime()}");
                            var currentDate = new Date();
                            var twentyMinutesEarlier = new Date().setMinutes(currentDate.getMinutes()-60);
                        </script>-->
                        <form:input type="hidden" path="rom" value="${resliste}"/>
                        <td><c:out value="${resliste}"></c:out><button class="slettknappMinSide" type="submit" id="slettReservasjon" name="slettHendelseKnapp" formaction="SlettBooking"/><span class="fontawesome-trash"></span></button><!--c:if test="{twentyMinutesEarlier le bestilltTid}"-->
                            <c:if test="${resliste.isKlokkesjekk()}"><input class="defaultKnapp" style="float: right" type="submit" value="Bekreft oppmøte" formaction="BekreftOppmote"/>
                            </c:if></td>
                    </tr>
                    </form:form>
                </c:forEach>
            </table>
        </section>           
    </fieldset>
                                
    <fieldset class="fieldsetDefault">
        <legend><b>Hendelser</b></legend>
        <div class="tabMinSide">
            <table>
            <c:forEach var="eventliste" items="${kalenderEventListe}">
                <tr>
                    <td>
                        <form:form modelAttribute="resultat" action="slett">
                        <form:input type="hidden" path="resultat" value="${eventliste}"/>
                        <c:out value ="${eventliste}"></c:out> <%-- printer ut listeverdiene--%>
                        <%-- legger til knappene for slett hendelse --%>
                        <button class="slettknappMinSide" type="submit" id="slettHendelse" name="slettHendelseKnapp" value="Slett"/><span class="fontawesome-trash"></span></button>
                        </form:form>
                    </td>
                </tr>   
            </c:forEach>
            </table>
        </div>       
    </fieldset>                                       
</main>

