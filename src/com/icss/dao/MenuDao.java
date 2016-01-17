package com.icss.dao;

import java.sql.ResultSet;
import java.util.Vector;

import com.icss.bean.MenuBean;

import sun.org.mozilla.javascript.internal.ast.ThrowStatement;

public class MenuDao extends BaseDao{
	/**
	 * 
	 * @param name
	 * @return
	 * @throws Exception
	 */
	public int addMenu(MenuBean mBean) throws Exception{
		return executeUpdate("INSERT INTO TB_MENU VALUES(?,?,?,?,?,?,default)", 
				mBean.getNum(),mBean.getSort_id(),mBean.getName(),mBean.getCode(),
				mBean.getUnit(),mBean.getUnit_price());
	}
	
	public int delMenu(int id) throws Exception{
		return executeUpdate("DELETE FROM TB_MENU WHERE ID= ?", id);
	}
	
	public Vector<Vector<Object>> checkMenu(String name) throws Exception{
		StringBuilder sql = new StringBuilder()
				.append(" select * FROM TB_MENU WHERE NAME=? ")
				.append(" AND STATE='销售' ")
				;
		return executeQueryVector(sql.toString(), name);
	}
	
	public Vector<Vector<Object>> sMenuOfSell() throws Exception{
		StringBuilder sql = new StringBuilder()
				.append(" SELECT rownum,num,m.name,code,s.name,unit,unit_price  ")
				.append(" FROM TB_MENU m,TB_SORT s ")
				.append(" WHERE M.SORT_ID = S.ID ")
				.append(" AND STATE = '销售' ")
				;
		return executeQueryVector(sql.toString());
	}	
	
	public Object getMaxNum() throws Exception{
		StringBuilder sql = new StringBuilder()
				.append(" SELECT Max(num)  ")
				.append(" FROM TB_MENU ")
				;
		return executeScalar(sql.toString());
	}	
	
	public Vector<Vector<Object>> queryAllMenu() throws Exception{
		StringBuilder sql = new StringBuilder()
				.append(" SELECT rownum,num,name,code,sort_id,unit,unit_price ")
				.append(" FROM TB_MENU  ")
				;
		return executeQueryVector(sql.toString());
	}
	
	public Vector<Vector<Object>> queryByName(String name) throws Exception{
		StringBuilder sql = new StringBuilder()
				.append(" SELECT num,unit_price  ")
				.append(" FROM TB_MENU  ")
				.append(" where name=? ")
				;
		return executeQueryVector(sql.toString(),name);
	}
	
	//根据助记码模糊查询
	public Vector<Vector<Object>> queryByCode(String code) throws Exception{
		StringBuilder sql = new StringBuilder()
				.append(" SELECT name,code,unit  ")
				.append(" FROM TB_MENU  ")
				.append(" where lower(code) ")
				.append(" like lower('"+ code +"%') ")
				;
		return executeQueryVector(sql.toString());
	}
	//根据编码模糊查询
	public Vector<Vector<Object>> queryByNum(int num) throws Exception{
		StringBuilder sql = new StringBuilder()
				.append(" SELECT name,code,unit  ")
				.append(" FROM TB_MENU  ")
				.append(" where num ")
				.append(" like '?%' ")
				;
		return executeQueryVector(sql.toString(),num);
	}
}
