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
 * �ս��ʴ���
 * @author king
 *
 */
public class DayDialog extends JDialog {
	private Vector<String> tableColumnV;	//����ͷ��Ϣ

	private Vector<Vector<Object>> tableValueV; //�������

	private JComboBox dayComboBox;	//�������б�

	private JComboBox monthComboBox;//�������б�

	private JComboBox yearComboBox;	//�������б�

	private int daysOfMonth[] = { 0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31,
			30, 31 };   //ÿ�¶�Ӧ��������

	

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
		setTitle("�ս���");
		setBounds(60, 60, 860, 620);

		final JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);

		//���ϵͳ��ǰ���ڵ�������
		int year = Today.getYEAR();
		int month = Today.getMONTH();
		int day = Today.getDAY();

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
		judgeLeapYear(year);
		//Ϊ�������б�����¼���������ѡ�����º��Զ��޸����������б�
		yearComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int year = (Integer) yearComboBox.getSelectedItem();// ���ѡ�е����
				judgeLeapYear(year);// �ж��Ƿ�Ϊ���꣬��ȷ��2�·ݵ�����
				int month = (Integer) monthComboBox.getSelectedItem();// ���ѡ�е��·�
				if (month == 2) {// ���ѡ�е�Ϊ2��
					int itemCount = dayComboBox.getItemCount();// ����������˵���ǰ������
					if (itemCount != daysOfMonth[2]) {// ����������˵���ǰ������������2�·ݵ�����
						if (itemCount == 28)// ����������˵���ǰ������Ϊ28��
							dayComboBox.addItem(29);// �����Ϊ29��
						else
							// �����������˵���ǰ��������Ϊ29��
							dayComboBox.removeItem(29);// �����Ϊ28��
					}
				}
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
		monthComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int month = (Integer) monthComboBox.getSelectedItem();// ���ѡ�е��·�
				int itemCount = dayComboBox.getItemCount();// ����������˵���ǰ������
				while (itemCount != daysOfMonth[month]) {// ����������˵���ǰ������������ѡ���·ݵ�����
					if (itemCount > daysOfMonth[month]) {// �������ѡ���·ݵ�����
						dayComboBox.removeItem(itemCount);// ���Ƴ����һ��ѡ����
						itemCount--;// �����������˵���ǰ��������1
					} else {// ����С��ѡ���·ݵ�����
						itemCount++;// ���������˵���ǰ��������1
						dayComboBox.addItem(itemCount);// �����Ϊѡ����
					}
				}
			}
		});
		panel.add(monthComboBox);

		final JLabel monthLabel = new JLabel();
		monthLabel.setText("��");
		panel.add(monthLabel);

		//�����������б�
		dayComboBox = new JComboBox();
		dayComboBox.setMaximumRowCount(10);
		int days = daysOfMonth[month];
		for (int d = 1; d <= days; d++) {
			dayComboBox.addItem(d);
		}
		dayComboBox.setSelectedItem(day);
		panel.add(dayComboBox);

		final JLabel dayLabel = new JLabel();
		dayLabel.setText("��    ");
		panel.add(dayLabel);

		//�ύ��ť
		final JButton submitButton = new JButton();
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//------------  �ڴ�д���� ------
				JOptionPane.showMessageDialog(null, "��ѯ�������������ʵ�");
//				tableValueV.removeAllElements();
//				int year = (Integer) yearComboBox.getSelectedItem();
//				int month = (Integer) monthComboBox.getSelectedItem();
//				int day = (Integer) dayComboBox.getSelectedItem();
//				int columnCount = tableColumnV.size();
				
				//��ѯ�������������嵥 sOrderFormOfDay
				
				//��ʾ����
				
				//������	
				Container contentPane = getContentPane();
				contentPane.remove(1);
				contentPane.add(new FixedColumnTablePanel(tableColumnV,
						tableValueV, 4), BorderLayout.CENTER);
				SwingUtilities.updateComponentTreeUI(contentPane);
			}
		});
		submitButton.setText("ȷ��");
		panel.add(submitButton);

		tableColumnV = new Vector<String>();
		tableColumnV.add("���");
		tableColumnV.add("̨��");
		tableColumnV.add("��̨ʱ��");
		tableColumnV.add("���ѽ��");
		//----------- �������Ӳ�Ʒѡ��
		//----------- �ڴ�д����
		
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
