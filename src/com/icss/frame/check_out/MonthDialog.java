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
 * 月结帐窗体
 * @author king
 *
 */
public class MonthDialog extends JDialog {

	private JTable table;

	private Vector<String> tableColumnV;	//表格表头信息

	private Vector tableValueV;		//表格数据

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
		setTitle("月结账");
		setBounds(60, 60, 860, 620);

		final JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);

		//获得系统当前日期的年月
		int year = Today.getYEAR();
		int month = Today.getMONTH();

		//创建年下拉列表对象
		yearComboBox = new JComboBox();
		yearComboBox.setMaximumRowCount(10);
		/*------------  年下拉列表中的数据要求：系统最早定单日期年份到系统当前时间表
		 *------------  例如：最早定单日期为2012-1-1,当前系统时间为2014-10-2,
		 *------------  则：年下拉列表要显示 2012,2013,2014
		*/
		//------------  在此写代码 ------
	
		//设定当前年为选中状态
		yearComboBox.setSelectedItem(year);
		judgeLeapYear(year);	//判断是否为闰年
		yearComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int year = (Integer) yearComboBox.getSelectedItem();
				judgeLeapYear(year);
			}
		});
		panel.add(yearComboBox);

		final JLabel yearLabel = new JLabel();
		yearLabel.setText("年");
		panel.add(yearLabel);

		//月下拉列表
		monthComboBox = new JComboBox();
		monthComboBox.setMaximumRowCount(12);
		for (int m = 1; m < 13; m++) {
			monthComboBox.addItem(m);
		}
		monthComboBox.setSelectedItem(month);
		panel.add(monthComboBox);

		final JLabel monthLabel = new JLabel();
		monthLabel.setText("月    ");
		panel.add(monthLabel);

		//提交按钮
		final JButton submitButton = new JButton();
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//-------- 在此写代码
				
				tableModel.setDataVector(tableValueV, tableColumnV);
			}
		});
		submitButton.setText("确定");
		panel.add(submitButton);

		final JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);

		tableColumnV = new Vector<String>();
		tableColumnV.add("日期");
		tableColumnV.add("开台总数");
		tableColumnV.add("消费总额");
		tableColumnV.add("平均消费额");
		tableColumnV.add("最大消费额");
		tableColumnV.add("最小消费额");

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
