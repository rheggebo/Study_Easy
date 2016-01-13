<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title><tiles:insertAttribute name="title" ignore="true" /></title>
    </head>
    <body bgcolor="green">
        <table border="1" cellpadding="2" cellspacing="1" width="800" align="center">
            <tr>
                <td height="50" colspan="1">
                    <tiles:insertAttribute name="header" />
                </td>
            </tr>
            <tr>
                <td height="70" colspan="1">
                     <tiles:insertAttribute name="menu" />
                </td>
            </tr>
                <td height="70" colspan="1">
                    <tiles:insertAttribute name="body" />
                </td>
            </tr>
            <tr>
                <td height="50" colspan="1">
                    <tiles:insertAttribute name="footer" />
                </td>
            </tr>
        </table>
    </body>
</html>
