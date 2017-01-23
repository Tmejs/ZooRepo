<%@page import="java.util.List"%>
<%@page import="dao.model.Zwierze"%>
<%@page import="dao.model.Wybieg"%>
<%@page import="dao.model.TypWybiegu"%>
<%@page import="java.util.ArrayList"%>
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
            ArrayList<String> rodzaje=new ArrayList<String>();
            for(TypWybiegu.RodzajWybiegu rw: TypWybiegu.RodzajWybiegu.values()){
                rodzaje.add(rw.toString());
            }
        %>
        <h1>TypyWybiegu:</h1>
        <ol>
            <form action="DodajWybiegServlet" method="get">

                Rodzaj Wybiegu:<br />
                <%
                    for(String tw : rodzaje){
                %>
                    <input type="radio" name="typ_wybiegu" value="<%=tw%>"><%=tw%></input> <br>
                <%
                    }
                %>
                <input type="submit" value="Dodaj Wybieg"/>
            </form>

        </ol>

    </body>
</html>