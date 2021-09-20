<%-- 
    Document   : Registrar
    Created on : Sep 19, 2021, 10:38:25 PM
    Author     : david
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registrar</title>
    </head>
    <body>
        <form action="./RegistrarServlet" method="POST">
            <label for="fname">Nombres completo:</label><br>
            <input type="text" id="fname" name="fname"><br><br>
            <label for="lname">Numero de documento:</label><br>
            <input type="text" id="lname" name="doc" ><br><br>
            <label for="lname">Correo electronico:</label><br>
            <input type="text" id="lname" name="mail" ><br><br>	
            <label for="lname">Telefono:</label><br>
            <input type="text" id="lname" name="telefono" ><br><br>
            <label for="lname">Direccion:</label><br>
            <input type="text" id="lname" name="direccion" ><br><br>
            <label for="lname">Usuario acceso:</label><br>
            <input type="text" id="lname" name="usuario" ><br><br>	
            <label for="lname">Password:</label><br>
            <input type="text" id="lname" name="password" ><br><br>	
            <label for="lname">Confirmacion Password:</label><br>
            <input type="text" id="lname" name="passwordC" ><br><br>	




            <input type="submit" value="Registrarse">
            <a href='index.html'>Volver atrás</a>   
            
        </form> 		
    </body>
</html>
