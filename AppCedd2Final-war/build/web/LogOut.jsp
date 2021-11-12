<%-- 
    Document   : LogOut
    Created on : Nov 11, 2021, 10:27:14 PM
    Author     : edwin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>CEDD OZ</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="styles/MainHome.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        
        <%!
            
            HttpSession misession;
        %>

        <%
            misession = request.getSession(true);
            misession.invalidate();    

        %>
        <img src="styles/Logo.png" alt=""/>
    
        <nav>
            <ul>
                <li><a href='Registrar.jsp'>Registrarse</a></li>
                <li><a href='LogIn.jsp'>Log in</a></li>
               
                
            </ul>
        </nav>
        <div>
            
        </div>
    </body>
</html>
