package com.chumore.orderitem.model;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OrderItemServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) {

			List<String> errorMsgs = new LinkedList<>(); // ??
			req.setAttribute("errorMsgs", errorMsgs);

			/* 接受請求參數 */

			String str = req.getParameter("orderItemId");

			if (str == null || (str.trim().length() == 0)) {
				errorMsgs.add("請輸入點餐編號");
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/selectPage.jsp"); // !!!
				failureView.forward(req, res);
				return;
			}

			Integer orderItemId = null;
			try {
				orderItemId = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.add("點餐編號不正確");
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/selectPage.jsp"); // !!!
				failureView.forward(req, res);
				return;
			}

			/* 查資料 */

			OrderItemService orderItemSvc = new OrderItemService();
			OrderItemVO orderItemVO = orderItemSvc.getOneOrderItem(orderItemId);
			if (orderItemVO == null) {
				errorMsgs.add("查無資料");
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/selectPage.jsp"); // !!!
				failureView.forward(req, res);
				return;
			}

			req.setAttribute("orderItemVO", orderItemVO);
			String url = "/listOneOrderItem.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);

		} else if ("getManyOrderItemByOrderId".equals(action)) {

			List<String> errorMsgs = new LinkedList<>(); // ??
			req.setAttribute("errorMsgs", errorMsgs);

			/* 接受請求參數 */

			String str = req.getParameter("orderId");

			Integer orderId = null;
			try {
				orderId = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.add("訂單編號不正確");
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/selectPage.jsp"); // !!!
				failureView.forward(req, res);
				return;
			}

			/* 查資料 */

			OrderItemService orderItemSvc = new OrderItemService();
			List<OrderItemVO> orderItemVOList = orderItemSvc.getOrderItemListByOrderId(orderId);
			if (orderItemVOList == null) {
				errorMsgs.add("查無資料");
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/selectPage.jsp"); // !!!
				failureView.forward(req, res);
				return;
			}
//			req.getServletContext().setAttribute("jjj", orderItemVOList);
			req.setAttribute("orderItemVOList", orderItemVOList);
			String url = "/listManyOrderItem.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);

		}

		/* 新增點餐資料 */
		/* 接受請求參數 */

		if ("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>(); // ??
			req.setAttribute("errorMsgs", errorMsgs);


			String orderIdS = req.getParameter("orderId");
			Integer orderId = null;

			if (orderIdS == null || orderIdS.trim().length() == 0) {

				errorMsgs.add("請選擇訂單編號");

			} else {

				orderId = Integer.valueOf(orderIdS);

			}

			String memo = req.getParameter("memo").trim();

			java.sql.Timestamp createdDatetime = Timestamp.from(Instant.now());
			java.sql.Timestamp updatedDatetime = Timestamp.from(Instant.now());

			OrderItemVO orderItemVO = new OrderItemVO();
			orderItemVO.setOrderId(orderId);
			orderItemVO.setMemo(memo);
			orderItemVO.setCreatedDatetime(createdDatetime);
			orderItemVO.setUpdatedDatetime(updatedDatetime);

			if (!errorMsgs.isEmpty()) {
				req.setAttribute("orderItemVO", orderItemVO);
				RequestDispatcher failureView = req.getRequestDispatcher("/addPage.jsp");
				failureView.forward(req, res);
				return;
			}
			
			/* 新增 */
			OrderItemService orderItemSvc = new OrderItemService();
			orderItemSvc.addOrderItem(orderId, memo, createdDatetime, updatedDatetime);
			
			String url = "/listAllOrderItem.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		
		if ("getOne_For_Update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>(); // ??
			req.setAttribute("errorMsgs", errorMsgs);
		
			Integer orderItemId =Integer.valueOf(req.getParameter("orderItemId"));
			OrderItemService orderItemSvc = new OrderItemService();
			OrderItemVO orderItemVO = orderItemSvc.getOneOrderItem(orderItemId);
			
			req.setAttribute("orderItemVO", orderItemVO);
			
			String url = "/updatePage.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			
		}
		
		/* 修改資料 */
		if ("update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>(); // ??
			req.setAttribute("errorMsgs", errorMsgs);
			
			String orderItemIdS = req.getParameter("orderItemId");
			String orderIdS = req.getParameter("orderId");
			String memo = req.getParameter("memo");
			String createdDatetime = req.getParameter("createdDatetime");
			
			Integer orderId = null;

			if (orderIdS == null || orderIdS.trim().length() == 0) {
				errorMsgs.add("請選擇訂單編號");
			} else {
				orderId = Integer.valueOf(orderIdS);
			}

			Integer orderItemId = null;
			if (orderItemIdS == null || orderItemIdS.trim().length() == 0) {
				errorMsgs.add("錯誤");
			} else {
				orderItemId = Integer.valueOf(orderItemIdS);
			}
			

			java.sql.Timestamp cdt = Timestamp.valueOf(createdDatetime);
			java.sql.Timestamp updatedDatetime = Timestamp.from(Instant.now());

			OrderItemVO orderItemVO = new OrderItemVO();
			orderItemVO.setOrderItemId(orderItemId);
			orderItemVO.setOrderId(orderId);
			orderItemVO.setMemo(memo);
			orderItemVO.setCreatedDatetime(cdt);
			orderItemVO.setUpdatedDatetime(updatedDatetime);

			if (!errorMsgs.isEmpty()) {
				req.setAttribute("orderItemVO", orderItemVO);
				RequestDispatcher failureView = req.getRequestDispatcher("/updatePage.jsp");
				failureView.forward(req, res);
				return;
			}
			
			
			/*  */
			OrderItemService orderItemSvc = new OrderItemService();
			orderItemVO = orderItemSvc.updateOrderItem(orderItemId, orderId, memo, cdt, updatedDatetime);
			
			req.setAttribute("orderItemVO", orderItemVO);
			String url = "/listOneOrderItem.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		
		/* 刪除 */
		if ("delete".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>(); // ??
			req.setAttribute("errorMsgs", errorMsgs);
			
			Integer orderItemId = Integer.valueOf(req.getParameter("orderItemId"));
			
			OrderItemService orderItemSvc = new OrderItemService();
			orderItemSvc.deleteItem(orderItemId);
			
			String url = "/listAllOrderItem.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		 
	}

}
