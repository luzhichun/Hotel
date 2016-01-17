package com.icss.dao;

import java.sql.ResultSet;
import java.util.Vector;

import com.icss.bean.MenuBean;
import com.icss.bean.OrderFormBean;

public class OrderFormDao extends BaseDao{

	public int addForm(int num,int desk_num) throws Exception{
		return executeUpdate("INSERT INTO TB_ORDER_FORM(NUM,DESK_NUM,rNum) VALUES(?,?)",num, 
				desk_num);
	}
	
	public void updForm(){       //更新money项
		
	}
	
	public int delForm(int num) throws Exception{
		return executeUpdate("DELETE FROM TB_MENU WHERE NUM= ?", num);
	}
	
	public ResultSet checkForm(String name) throws Exception{
		StringBuilder sql = new StringBuilder()
				.append(" select * FROM TB_MENU WHERE NAME=? ")
				.append(" AND STATE='销售' ")
				;
		return executeQuery(sql.toString(), name);
	}
	
	public Vector<Vector<Object>> queryAllForm() throws Exception{
		StringBuilder sql = new StringBuilder()
				.append(" SELECT rownum,desk_num,datetime  ")
				.append(" FROM TB_ORDER_FORM ")
				;
		return executeQueryVector(sql.toString());
	}	
	
	public Vector<Vector<Object>> queryByNum(int num) throws Exception{             //返回单个对象
		StringBuilder sql = new StringBuilder()
				.append(" SELECT desk_num,datetime,num,rnum  ")
				.append(" FROM TB_ORDER_FORM ")
				.append(" WHERE DESK_NUM=? ")
				;
		return executeQueryVector(sql.toString(),num);
	}
	
	public Object getMaxNum() throws Exception{
		StringBuilder sql = new StringBuilder()
				.append(" SELECT Max(num)  ")
				.append(" FROM TB_ORDER_FORM ")
				;
		return executeScalar(sql.toString());
	}	
}
