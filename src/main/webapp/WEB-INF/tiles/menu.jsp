
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<nav class="dropdownmenu">
    <ul> 
        <li><a id="forside" href="Forside">Forside</a></li>
        <li><a href="Kontakt">Kontakt</a></li>
        <li><a id="romValg" href="#">Romvalg</a>
            <ul id="submenu">
                <li><a id="bestillRom" href="VelgRom">Bestill rom</a></li>
                <li><a id="finnRom" href="FinnRom">Finn rom</a></li>
            </ul>
        </li>
        <li><a id="sokeSide" href="SokeSide">Søk</a></li>
        <li><a id="minSide" href="#">Min side</a>
            <ul id="submenu">
                
                <li><a id="minInfo" href="MinSide">Min informasjon</a></li>
                
                <c:set var="tilgang" value="${bruker.getTilgangsniva()}"></c:set>
                <c:if test="${tilgang == 2}">
                <li>
                    <a id="leggTilBruker" href="LeggTilBruker">Administratoroperasjoner</a>
                    
                </li>
                </c:if>
                
                <li><a id="loggUt" href="loggUt">Logg ut</a></li>
                
            </ul>
        </li>
    </ul>
</nav>
