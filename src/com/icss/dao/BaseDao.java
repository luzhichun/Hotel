package com.icss.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class BaseDao {
	//将Connection与本地线程绑定
	private static ThreadLocal<Connection> threadLocal = new ThreadLocal<Connection>();
	private static final String URL = "jdbc:oracle:thin:@127.0.0.1:1521:orcl";
	private static final String USER = "aa";
	private static final String PASSWORD = "aa";
	
	//添加数据库连接相关对象的属性
	private Connection conn;
	private PreparedStatement pstm;
	private ResultSet rs;
	
	// 加载驱动
	static{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private Connection getConnection() throws SQLException{
		// 先从本地线程中获得数据库连接对象
		conn = threadLocal.get();
		// 判断对象是否为空
		if(conn == null){
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			// 将连接对象添加到本地线程中
			threadLocal.set(conn);
		}
		return conn;
	}
	/**
	 * 关闭连接的方法
	 */
	private void close() throws Exception{
		// 先从本地线程中获得数据库连接对象
		conn = threadLocal.get();
		if(conn != null && !conn.isClosed()){
			conn.close();	//连接虽然关闭了，但连接对象依然存在
			threadLocal.remove(); //从本地线程中清除连接对象
			conn = null;	//清空连接对象
		}
	}
	
	//5 关闭
	/**
	 * 关闭数据库连接相关对象
	 * 提供给子类使用
	 * @throws Exception 
	 */
	protected void closeAll() throws Exception{
		if(rs != null){
			rs.close();	//关闭结果集
			rs = null;
		}
		if(pstm != null){
			pstm.close();
			pstm = null;
		}
		close();  //关闭连接
	}
	
	/**
	 * 基本的增删改方法
	 * @param sql  
	 * @param param
	 * @return 该语句影响的行数
	 * @throws Exception 
	 */
	protected int executeUpdate(String sql, Object... param) throws Exception {
		// 执行：try-finally
		try {
			// 建立连接
			conn = getConnection();
			// 预编译SQL语句对象
			pstm = conn.prepareStatement(sql.toString());
			//通过可变长参数及循环完成？赋值
			for(int i=0;i<param.length;i++)
			{
				pstm.setObject(i+1, param[i]);
			}
			
			// 执行并处理结果
			return pstm.executeUpdate();
		} finally {
			closeAll();
		}
	}
	/**
	 * 执行查询语句
	 * @param sql
	 * @param param
	 * @return 当前查询得到的结果集
	 * @throws Exception
	 */
	protected ResultSet executeQuery(String sql,Object...param)throws Exception{
		
		conn = getConnection();
		pstm = conn.prepareStatement(sql.toString());
		for(int i=0;i<param.length;i++){
			pstm.setObject(i+1, param[i]);
		}
		rs = pstm.executeQuery();
		//把关闭的操作放给子类做	
		return rs;
	}
	/**
	 * 查询并返回结果集，并将结果集封装为：单行数据封闭到Vector,再将若干行数据封装Vector
	 * @param sql
	 * @param param
	 * @return
	 * @throws Exception
	 */
	protected Vector<Vector<Object>> executeQueryVector(String sql,Object...param)throws Exception{
		Vector<Vector<Object>> allData = new Vector<Vector<Object>>();
		conn = getConnection();
		pstm = conn.prepareStatement(sql.toString());
		for(int i=0;i<param.length;i++){
			pstm.setObject(i+1, param[i]);
		}
		rs = pstm.executeQuery();
		ResultSetMetaData rsmd = rs.getMetaData();
		int colCount = rsmd.getColumnCount();
		while(rs.next()){
			//单行数据封闭到Vector
			Vector<Object> oneRow = new Vector<Object>();
			for(int i=0;i<colCount;i++){
				oneRow.add(rs.getObject(i+1));
			}
			//若干行数据封装Vector
			allData.add(oneRow);
		}
		return allData;
	}
	/**
	 * 查询单列单值数据：SUM COUNT MAX MIN AVG 
	 * @param sql
	 * @param param
	 * @return 单值对象
	 * @throws SQLException
	 */
	protected Object executeScalar(String sql, Object... param) throws Exception
	{
		Object obj = null;
		
		try{
			conn = getConnection();
			pstm = conn.prepareStatement(sql);
			if(param != null)
			{
				for(int i=0;i<param.length;i++)
				{
					pstm.setObject(i+1, param[i]);
				}
			}
			rs = pstm.executeQuery();
			if(rs.next())
			{
				obj = rs.getObject(1);
			}
		}finally{
			closeAll();
		}
		return obj;
	}
}
