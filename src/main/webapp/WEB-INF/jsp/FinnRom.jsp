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
        <fieldset class="fieldsetDefault">
            <legend><b>Velg ønsket spesifikasjoner</b></legend>
            <form:form modelAttribute="formFinnRom" method="POST" onsubmit="valider()">
                <table>
                    <tr>
                        <td>Dato:<em>* </em></td>
                        <td><form:input class="velgRomForm" id="datoFraDefault" type="date" path="fraDato" /></td>
                        <td><form:errors path="fraDato" /></td>
                    </tr>
                    <tr>
                        <td>Tid fra:<em>* </em></td>
                        <td>
                            <form:select class="velgRomForm" path="fraTid">
                                <form:options items="${formFinnRom.tiderList}"/>
                            </form:select>
                        </td>
                        <td><form:errors path="fraTid"/></td>
                    </tr>
                    <tr>
                        <td>Varighet:<em>* </em></td>
                        <td>
                            <form:select class="velgRomForm" path="varighet">
                                <form:options items="${formFinnRom.varighetList}"/>
                            </form:select>
                        </td>
                        <td><form:errors path="varighet"/></td>
                    </tr>
                    <tr>
                        <td>Romtype:<em>* </em></td>
                        <td>
                            <form:select class="velgRomForm" path="romtype">
                                <form:options items="${formFinnRom.romtypeList}"/>
                            </form:select>
                        </td>
                        <td><form:errors path="romtype"/></td>
                    </tr>
                    <tr>
                        <td>Romstørrelse m<sup>2</sup>(min):<em>* </em></td>
                        <td><form:input class="velgRomForm" type="number" min="0" path="storrelse" /></td>
                        <td><form:errors path="storrelse" /></td>
                    </tr>
                    <tr>
                        <td>Sitteplasser(min):<em>* </em></td>
                        <td><form:input class="velgRomForm" type="number" min="0" path="sitteplasser" /></td>
                        <td><form:errors path="skjerm" /></td>
                    </tr>
                    <tr>
                        <td>Skjerm(min):<em>* </em></td>
                        <td><form:input class="velgRomForm" type="number" min="0" path="skjerm" /></td>
                        <td><form:errors path="skjerm" /></td>
                    </tr>
                    <tr>
                        <td>Tavle(min):<em>* </em></td>
                        <td><form:input class="velgRomForm" type="number" min="0" path="tavle" /></td>
                        <td><form:errors path="tavle" /></td>
                    </tr>
                    <tr>
                        <td>Projektor(min):<em>* </em></td>
                        <td><form:input class="velgRomForm" type="number" min="0" path="projektor" /></td>
                        <td><form:errors path="projektor" /></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td>
                            <input class="defaultKnapp" formaction="finnromdata" type="submit" value="Søk">
                        </td>
                        <td></td>
                    </tr>
                    <tr>
                        <td>Tittel:<em>* </em></td>
                        <td><form:input class="velgRomForm" path="tittel" /></td>
                        <td><form:errors path="tittel" /></td>
                    </tr>
                    <tr>
                        <td>Type:<em>* </em></td>
                        <td>
                            <form:select class="velgRomForm" path="type">
                                <form:options items="${formFinnRom.typeList}"/>
                            </form:select>
                        </td>
                        <td><form:errors path="type"/></td>
                    </tr>
                    <tr>
                        <td>Fag:<em>* </em></td>
                        <td>
                            <form:select class="velgRomForm" path="fag">
                                <form:options items="${formFinnRom.fagList}"/>
                            </form:select>
                        </td>
                        <td><form:errors path="fag"/></td>
                    </tr>
                    <tr>
                        <td>Notat:<em>* </em></td>
                        <td><form:textarea id="textareaNotat" class="velgRomForm" path="notat" /></td>
                        <td><form:errors path="notat" /></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td>
                            <input class="defaultKnapp" formaction="VelgRomReserverRom" type="submit" value="Bestill">
                        </td>
                        <td></td>
                    </tr>  
                </table>
            </form:form>
        </fieldset>
    </div>
    <div>
    <section class="searchInfo2">
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
    </div>
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
            $('#datoFraDefault').prop('min', function(){
                return new Date().toJSON().split('T')[0];
            });
        });
    </script>
    <script>var date = new Date();

        var day = date.getDate();
        var month = date.getMonth() + 1;
        var year = date.getFullYear();

        if (month < 10) month = "0" + month;
        if (day < 10) day = "0" + day;

        var today = year + "-" + month + "-" + day;

        document.getElementById('datoFraDefault').value=today;
    </script>
</main>