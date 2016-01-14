<!--
<a href="<c:url value="Forside"/>" >           <spring:message code="menyvalg1" />  </a><br>
<a href="<c:url value="MinSide"/>" >   <spring:message code="menyvalg2" />  </a><br>
<a href="<c:url value="Kontakt"/>">         <spring:message code="menyvalg3" />  </a><br>
-->

<nav class="dropdownmenu">
    <ul> 
        <li><a href="Forside">Forside</a></li>
        <li><a href="Kontakt">Kontakt</a></li>
        <li><a href="#">Romvalg</a>
            <ul id="submenu">
                <li><a href="VelgRom">Bestill rom</a></li>
                <li><a href="FinnRom">Finn rom</a></li>
            </ul>
        </li>
        <li><a href="SokeSide">Søk</a></li>
        <li><a href="#">Min side</a>
            <ul id="submenu">
                
                <li><a href="MinSide">Min informasjon</a></li>
                
                <li>
                    <c:set var="tilgang" value="${brukerb.getTilgangsniva()}"></c:set>
                    <c:if test="${tilgang == 2}">
                        <a href="LeggTilBruker">Legg til bruker</a>
                    </c:if>
                </li>
                
                <li><a href="loggUt">Logg ut</a></li>
                
            </ul>
        </li>
    </ul>
</nav>
