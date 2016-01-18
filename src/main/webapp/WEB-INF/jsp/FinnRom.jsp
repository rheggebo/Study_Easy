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
    <aside>
        <form action="finnromdata" modelAttribute='finnrom' method="post" onsubmit="return validate();">
            <fieldset>
                <legend>Velg ønsket spesifikasjoner:</legend>
                <dl>
                    <dt><label for="romtypeFinnRom">Romtype:<em>*</em></label></dt>
                    <dd><select id="romtypeFinnRom" name="romtype" required >
                        <option value="forelesingssal">Forelesingssal</option>
                        <option value="møterom">Møterom</option>
                        <option value="grupperom">Grupperom</option>
                        <option value="klasserom">Klasserom</option>
                    </select>

                    </dd>
                    <!-- <dd><input type="text" name="romtype" list="ref-romtype" placeholder="Nedtrekksliste"></dd>
                     <datalist id="ref-romtype">
                     </datalist> -->

                    <dt><label for="antStolplasserFinnRom">Antall stolplasser:<em>*</em></label></dt>
                    <dd><input id="antStolplasserFinnRom" type="number" name="num-stoler" min="1" max="150" value="1" required></dd>

                    <dt><label for="strFinnRom">Strørrelse(m<sup>2</sup>):</label></dt>
                    <dd><input id="strFinnRom" name="størrelse" type="number" min="5" max="200" value="5"></dd>

                    <dt><label for="checkboxes">Utstyr:</label></dt>
                    <dd><input class="checkboxes" type="checkbox" id="skjerm" value="Skjerm">PC-skjerm<br>
                        <input class="checkboxes" type="checkbox" id="tavle" value="tavle">Tavle<br>
                        <input class="checkboxes" type="checkbox" id="stikkontakter" value="stikkontakter">Stikkontakter<br>
                        <input class="checkboxes" type="checkbox" id="prosjektor" value="prosjektor">Prosjektor<br>
                    </dd>
                    

                   <dt><label for="datoFinnRom">Dato:<em>*</em></label></dt>
                    <dd><input id="datoFinnRom" type="date" name="date" required></dd>
                    
                    <dt><label for="fraTid">Tid fra:<em>*</em></label></dt>
                    <dd><select id="fraTid" required/></dd>
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
                    
                    <dt><label for="tilTid">Tid til:<em>*</em></label></dt>
                    <dd><select id="tilTid" required/></dd>
                    <option value="1">07.00</option>
                    <option value="2">08.00</option>
                    <option value="3">09.00</option>
                    <option value="4">10.00</option>
                    <option value="5">11.00</option>
                    <option value="6">12.00</option>
                    <option value="7">13.00</option>
                    <option value="8">14.00</option>
                    <option value="9">15.00</option>
                    <option value="10">16.00</option>
                    <option value="11">17.00</option>
                    <option value="12">18.00</option>
                    <option value="13">19.00</option>
                    <option value="14">20.00</option>
                    <option value="15">21.00</option>
                    <option value="16">22.00</option>
                    <option value="17">23.00</option>

                </dl>
                <input type="submit" value="Finn ledig rom">
            </fieldset>
        </form>
    </aside>

    <section class="info">
        <table>
            <c:forEach var="liste" items="${liste}">
                <tr>
                    <td><c:out value="${liste}"></c:out></td>
                </tr>                    
                          
            </c:forEach>                      
    </table>
    </section>

</main>
