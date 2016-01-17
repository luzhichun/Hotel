package com.icss.dao;

import java.util.Vector;

import org.omg.CORBA.INTERNAL;

import com.icss.bean.DeskBean;

public class DeskDao extends BaseDao{

	public int addDesk(int id,int seats) throws Exception {
		return executeUpdate(" INSERT INTO TB_DESK VALUES(?,?) ",id, seats);
	}
	
	public int delDesk(int id) throws Exception{
		return executeUpdate(" DELETE FROM TB_DESK WHERE NUM= ?", id);
	}
	
	public Vector<Vector<Object>> queryAllDesk() throws Exception{
		return executeQueryVector(" SELECT ROWNUM,NUM,SEATING FROM TB_DESK ORDER BY ROWNUM ");
	}
	
	public Vector<Vector<Object>> queryByNum(int num) throws Exception{
		return executeQueryVector(" SELECT NUM,SEATING FROM TB_DESK WHERE NUM=? ", num);
	}
}
