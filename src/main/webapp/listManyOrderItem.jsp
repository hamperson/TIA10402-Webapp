<%@page import="org.hibernate.internal.build.AllowSysOut"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="com.chumore.orderitem.model.*"%>
<%@ page import="java.util.List"%>

<%
List<OrderItemVO> orderItemList = (List<OrderItemVO>) request.getAttribute("orderItemVOList"); //EmpServlet.java(Concroller), 存入req的empVO物件
pageContext.setAttribute("orderItemList",orderItemList);
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<title>訂單編號查詢點餐資料 -listManyOrderItem</title>
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
				<h3>點餐資料 - listManyOrderItem.jsp</h3>
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
		<c:forEach var="orderItemVO" items="${orderItemList}">
			<tr>
				<td>${orderItemVO.orderItemId}</td>
				<td>${orderItemVO.orderId}</td>
				<td>${orderItemVO.memo}</td>
				<td>${orderItemVO.formatCreatedDatetime}</td>
				<td>${orderItemVO.formatUpdatedDatetime}</td>
			</tr>
		</c:forEach>
	</table>

</body>
</html>