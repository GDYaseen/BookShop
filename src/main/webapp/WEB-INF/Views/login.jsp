<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="./CSS/login.css">
    <title>Se connecter</title>
</head>
<body>
    <div>
        <img src="./IMG/logo.jpg" alt="">
        <span><% if(session.getAttribute("fail")!=null){
						if(session.getAttribute("fail").equals("true")){
							out.print("Username ou mot de passe incorrect");
						}else{
							out.print("Connectez-vous");
						}
        }%></span>
        <form action="Connect" method="post">
            <input required type="text" name="username" placeholder="Nom d'utilisateur">
            <input required type="password" name="password" placeholder="Mot de passe">
            <button type="submit">Se connecter</button>
        </form>
    </div>
</body>
</html>