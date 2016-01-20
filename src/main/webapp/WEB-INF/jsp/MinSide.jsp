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
                <dd><form:input id="klasseMinSide" type="text" name="ref"  list="ref-list1" readonly="true" path="klasse"/>
                    <datalist id="ref-list1">
                        <option value="Dataingeniør">
                        <option value="Drift av datasystemer">
                        <option value="IT-støttet bedriftsutvikling">
                    </datalist>

            </dl>
            </form:form>
            <c:set var="tilgang" value="${bruker.getTilgangsniva()}"></c:set>
            <c:if test="${tilgang == 2}">
                <form action="MinSideRed" id="formen">
                    <input id="endreOpplysninger" type="submit" value="Endre opplysninger"/>
                </form>
            </c:if>
            <br>
            <form:form action="EndrePassordRed">
                <input id="endrePassord" type="submit" value="Endre passord"/>
            </form:form>
        </fieldset>
        
        <form:form modelAttribute="abonemenntListe" action="Abonnement">
        <fieldset>
            <legend>Fag</legend>
            <div class="tab">
                <table>
                <c:forEach var="abliste" items="${abonemenntListe}">
                    <c:if test="${abliste.getType() == 1}">
                    <tr>
                        <td>
                        <c:out value ="${abliste}"></c:out>
                        <input class="slettknapp" type="submit" name="slettAbKnapp" value="Slett"/><br>
                          </td>
                    </tr>  
                    </c:if>
                </c:forEach>
                </table>
            </div>

        </fieldset>

        <fieldset>
            <legend>Bruker abonnement</legend>
            
                <c:forEach var="abliste" items="${abonemenntListe}">
                    <c:if test="${abliste.getType() == 0}">
                        <c:out value ="${abliste}"></c:out>
                        <input class="slettKnapp" type="submit" name="slettAbKnapp" value="Slett"/><br>
                            
                    </c:if>
                </c:forEach>
                   
                       
        </fieldset>
        </form:form>
            
        <fieldset>
            <legend>Romreservasjoner</legend>
            <section class="searchInfo">
                <table>
                    <c:forEach var="resliste" items="${reservasjonsliste}">
                        <tr>
                            <td><c:out value="${resliste}"></c:out><input class="slettKnapp" type="submit" value="Slett"/></td>
                        </tr>
                    </c:forEach>
                </table>
            </section>
            <div class="tab">
                <table>
                <c:forEach var="abliste" items="${abonemenntListe}">                   
                    <c:if test="${abliste.getType() == 0}">
                    <tr>
                        <td>
                        <c:out value ="${abliste}" ></c:out>
                        <input class="slettknapp" type="submit" name="slettAbKnapp" value="Slett"/><br>                                                 
                        </td>
                    </tr>
                    </c:if> 
                        
                </c:forEach>
                       
                </table>
            </div>       
                       
        </fieldset>
        </form:form>
            
        <fieldset>
            <legend>Romreservasjoner</legend>

        </fieldset>

    
</main>
