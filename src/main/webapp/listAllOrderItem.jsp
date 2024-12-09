<%@page import="org.hibernate.internal.build.AllowSysOut"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="com.chumore.orderitem.model.*"%>
<%@ page import="java.util.List"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>所有點餐資料</title>
<style>
table#table-1 {
	background-color: #CCCCFF;
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

<jsp:useBean id="orderItemSvc" scope="page"
	class="com.chumore.orderitem.model.OrderItemService" />

<body bgcolor='white'>

	<table id="table-1">
		<tr>
			<td>
				<h3>點餐資料</h3>
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
			<th>修改</th>
			<th>刪除</th>
		</tr>
		<c:forEach var="orderItemVO" items="${orderItemSvc.all}">
			<tr>
				<td>${orderItemVO.orderItemId}</td>
				<td>${orderItemVO.orderId}</td>
				<td>${orderItemVO.memo}</td>
				<td>${orderItemVO.createdDatetime}</td>
				<td>${orderItemVO.updatedDatetime}</td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/orderItem.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="修改"> <input type="hidden"
							name="orderItemId" value="${orderItemVO.orderItemId}">
						<input type="hidden" name="action" value="getOne_For_Update">
					</FORM>
				</td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/orderItem.do"
						style="margin-bottom: 0px;">
						<input type="button" class="btndelete" value="刪除"> 
						<input type="hidden" name="orderItemId" value="${orderItemVO.orderItemId}"> 
							<input type="hidden" name="action" value="delete">
					</FORM>
				</td>
			</tr>
		</c:forEach>
	</table>
	
	<script>
	let submitList = document.querySelectorAll("input.btndelete");
	for(var i = 0; i < submitList.length; i++){
		submitList[i].addEventListener("click", function(){
			let result = confirm("確認刪除?");
			if(result){
				this.closest("form").submit();
			}
		});
		
	}
	

	</script>
</body>
</html>