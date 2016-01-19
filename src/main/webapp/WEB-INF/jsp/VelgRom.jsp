
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>


<main>
    <div id="velgRomSøkeBokser">
        <form action="VelgRomSok" method="post" onsubmit=" return validate();">
            <fieldset>
                <legend>Velg ønsket spesifikasjoner:</legend>
                <dl>
                    <dt><label for="datoFinnRom">Dato:<em>*</em></label></dt>
                    <dd><input id="datoFinnRom" type="date" name="fraDato" required></dd>
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
                <input type="submit" value="Finn ledig rom">
            </fieldset>
        </form>
    </div>
    
    <div id="RomInfo">
        <fieldset>
            <legend>Rominformasjon:</legend>
                <form action="VelgRomRes" method="post">    
                    <table>
                        <tr>
                            <td>Rom-id:</td>
                            <td><label id="rom"> </td>
                        </tr>
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
                        <tr>
                            <td>Utstyr:</td>
                            <td><label id="okUtstyr"></label></td>
                        </tr>
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
                    </table>
                    <input type="submit" value="Reserver rom">
                </form>
        </fieldset>
    </div>
    <div id="musOverNavn"><p id="romNavn"></p></div>
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
    <div  style="position: absolute; margin-left: 750px; z-index: 10;"><p id="etasjeTeller">1. etg</p> </div>
    
                
</main>
    
    
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>

    <!--
        Dette er kodet inn for våre 4 bilder hvis vi utvider til flere bygninger må vi endre løsning:
        1. Burde loade inn blider med Jquary i objectene våre. 
        2. Endre løsning på hvilket bile som er øverst selv om vi kan sikkert bruke zIndex.
    -->
    
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
                     document.getElementById('etasjeTeller').innerHTML="2. etg";
                     
                }
                if( document.getElementById('plantegningEtasje2').style.zIndex==10){
                     document.getElementById('plantegningEtasje2').style.zIndex = 6;
                     document.getElementById('etasjeTeller').innerHTML="3. etg";
                }
                if( document.getElementById('plantegningEtasje3').style.zIndex==10){
                     document.getElementById('plantegningEtasje3').style.zIndex = 6;
                     document.getElementById('etasjeTeller').innerHTML="4. etg";
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
                     document.getElementById('etasjeTeller').innerHTML="1. etg";
                     
                }
                if( document.getElementById('plantegningEtasje2').style.zIndex==5){
                     document.getElementById('plantegningEtasje2').style.zIndex = 9;
                     document.getElementById('etasjeTeller').innerHTML="2. etg";
                }
                if( document.getElementById('plantegningEtasje3').style.zIndex==5){
                     document.getElementById('plantegningEtasje3').style.zIndex = 9;
                     document.getElementById('etasjeTeller').innerHTML="3. etg";
                }
            }
        }
    </script>
    
    
    <script>
        setLedigeRom();
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
    </script>
    
    <script>
        function setLedigeRom(){
            
        }
    </script>
    <script>
        function finnPos(liste){
            
        }
    </script>

    <script>
        function bekreftRom(){
            document.getElementById('okRomNavn').innerHTML=
            document.getElementById('okEtasje').innerHTML=
            document.getElementById('okPlasser').innerHTML=
            document.getElementById('okUtstyr').innerHTML=
            document.getElementById('okDato').innerHTML=
            document.getElementById('okTidFra').innerHTML=
            document.getElementById('okTidTil').innerHTML=
        }
    </script>
    
    <!--
    <script>
        $(window).load(function(){
            alert("test");
            $('.pantegningObject').on("click", function(){
                alert("test");
            });
        });
    </script>
    -->
    <!--
    <script>
         document.querySelector('object').addEventListener('load',function(){
         var p = this.contentDocument.documentElement.querySelectorAll('path');
         for(i=0;i<p.length;i++){
          p[i].addEventListener('click', function(){ 
               alert("Hello my name is "+this.id+"…");
             });
         }
         });
     </script>
    -->

<!--
<script>
    var date = new Date();

    var day = date.getDate();
    var month = date.getMonth() + 1;
    var year = date.getFullYear();

    if (month < 10) month = "0" + month;
    if (day < 10) day = "0" + day;

    var today = year + "-" + month + "-" + day;
    var maks = year+1 + "-" + month + "-" + day;

    document.getElementById('datoen').value = today;
</script>
-->
