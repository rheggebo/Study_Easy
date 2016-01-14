
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
