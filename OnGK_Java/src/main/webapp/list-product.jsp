<%@page import="com.entity.Product"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%List<Product> list = (List<Product>)session.getAttribute("products"); %>
<a href="add-product.jsp">ADD Product</a>

<table border="1" >
	<tr>
		<th>ID</th>
		<th>Name</th>
		<th>Price</th>
		<th>Quantity</th>
	</tr>
	<%for (Product p : list) { %>
		<tr>
			<td><%=p.getId() %>1</td>
			<td><%=p.getName() %></td>
			<td><%=p.getPrice() %></td>
			<td><%=p.getQuantity() %></td>
		</tr>
	<%} %>
</table>
</body>
</html>