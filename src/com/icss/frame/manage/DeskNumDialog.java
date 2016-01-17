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
 * ����������
 * @author king
 *
 */
public class DeskNumDialog extends JDialog {

	private JTable table;
	
	private JOptionPane pane;

	private JTextField seatingTextField;

	private JTextField numTextField;

	private final Vector columnNameV = new Vector();
	
	private Vector tableValueV ;  //�����������

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
		setTitle("̨�Ź���");
		setBounds(100, 100, 500, 375);

		this.openedDeskTable = rightTable;

		final JPanel operatePanel = new JPanel();
		getContentPane().add(operatePanel, BorderLayout.NORTH);

		final JLabel numLabel = new JLabel();
		operatePanel.add(numLabel);
		numLabel.setText("̨  �ţ�");

		numTextField = new JTextField();
		numTextField.setColumns(6);
		operatePanel.add(numTextField);

		final JLabel seatingLabel = new JLabel();
		operatePanel.add(seatingLabel);
		seatingLabel.setText("  ��λ����");

		seatingTextField = new JTextField();
		seatingTextField.setColumns(5);
		operatePanel.add(seatingTextField);

		final JLabel topPlaceholderLabel = new JLabel();
		topPlaceholderLabel.setPreferredSize(new Dimension(20, 40));
		operatePanel.add(topPlaceholderLabel);

		final JButton addButton = new JButton();//�������̨�Ű�ť����
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String numDesk = numTextField.getText().trim();   // ��ȡ̨�ţ���ȥ����β�ո�
				int numNum = Integer.parseInt(numDesk);
				String seatDesk = seatingTextField.getText().trim();     // ��ȡ��λ������ȥ����β�ո�
				int seatNum = Integer.parseInt(seatDesk);

				if(numNum>0&&numDesk.length()<5){    // �鿴�û��Ƿ�������̨�ź���λ��  // �鿴̨�ŵĳ����Ƿ񳬹���5λ
					if(seatNum>1 && seatNum<99){      // ��֤��λ���Ƿ���1����99֮��
						try {
							Vector<Vector<Object>> allDesk = dao.queryByNum(numNum);
							System.out.println(allDesk);
							if(allDesk.size()>0){
								pane.showConfirmDialog(null, "�������Ѵ���","��ʾ��",JOptionPane.DEFAULT_OPTION);
							}else{
								dao.addDesk(numNum, seatNum);
								numTextField.setText("");
								seatingTextField.setText("");//��̨���ı�������Ϊ�� ,����λ���ı�������Ϊ��, ������ӵ�̨����Ϣ���浽���ݿ���	
							}
						} catch (Exception e1) {
							e1.printStackTrace();
						}	
					}
				}				
				
				// ��õ�ǰӵ��̨�ŵĸ���
				// ����һ��������̨�ŵ�����
				// ���������
				// ���̨��
				// �����λ��
				// ����̨����Ϣ��ӵ������
				// ��������ӵ�̨��Ϊѡ�е�
				
				// �ر����ݿ�����
			}
		});
		addButton.setText("���");
		operatePanel.add(addButton);

		final JButton delButton = new JButton();//����ɾ��̨�Ű�ť����
		delButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				Object oNum = table.getValueAt(row, 1);    // ���ѡ�еĲ�̨
				int oDeskNum = Integer.parseInt(oNum.toString());
				Object oSeat = table.getValueAt(row, 2);
				if(row<=0){            // δѡ���κβ�̨��������ʾ
					System.out.println("δѡ���κβ�̨");
				}else{
					// �鿴�ò�̨�Ƿ����ڱ�ʹ��
					// �ò�̨���ڱ�ʹ�ã�����ɾ��������
					// ��֯ȷ����Ϣ
					// ����ȷ����ʾ
					try {
						dao.delDesk(oDeskNum);				// ȷ��ɾ��  // �����ݿ���ɾ��  // ˢ�±��	
						
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		delButton.setText("ɾ��");
		operatePanel.add(delButton);

		final JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane);

		String columnNames[] = new String[] { "��  ��", "̨  ��", "��λ��" };
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
		exitButton.setText("�˳�");
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
