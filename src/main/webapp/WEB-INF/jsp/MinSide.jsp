<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<main>
    
        <fieldset>
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
                    <input id="endreOpplysninger" class="vanligknapp" type="submit" value="Endre opplysninger"/>
                </form>
            </c:if>
            <br>
            <form:form action="EndrePassordRed">
                <input id="endrePassord" class="vanligknapp" type="submit" value="Endre passord"/>
            </form:form>
        </fieldset>
            

        
        <fieldset>
            
            
            <legend>Fag </legend>
            
            <%-- setter og printer ut eventuell feilmelding --%>
                                <c:set var="meldingFaf" value="${meldingFag}"></c:set>
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

                                <%-- legger til knappene for slett abonemennt --%>
                                <input class="slettknapp" type="submit" name="slettFagAbKnapp" value="Slett"/>

                                </form:form>
                            </td>
                        </tr> 

                    </c:if>
                </c:forEach>
                
                </table>
            </div>

        </fieldset>

        <fieldset>

            <legend>Bruker abonnement</legend>
            
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
                                <input class="slettknapp" type="submit" name="slettBrukerAbKnapp" value="Slett"/>

                                </form:form>
                            </td>
                        </tr>   
                    </c:if>
                </c:forEach>
                </table>
            </div>
                       
        </fieldset>
            
        <fieldset>
            <legend>Romreservasjoner</legend>
            <section class="tab">
                <table>
                    <c:forEach var="resliste" items="${reservasjonsliste}">
                        <tr>
                            <td><c:out value="${resliste}"></c:out><input class="slettknapp" type="submit" value="Slett"/></td>
                        </tr>
                    </c:forEach>
                </table>
            </section>           
        </fieldset>
                                
        <fieldset>

            <legend>Hendelser</legend>
            
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
                                <input class="slettknapp" type="submit" name="slettBrukerAbKnapp" value="Slett"/>

                                </form:form>
                            </td>
                        </tr>   
                    </c:if>
                </c:forEach>
                </table>
            </div>
                       
        </fieldset>   
</main>
