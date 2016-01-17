package com.icss.frame.user;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.icss.mwing.MTable;
import com.icss.tool.Validate;
/**
 * �û�������
 * @author king
 *
 */
public class UserManagerDialog extends JDialog {

	private MTable table;

	private Vector<String> tableColumnV;

	private Vector<Vector> tableValueV;

	private DefaultTableModel tableModel;

	private ButtonGroup buttonGroup = new ButtonGroup();

	private JTextField passwordTextField;

	private JTextField idCardTextField;

	private JTextField birthdayTextField;

	private JTextField nameTextField;

	private boolean isAdd = true;

	/**
	 * Launch the application
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		System.out.println(Validate.execute("(\\d){4,6}", "22000"));
		 try {
		 UserManagerDialog dialog = new UserManagerDialog();
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
	public UserManagerDialog() {
		super();
		setModal(true);
		setTitle("�û�����");
		setBounds(100, 100, 800, 375);

		final JPanel inputPanel = new JPanel();
		inputPanel.setLayout(new GridBagLayout());
		getContentPane().add(inputPanel, BorderLayout.NORTH);

		final JLabel nameLabel = new JLabel();
		nameLabel.setText("��    ����");
		final GridBagConstraints gridBagConstraints_13 = new GridBagConstraints();
		gridBagConstraints_13.insets = new Insets(10, 0, 0, 0);
		inputPanel.add(nameLabel, gridBagConstraints_13);

		nameTextField = new JTextField();
		nameTextField.setColumns(12);
		final GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.insets = new Insets(10, 0, 0, 0);
		gridBagConstraints.gridy = 0;
		gridBagConstraints.gridx = 1;
		inputPanel.add(nameTextField, gridBagConstraints);

		final JLabel sexLabel = new JLabel();
		sexLabel.setText("�Ա�");
		final GridBagConstraints gridBagConstraints_1 = new GridBagConstraints();
		gridBagConstraints_1.insets = new Insets(10, 15, 0, 0);
		gridBagConstraints_1.gridy = 0;
		gridBagConstraints_1.gridx = 2;
		inputPanel.add(sexLabel, gridBagConstraints_1);

		final JRadioButton manRadioButton = new JRadioButton();
		buttonGroup.add(manRadioButton);
		manRadioButton.setText("��");
		manRadioButton.setSelected(true);
		final GridBagConstraints gridBagConstraints_2 = new GridBagConstraints();
		gridBagConstraints_2.insets = new Insets(10, 0, 0, 0);
		gridBagConstraints_2.gridy = 0;
		gridBagConstraints_2.gridx = 3;
		inputPanel.add(manRadioButton, gridBagConstraints_2);

		final JRadioButton womanRadioButton = new JRadioButton();
		buttonGroup.add(womanRadioButton);
		womanRadioButton.setText("Ů");
		final GridBagConstraints gridBagConstraints_3 = new GridBagConstraints();
		gridBagConstraints_3.insets = new Insets(10, 0, 0, 0);
		gridBagConstraints_3.gridy = 0;
		gridBagConstraints_3.gridx = 4;
		inputPanel.add(womanRadioButton, gridBagConstraints_3);

		final JLabel birthdayLabel = new JLabel();
		birthdayLabel.setText("�������ڣ�");
		final GridBagConstraints gridBagConstraints_4 = new GridBagConstraints();
		gridBagConstraints_4.insets = new Insets(10, 15, 0, 0);
		gridBagConstraints_4.gridy = 0;
		gridBagConstraints_4.gridx = 5;
		inputPanel.add(birthdayLabel, gridBagConstraints_4);

		birthdayTextField = new JTextField();
		birthdayTextField.setColumns(20);
		final GridBagConstraints gridBagConstraints_5 = new GridBagConstraints();
		gridBagConstraints_5.insets = new Insets(10, 0, 0, 0);
		gridBagConstraints_5.gridy = 0;
		gridBagConstraints_5.gridx = 6;
		inputPanel.add(birthdayTextField, gridBagConstraints_5);

		final JLabel passwordLabel = new JLabel();
		passwordLabel.setText("��¼���룺");
		final GridBagConstraints gridBagConstraints_8 = new GridBagConstraints();
		gridBagConstraints_8.insets = new Insets(5, 0, 0, 0);
		gridBagConstraints_8.gridy = 1;
		gridBagConstraints_8.gridx = 0;
		inputPanel.add(passwordLabel, gridBagConstraints_8);

		passwordTextField = new JTextField();
		passwordTextField.setColumns(33);
		final GridBagConstraints gridBagConstraints_9 = new GridBagConstraints();
		gridBagConstraints_9.gridwidth = 4;
		gridBagConstraints_9.anchor = GridBagConstraints.WEST;
		gridBagConstraints_9.insets = new Insets(5, 0, 0, 0);
		gridBagConstraints_9.gridy = 1;
		gridBagConstraints_9.gridx = 1;
		inputPanel.add(passwordTextField, gridBagConstraints_9);

		final JLabel idCardLabel = new JLabel();
		idCardLabel.setText("���֤�ţ�");
		final GridBagConstraints gridBagConstraints_6 = new GridBagConstraints();
		gridBagConstraints_6.insets = new Insets(5, 16, 0, 0);
		gridBagConstraints_6.gridy = 1;
		gridBagConstraints_6.gridx = 5;
		inputPanel.add(idCardLabel, gridBagConstraints_6);

		idCardTextField = new JTextField();
		idCardTextField.setColumns(20);
		final GridBagConstraints gridBagConstraints_7 = new GridBagConstraints();
		gridBagConstraints_7.insets = new Insets(5, 0, 0, 0);
		gridBagConstraints_7.gridy = 1;
		gridBagConstraints_7.gridx = 6;
		inputPanel.add(idCardTextField, gridBagConstraints_7);

		final JPanel buttonPanel = new JPanel();
		final GridBagConstraints gridBagConstraints_10 = new GridBagConstraints();
		gridBagConstraints_10.anchor = GridBagConstraints.EAST;
		gridBagConstraints_10.insets = new Insets(5, 0, 10, 0);
		gridBagConstraints_10.gridwidth = 7;
		gridBagConstraints_10.gridy = 2;
		gridBagConstraints_10.gridx = 0;
		inputPanel.add(buttonPanel, gridBagConstraints_10);

		//������û�
		final JButton subButton = new JButton();
		subButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// ����û��ύ����
				// ��֤�������ֻ��Ϊ 4 �����֣�
				// ��֤���������Ƿ��������, "��ȷ��ʽΪ��2008-8-8"��
				// ��֤���֤���Ƿ��������", "���֤�ű���Ϊ15λ��18λ��"
				// ��֤��������ܳ��� 20 ���ַ���
				// �����ݿ����������
				// ���±������
				// ���
				nameTextField.setText("");
				manRadioButton.setSelected(true);
				birthdayTextField.setText("");
				idCardTextField.setText("");
				passwordTextField.setText("");
			}
		});
		subButton.setText("���");
		buttonPanel.add(subButton);

		// ɾ���û���Ϣ
		final JButton delButton = new JButton();
		delButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// ���Ҫɾ���û�����
				// ��ʾ "ȷ��Ҫɾ���û�"
				// �����ݿ��������û�״̬Ϊ "����"
				// �޸ı������
			}
		});
		delButton.setText("ɾ��");
		buttonPanel.add(delButton);

		final JButton exitButton = new JButton();
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		exitButton.setText("�˳�");
		buttonPanel.add(exitButton);

		final JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);

		tableColumnV = new Vector<String>();
		String tableColumns[] = { "��    ��", "��    ��", "��    ��", "��������", "���֤��" };
		for (int column = 0; column < tableColumns.length; column++) {
			tableColumnV.add(tableColumns[column]);
		}

		//------��ʾȫ������
		tableValueV = new Vector<Vector>();
		tableModel = new DefaultTableModel(tableValueV, tableColumnV);

		table = new MTable(tableModel);
		if (table.getRowCount() > 0)
			table.setRowSelectionInterval(0);
		scrollPane.setViewportView(table);
		//
	}
}
