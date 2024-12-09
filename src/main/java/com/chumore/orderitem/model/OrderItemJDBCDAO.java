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

public class OrderItemJDBCDAO implements OrderItemDAO_interface {

	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/chumore?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO order_item (order_id, memo, created_datetime, updated_datetime)VALUES (?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT order_item_id, order_id, memo, created_datetime, updated_datetime FROM order_item order by order_item_id";
	private static final String GET_ALL_ORDERID_STMT = "SELECT distinct oi.order_id FROM order_item oi order by order_id";
	private static final String GET_ALL_ORDERITIEMLIST_STMT = "SELECT order_item_id, order_id, memo, created_datetime, updated_datetime FROM order_item where order_id = ?;";
	private static final String GET_ONE_STMT = "SELECT order_item_id, order_id, memo, created_datetime, updated_datetime FROM order_item where order_item_id = ?";
	private static final String DELETE = "DELETE FROM order_item where order_item_id =?";
	private static final String UPDATE = "UPDATE order_item set order_id=?, memo=?, updated_datetime=? where order_item_id =?";

	@Override
	public void insert(OrderItemVO orderItemVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, orderItemVO.getOrderId());
			pstmt.setString(2, orderItemVO.getMemo());
			pstmt.setTimestamp(3, orderItemVO.getCreatedDatetime());
			pstmt.setTimestamp(4, orderItemVO.getUpdatedDatetime());

			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured." + se.getLocalizedMessage());
		} finally {
			close(pstmt, con);
		}
	}

	@Override
	public void update(OrderItemVO orderItemVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, orderItemVO.getOrderId());
			pstmt.setString(2, orderItemVO.getMemo());
			pstmt.setTimestamp(3, orderItemVO.getUpdatedDatetime());
			pstmt.setInt(4, orderItemVO.getOrderItemId());

			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured." + se.getLocalizedMessage());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} finally {
			close(pstmt, con);
		}
	}

	@Override
	public void delete(Integer orderItemId) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, orderItemId);

			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured." + se.getLocalizedMessage());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} finally {
			close(pstmt, con);
		}
	}

	@Override
	public OrderItemVO findByPrimaryKey(Integer orderItemId) {
		OrderItemVO orderItemVO = null; // ???
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			try {
				Class.forName(driver);

				con = DriverManager.getConnection(url, userid, passwd);
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
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		} finally {
			close(rs, pstmt, con);
		}

		return orderItemVO;

	}

	@Override
	public List<OrderItemVO> getAll() {
		List<OrderItemVO> list = new ArrayList<>(); // ???
		OrderItemVO orderItemVO = null; // ???

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured." + se.getLocalizedMessage());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} finally {
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_ORDERID_STMT);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				int orderId = rs.getInt("order_id");
				list.add(orderId);
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured." + se.getLocalizedMessage());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} finally {
			close(rs, pstmt, con);
		}

		return list;
	}

	@Override
	public List<OrderItemVO> getOrderItemListByOrderId(int orderId) {
		List<OrderItemVO> list = new ArrayList<>(); // ???
		OrderItemVO orderItemVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_ORDERITIEMLIST_STMT);
			
			pstmt.setInt(1, orderId);
			
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

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured." + se.getLocalizedMessage());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

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

	public static void main(String[] args) {
		OrderItemJDBCDAO dao = new OrderItemJDBCDAO();
		// 新增
//		OrderItemVO orderItemVO1 = new OrderItemVO();
//		orderItemVO1.setOrderId(5);
//		orderItemVO1.setMemo("加醬");
//		orderItemVO1.setCreatedDatetime(java.sql.Timestamp.valueOf("2024-12-06 17:40:00"));
//		orderItemVO1.setUpdatedDatetime(java.sql.Timestamp.valueOf("2024-12-06 17:50:00"));
//		
//		dao.insert(orderItemVO1);

		// 修改
//		OrderItemVO orderItemVO2 = new OrderItemVO();
//		orderItemVO2.setOrderItemId(31);
//		orderItemVO2.setOrderId(2);
//		orderItemVO2.setMemo("焦一點");
//		orderItemVO2.setCreatedDatetime(java.sql.Timestamp.valueOf("2024-12-07 17:40:00"));
//		orderItemVO2.setUpdatedDatetime(java.sql.Timestamp.valueOf("2024-12-07 17:50:00"));
//		
//		dao.update(orderItemVO2);

		// 刪除
		dao.delete(31);

		// 查詢單筆
		OrderItemVO orderItemVO3 = dao.findByPrimaryKey(30);
		System.out.print(orderItemVO3.getOrderItemId() + ",");
		System.out.print(orderItemVO3.getOrderId() + ",");
		System.out.print(orderItemVO3.getMemo() + ",");
		System.out.print(orderItemVO3.getCreatedDatetime() + ",");
		System.out.println(orderItemVO3.getUpdatedDatetime() + ",");
		System.out.println("---------------------");

		// 查詢全部
		List<OrderItemVO> list = dao.getAll();
		for (OrderItemVO aOdi : list) {
			System.out.print(aOdi.getOrderItemId() + ",");
			System.out.print(aOdi.getOrderId() + ",");
			System.out.print(aOdi.getMemo() + ",");
			System.out.print(aOdi.getCreatedDatetime() + ",");
			System.out.println(aOdi.getUpdatedDatetime() + ",");
		}
	}

}
