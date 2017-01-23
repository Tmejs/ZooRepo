<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="dao.model.Zwierze"%>
<%@page import="dao.RepositoryCatalogue"%>
<%@page import="dao.ZwierzetaRepository"%>

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
            List<Zwierze> zwieList=null;
            try {
            RepositoryCatalogue rp = new RepositoryCatalogue();
            ZwierzetaRepository zwr = rp.zwierzetaRepository();

            zwieList = zwr.getAll();


        } catch (Exception e) {
            e.printStackTrace();
        }

        %>
        <h1>Zwierzeta</h1>

        Rodzaj Wybiegu:<br />
        <%
            for(Zwierze tw : zwieList){
        %>
            <a href="costam<%=tw.getId()%>"><%=tw.getNazwa()%></a>
            Poziom najedzenia:<%=tw.getPoziomNajedzenia()%><br>
            
            
        <%
            }
        %>
</body>
</html>