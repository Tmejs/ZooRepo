<%@page import="java.util.List"%>
<%@page import="domain.model.Zwierze"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lista Zwierzat</title>
    </head>
    <body>
        <%
            List<Zwierzaki> zwierzakiList = (List<Zwierzaki>) session.getAttribute("ZwierzakList");
            Zwierze zwierze = (Zwierze)session.getAttribute("SelectedZwierze");
        %>
        <h1>Zwierzeta:</h1>
        <ol>
            <%for(Zwierzak zw: zwierzakiList){ %>
            <li>
                <%=zw.getName() %></li>
                <%} %>
            
        </ol>
        
    </body>
</html>