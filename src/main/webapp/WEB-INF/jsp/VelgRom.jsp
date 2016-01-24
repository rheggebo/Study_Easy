
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<!--
        1. Burde loade inn blider med Jquary i objectene våre. 
        3. SVG fungerer dårlig i firefox 
-->
    
<main>
    <c:set var="feilMeldingReservereRom" value="${feilMeldingReservereRom}"></c:set>
        <c:if test="${not empty feilMeldingReservereRom}">
            <fieldset class="feilmelding">
                <spring:message code="${feilMeldingReservereRom}"/>
            </fieldset>
        </c:if>
    <div id="velgRomSøkeBokser"  class="tekst">
        <fieldset class="fieldsetDefault">
            <legend><b>Velg ønsket spesifikasjoner</b></legend>
            <form:form modelAttribute="formVelgRom" method="POST">
                <table>
                    <tr>
                        <td>Dato:<em>* </em></td>
                        <td><form:input class="velgRomForm" type="date" id="datoFraDefault" required="true" path="fraDato"/></td>
                        <td><form:errors path="fraDato" /></td>
                    </tr>
                    <tr>
                        <td>Tid fra:<em>* </em></td>
                        <td>
                            <form:select class="velgRomForm" path="fraTid">
                                <form:options items="${formVelgRom.tiderList}"/>
                            </form:select>
                        </td>
                        <td><form:errors path="fraTid"/></td>
                    </tr>
                    <tr>
                        <td>Varighet:<em>* </em></td>
                        <td>
                            <form:select class="velgRomForm" path="varighet">
                                <form:options items="${formVelgRom.varighetList}"/>
                            </form:select>
                        </td>
                        <td><form:errors path="varighet"/></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td>
                            <input class="defaultKnapp" id="sok" formaction="VelgRomSok" type="submit" value="Søk">
                        </td>
                        <td></td>
                    </tr>
                    <c:set var="harSøkt" value="${formVelgRom.getVarighet()}"></c:set>
                    <c:set var="opptatt" value="${opptatt}"></c:set>
                    <c:if test="${!opptatt}">
                    <c:if test="${harSøkt >= 1}">
                        <tr>
                            <td>RomID:<em>* </em></td>
                            <td ><label class="velgRomForm" id="romLable"/>
                            </td>
                            <td>
                                <form:input hidden="true" class="velgRomForm" id="rom" path="romId"/>
                            </td>
                        </tr>
                        <tr>
                            <td></td>
                            <td>
                                <input class="defaultKnapp" id="bestill" formaction="VelgRomReserver" type="submit" value="Bestill">
                            </td>
                            <td></td>
                        </tr>  
                        <c:set var="tilgang" value="${bruker.getTilgangsniva()}"></c:set>
                        <c:if test="${tilgang == 2}">
                            <tr><td>
                                </td><td> 
                                    <input type="submit" class="defaultKnapp" formaction="VelgRomRed" value="Rediger rom">
                                </td>
                                <td></td>
                            </tr>
                        </c:if> 
                    </c:if>
                    </c:if>
                </table>
            </form:form>
        </fieldset>
    </div>
                
    <div>
        <span class="tekst">Du befinner deg i</span>
        <p id="etasjeTeller">1. etasje</p>
        <span class="tekst">&nbsp;<p id="romNavn">(ingen rom valgt)</p></span> 
    </div>  
       
    <div id="velgRomSøkeSVG">
        <object id="plantegningEtasje1" class="pantegningObject" style="z-index:9;"
                data="<c:url value="/resources/SVG/plantegningE1.svg"/>"> Din nettleser støtter ikke SVG </object>
        <object id="plantegningEtasje2" class="pantegningObject" style="z-index:8;" 
                data="<c:url value="/resources/SVG/plantegningE2.svg"/>"> Din nettleser støtter ikke SVG </object>
        <object id="plantegningEtasje3" class="pantegningObject" style="z-index:7;" 
                data="<c:url value="/resources/SVG/plantegningE3.svg"/>"> Din nettleser støtter ikke SVG </object>
        <object id="plantegningEtasje4" class="pantegningObject" style="z-index:6;" 
                data="<c:url value="/resources/SVG/plantegningE4.svg"/>"> Din nettleser støtter ikke SVG </object>
    </div>

     <ul class="ikonForside" style=" z-index:1;">
        <li class="knappVelgRom">
            <a class="infoKnappForside" href="#infoVindu2"><span class="fontawesome-info-sign"></span></a>
            <div id="infoVindu2" class="infoVinduForside">
                <div>
                    <a href="#lukk" title="Lukk" class="lukk">X</a>
                    <h2>Informasjon</h2>
                    <p>Trykk på pilene for å skifte etasje,
                        og trykk på rommet for å få mer informasjon. Ledige rom vises
                        ved søk.
                    </p>
                </div>
            </div>
        </li>
        <li class="knappVelgRom">
            <button class="knappForside" id="leggTilKnappForside" onclick="changeZIndexPlantegningerOpp()">
                <span class="fontawesome-circle-arrow-up"></span>
            </button>
        </li>
        <li class="knappVelgRom">
            <button class="knappForside" id="søkeKnappForside"  onclick="changeZIndexPlantegningerNed()">
                <span class="fontawesome-circle-arrow-down"></span>
            </button>
        </li>
    </ul>
    <div id="velgRomFarge">
        <ul style="list-style-type: none;">
            <li>
                <input type="text" class="fargeTekst"  style="background-color: #00FF7F;">
                <label for="checkbox_2">Ledige rom</label>
            </li>
            <li>
                <input type="text" class="fargeTekst"  style="background-color: #FFA500;">
                <label for="checkbox_3">Opptatte rom</label>
            </li>
        </ul>
    </div>
