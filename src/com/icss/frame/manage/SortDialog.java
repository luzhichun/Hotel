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
 * ��ϵ������
 * @author king
 */
public class SortDialog extends JDialog {

	private JTable table;

	private JTextField sortNameTextField;

	private final Vector columnNameV = new Vector();
	
	private Vector tableValueV ;  //�����������
	
	private SortDao dao = new SortDao();	//��ϵ���ݿ��������

	private DefaultTableModel tableModel = new DefaultTableModel();

	/**
	 * ��¼Ӧ��
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
		setTitle("��ϵ����");
		setBounds(100, 100, 500, 375);

		final JPanel operatePanel = new JPanel();
		getContentPane().add(operatePanel, BorderLayout.NORTH);
		final JLabel sortNameLabel = new JLabel();
		operatePanel.add(sortNameLabel);
		sortNameLabel.setText("��ϵ���ƣ�");

		sortNameTextField = new JTextField();
		operatePanel.add(sortNameTextField);
		sortNameTextField.setColumns(20);

		final JLabel topPlaceholderLabel = new JLabel();
		topPlaceholderLabel.setPreferredSize(new Dimension(20, 40));
		operatePanel.add(topPlaceholderLabel);

		final JButton addButton = new JButton();// ������Ӳ�ϵ���ư�ť����
		addButton.addActionListener(new ActionListener() {       //�����ڲ���ע���¼�
			public void actionPerformed(ActionEvent e) {
				// ��ò�ϵ���ƣ���ȥ����β�ո�
				String sortName = sortNameTextField.getText().trim();   
				// �鿴�Ƿ������˲�ϵ����
				// �鿴��ϵ���Ƶĳ����Ƿ񳬹���10������
				if(sortName.length()<10 && sortName.length()>0){
					// �鿴�ò�ϵ�����Ƿ��Ѿ�����
					try {
						// ���²�ϵ������Ϣ��ӵ������
						
						// ��������ӵĲ�ϵ����Ϊѡ�е�
						// ����ϵ�����ı�������Ϊ��
						sortNameTextField.setText("");
						// ������ӵĲ�ϵ������Ϣ���浽���ݿ���
						dao.addSort(sortName);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				} else{
					System.out.println("��������ֹ�����");
				}		
			}
		});
		addButton.setText("���");
		operatePanel.add(addButton);

		final JButton delButton = new JButton();// ����ɾ����ϵ���ư�ť����
		delButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// ���ѡ�еĲ�ϵ
				int row = table.getSelectedRow();
				System.out.println(row);
				// ���ѡ�еĲ�ϵ����
				// ����ȷ����ʾ
				JOptionPane optionPane = new JOptionPane();
				int opt = optionPane.showConfirmDialog(null,"ȷ��Ҫɾ����","��ʾ��",JOptionPane.YES_NO_OPTION);
				// ȷ��ɾ��
//				if(opt){
//					// �ӱ�����Ƴ���ϵ��Ϣ
//					// �����ݿ���ɾ����ϵ
//					dao.delSort(id);
//				}
			}
		});
		delButton.setText("ɾ��");
		operatePanel.add(delButton);

		final JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane);

		//����������Ϣ
		columnNameV.add("��    ��");
		columnNameV.add("��ϵ����");

		//---------- ��ʼ������
		//����������Ϣ
		try {
			tableValueV = dao.queryAllData();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		//����TableModel
		tableModel = new DefaultTableModel(tableValueV, columnNameV);
		//����table
		table = new MTable(tableModel);
		//��������д���0��ѡ�е�һ��
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
	}
}
