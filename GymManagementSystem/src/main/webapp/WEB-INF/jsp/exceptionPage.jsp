<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	</head>
		<style>
			div{
				height:100vh;
				display:flex;
				justify-content:center;
				align-items:center;
				border:2px solid black;
				box-shadow:5px 5px 10px black;
				flex-direction:column;
			}
			div:hover{
				box-shadow:5px 5px 10px goldenrod;
			}
			
			button{
			  background-color:green;
			  margin-bottom:10px;
			}
			button a{
			  text-decoration:none;
			}
			button:hover{
			  background-color:azure;
			  font-size:1em;
			}
			h1{
			  color:red;
			}
			
		</style>
	<body>
		<div>
		    <h2>${errorMessage}</h2>
		    <button><a href="/index">Back to Home</a></button>
	    </div>
	</body>
</html>