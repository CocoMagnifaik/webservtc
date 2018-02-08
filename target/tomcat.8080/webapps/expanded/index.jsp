<%@page import="com.mongodb.DBObject"%>
<%@page import="com.mongodb.BasicDBObject"%>
<%@page import="com.mongodb.DB"%>
<%@page import="org.mongodb.ConnectionBase"%>
<%@page import="com.mongodb.DBCollection"%>
<%@page import="com.mongodb.DBCursor"%>
<%@page import="org.mongodb.Customers"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inscription</title>
        <%
            ConnectionBase mon = new ConnectionBase();
            String login = request.getParameter("Login");
            if(login != null) {
                DB db = mon.getConnection();
                DBCollection table = db.getCollection("customers");
                
                String uname = request.getParameter("username");
                String pwd = request.getParameter("password");
            
                BasicDBObject query = new BasicDBObject();
                query.put("nom", uname);
                query.put("prenom", pwd);
                BasicDBObject where = new BasicDBObject();
                where.put("$and",query);
                DBObject doc = table.findOne(query);
                if(doc != null) {
                    session.setAttribute("id", request.getParameter("username"));
                    response.sendRedirect("back.jsp");
                }
                else {
                    out.println("Pas GG!");
                }
            } 
	/*	Customers cus = new Customers();
                DBCursor cursor=cus.findCustomer();
                String d = null;
                String[] td = null;
                String[] td1 = null;
                String[] td11 = null;
                
                String[] td2 = null;
                String[] td22 = null;
                while (cursor.hasNext()) {
                    d=""+cursor.next();
                    td=d.split(",");
                    
                    td1=td[1].split("\"nom\" : \"");
                    td11=td1[1].split("\" ");
                    out.println(td11[0]);*/%><%

                 /*   td2=td[2].split("\"prenom\" : \"");
                    td22=td2[1].split("\"}");
                    out.println(td22[0]);*/%></br><%/*
                }*/
        %>
    </head>
    <body>
        <form method="post" action="index.jsp">
            <input type="text" name="username" placeholder="Nom"/><br />
            <input type="password" name="password" placeholder="Mot de passe"/><br />
            <input type="submit" name="Login" value="Login"><br />
        </form>
    </body>
</html>
