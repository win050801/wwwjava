<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="ProductController">
		<input type="hidden" name="action" value="add-product" />
		Name:<input type="text" name="name">
  	<br/>
	  Price:<input type="text" name="price">
	  <br/>
	
	  Quantity:<input type="text" name="quantity">
	  
	  <br/>
	
	  <button type="submit">ADD Product</button>
	</form>
</body>
</html>