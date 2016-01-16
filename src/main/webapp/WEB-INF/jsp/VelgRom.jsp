<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>


<main>
    <div id="velgRomSøkeBokser">
        <form>
            <fieldset>
                <legend>Bestill rom</legend>
                <p>Velg dato for rombestilling:<em>*</em></p>
                <input id="datoVelgRom" type="date" name="date" id="datoen" placeholder="ÅÅÅÅ-MM-DD required">

                <p>Velg klokkeslett<em>(kan velge flere)</em></p>
                <select id="klokkeslett" multiple required name="klokkeslett">
                    <option value="1">08.00 - 09.00</option>
                    <option value="2">09.00 - 10.00</option>
                    <option value="3">10.00 - 11.00</option>
                    <option value="4">11.00 - 12.00</option>
                    <option value="5">12.00 - 13.00</option>
                    <option value="6">13.00 - 14.00</option>
                    <option value="7">14.00 - 15.00</option>
                    <option value="8">15.00 - 16.00</option>
                    <option value="9">16.00 - 17.00</option>
                    <option value="10">17.00 - 18.00</option>
                </select>
                <br>
                <p>Her skal ledige rom listes ut</p>
                <br>
                <input type="submit" value="Velg rom">
            </fieldset>
        </form>
    </div>
    
    <div id="velgRomSøkeSVG">
        <object id="plantegningEtasje1" class="#plantegning" data="<c:url value="/resources/SVG/plantegningE1.svg"/>"> Din nettleser støtter ikke SVG </object>
        <object id="plantegningEtasje2" class="#plantegning" data="<c:url value="/resources/SVG/plantegningE2.svg"/>"> Din nettleser støtter ikke SVG </object>
        <object id="plantegningEtasje3" class="#plantegning" data="<c:url value="/resources/SVG/plantegningE3.svg"/>"> Din nettleser støtter ikke SVG </object>
        <object id="plantegningEtasje4" class="#plantegning" data="<c:url value="/resources/SVG/plantegningE4.svg"/>"> Din nettleser støtter ikke SVG </object>
    </div>
</main>
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
