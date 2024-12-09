package com.chumore.orderitem.model;

import java.util.List;

public class OrderItemService {
	private OrderItemDAO_interface dao;
	
	public OrderItemService() {
		dao = new OrderItemJDBCDAO();
	}
	
	
	
	public OrderItemVO addOrderItem(Integer orderId, String memo, java.sql.Timestamp createdDatetime, java.sql.Timestamp updatedDatetime) {
		OrderItemVO orderItemVO = new OrderItemVO();
		
		orderItemVO.setOrderId(orderId);
		orderItemVO.setMemo(memo);
		orderItemVO.setCreatedDatetime(createdDatetime);
		orderItemVO.setUpdatedDatetime(updatedDatetime);
		dao.insert(orderItemVO);
		
		return orderItemVO;
	}
	
	public OrderItemVO updateOrderItem(Integer orderItemId, Integer orderId, String memo, java.sql.Timestamp createdDatetime, java.sql.Timestamp updatedDatetime) {
		
		OrderItemVO orderItemVO = new OrderItemVO();
		
		orderItemVO.setOrderItemId(orderItemId);
		orderItemVO.setOrderId(orderId);
		orderItemVO.setMemo(memo);
		orderItemVO.setCreatedDatetime(createdDatetime);
		orderItemVO.setUpdatedDatetime(updatedDatetime);
		dao.update(orderItemVO);
		
		return orderItemVO;
	}
	
	public void deleteItem(Integer orderItemId) {  //???
		dao.delete(orderItemId);
	}
	
	public OrderItemVO getOneOrderItem(Integer orderItemId) {
		return dao.findByPrimaryKey(orderItemId);
	}
	
	public List<OrderItemVO> getAll() {
		return dao.getAll();
	}
	
	public List<OrderItemVO> getOrderItemListByOrderId(int orderId) {
		return dao.getOrderItemListByOrderId(orderId);
	}
	
	public List<Integer> getOrderIdList(){
		return dao.getOrderIdList();
	}

	
}
