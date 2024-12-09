<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>點餐編號查詢</title>

<style>
table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
    border: 3px ridge Gray;
    height: 80px;
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

</head>
<body bgcolor='white'>

<table id="table-1">
   <tr><td><h3>點餐編號查詢: Home</h3></td></tr>
</table>

<p>查詢主頁</p>

<h3>資料查詢:</h3>
	
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
  <li><a href='listAllOrderItem.jsp'>查看所有點餐</a> all OrderItem<br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="orderItem.do" >
        <b>輸入點餐編號:</b>
        <input type="text" name="orderItemId">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>

  <jsp:useBean id="orderItemSvc" scope="page" class="com.chumore.orderitem.model.OrderItemService" />
   
  <li>
     <FORM METHOD="post" ACTION="orderItem.do" >
       <b>選擇點餐編號:</b>
       <select size="1" name="orderItemId">
         <c:forEach var="orderItemVO" items="${orderItemSvc.all}" > 
          <option value="${orderItemVO.orderItemId}">${orderItemVO.orderItemId}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="orderItem.do" >
       <b>選擇訂單編號:</b>
       <select size="1" name="orderId">
         <c:forEach var="orderId" items="${orderItemSvc.orderIdList}" > 
          <option value="${orderId}">${orderId}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getManyOrderItemByOrderId">
       <input type="submit" value="送出">
     </FORM>
  </li>
</ul>


<h3>點餐管理</h3>

<ul>
  <li><a href='addPage.jsp'>Add</a> a new OrderItem.</li>
</ul>

</body>
</html>