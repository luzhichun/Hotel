package com.icss.dao;

import java.util.Vector;

public class SortDao extends BaseDao{
	/**
	 * 添加菜系
	 * @param name 菜系名
	 * @return 影响行数
	 * @throws Exception
	 */
	public int addSort(String name) throws Exception{
		return executeUpdate("INSERT INTO TB_SORT(NAME) VALUES(?)", name);
	}

	/**
	 * 删除菜系
	 * @param id 菜系编号
	 * @return 影响行数
	 * @throws Exception
	 */
	public int delSort(int id) throws Exception{
		return executeUpdate("DELETE FROM TB_SORT WHERE ID=?", id);
	}
	/**
	 * 查询全部菜系数据
	 * @return
	 * @throws Exception
	 */
	public Vector<Vector<Object>> queryAllData()throws Exception{
		return executeQueryVector("SELECT ID,NAME FROM TB_SORT ORDER BY ID ");
	}
	
	
}
