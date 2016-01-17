package com.icss.dao;

import java.util.Vector;

public class SortDao extends BaseDao{
	/**
	 * ��Ӳ�ϵ
	 * @param name ��ϵ��
	 * @return Ӱ������
	 * @throws Exception
	 */
	public int addSort(String name) throws Exception{
		return executeUpdate("INSERT INTO TB_SORT(NAME) VALUES(?)", name);
	}

	/**
	 * ɾ����ϵ
	 * @param id ��ϵ���
	 * @return Ӱ������
	 * @throws Exception
	 */
	public int delSort(int id) throws Exception{
		return executeUpdate("DELETE FROM TB_SORT WHERE ID=?", id);
	}
	/**
	 * ��ѯȫ����ϵ����
	 * @return
	 * @throws Exception
	 */
	public Vector<Vector<Object>> queryAllData()throws Exception{
		return executeQueryVector("SELECT ID,NAME FROM TB_SORT ORDER BY ID ");
	}
	
	
}
