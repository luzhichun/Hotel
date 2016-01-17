package com.icss.frame.manage;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Iterator;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.icss.dao.DeskDao;
import com.icss.mwing.MTable;
import com.icss.tool.Validate;
/**
 * 餐桌管理窗体
 * @author king
 *
 */
public class DeskNumDialog extends JDialog {

	private JTable table;
	
	private JOptionPane pane;

	private JTextField seatingTextField;

	private JTextField numTextField;

	private final Vector columnNameV = new Vector();
	
	private Vector tableValueV ;  //表格数据向量

	private DefaultTableModel tableModel = new DefaultTableModel();

	private JTable openedDeskTable;
	
	protected DeskDao dao = new DeskDao();

	/**
	 * Launch the application
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			DeskNumDialog dialog = new DeskNumDialog(null);
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
	public DeskNumDialog(JTable rightTable) {
		super();
		setModal(true);
		getContentPane().setLayout(new BorderLayout());
		setResizable(false);
		setTitle("台号管理");
		setBounds(100, 100, 500, 375);

		this.openedDeskTable = rightTable;

		final JPanel operatePanel = new JPanel();
		getContentPane().add(operatePanel, BorderLayout.NORTH);

		final JLabel numLabel = new JLabel();
		operatePanel.add(numLabel);
		numLabel.setText("台  号：");

		numTextField = new JTextField();
		numTextField.setColumns(6);
		operatePanel.add(numTextField);

		final JLabel seatingLabel = new JLabel();
		operatePanel.add(seatingLabel);
		seatingLabel.setText("  座位数：");

		seatingTextField = new JTextField();
		seatingTextField.setColumns(5);
		operatePanel.add(seatingTextField);

		final JLabel topPlaceholderLabel = new JLabel();
		topPlaceholderLabel.setPreferredSize(new Dimension(20, 40));
		operatePanel.add(topPlaceholderLabel);

		final JButton addButton = new JButton();//创建添加台号按钮对象
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String numDesk = numTextField.getText().trim();   // 获取台号，并去掉首尾空格
				int numNum = Integer.parseInt(numDesk);
				String seatDesk = seatingTextField.getText().trim();     // 获取座位数，并去掉首尾空格
				int seatNum = Integer.parseInt(seatDesk);

				if(numNum>0&&numDesk.length()<5){    // 查看用户是否输入了台号和座位数  // 查看台号的长度是否超过了5位
					if(seatNum>1 && seatNum<99){      // 验证座位数是否在1——99之间
						try {
							Vector<Vector<Object>> allDesk = dao.queryByNum(numNum);
							System.out.println(allDesk);
							if(allDesk.size()>0){
								pane.showConfirmDialog(null, "该数据已存在","提示框",JOptionPane.DEFAULT_OPTION);
							}else{
								dao.addDesk(numNum, seatNum);
								numTextField.setText("");
								seatingTextField.setText("");//将台号文本框设置为空 ,将座位数文本框设置为空, 将新添加的台号信息保存到数据库中	
							}
						} catch (Exception e1) {
							e1.printStackTrace();
						}	
					}
				}				
				
				// 获得当前拥有台号的个数
				// 创建一个代表新台号的向量
				// 添加添加序号
				// 添加台号
				// 添加座位数
				// 将新台号信息添加到表格中
				// 设置新添加的台号为选中的
				
				// 关闭数据库连接
			}
		});
		addButton.setText("添加");
		operatePanel.add(addButton);

		final JButton delButton = new JButton();//创建删除台号按钮对象
		delButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				Object oNum = table.getValueAt(row, 1);    // 获得选中的餐台
				int oDeskNum = Integer.parseInt(oNum.toString());
				Object oSeat = table.getValueAt(row, 2);
				if(row<=0){            // 未选中任何餐台给友情提示
					System.out.println("未选中任何餐台");
				}else{
					// 查看该餐台是否正在被使用
					// 该餐台正在被使用，不能删除，返回
					// 组织确认信息
					// 弹出确认提示
					try {
						dao.delDesk(oDeskNum);				// 确认删除  // 从数据库中删除  // 刷新表格	
						
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		delButton.setText("删除");
		operatePanel.add(delButton);

		final JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane);

		String columnNames[] = new String[] { "序  号", "台  号", "座位数" };
		for (int i = 0; i < columnNames.length; i++) {
			columnNameV.add(columnNames[i]);
		}
		try {
			tableValueV = dao.queryAllDesk();
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		tableModel = new DefaultTableModel(tableValueV, columnNameV);
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
		//
	}
}
