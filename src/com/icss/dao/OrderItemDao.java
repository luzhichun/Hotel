package com.icss.dao;

import java.sql.ResultSet;
import java.util.Vector;

public class OrderItemDao extends BaseDao{

	public int addItem(int oformnum,int menuNum,int amount,double tot) throws Exception{
		StringBuilder sql = new StringBuilder()
				.append(" INSERT INTO TB_ORDER_ITEM(ORDER_FORM_NUM,Menu_Num,Amount,total) ")
				.append(" VALUES(?,?,?,?) ")
				;
		return executeUpdate(sql.toString(),oformnum, menuNum,amount,tot);
	}
	
	public int delItem(int num) throws Exception{
		return executeUpdate("DELETE FROM TB_MENU WHERE NUM= ?", num);
	}
	
	public ResultSet checkItem(String name) throws Exception{
		StringBuilder sql = new StringBuilder()
				.append(" select * FROM TB_MENU WHERE NAME=? ")
				.append(" AND STATE='œ˙ €' ")
				;
		return executeQuery(sql.toString(), name);
	}
	
	public Vector<Vector<Object>> queryAllItem() throws Exception{
		StringBuilder sql = new StringBuilder()
				.append(" SELECT rownum,desk_num,datetime  ")
				.append(" FROM TB_ORDER_FORM ")
				;
		return executeQueryVector(sql.toString());
	}	
	
	public Object queryByDeskNum(int deskNum) throws Exception{
		StringBuilder sql = new StringBuilder()
				.append(" SELECT num  ")
				.append(" FROM TB_ORDER_FORM ")
				.append(" WHERE DESK_NUM=? ")
				;
		return  executeScalar(sql.toString(),deskNum);
	}
}
