package com.icss.frame.manage;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.icss.dao.SortDao;
import com.icss.mwing.MTable;
/**
 * 菜系管理窗体
 * @author king
 */
public class SortDialog extends JDialog {

	private JTable table;

	private JTextField sortNameTextField;

	private final Vector columnNameV = new Vector();
	
	private Vector tableValueV ;  //表格数据向量
	
	private SortDao dao = new SortDao();	//菜系数据库操作对象

	private DefaultTableModel tableModel = new DefaultTableModel();

	/**
	 * 登录应用
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			SortDialog dialog = new SortDialog();
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
	public SortDialog() {
		super();
		setModal(true);
		getContentPane().setLayout(new BorderLayout());
		setResizable(false);
		setTitle("菜系管理");
		setBounds(100, 100, 500, 375);

		final JPanel operatePanel = new JPanel();
		getContentPane().add(operatePanel, BorderLayout.NORTH);
		final JLabel sortNameLabel = new JLabel();
		operatePanel.add(sortNameLabel);
		sortNameLabel.setText("菜系名称：");

		sortNameTextField = new JTextField();
		operatePanel.add(sortNameTextField);
		sortNameTextField.setColumns(20);

		final JLabel topPlaceholderLabel = new JLabel();
		topPlaceholderLabel.setPreferredSize(new Dimension(20, 40));
		operatePanel.add(topPlaceholderLabel);

		final JButton addButton = new JButton();// 创建添加菜系名称按钮对象
		addButton.addActionListener(new ActionListener() {       //匿名内部类注册事件
			public void actionPerformed(ActionEvent e) {
				// 获得菜系名称，并去掉首尾空格
				String sortName = sortNameTextField.getText().trim();   
				// 查看是否输入了菜系名称
				// 查看菜系名称的长度是否超过了10个汉字
				if(sortName.length()<10 && sortName.length()>0){
					// 查看该菜系名称是否已经存在
					try {
						// 将新菜系名称信息添加到表格中
						
						// 设置新添加的菜系名称为选中的
						// 将菜系名称文本框设置为空
						sortNameTextField.setText("");
						// 将新添加的菜系名称信息保存到数据库中
						dao.addSort(sortName);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				} else{
					System.out.println("输入的名字过长！");
				}		
			}
		});
		addButton.setText("添加");
		operatePanel.add(addButton);

		final JButton delButton = new JButton();// 创建删除菜系名称按钮对象
		delButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 获得选中的菜系
				int row = table.getSelectedRow();
				System.out.println(row);
				// 获得选中的菜系名称
				// 弹出确认提示
				JOptionPane optionPane = new JOptionPane();
				int opt = optionPane.showConfirmDialog(null,"确认要删除吗","提示框",JOptionPane.YES_NO_OPTION);
				// 确认删除
//				if(opt){
//					// 从表格中移除菜系信息
//					// 从数据库中删除菜系
//					dao.delSort(id);
//				}
			}
		});
		delButton.setText("删除");
		operatePanel.add(delButton);

		final JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane);

		//表格的列名信息
		columnNameV.add("序    号");
		columnNameV.add("菜系名称");

		//---------- 初始化数据
		//表格的数据信息
		try {
			tableValueV = dao.queryAllData();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		//创建TableModel
		tableModel = new DefaultTableModel(tableValueV, columnNameV);
		//创建table
		table = new MTable(tableModel);
		//表格数据行大于0则选中第一行
		if (table.getRowCount() > 0)
			table.setRowSelectionInterval(0, 0);
		scrollPane.setViewportView(table);

		final JLabel leftPlaceholderLabel = new JLabel();
		leftPlaceholderLabel.setPreferredSize(new Dimension(20, 20));
		getContentPane().add(leftPlaceholderLabel, BorderLayout.WEST);

		final JPanel exitPanel = new JPanel();
		final FlowLayout flowLayout = new FlowLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		exitPanel.setLayout(flowLayout);
		getContentPane().add(exitPanel, BorderLayout.SOUTH);

		final JButton exitButton = new JButton();
		exitPanel.add(exitButton);
		exitButton.setText("退出");
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		final JLabel bottomPlaceholderLabel = new JLabel();
		bottomPlaceholderLabel.setPreferredSize(new Dimension(10, 40));
		exitPanel.add(bottomPlaceholderLabel);

		final JLabel rightPlaceholderLabel = new JLabel();
		rightPlaceholderLabel.setPreferredSize(new Dimension(20, 20));
		getContentPane().add(rightPlaceholderLabel, BorderLayout.EAST);
	}
}
