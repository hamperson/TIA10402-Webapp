package com.chumore.orderitem.model;

import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;



public class OrderItemVO {
	private Integer orderItemId;
	private Integer orderId;
	private String memo;
	private Timestamp createdDatetime;
	private Timestamp updatedDatetime;
	
	
	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	
	public Integer getOrderItemId() {
		return orderItemId;
	}
	public void setOrderItemId(Integer orderItemId) {
		this.orderItemId = orderItemId;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public Timestamp getCreatedDatetime() {
		return createdDatetime;
	}
	public void setCreatedDatetime(Timestamp createdDatetime) {
		this.createdDatetime = createdDatetime;
	}
	public Timestamp getUpdatedDatetime() {
		return updatedDatetime;
	}
	public void setUpdatedDatetime(Timestamp updatedDatetime) {
		this.updatedDatetime = updatedDatetime;
	}
	
	public String getFormatCreatedDatetime() {
		return createdDatetime.toLocalDateTime().format(FORMATTER);
	}
	
	public String getFormatUpdatedDatetime() {
		return updatedDatetime.toLocalDateTime().format(FORMATTER);
	}
	
}
