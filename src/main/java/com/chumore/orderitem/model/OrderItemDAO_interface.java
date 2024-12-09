package com.chumore.orderitem.model;

import java.util.*;

public interface OrderItemDAO_interface {
	public void insert(OrderItemVO orderItermVO);
	public void update(OrderItemVO orderItemVO);
	public void delete(Integer orderItemId);
	public OrderItemVO findByPrimaryKey(Integer orderItemId);
	public List<OrderItemVO> getAll();
	public List<Integer> getOrderIdList();
	public List<OrderItemVO> getOrderItemListByOrderId(int orderId);
}
