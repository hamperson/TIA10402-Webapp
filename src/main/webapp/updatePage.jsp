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
<title>點餐資料修改</title>

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
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
}

table, th, td {
	border: 0px solid #CCCCFF;
}

th, td {
	padding: 1px;
}
</style>

</head>

<jsp:useBean id="orderItemSvc" scope="page"
	class="com.chumore.orderitem.model.OrderItemService" />

<body bgcolor='white'>

	<table id="table-1">
		<tr>
			<td>
				<h3>資料修改</h3>
				<h4>
					<a href="selectPage.jsp">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<h3>資料修改:</h3>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>


	
	<FORM METHOD="post" ACTION="orderItem.do" name="form1">
		<table>
			<tr>
				<td>點餐ID:<font color=red><b>*</b></font></td>
				<td>${orderItemVO.orderItemId}</td>
			</tr>
			<tr>
				<td>訂單ID:</td>
				<td><select size=1 name="orderId">
				<option selected value="${orderItemVO.orderId}">${orderItemVO.orderId}
				<c:forEach var="orderId" items="${orderItemSvc.orderIdList}">
				<option value="${orderId}">${orderId}
				</c:forEach>
				</select>
				</td>
			</tr>
			<tr>
				<td>點餐備註:</td>
				<td><input type="TEXT" name="memo" value="${orderItemVO.memo}"
					size="45" /></td>
			</tr>
			<tr>
				<td>建立時間:</td>
				<td>${orderItemVO.formatCreatedDatetime}</td>
			</tr>
			<tr>
				<td>更新時間:</td>
				<td>${orderItemVO.formatUpdatedDatetime}</td>
			</tr>


		</table>
		<br> <input type="hidden" name="action" value="update"> 
		<input type="hidden" name="orderItemId" value="${orderItemVO.orderItemId}">
		<input type="hidden" name="createdDatetime" value="${orderItemVO.createdDatetime}">
		<input type="submit" value="送出修改">
	</FORM>
</body>
</html>