</main>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>

<script>
    function changeZIndexPlantegningerOpp() {
        if( document.getElementById('plantegningEtasje4').style.zIndex!=9){
            var teller;
            document.getElementById('plantegningEtasje1').style.zIndex++;
            document.getElementById('plantegningEtasje2').style.zIndex++;
            document.getElementById('plantegningEtasje3').style.zIndex++;
            document.getElementById('plantegningEtasje4').style.zIndex++;

            if(document.getElementById('plantegningEtasje1').style.zIndex==10){
                 document.getElementById('plantegningEtasje1').style.zIndex = 6;
                 document.getElementById('etasjeTeller').innerHTML="2. etasje";

            }
            if( document.getElementById('plantegningEtasje2').style.zIndex==10){
                 document.getElementById('plantegningEtasje2').style.zIndex = 6;
                 document.getElementById('etasjeTeller').innerHTML="3. etasje";
            }
            if( document.getElementById('plantegningEtasje3').style.zIndex==10){
                 document.getElementById('plantegningEtasje3').style.zIndex = 6;
                 document.getElementById('etasjeTeller').innerHTML="4. etasje";
            }
        }
    }
    function changeZIndexPlantegningerNed() {
        if( document.getElementById('plantegningEtasje1').style.zIndex!=9){
            var teller;
            document.getElementById('plantegningEtasje1').style.zIndex--;
            document.getElementById('plantegningEtasje2').style.zIndex--;
            document.getElementById('plantegningEtasje3').style.zIndex--;
            document.getElementById('plantegningEtasje4').style.zIndex--;

            if(document.getElementById('plantegningEtasje1').style.zIndex==5){
                 document.getElementById('plantegningEtasje1').style.zIndex = 9;
                 document.getElementById('etasjeTeller').innerHTML="1. etasje";

            }
            if( document.getElementById('plantegningEtasje2').style.zIndex==5){
                 document.getElementById('plantegningEtasje2').style.zIndex = 9;
                 document.getElementById('etasjeTeller').innerHTML="2. etasje";
            }
            if( document.getElementById('plantegningEtasje3').style.zIndex==5){
                 document.getElementById('plantegningEtasje3').style.zIndex = 9;
                 document.getElementById('etasjeTeller').innerHTML="3. etasje";
            }
        }
    }
</script>

<script>
    // Veldig dårlig måte å endre brilde når vi kommer fra søk:

    if((erLedig(" 1 stÃ¸rrelse"))==1){
    }
    if((erLedig(" 2 stÃ¸rrelse")==1)){
        changeZIndexPlantegningerOpp();
    }
    if((erLedig(" 3 stÃ¸rrelse"))==1){
        changeZIndexPlantegningerOpp();
        changeZIndexPlantegningerOpp();
    }
    if((erLedig(" 4 stÃ¸rrelse"))==1){
        changeZIndexPlantegningerOpp();
        changeZIndexPlantegningerOpp();
        changeZIndexPlantegningerOpp();
    }
    
    function erLedig(id){
        if (("${liste}").indexOf(id) !=-1) {
            return 1;
        }
        //alert(id+" er ikke ledig");
        return -1;
    }
    

    $(window).load(function(){
        // Get the Object by ID
        var SVG = document.getElementById("plantegningEtasje1");
        // Get the SVG document inside the Object tag
        var svgDoc = SVG.contentDocument;
        // Get one of the SVG items by Class;
        var svgElements = svgDoc.getElementsByClassName("planRomOpptatt");
        for (i=0; i<svgElements.length;i++){
            if (erLedig(svgElements[i].id)>=0){
                svgElements[i].setAttribute('class','planRom');
                //fjerner elementet i svgElements når jeg bytter klasse så hvis føste element er opptatt slettes svgElements[0]
                i--;
            }
        }
        // etasje 2:
        var SVG2 = document.getElementById("plantegningEtasje2");
        // Get the SVG document inside the Object tag
        var svgDoc2 = SVG2.contentDocument;
        // Get one of the SVG items by ID;
        var svgElements2 = svgDoc2.getElementsByClassName("planRomOpptatt");
        for (i=0; i<svgElements2.length;i++){
            if (erLedig(svgElements2[i].id)>=0){
                svgElements2[i].setAttribute('class','planRom');
                i--;
            }
        }
        //eatasje 3:
        var SVG3 = document.getElementById("plantegningEtasje3");
        // Get the SVG document inside the Object tag
        var svgDoc3 = SVG3.contentDocument;
        // Get one of the SVG items by ID;
        var svgElements3 = svgDoc3.getElementsByClassName("planRomOpptatt");
        for (i=0; i<svgElements3.length;i++){
            if (erLedig(svgElements3[i].id)>=0){
                svgElements3[i].setAttribute('class','planRom');
                i--;
            }
        }
        //etasje 4:
        var SVG4 = document.getElementById("plantegningEtasje4");
        // Get the SVG document inside the Object tag
        var svgDoc4 = SVG4.contentDocument;
        // Get one of the SVG items by ID;
        var svgElements4 = svgDoc4.getElementsByClassName("planRomOpptatt");
        for (i=0; i<svgElements4.length;i++){
            if (erLedig(svgElements4[i].id)>=0){
                svgElements4[i].setAttribute('class','planRom');
                i--;
            }
        }
    }); 
