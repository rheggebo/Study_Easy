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
        <form action="" method="post" onsubmit="return validate();">

            <legend>Velg ønsket spesifikasjoner rommet skal ha:</legend>
            <dl>
                Romtype:<em>*</em><br>
                <dd><select name="romtype" required >
                    <option value="forelesingssal">Forelesingssal</option>
                    <option value="møterom">Møterom</option>
                    <option value="grupperom">Grupperom</option>
                    <option value="klasserom">Klasserom</option>
                </select>

                </dd>
                <!-- <dd><input type="text" name="romtype" list="ref-romtype" placeholder="Nedtrekksliste"></dd>
                 <datalist id="ref-romtype">
                 </datalist> -->

                Antall stolplasser:<em>*</em><br>
                <dd><input type="number" name="num-stoler" min="1" max="150" value="1" required></dd>

                Størrelse(m2):<br>
                <dd><input id="str" name="størrelse" type="number" min="5" max="200" value="5"></dd>

                Utstyr:<br>
                <dd><input type="checkbox" id="skjerm" value="Skjerm">PC-skjerm <br>
                    <input type="checkbox" id="tavle" value="tavle">Tavle <br>
                    <input type="checkbox" id="stikkontakter" value="stikkontakter">Stikkontakter <br>
                    <input type="checkbox" id="prosjektor" value="prosjektor">Prosjektor <br></dd>



                Dato:<em>*</em><br>
                <dd><input type="date" name="date" required></dd>

            </dl>
            <input type="submit" value="Finn ledig rom">
        </form>
    </aside>

    <section class="info">
        <p>Resultatet kan inn her</p>
    </section>

</main>
