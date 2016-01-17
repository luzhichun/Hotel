package com.icss.frame.check_out;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import com.icss.mwing.FixedColumnTablePanel;
import com.icss.mwing.MTable;
import com.icss.tool.Today;
/**
 * 日结帐窗体
 * @author king
 *
 */
public class DayDialog extends JDialog {
	private Vector<String> tableColumnV;	//表格表头信息

	private Vector<Vector<Object>> tableValueV; //表格数据

	private JComboBox dayComboBox;	//日下拉列表

	private JComboBox monthComboBox;//月下拉列表

	private JComboBox yearComboBox;	//年下拉列表

	private int daysOfMonth[] = { 0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31,
			30, 31 };   //每月对应天数数组

	

	/**
	 * Launch the application
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			DayDialog dialog = new DayDialog();
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
	public DayDialog() {
		super();
		setModal(true);
		setTitle("日结账");
		setBounds(60, 60, 860, 620);

		final JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);

		//获得系统当前日期的年月日
		int year = Today.getYEAR();
		int month = Today.getMONTH();
		int day = Today.getDAY();

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
		judgeLeapYear(year);
		//为年下拉列表添加事件监听：当选中年月后自动修改日期下拉列表
		yearComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int year = (Integer) yearComboBox.getSelectedItem();// 获得选中的年度
				judgeLeapYear(year);// 判断是否为闰年，以确定2月份的天数
				int month = (Integer) monthComboBox.getSelectedItem();// 获得选中的月份
				if (month == 2) {// 如果选中的为2月
					int itemCount = dayComboBox.getItemCount();// 获得日下拉菜单当前的天数
					if (itemCount != daysOfMonth[2]) {// 如果日下拉菜单当前的天数不等于2月份的天数
						if (itemCount == 28)// 如果日下拉菜单当前的天数为28天
							dayComboBox.addItem(29);// 则添加为29天
						else
							// 否则日下拉菜单当前的天数则为29天
							dayComboBox.removeItem(29);// 则减少为28天
					}
				}
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
		monthComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int month = (Integer) monthComboBox.getSelectedItem();// 获得选中的月份
				int itemCount = dayComboBox.getItemCount();// 获得日下拉菜单当前的天数
				while (itemCount != daysOfMonth[month]) {// 如果日下拉菜单当前的天数不等于选中月份的天数
					if (itemCount > daysOfMonth[month]) {// 如果大于选中月份的天数
						dayComboBox.removeItem(itemCount);// 则移除最后一个选择项
						itemCount--;// 并将日下拉菜单当前的天数减1
					} else {// 否则小于选中月份的天数
						itemCount++;// 将日下拉菜单当前的天数加1
						dayComboBox.addItem(itemCount);// 并添加为选择项
					}
				}
			}
		});
		panel.add(monthComboBox);

		final JLabel monthLabel = new JLabel();
		monthLabel.setText("月");
		panel.add(monthLabel);

		//创建月下拉列表
		dayComboBox = new JComboBox();
		dayComboBox.setMaximumRowCount(10);
		int days = daysOfMonth[month];
		for (int d = 1; d <= days; d++) {
			dayComboBox.addItem(d);
		}
		dayComboBox.setSelectedItem(day);
		panel.add(dayComboBox);

		final JLabel dayLabel = new JLabel();
		dayLabel.setText("日    ");
		panel.add(dayLabel);

		//提交按钮
		final JButton submitButton = new JButton();
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//------------  在此写代码 ------
				JOptionPane.showMessageDialog(null, "查询本日所有消费帐单");
//				tableValueV.removeAllElements();
//				int year = (Integer) yearComboBox.getSelectedItem();
//				int month = (Integer) monthComboBox.getSelectedItem();
//				int day = (Integer) dayComboBox.getSelectedItem();
//				int columnCount = tableColumnV.size();
				
				//查询本日所有消费清单 sOrderFormOfDay
				
				//显示数据
				
				//回填表格	
				Container contentPane = getContentPane();
				contentPane.remove(1);
				contentPane.add(new FixedColumnTablePanel(tableColumnV,
						tableValueV, 4), BorderLayout.CENTER);
				SwingUtilities.updateComponentTreeUI(contentPane);
			}
		});
		submitButton.setText("确定");
		panel.add(submitButton);

		tableColumnV = new Vector<String>();
		tableColumnV.add("编号");
		tableColumnV.add("台号");
		tableColumnV.add("开台时间");
		tableColumnV.add("消费金额");
		//----------- 表格中添加菜品选项
		//----------- 在此写代码
		
		tableValueV = new Vector();

		getContentPane().add(
				new FixedColumnTablePanel(tableColumnV, tableValueV, 4),
				BorderLayout.CENTER);
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
