<%@page import="org.hibernate.internal.build.AllowSysOut"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="com.chumore.orderitem.model.*"%>

<%
OrderItemVO orderItemVO = (OrderItemVO) request.getAttribute("orderItemVO");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>依點餐Id查詢資料 -listOneOrderItem</title>
<style>
table#table-1 {
	background-color: rgb(189, 231, 237);
	border: 2px solid black;
	text-align: center;
}

table#table-1 h4 {
	color: red;
	display: block;
	margin-bottom: 1px;
}

h4 {
	color: blue;
	display: inline;
}
</style>

<style>
table {
	width: 600px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
}

table, th, td {
	border: 1px solid #CCCCFF;
}

th, td {
	padding: 5px;
	text-align: center;
}
</style>

</head>
<body bgcolor='white'>

	<table id="table-1">
		<tr>
			<td>
				<h3>點餐資料 - listOneOrderItem.jsp</h3>
				<h4>
					<a href="selectPage.jsp">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<table>
		<tr>
			<th>點餐ID</th>
			<th>訂單ID</th>
			<th>點餐備註</th>
			<th>建立時間</th>
			<th>更新時間</th>
		</tr>

		<tr>
			<td>${orderItemVO.orderItemId}</td>
			<td>${orderItemVO.orderId}</td>
			<td>${orderItemVO.memo}</td>
			<td>${orderItemVO.formatCreatedDatetime}</td>
			<td>${orderItemVO.formatUpdatedDatetime}</td>
		</tr>

	</table>
	<a href="listAllOrderItem.jsp">查看所有清單</a>
</body>
</html>