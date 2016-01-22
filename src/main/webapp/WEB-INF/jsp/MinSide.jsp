<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<main>
    
        <fieldset class="fieldsetDefault">
            <legend>Personopplysninger</legend>
            <dl>
                <form:form modelAttribute="bruker">
                <dt><label for="fornavnMinSide">Fornavn:</label></dt>
                <dd><form:input id="fornavnMinSide" type="text" name="fornavn" path="fornavn" readonly="true"/></dd>
                <dt><label for="etternavnMinSide">Etternavn:</label></dt>
                <dd><form:input type="text" name="etternavn" placeholder="Nordmann" path="etternavn" readonly="true"/></dd>

                <dt><label for="emailMinSide">E-post:</label></dt>
                <dd><form:input id="emailMinSide" type="email" name="email1" readonly="true" path="epost"/></dd>

                <dt><label for="klasseMinSide">Klasse:</label></dt>
                <%--
                <dd><form:input id="klasseMinSide" type="text" name="ref"  list="ref-list1" readonly="true" path="klasse"/>
                    <datalist id="ref-list1">
                        <option value="Dataingeniør">
                        <option value="Drift av datasystemer">
                        <option value="IT-støttet bedriftsutvikling">
                    </datalist>
                --%>
            </dl>
            </form:form>
            <c:set var="tilgang" value="${bruker.getTilgangsniva()}"></c:set>
            <c:if test="${tilgang == 2}">
                <form action="MinSideRed" id="formen">
                    <input id="endreOpplysninger" class="defaultKnapp" type="submit" value="Endre opplysninger"/>
                </form>
            </c:if>
            <br>
            <form:form action="EndrePassordRed">
                <input id="endrePassord" class="defaultKnapp" type="submit" value="Endre passord"/>
            </form:form>
        </fieldset>
            

        
        <fieldset class="fieldsetDefault">
            
            
            <legend>Fag </legend>
            
            <%-- setter og printer ut eventuell feilmelding --%>
                                <c:set var="meldingFag" value="${meldingFag}"></c:set>
                                <c:if test="${not empty meldingFag}">
                                    <spring:message code="${meldingFag}" />
                                </c:if>
            
            
            
            <div class="tab">
                
               
                <table>
                
                <c:forEach var="abliste" items="${abonemenntListe}">
                    <c:if test="${abliste.getType() == 1}">
                        <tr>
                            <td>
                                <form:form modelAttribute="resultat" action="slett">
                                <form:input type="hidden" path="resultat" value="${abliste}"/>
                                <c:out value ="${abliste}"></c:out> <%-- printer ut listeverdiene--%>

                                <%-- legger til knappene for slett fag --%>
                                <button class="slettknappMinSide" id="slettKnapp" type="submit" name="slettFagAbKnapp"/><span class="fontawesome-trash"></span></button>

                                </form:form>
                            </td>
                        </tr> 

                    </c:if>
                </c:forEach>
                
                </table>
            </div>

        </fieldset>

        <fieldset class="fieldsetDefault">

            <legend>Brukerabonnement</legend>
            
            <%-- setter og printer ut eventuell feilmelding --%>
                                <c:set var="meldingB" value="${meldingBruker}"></c:set>
                                <c:if test="${not empty meldingB}">
                                    <spring:message code="${meldingBruker}" />
                                </c:if>
            
            <div class="tab">
                <table>
                <c:forEach var="abliste" items="${abonemenntListe}">
                    <c:if test="${abliste.getType() == 0}">
                    <tr>
                            <td>
                                <form:form modelAttribute="resultat" action="slett">
                                <form:input type="hidden" path="resultat" value="${abliste}"/>
                                <c:out value ="${abliste}"></c:out> <%-- printer ut listeverdiene--%>

                                <%-- legger til knappene for slett abonemennt --%>
                                <button class="slettknappMinSide" type="submit" name="slettBrukerAbKnapp"/><span class="fontawesome-trash"></span></button>

                                </form:form>
                            </td>
                        </tr>   
                    </c:if>
                </c:forEach>
                </table>
            </div>
                       
        </fieldset>
            
        <fieldset class="fieldsetDefault">
            <legend>Romreservasjoner</legend>
            <section class="tab">
                <table>
                    <c:forEach var="resliste" items="${reservasjonsliste}">
                        <form:form modelAttribute="event" action="SlettBooking">
                        <tr>
                            <!--<script>
                                var bestilltTid = new Date();
                                bestilltTid.setTime("{resliste.getStartDato().getTime()}");
                                var currentDate = new Date();
                                var twentyMinutesEarlier = new Date().setMinutes(currentDate.getMinutes()-60);
                            </script>-->
                            <form:input type="hidden" path="rom" value="${resliste}"/>
                            <td><c:out value="${resliste}"></c:out><button class="defaultKnapp" id="slettKnapp" type="submit" name="slettHendelseKnapp"/><span class="fontawesome-trash"></span></button><!--c:if test="{twentyMinutesEarlier le bestilltTid}"-->
                                <c:if test="${resliste.isKlokkesjekk()}"><input class="defaultKnapp"type="submit" value="Bekreft oppmøte"/>
                                </c:if></td>
                        </tr>
                        </form:form>
                    </c:forEach>
                </table>
            </section>           
        </fieldset>
                                
        <fieldset class="fieldsetDefault">

            <legend>Hendelser</legend>
            
            <%-- setter og printer ut eventuell feilmelding --%>
                                <c:set var="meldingH" value="${meldingHendelse}"></c:set>
                                <c:if test="${not empty meldingH}">
                                    <spring:message code="${meldingHendelse}" />
                                </c:if>
            
            <div class="tab">
                <table>
                <c:forEach var="eventliste" items="${kalenderEventListe}">
                    <tr>
                        <td>
                            
                            <form:form modelAttribute="resultat" action="slett">
                            <form:input type="hidden" path="resultat" value="${eventliste}"/>
                            
                            <c:out value ="${eventliste}"></c:out> <%-- printer ut listeverdiene--%>

                            <%-- legger til knappene for slett hendelse --%>
                            <button class="slettknappMinSide" type="submit" name="slettHendelseKnapp"/><span class="fontawesome-trash"></span></button>
                            
                            
                            </form:form>
                            
                        </td>
                    </tr>   
                </c:forEach>
                </table>
            </div>
                       
        </fieldset>                                       
</main>

