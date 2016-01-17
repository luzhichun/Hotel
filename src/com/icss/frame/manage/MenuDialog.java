package com.icss.frame.manage;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import org.omg.CORBA.PRIVATE_MEMBER;

import com.icss.bean.MenuBean;
import com.icss.dao.MenuDao;
import com.icss.dao.SortDao;
import com.icss.mwing.MTable;
import com.icss.tool.Today;
/**
 * 菜品管理窗体
 * @author king
 *
 */
public class MenuDialog extends JDialog {

	private JTextField numTextField;

	private JTextField nameTextField;

	private JTextField unitTextField;

	private JTextField codeTextField;

	private JComboBox sortComboBox;

	private JTextField unitPriceTextField;

	private JTable table;

	private final Vector tableColumnV = new Vector();
	private final Vector tableValueV = new Vector();

	private DefaultTableModel tableModel = new DefaultTableModel();
	
	private MenuDao dao = new MenuDao();


	/**
	 * Launch the application
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			MenuDialog dialog = new MenuDialog();
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
	public MenuDialog() {
		super();
		setModal(true);
		getContentPane().setLayout(new BorderLayout());
		setResizable(false);
		setTitle("菜品管理");
		setBounds(100, 100, 800, 375);

		final JPanel operatePanel = new JPanel();
		operatePanel.setLayout(new GridBagLayout());
		getContentPane().add(operatePanel, BorderLayout.NORTH);

		final JLabel numLabel = new JLabel();
		numLabel.setText("编  号：");
		final GridBagConstraints gridBagConstraints_6 = new GridBagConstraints();
		gridBagConstraints_6.insets = new Insets(15, 0, 0, 0);
		gridBagConstraints_6.gridx = 0;
		gridBagConstraints_6.gridy = 0;
		operatePanel.add(numLabel, gridBagConstraints_6);

		numTextField = new JTextField();
		numTextField.setHorizontalAlignment(SwingConstants.CENTER);
		numTextField.setEditable(false);
		numTextField.setColumns(10);
		final GridBagConstraints gridBagConstraints_15 = new GridBagConstraints();
		gridBagConstraints_15.insets = new Insets(15, 0, 0, 0);
		gridBagConstraints_15.gridy = 0;
		gridBagConstraints_15.gridx = 1;
		operatePanel.add(numTextField, gridBagConstraints_15);

		final JLabel nameLabel = new JLabel();
		final GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.insets = new Insets(15, 15, 0, 0);
		gridBagConstraints.gridx = 2;
		gridBagConstraints.gridy = 0;
		operatePanel.add(nameLabel, gridBagConstraints);
		nameLabel.setText("名称：");

		nameTextField = new JTextField();
		// nameTextField.setName("名称");
		final GridBagConstraints gridBagConstraints_1 = new GridBagConstraints();
		gridBagConstraints_1.insets = new Insets(15, 0, 0, 0);
		gridBagConstraints_1.gridx = 3;
		gridBagConstraints_1.gridy = 0;
		operatePanel.add(nameTextField, gridBagConstraints_1);
		nameTextField.setColumns(21);

		final JLabel unitPriceLabel = new JLabel();
		unitPriceLabel.setText("单价：");
		final GridBagConstraints gridBagConstraints_9 = new GridBagConstraints();
		gridBagConstraints_9.insets = new Insets(10, 15, 0, 0);
		gridBagConstraints_9.gridy = 1;
		gridBagConstraints_9.gridx = 4;
		operatePanel.add(unitPriceLabel, gridBagConstraints_9);

		final JLabel unitLabel = new JLabel();
		unitLabel.setText("单位：");
		final GridBagConstraints gridBagConstraints_8 = new GridBagConstraints();
		gridBagConstraints_8.insets = new Insets(10, 15, 0, 0);
		gridBagConstraints_8.gridy = 0;
		gridBagConstraints_8.gridx = 4;
		operatePanel.add(unitLabel, gridBagConstraints_8);

		unitTextField = new JTextField();
		unitTextField.setName("单位");
		unitTextField.setColumns(10);
		final GridBagConstraints gridBagConstraints_11 = new GridBagConstraints();
		gridBagConstraints_11.gridwidth = 2;
		gridBagConstraints_11.insets = new Insets(10, 0, 0, 0);
		gridBagConstraints_11.gridy = 0;
		gridBagConstraints_11.gridx = 5;
		operatePanel.add(unitTextField, gridBagConstraints_11);

		final JLabel codeLabel = new JLabel();
		final GridBagConstraints gridBagConstraints_2 = new GridBagConstraints();
		gridBagConstraints_2.insets = new Insets(15, 0, 0, 0);
		gridBagConstraints_2.gridx = 0;
		gridBagConstraints_2.gridy = 1;
		operatePanel.add(codeLabel, gridBagConstraints_2);
		codeLabel.setText("助记码：");

		codeTextField = new JTextField();
		codeTextField.setName("助记码");
		codeTextField.setColumns(10);
		final GridBagConstraints gridBagConstraints_3 = new GridBagConstraints();
		gridBagConstraints_3.insets = new Insets(15, 0, 0, 0);
		gridBagConstraints_3.gridx = 1;
		gridBagConstraints_3.gridy = 1;
		operatePanel.add(codeTextField, gridBagConstraints_3);

		final JLabel sortLabel = new JLabel();
		sortLabel.setText("菜系：");
		final GridBagConstraints gridBagConstraints_4 = new GridBagConstraints();
		gridBagConstraints_4.insets = new Insets(10, 15, 0, 0);
		gridBagConstraints_4.gridy = 1;
		gridBagConstraints_4.gridx = 2;
		operatePanel.add(sortLabel, gridBagConstraints_4);

		sortComboBox = new JComboBox();
		sortComboBox.addItem("请选择");
		final GridBagConstraints gridBagConstraints_7 = new GridBagConstraints();
		gridBagConstraints_7.anchor = GridBagConstraints.WEST;
		gridBagConstraints_7.insets = new Insets(10, 0, 0, 0);
		gridBagConstraints_7.gridy = 1;
		gridBagConstraints_7.gridx = 3;
		operatePanel.add(sortComboBox, gridBagConstraints_7);
		try {
			Vector<Vector<Object>> sDao = new SortDao().queryAllData();
			Iterator<Vector<Object>> iterator = sDao.iterator();
			while(iterator.hasNext()){
				Vector<Object> oneRow = iterator.next();
				sortComboBox.addItem(oneRow.get(1));  //我怎么这么聪明呢？？？
			}
		} catch (Exception e2) {
			e2.printStackTrace();
		}

		unitPriceTextField = new JTextField();
		unitPriceTextField.setName("单价");
		unitPriceTextField.setColumns(8);
		final GridBagConstraints gridBagConstraints_12 = new GridBagConstraints();
		gridBagConstraints_12.insets = new Insets(10, 0, 0, 0);
		gridBagConstraints_12.gridy = 1;
		gridBagConstraints_12.gridx = 5;
		operatePanel.add(unitPriceTextField, gridBagConstraints_12);

		final JLabel label = new JLabel();
		label.setText("元");
		final GridBagConstraints gridBagConstraints_5 = new GridBagConstraints();
		gridBagConstraints_5.insets = new Insets(10, 0, 0, 0);
		gridBagConstraints_5.gridy = 1;
		gridBagConstraints_5.gridx = 6;
		operatePanel.add(label, gridBagConstraints_5);

		final JPanel panel = new JPanel();
		final FlowLayout flowLayout_1 = new FlowLayout();
		panel.setLayout(flowLayout_1);
		final GridBagConstraints gridBagConstraints_14 = new GridBagConstraints();
		gridBagConstraints_14.anchor = GridBagConstraints.EAST;
		gridBagConstraints_14.insets = new Insets(5, 0, 10, 0);
		gridBagConstraints_14.gridwidth = 7;
		gridBagConstraints_14.gridy = 2;
		gridBagConstraints_14.gridx = 0;
		operatePanel.add(panel, gridBagConstraints_14);

		//添加新菜品按钮
		
		final JButton addButton = new JButton();
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String nameMenu = nameTextField.getText().trim();        // 验证菜品文本框为空
				String maxNum=null;                                      // 获得菜品编号
				String nextNum = null;                            
				try {
					maxNum = dao.getMaxNum().toString();
					nextNum = getNextNum(maxNum);
				} catch (Exception e1) {
					e1.printStackTrace();
				}             
				String codeMenu = codeTextField.getText();               // 获得菜品助记码
				Object sortMenu = sortComboBox.getSelectedItem();        // 获得菜品所属菜系
				int sort_id = sortComboBox.getSelectedIndex();
				
				String sort = sortMenu.toString();
				String unitMenu = unitTextField.getText();               // 获得菜品单位
				String unitPriceMenu = unitPriceTextField.getText();     // 获得菜品单价
				double unitPrice = Integer.parseInt(unitPriceMenu);
				
				if(nameMenu.length()>0 && nameMenu.length()<10){            // 验证菜品名称最多只能为 10 个汉字
					if(codeMenu.length()>10){                     // 验证助记码最多只能为 10 个字符
						System.out.println("只能输入10个字符");
					}else{
						if(unitMenu.length()>2){                  // 验证单位最多只能为 2 个汉字
							System.out.println("单位只能输入两个汉字");
						}else{
							if(unitPrice<1 || unitPrice>9999){   // 验证单价必须在 1——9999，验证菜系下拉菜单
								System.out.println("单价不合理");
							}else{
								// 判断当前菜品是否已经存在并且状态为“销售”，是则提示 "该菜品已经存在！"	
								try {                                         // 将新菜品信息保存到数据库
									Vector<Vector<Object>> mVector = dao.checkMenu(nameMenu);
									if(mVector.size()>0){
										JOptionPane jPane = new JOptionPane();
										jPane.showConfirmDialog(null, "提示框", "该菜品已经存在!",JOptionPane.DEFAULT_OPTION);
									}else{
										int nextNum2 = Integer.parseInt(nextNum);
									    
										MenuBean menuBean = new MenuBean();
									    menuBean.setNum(nextNum2);
										menuBean.setName(nameMenu);
										menuBean.setCode(codeMenu);
										menuBean.setSort_id(sort_id);
										menuBean.setUnit(unitMenu);
										menuBean.setUnit_price(unitPrice);
										dao.addMenu(menuBean);
										nameTextField.setText("");     // 清空表格数据
										codeTextField.setText("");
										unitTextField.setText("");
										unitPriceTextField.setText("");
									}
								} catch (Exception e1) {
									e1.printStackTrace();
								}
							}
						}
					}
				}					
			}
		});
		addButton.setText("添加");
		panel.add(addButton);

		//删除按钮
		final JButton delButton = new JButton();
		delButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 获得选中的菜品
				// 弹出确认提示框
				// 确认删除
				// 从表格中移除菜品信息
				// 从数据库中删除菜品
				
			}
		});
		delButton.setText("删除");
		panel.add(delButton);

		final JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane);

		String columnNames[] = new String[] { "序 号", "编 号", "名 称", "助记码",
				"菜 系", "单 位", "单 价" };
		for (int i = 0; i < columnNames.length; i++) {
			tableColumnV.add(columnNames[i]);
		}
		try {                                                         //获取所有菜品数据
			Vector<Vector<Object>> menus = dao.queryAllMenu();
			Iterator<Vector<Object>>  it = menus.iterator();
			while (it.hasNext()) {
				Vector<Object> oneRow = it.next();
				tableValueV.add(oneRow);
			}
			
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		//---------- 获得菜系下拉列表数据
		tableModel = new DefaultTableModel(tableValueV, tableColumnV);
		table = new MTable(tableModel);
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
	/*Today dateNow = new Today();
				String date = dateNow.getDate();
				String num1 = date.replaceAll("-", "").substring(2, 8);
				int i=0;
				String num3=null;
    */
	private String getNextNum(String maxNum) {
		String date = Today.getDateOfNum().substring(2);
		if (maxNum == null) {
			maxNum = date + "01";
		} else {
			if (maxNum.subSequence(0, 6).equals(date)) {
				maxNum = maxNum.substring(6);
				int nextNum = Integer.valueOf(maxNum) + 1;
				if (nextNum < 10)
					maxNum = date + "0" + nextNum;
				else
					maxNum = date + nextNum;
			} else {
				maxNum = date + "01";
			}
		}
		return maxNum;
	}
		
}