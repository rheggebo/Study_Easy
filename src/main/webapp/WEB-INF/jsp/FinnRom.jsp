<%-- 
    Document   : FinnRom
    Created on : 08.jan.2016, 14:47:36
    Author     : Ane
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<script src="http://code.jquery.com/jquery-1.8.2.min.js"></script>
<script src="modernizr.custom.39755.js"></script>
<script src="http://afarkas.github.com/webshim/demos/js-webshim/minified/polyfiller.js"></script>
<script>
    //implement all unsupported features || call polyfill before DOM-Ready to implement everything as soon and as fast as possible
    $.webshims.polyfill();
</script>

<main>
    <div style="display: inline-block">
        <form:form action="finnromdata" modelAttribute="rom" method="post" onsubmit="return validate();">
            <fieldset>
                <legend>Velg ønsket spesifikasjoner:</legend>
                <dl>
                    <dt><label for="romtypeFinnRom">Romtype:<em>*</em></label></dt>
                    <dd><select id="romtypeFinnRom" name="romtype" required >
                        <option value="forelesningssal">Forelesingssal</option>
                        <option value="moterom">Møterom</option>
                        <option value="grupperom">Grupperom</option>
                    </select>

                    </dd>
                    <!-- <dd><input type="text" name="romtype" list="ref-romtype" placeholder="Nedtrekksliste"></dd>
                     <datalist id="ref-romtype">
                     </datalist> -->

                    <dt><label for="strFinnRom">Strørrelse(m<sup>2</sup>):</label><input class="checkboxes" type="checkbox" id="storrelse" value="Skjerm" name="storrelse"></dt>
                    <dd><input id="strFinnRom" name="storrelseNum" type="number" min="5" max="200" value="5" disabled></dd>

                    <dt><label for="checkboxes">Utstyr:</label></dt>
                    <dd><input class="checkboxes" type="checkbox" id="skjerm" name="skjerm">PC-skjerm<input type="number" id="antSkjerm" name="antSkjerm" min="0" value="0" disabled><br> 
                        <input class="checkboxes" type="checkbox" id="tavle" name="tavle">Tavle<input type="number" id="antTavle" name="antTavle" min="0" value="0" disabled><br> 
                        <input class="checkboxes" type="checkbox" id="sitteplass" name="sitteplass">Sitteplass<input type="number" id="antSitteplass" name="antSitteplass" min="0" value="0" disabled><br> 
                        <input class="checkboxes" type="checkbox" id="prosjektor" name="prosjektor">Prosjektor<input type="number" id="antProsjektor" name="antProsjektor" min="0" value="0" disabled><br> 
                    </dd>
                    

                    <dt><label for="datoFinnRom">Dato:<em>*</em></label></dt>
                    <dd><input class="min-today" id="datoFinnRom" type="date" name="fraDato" value="${fraDato}" required></dd>
                    
                    
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
                    
                    <dt><label for="tilTid">Tid til:<em>*</em></label></dt>
                    <dd><select id="tilTid" name="varighet" required>
                    <option value="1">1 time</option>
                    <option value="2">2 timer</option>
                    <option value="3">3 timer</option>
                    </select></dd>
                    


                </dl>
                <input type="submit" value="Finn ledig rom">
            </fieldset>
        </form:form>
</div>
    <div style = "display: inline-block; float: right">
        <fieldset >
        <legend>Ny hendelse</legend>
            <input type="checkbox" name="leggtil" value="add" required="true">Opprett hendelse i rom-booking<br>
            <dt><label for="fornavn">Tittel:<em>*</em></label></dt>
            <dd><input type="text" placeholder="Skriv tittel her"> 
                
            </dd>
            <dt><label for="privatOffentlig">Privat/Offentlig:<em>*</em></label></dt>
            <dd><select name="valg" id="valg" required="true">
                    <option value="Offentlig">Offentlig</option>
                    <option value="Privat">Privat</option>
                </select>
            </dd>
            <dt><label for="romtypeFinnRom">Type:<em>*</em></label></dt>
                    <dd><select id="romtypeFinnRom" name="romtype" required >
                        <option value="forelesningssal">Forelesning</option>
                        <option value="moterom">Møte</option>
                        <option value="grupperom">Privat</option>
                    </select>

            <dt><label for="privatOffentlig">Fag:<em>*</em></label></dt>
            <dd><select name="valg" id="valg" required="true">
                    <option value="Fag1">Fag 1</option>
                    <option value="Fag2">Fag 2</option>
                    <option value="Fag3">Fag 3</option>
                    <option value="Fag4">Fag 4</option>
                </select>
            </dd>
            
            <dt><label for="Notat">Notat:</label></dt>
            <textarea id="notat" placeholder="Skriv notat her" name="notat" autofocus="true" rows="10" cols="55" ></textarea>
            
        <p><input type="submit" value="Legg til" onclick=""></p>
    </fieldset>
    </div>
    <section class="searchInfo">
        <table>
            <c:forEach var="liste" items="${liste}" varStatus="status">
                <form:form modelAttribute="event" action="BookRom">
                    <tr>
                        <form:input type="hidden" path="rom" value="${liste}"/>
                        <form:input type="hidden" path="epost"/>
                        <pre><td><c:out value="${liste}"></c:out><input class=slettknapp type='submit' value='Book!'/></td></pre>
                    </tr>
                </form:form>      
            </c:forEach>                      
        </table>
    </section>
    <script language="JavaScript">
        $(document).ready(function(){
            function skjerm(){
                var element = document.getElementById("skjerm");
                element.checked ? document.getElementById("antSkjerm").disabled = false : 
                document.getElementById("antSkjerm").disabled = true;
            }
            $("#skjerm").on("change", skjerm);
        });
        $(document).ready(function(){
            function tavle(){
                var element = document.getElementById("tavle");
                element.checked ? document.getElementById("antTavle").disabled = false : 
            document.getElementById("antTavle").disabled = true;  
            }
            $("#tavle").on("change", tavle);
        });
        $(document).ready(function(){
            function sittplass(){
                var element = document.getElementById("sitteplass");
                element.checked ? document.getElementById("antSitteplass").disabled = false : 
            document.getElementById("antSitteplass").disabled = true;  
            }
            $("#sitteplass").on("change", sittplass);
        });
        $(document).ready(function(){
            function prosjektor(){
                var element = document.getElementById("prosjektor");
                element.checked ? document.getElementById("antProsjektor").disabled = false : 
            document.getElementById("antProsjektor").disabled = true;  
            }
            $("#prosjektor").on("change", prosjektor);
        });
        $(document).ready(function(){
            function prosjektor(){
                var element = document.getElementById("storrelse");
                element.checked ? document.getElementById("strFinnRom").disabled = false : 
            document.getElementById("strFinnRom").disabled = true;  
            }
            $("#storrelse").on("change", prosjektor);
        });
    </script>
    <script>
        $(function(){
            $('[type="date"].min-today').prop('min', function(){
                return new Date().toJSON().split('T')[0];
            });
        });
    </script>
</main>
