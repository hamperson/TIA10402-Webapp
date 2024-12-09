package com.chumore.orderitem.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class OrderItemJNDIDAO implements OrderItemDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/RubyConnect");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO order_item (order_id, memo, created_datetime, updated_datetime)VALUES (?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT order_item_id, order_id, memo, created_datetime, updated_datetime FROM order_item order by order_item_id";
	private static final String GET_ALL_ORDERID_STMT = "SELECT distinct oi.order_id FROM order_item oi order by order_id";
	private static final String GET_ONE_STMT = "SELECT order_item_id, order_id, memo, created_datetime, updated_datetime FROM order_item where order_item_id = ?";
	private static final String DELETE = "DELETE FROM order_item where order_item_id =?";
	private static final String UPDATE = "UPDATE order_item set order_id=?, memo=?, created_datetime=?, updated_datetime=? where order_item_id =?";

	@Override
	public void insert(OrderItemVO orderItemVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, orderItemVO.getOrderId());
			pstmt.setString(2, orderItemVO.getMemo());
			pstmt.setTimestamp(3, orderItemVO.getCreatedDatetime());
			pstmt.setTimestamp(4, orderItemVO.getUpdatedDatetime());

			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured." + se.getLocalizedMessage());
		}finally {
			close(pstmt, con);
		}

	}

	@Override
	public void update(OrderItemVO orderItemVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, orderItemVO.getOrderId());
			pstmt.setString(2, orderItemVO.getMemo());
			pstmt.setTimestamp(3, orderItemVO.getCreatedDatetime());
			pstmt.setTimestamp(4, orderItemVO.getUpdatedDatetime());
			pstmt.setInt(5, orderItemVO.getOrderItemId());

			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured." + se.getLocalizedMessage());
		}finally {
			close(pstmt, con);
		}
	}

	@Override
	public void delete(Integer orderItemId) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, orderItemId);

			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured." + se.getLocalizedMessage());
		}finally {
			close(pstmt, con);
		}
	}

	@Override
	public OrderItemVO findByPrimaryKey(Integer orderItemId) {
		OrderItemVO orderItemVO = null; //???
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, orderItemId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				orderItemVO = new OrderItemVO();
				orderItemVO.setOrderItemId(rs.getInt("order_item_id"));
				orderItemVO.setOrderId(rs.getInt("order_Id"));
				orderItemVO.setMemo(rs.getString("memo"));
				orderItemVO.setCreatedDatetime(rs.getTimestamp("created_datetime"));
				orderItemVO.setUpdatedDatetime(rs.getTimestamp("updated_datetime"));
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured." + se.getLocalizedMessage());

		}finally {
			close(rs, pstmt, con);
		}

		return orderItemVO;

	}

	@Override
	public List<OrderItemVO> getAll() {
		List<OrderItemVO> list = new ArrayList<>(); //???
		OrderItemVO orderItemVO = null;  //???
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				orderItemVO = new OrderItemVO();
				orderItemVO.setOrderItemId(rs.getInt("order_item_id"));
				orderItemVO.setOrderId(rs.getInt("order_id"));
				orderItemVO.setMemo(rs.getString("memo"));
				orderItemVO.setCreatedDatetime(rs.getTimestamp("created_datetime"));
				orderItemVO.setUpdatedDatetime(rs.getTimestamp("updated_datetime"));
				list.add(orderItemVO);
			}
			
			
		}catch (SQLException se) {
			throw new RuntimeException("A database error occured." + se.getLocalizedMessage());

		}finally {
			close(rs, pstmt, con);
		}
		
		
		return list;
	}
	
	@Override
	public List<Integer> getOrderIdList() {
		List<Integer> list = new ArrayList<>(); // ???
		

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_ORDERID_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				int orderId = rs.getInt("order_id");
				list.add(orderId);
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured." + se.getLocalizedMessage());

		} finally {
			close(rs, pstmt, con);
		}

		return list;
	}
	
	
	public static void close(ResultSet rs, PreparedStatement pstmt, Connection con) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException se) {
				se.printStackTrace(System.err);
			}
		}
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException se) {
				se.printStackTrace(System.err);
			}
		}
		if (con != null) {
			try {
				con.close();
			} catch (Exception e) {
				e.printStackTrace(System.err);
			}
		}
	}
	public static void close(PreparedStatement pstmt, Connection con) {
		close(null, pstmt, con);
	}
	
	public static void close(Connection con) {
		close(null, null, con);
	}

	@Override
	public List<OrderItemVO> getOrderItemListByOrderId(int orderId) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