</script>
<script>
    function erLedig3(id){
        if (("${liste}").indexOf(id) !=-1) {
            return 1;
        }
        //alert(id+" er ikke ledig");
        return -1;
    }
    //Klikkhendelser for planRomOpptatt objeckter(de kommer inn som planOpptatt):
    //Første etasje:
    var aO1 = document.getElementById("plantegningEtasje1");
    aO1.addEventListener("load",function(){
      var svgDoc = aO1.contentDocument;
      var els = svgDoc.querySelectorAll(".planRomOpptatt");
      for (var i = 0, length = els.length; i < length; i++) {
        var delta= els[i];
        //Mus over:
        delta.addEventListener("mouseover", function(){ 
               document.getElementById('romNavn').innerHTML=this.id;
        }, false);
        //KlikkFunksjon:
        delta.addEventListener("click", function(){ 
                    document.getElementById('rom').value=this.id;
                    document.getElementById('romLable').innerHTML=this.id;
                if (erLedig3(this.id)!=1){
                    document.getElementById('rom').value=this.id+" Opptatt";
                    document.getElementById('romLable').innerHTML=this.id +" Opptatt";
                }
            }, false);
    }},false);
    //Andre etasje:
    var aO2 = document.getElementById("plantegningEtasje2");
    aO2.addEventListener("load",function(){
      var svgDocO2 = aO2.contentDocument;
      var elsO2 = svgDocO2.querySelectorAll(".planRomOpptatt");
      for (var i = 0, length = elsO2.length; i < length; i++) {
        var deltaO2= elsO2[i];
        deltaO2.addEventListener("mouseover", function(){ 
               document.getElementById('romNavn').innerHTML=this.id;
        }, false);
        deltaO2.addEventListener("click", function(){ 
                    document.getElementById('rom').value=this.id;
                    document.getElementById('romLable').innerHTML=this.id;
                if (erLedig3(this.id)!=1){
                    document.getElementById('rom').value=this.id+" Opptatt";
                    document.getElementById('romLable').innerHTML=this.id +" Opptatt";
                }
            }, false);
    }},false);
    //Tredje etasje:    
    var aO3 = document.getElementById("plantegningEtasje3");
    aO3.addEventListener("load",function(){
      var svgDocO3 = aO3.contentDocument;
      var elsO3 = svgDocO3.querySelectorAll(".planRomOpptatt");
      for (var i = 0, length = elsO3.length; i < length; i++) {
        var deltaO3= elsO3[i];
        deltaO3.addEventListener("mouseover", function(){ 
               document.getElementById('romNavn').innerHTML=this.id;
        }, false);
        deltaO3.addEventListener("click", function(){ 
                    document.getElementById('rom').value=this.id;
                    document.getElementById('romLable').innerHTML=this.id;
                if (erLedig3(this.id)!=1){
                    document.getElementById('rom').value=this.id+" Opptatt";
                    document.getElementById('romLable').innerHTML=this.id+" Opptatt";
                }
            }, false);
    }},false);
    //Fjerde etasje:
    var aO4 = document.getElementById("plantegningEtasje4");
    aO4.addEventListener("load",function(){
      var svgDocO4 = aO4.contentDocument;
      var elsO4 = svgDocO4.querySelectorAll(".planRomOpptatt");
      for (var i = 0, length = elsO4.length; i < length; i++) {
        var deltaO4= elsO4[i];
        deltaO4.addEventListener("mouseover", function(){ 
               document.getElementById('romNavn').innerHTML=this.id;
        }, false);
        deltaO4.addEventListener("click", function(){ 
                    document.getElementById('rom').value=this.id;
                    document.getElementById('romLable').innerHTML=this.id;
                if (erLedig3(this.id)!=1){
                    document.getElementById('rom').value=this.id+" Opptatt";
                    document.getElementById('romLable').innerHTML=this.id+" Opptatt";
                }
        }, false);
    } },false);
</script>

<script>
    // setter dato min til idag
    $(function(){
        $('#datoFraDefault').prop('min', function(){
            return new Date().toJSON().split('T')[0];
        });
    });
</script>
