<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
    <head>
    </head>
    <body>
        <h3>Welcome, Enter The Employee Details</h3>
        <form:form method="POST" action="/MySpringMvc/userforming" modelAttribute="usered">
             <table>
                <tr>
                    <td><form:label path="username">Name</form:label></td>
                    <td><form:input path="username"/></td>
                </tr>
                <tr>
                    <td><form:label path="Id">Id</form:label></td>
                    <td><form:input path="Id"/></td>
                </tr>
                <tr>
                    <td><form:label path="state">Contact Number</form:label></td>
                    <td><form:input path="state"/></td>
                </tr>
                <tr>
                    <td><input type="submit" value="Submit"/></td>
                </tr>
            </table>
        </form:form>
    </body>
</html>