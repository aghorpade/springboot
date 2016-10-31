<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
 
<html>
<head>
    <title>Add Employee Form</title>
</head>
 
<body>
    <h2><spring:message code="lbl.page" text="Download PDF Files" /></h2>
    <br/>
    <form:form method="post" modelAttribute="tracker">
        <table>
            <tr>
                <td><spring:message code="lbl.trackingNumber" text="Track Number" /></td>
                <td><form:input path="trackingNumbers" /></td>
            </tr>
            
            <tr>
                <td colspan="2"><input type="submit" value="Download PDF"/></td>
            </tr>
        </table>
    </form:form>
</body>
</html>