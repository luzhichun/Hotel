package com.icss.frame.check_out;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.icss.mwing.MTable;
import com.icss.tool.Today;
/**
 * �½��ʴ���
 * @author king
 *
 */
public class MonthDialog extends JDialog {

	private JTable table;

	private Vector<String> tableColumnV;	//����ͷ��Ϣ

	private Vector tableValueV;		//�������

	private DefaultTableModel tableModel;

	private JComboBox monthComboBox;

	private JComboBox yearComboBox;

	private int daysOfMonth[] = { 0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31,
			30, 31 };

	/**
	 * Launch the application
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			MonthDialog dialog = new MonthDialog();
			dialog.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					System.exit(0);
				}
			});
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog
	 */
	public MonthDialog() {
		super();
		setModal(true);
		setTitle("�½���");
		setBounds(60, 60, 860, 620);

		final JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);

		//���ϵͳ��ǰ���ڵ�����
		int year = Today.getYEAR();
		int month = Today.getMONTH();

		//�����������б����
		yearComboBox = new JComboBox();
		yearComboBox.setMaximumRowCount(10);
		/*------------  �������б��е�����Ҫ��ϵͳ���綨��������ݵ�ϵͳ��ǰʱ���
		 *------------  ���磺���綨������Ϊ2012-1-1,��ǰϵͳʱ��Ϊ2014-10-2,
		 *------------  ���������б�Ҫ��ʾ 2012,2013,2014
		*/
		//------------  �ڴ�д���� ------
	
		//�趨��ǰ��Ϊѡ��״̬
		yearComboBox.setSelectedItem(year);
		judgeLeapYear(year);	//�ж��Ƿ�Ϊ����
		yearComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int year = (Integer) yearComboBox.getSelectedItem();
				judgeLeapYear(year);
			}
		});
		panel.add(yearComboBox);

		final JLabel yearLabel = new JLabel();
		yearLabel.setText("��");
		panel.add(yearLabel);

		//�������б�
		monthComboBox = new JComboBox();
		monthComboBox.setMaximumRowCount(12);
		for (int m = 1; m < 13; m++) {
			monthComboBox.addItem(m);
		}
		monthComboBox.setSelectedItem(month);
		panel.add(monthComboBox);

		final JLabel monthLabel = new JLabel();
		monthLabel.setText("��    ");
		panel.add(monthLabel);

		//�ύ��ť
		final JButton submitButton = new JButton();
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//-------- �ڴ�д����
				
				tableModel.setDataVector(tableValueV, tableColumnV);
			}
		});
		submitButton.setText("ȷ��");
		panel.add(submitButton);

		final JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);

		tableColumnV = new Vector<String>();
		tableColumnV.add("����");
		tableColumnV.add("��̨����");
		tableColumnV.add("�����ܶ�");
		tableColumnV.add("ƽ�����Ѷ�");
		tableColumnV.add("������Ѷ�");
		tableColumnV.add("��С���Ѷ�");

		tableValueV = new Vector();

		tableModel = new DefaultTableModel(tableValueV, tableColumnV);

		table = new MTable(tableModel);
		scrollPane.setViewportView(table);
		//
	}

	private void judgeLeapYear(int year) {
		if (year % 100 == 0) {
			if (year % 400 == 0)
				daysOfMonth[2] = 29;
			else
				daysOfMonth[2] = 28;
		} else {
			if (year % 4 == 0)
				daysOfMonth[2] = 29;
			else
				daysOfMonth[2] = 28;
		}
	}

}
