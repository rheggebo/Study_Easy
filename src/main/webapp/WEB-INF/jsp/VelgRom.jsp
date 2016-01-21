
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<!--
        Dette er kodet inn for våre 4 bilder hvis vi utvider til flere bygninger må vi endre løsning:
        1. Burde loade inn blider med Jquary i objectene våre. 
        2. Endre løsning på hvilket bile som er øverst selv om vi kan sikkert bruke zIndex.
        3. SVG fungerer dårlig i firefox 
-->
    
<main>
    <div id="velgRomSøkeBokser"  class="tekst">
        <fieldset class="fieldsetDefault">
            <legend><b>Velg ønsket spesifikasjoner</b></legend>
            <form:form modelAttribute="formVelgRom" method="POST">
                <table>
                    <tr>
                        <td>Dato:<em>* </em></td>
                        <td><form:input class="min-today" type="date" id="datoFraDefault" path="fraDato"/></td>
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
                            <input class="defaultKnapp" formaction="VelgRomSok" type="submit" value="Søk">
                        </td>
                        <td></td>
                    </tr>
                    <tr>
                        <td>RomID:<em>* </em></td>
                        <td colspan="2"><form:input class="velgRomForm" id="rom" path="romId" disabled="true"/></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td>
                            <input class="defaultKnapp" formaction="VelgRomReserverRom" type="submit" value="Bestill">
                        </td>
                        <td></td>
                    </tr>  
                    <c:set var="tilgang" value="${bruker.getTilgangsniva()}"></c:set>
                    <c:if test="${tilgang == 1}">
                        <tr>
                            <td>
                            </td>
                            <td>
                            <input type="submit" class="defaultKnapp" formaction="OverstyrRomL" value="Overstyr rombestilling">
                            </td>
                            <td>
                            </td>
                        </tr>
                     </c:if>
                    <c:if test="${tilgang == 2}">
                        <tr><td>
                            </td>
                            <td>
                            <input type="submit" class="defaultKnapp" formaction="OverstyrRomAdmin" value="Overstyr rombestilling">
                            </td>
                            <td>
                            <input type="submit" class="defaultKnapp" formaction="VelgRomRed" value="Rediger rom">
                            </td>
                        </tr>
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
                    <p>Trykk på pilene for å endre etasje og på rommet for å få mer informasjon.</p>
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
        function erLedig(id){
            if (("${liste}").indexOf(id) !=-1) {
                return 1;
            }
            //alert(id+" er ikke ledig");
            return -1;
            
        }
        
        $(window).load(function(){
            // Get the Object by ID
            var a = document.getElementById("plantegningEtasje1");
            // Get the SVG document inside the Object tag
            var svgDoc = a.contentDocument;
            // Get one of the SVG items by ID;
            var svgLayer = svgDoc.getElementsByClassName("planRom");
            
            //Av en eller annen grunn skipper noen av forløkkene noen rom så vi må kjøre den 5 ganger......
            for (i=0; i<svgLayer.length;i++){
                if (erLedig(svgLayer[i].id)<=0){
                    svgLayer[i].setAttribute('class','planRomOpptatt');
                }
            }
            for (i=0; i<svgLayer.length;i++){
                if (erLedig(svgLayer[i].id)<=0){
                    svgLayer[i].setAttribute('class','planRomOpptatt');
                }
            }
            for (i=0; i<svgLayer.length;i++){
                if (erLedig(svgLayer[i].id)<=0){
                    svgLayer[i].setAttribute('class','planRomOpptatt');
                }
            }
            for (i=0; i<svgLayer.length;i++){
                if (erLedig(svgLayer[i].id)<=0){
                    svgLayer[i].setAttribute('class','planRomOpptatt');
                }
            }
            // etasje 2:
            var a2 = document.getElementById("plantegningEtasje2");
            // Get the SVG document inside the Object tag
            var svgDoc2 = a2.contentDocument;
            // Get one of the SVG items by ID;
            var svgLayer2 = svgDoc2.getElementsByClassName("planRom");
            for (i=0; i<svgLayer2.length;i++){
                if (erLedig(svgLayer2[i].id)<=0){
                    svgLayer2[i].setAttribute('class','planRomOpptatt');
                }
            }
            for (i=0; i<svgLayer2.length;i++){
                if (erLedig(svgLayer2[i].id)<=0){
                    svgLayer2[i].setAttribute('class','planRomOpptatt');
                }
            }
            for (i=0; i<svgLayer2.length;i++){
                if (erLedig(svgLayer2[i].id)<=0){
                    svgLayer2[i].setAttribute('class','planRomOpptatt');
                }
            }
            for (i=0; i<svgLayer2.length;i++){
                if (erLedig(svgLayer2[i].id)<=0){
                    svgLayer2[i].setAttribute('class','planRomOpptatt');
                }
            }
            //eatasje 3:
            var a3 = document.getElementById("plantegningEtasje3");
            // Get the SVG document inside the Object tag
            var svgDoc3 = a3.contentDocument;
            // Get one of the SVG items by ID;
            var svgLayer3 = svgDoc3.getElementsByClassName("planRom");
            for (i=0; i<svgLayer3.length;i++){
                if (erLedig(svgLayer3[i].id)<=0){
                    svgLayer3[i].setAttribute('class','planRomOpptatt');
                }
            }
            for (i=0; i<svgLayer3.length;i++){
                if (erLedig(svgLayer3[i].id)<=0){
                    svgLayer3[i].setAttribute('class','planRomOpptatt');
                }
            }
            for (i=0; i<svgLayer3.length;i++){
                if (erLedig(svgLayer3[i].id)<=0){
                    svgLayer3[i].setAttribute('class','planRomOpptatt');
                }
            }
            for (i=0; i<svgLayer3.length;i++){
                if (erLedig(svgLayer3[i].id)<=0){
                    svgLayer3[i].setAttribute('class','planRomOpptatt');
                }
            }
            
            //etasje 4:
            var a4 = document.getElementById("plantegningEtasje4");
            // Get the SVG document inside the Object tag
            var svgDoc4 = a4.contentDocument;
            // Get one of the SVG items by ID;
            var svgLayer4 = svgDoc4.getElementsByClassName("planRom");
            for (i=0; i<svgLayer4.length;i++){
                if (erLedig(svgLayer4[i].id)<=0){
                    svgLayer4[i].setAttribute('class','planRomOpptatt');
                }
            }
            for (i=0; i<svgLayer4.length;i++){
                if (erLedig(svgLayer4[i].id)<=0){
                    svgLayer4[i].setAttribute('class','planRomOpptatt');
                }
            }
            for (i=0; i<svgLayer4.length;i++){
                if (erLedig(svgLayer4[i].id)<=0){
                    svgLayer4[i].setAttribute('class','planRomOpptatt');
                }
            }
            for (i=0; i<svgLayer4.length;i++){
                if (erLedig(svgLayer4[i].id)<=0){
                    svgLayer4[i].setAttribute('class','planRomOpptatt');
                }
            }
            
           
            
        });
        
            
    </script>
    
    <script>    
        /*
        function erLedig(id){
            if (("${liste}").indexOf(id) !=-1) {
                //alert(id+" er ledig");
                return 1;
            }
            //alert(id+" er ikke ledig");
            return -1;
        }
        */
        /*
        //Setter Ledig:
            //Første Etasje:
            var sVGBilde1 = document.getElementById("plantegningEtasje1");
            sVGBilde1.addEventListener("load",function(){
                var sVGContent1 = sVGBilde1.contentDocument;
                var sVGRom1 = sVGContent1.querySelectorAll(".planRom");
                for (var i = 0, length = sVGRom1.length; i < length; i++) {
                    var rom1= sVGRom1[i];    
                    rom1.addEventListener("mouseover", function(){ 
                    if(erLedig(this.id)<=0){
                        //alert("Setter planrom");
                        $(this).attr("class","planRomOpptatt");
                    }}, false);
            }},false);
            //Andre etasje:
            var aLedig2 = document.getElementById("plantegningEtasje2");
            aLedig2.addEventListener("load",function(){
              var svgDocLedig2 = aLedig2.contentDocument;
              var elsLedig2 = svgDocLedig2.querySelectorAll(".planRom");
              for (var i = 0, length = elsLedig2.length; i < length; i++) {
                var deltaLedig2= elsLedig2[i];
                deltaLedig2.addEventListener("mouseover", function(){ 
                    if(erLedig(this.id)<=0){
                        $(this).attr("class","planRomOpptatt");
                    }}, false);
            }},false);
            //Tredje etasje:    
            var aLedig3 = document.getElementById("plantegningEtasje3");
            aLedig3.addEventListener("load",function(){
              var svgDocLedig3 = aLedig3.contentDocument;
              var elsLedig3 = svgDocLedig3.querySelectorAll(".planRom");
              for (var i = 0, length = elsLedig3.length; i < length; i++) {
                var deltaLedig3= elsLedig3[i];
                deltaLedig3.addEventListener("mouseover", function(){ 
                    if(erLedig(this.id)<=0){
                        $(this).attr("class","planRomOpptatt");
                    }}, false);
            }},false);
            //Fjerde etasje:
            var aLedig4 = document.getElementById("plantegningEtasje4");
            aLedig4.addEventListener("load",function(){
              var svgDocLedig4 = aLedig4.contentDocument;
              var elsLedig4 = svgDocLedig4.querySelectorAll(".planRom");
              for (var i = 0, length = elsLedig4.length; i < length; i++) {
                var deltaLedig4= elsLedig4[i];
                deltaLedig4.addEventListener("mouseover", function(){ 
                    if(erLedig(this.id)<=0){
                        $(this).attr("class","planRomOpptatt");
                    }}, false);
            } },false);
        */
        //Klikkhendelser for planRom objeckter:
            //Første etasje:
            var a = document.getElementById("plantegningEtasje1");
            a.addEventListener("load",function(){
              var svgDoc = a.contentDocument;
              var els = svgDoc.querySelectorAll(".planRom");
              for (var i = 0, length = els.length; i < length; i++) {
                var delta= els[i];
                //Mus over:
                delta.addEventListener("mouseover", function(){ 
                       document.getElementById('romNavn').innerHTML=this.id;
                }, false);
                //KlikkFunksjon:
                delta.addEventListener("click", function(){ 
                       document.getElementById('rom').value=this.id;
                    }, false);
            }},false);
            //Andre etasje:
            var a2 = document.getElementById("plantegningEtasje2");
            a2.addEventListener("load",function(){
              var svgDoc2 = a2.contentDocument;
              var els2 = svgDoc2.querySelectorAll(".planRom");
              for (var i = 0, length = els2.length; i < length; i++) {
                var delta2= els2[i];
                delta2.addEventListener("mouseover", function(){ 
                       document.getElementById('romNavn').innerHTML=this.id;
                }, false);
                delta2.addEventListener("click", function(){ 
                        document.getElementById('rom').value=this.id;
                    }, false);
            }},false);
            //Tredje etasje:    
            var a3 = document.getElementById("plantegningEtasje3");
            a3.addEventListener("load",function(){
              var svgDoc3 = a3.contentDocument;
              var els3 = svgDoc3.querySelectorAll(".planRom");
              for (var i = 0, length = els3.length; i < length; i++) {
                var delta3= els3[i];
                delta3.addEventListener("mouseover", function(){ 
                       document.getElementById('romNavn').innerHTML=this.id;
                }, false);
                delta3.addEventListener("click", function(){ 
                       document.getElementById('rom').value=this.id;
                    }, false);
            }},false);
            //Fjerde etasje:
            var a4 = document.getElementById("plantegningEtasje4");
            a4.addEventListener("load",function(){
              var svgDoc4 = a4.contentDocument;
              var els4 = svgDoc4.querySelectorAll(".planRom");
              for (var i = 0, length = els4.length; i < length; i++) {
                var delta4= els4[i];
                delta4.addEventListener("mouseover", function(){ 
                       document.getElementById('romNavn').innerHTML=this.id;
                }, false);
                delta4.addEventListener("click", function(){ 
                       document.getElementById('rom').value=this.id;
                }, false);
            } },false);

        //Klikkhendelser for planRomOpptatt objeckter:
        //trenger ikke begge siden begge gjør det samme nå, men hvis man vil endre det)
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
                }, false);
            } },false);
    </script>
     
    <script>
        // setter dato min til idag
        $(function(){
            $('[type="date"].min-today').prop('min', function(){
                return new Date().toJSON().split('T')[0];
            });
        });
    </script>
    
    <script>
        // setter dato til idag
        var date = new Date();
        
        var day = date.getDate();
        var month = date.getMonth() + 1;
        var year = date.getFullYear();

        if (month < 10) month = "0" + month;
        if (day < 10) day = "0" + day;

        var today = year + "-" + month + "-" + day;

        document.getElementById('datoFraDefault').value=today;
    </script>

