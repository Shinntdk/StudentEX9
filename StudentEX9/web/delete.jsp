<%-- 
    Document   : delete
    Created on : 15-Nov-2021, 21:14:25
    Author     : natthidak
--%>

<%@page import="java.util.List"%>
<%@page import="database.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Delete Student By ID</title>
    </head>
    <body>
        <h1>Delete Student By ID</h1>
        List Student
        <br/>
        <%
            List<Student> StudentList = StudentTable.findAllStudent();
            if (StudentList.size() > 0) {
                for (Student stud : StudentList) {
                    out.print(stud.getId() + " ");
                    out.print(stud.getName() + " ");
                    out.print(stud.getGpa() + " ");
                    out.print("<br/>");
                }
            } else {
                out.println("No Data");
            }
        %>
        <br/>
        Enter id to delete <br/>
        <form name="main" action="Delete" method="POST">
            ID: <input type="text" name="id" value="" /> <br/>
            <input type="submit" value="send" name="btn" />
        </form>
    </body>
</html>
