
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
    <div id="velgRomSøkeBokser" class="tekst">
        <form action="VelgRomSok" method="post" onsubmit=" return validate();">
            <fieldset>
                <legend><b>Velg ønsket spesifikasjoner:</b></legend>
                <dl>
                    <dt><label for="datoFinnRom">Dato:<em>*</em></label></dt>
                    <dd><input id="datoFinnRom" class="min-today" type="date" name="fraDato" required></dd>
                    <dt><label for="fraTid">Tid fra:<em>*</em></label></dt>
                    <dd><select id="fraTid" name="fraTid" required>
                    <option value="1">06.00</option>
                    <option value="2">07.00</option>
                    <option value="3">08.00</option>
                    <option value="4">09.00</option>
                    <option value="5">10.00</option>
                    <option value="6">11.00</option>
                    <option value="7">12.00</option>
                    <option value="8">13.00</option>
                    <option value="9">14.00</option>
                    <option value="10">15.00</option>
                    <option value="11">16.00</option>
                    <option value="12">17.00</option>
                    <option value="13">18.00</option>
                    <option value="14">19.00</option>
                    <option value="15">20.00</option>
                    <option value="16">21.00</option>
                    <option value="17">22.00</option>
                    </select>
                    </dd>

                    <dt><label for="tilTid">Varighet:<em>*</em></label></dt>
                    <dd><select id="tilTid" name="varighet" required>
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                    </select></dd>

                </dl>
                <input type="submit" class="vanligknapp" value="Finn ledig rom">
            </fieldset>
        </form>
    </div>
    
    <div id="RomInfo" class="tekst">
        <fieldset>
            <legend><b>Rominformasjon:</b></legend>
                <form action="VelgRomRes" method="post">    
                    <table>
                        <tr>
                            <td>Rom-id:</td>
                            <td><label id="rom"> </td>
                        </tr>
                        <!--
                        <tr>
                            <td>Rom navn:</td>
                            <td><label id="okRomNavn"></label></td>
                        </tr>
                        <tr>
                            <td>Etasje:</td>
                            <td><label id="okEtasje"></label></td>
                        </tr>
                        <tr>
                            <td>Plasser:</td>
                            <td><label id="okPlasser"></label></td>
                        </tr>
                        -->
                        <tr>
                            <td>Dato:</td>
                            <td><label id="okDato"></label></td>
                        </tr>
                        <tr>
                            <td>Tid fra:</td>
                            <td><label id="okTidFra"></label></td>
                        </tr>
                        <tr>
                            <td>Tid til</td>
                            <td><label id="okTidTil"></label></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td><label>${tilbakeMelding}</label></td>
                        </tr>
                    </table>
                    <input type="submit" class="vanligknapp" id="resRomVelgRom" value="Reserver rom">
                    <c:set var="tilgang" value="${bruker.getTilgangsniva()}"></c:set>
                    <c:if test="${tilgang == 1}">
                    <input type="submit" class="vanligknapp" formaction="OverstyrRomL" value="Overstyr rombestilling">
                    </c:if>
                    <br>
                    <c:if test="${tilgang == 2}">
                    <input type="submit" class="vanligknapp" formaction="OverstyrRomAdmin" value="Overstyr rombestilling">
                    </c:if>
                    
                    <c:if test="${tilgang == 2}">
                    <input type="submit" class="vanligknapp" formaction="VelgRomRed" value="Rediger rom informasjon">
                    </c:if>
                </form>
        </fieldset>
        
    </div>
    
                
    <div>
        <span class="tekst">Du befinner deg i</span>
            <p id="etasjeTeller">1. etasje</p>
                <span class="tekst">på&nbsp;<p id="romNavn">(ingen rom valgt)</p></span> 
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
        $(function(){
            $('[type="date"].min-today').prop('min', function(){
                return new Date().toJSON().split('T')[0];
            });
        });
    </script>
    
    <script>    
        function erLedig(id){
            if (("${liste}").indexOf(id) !=-1) {
                //alert(id+" er ledig");
                return 1;
            }
            //alert(id+" er ikke ledig");
            return -1;
        }
        
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
                        document.getElementById('rom').innerHTML=this.id;
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
                        document.getElementById('rom').innerHTML=this.id;
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
                       document.getElementById('rom').innerHTML=this.id;
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
                       document.getElementById('rom').innerHTML=this.id;
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
                        document.getElementById('rom').innerHTML=this.id;
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
                        document.getElementById('rom').innerHTML=this.id;
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
                       document.getElementById('rom').innerHTML=this.id;
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
                       document.getElementById('rom').innerHTML=this.id;
                }, false);
            } },false);
    </script>
