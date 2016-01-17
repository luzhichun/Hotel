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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import sun.swing.SwingUtilities2;

import com.icss.mwing.FixedColumnTablePanel;
import com.icss.mwing.MTable;
import com.icss.tool.Today;

public class YearDialog extends JDialog {

	private JTable table;

	private Vector<String> tableColumnV;

	private Vector<Vector<Object>> tableValueV;

	private DefaultTableModel tableModel;

	private JComboBox yearComboBox;


	/**
	 * Launch the application
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			YearDialog dialog = new YearDialog();
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
	public YearDialog() {
		super();
		setModal(true);
		setTitle("�����");
		setBounds(60, 60, 860, 620);

		final JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);

		int year = Today.getYEAR();
		
		yearComboBox = new JComboBox();
		yearComboBox.setMaximumRowCount(10);
		
		/*------------  �������б��е�����Ҫ��ϵͳ���綨��������ݵ�ϵͳ��ǰʱ���
		 *------------  ���磺���綨������Ϊ2012-1-1,��ǰϵͳʱ��Ϊ2014-10-2,
		 *------------  ���������б�Ҫ��ʾ 2012,2013,2014
		*/
		//------------  �ڴ�д���� ------
	
		//�趨��ǰ��Ϊѡ��״̬
		yearComboBox.setSelectedItem(year);
		panel.add(yearComboBox);

		final JLabel yearLabel = new JLabel();
		yearLabel.setText("��    ");
		panel.add(yearLabel);

		final JButton submitButton = new JButton();
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tableValueV.removeAllElements();
				//---------- �ڴ�д����
				
				//������
				Container contentPane = getContentPane();
				contentPane.remove(1);
				getContentPane().add(new FixedColumnTablePanel(tableColumnV,
										tableValueV, 1), BorderLayout.CENTER);
				SwingUtilities.updateComponentTreeUI(contentPane);
			}
		});
		submitButton.setText("ȷ��");
		panel.add(submitButton);

		tableColumnV = new Vector<String>();
		tableColumnV.add("����");
		for (int i = 1; i <= 12; i++) {
			tableColumnV.add(i + " ��");
		}
		tableColumnV.add("�ܼ�");

		tableValueV = new Vector<Vector<Object>>();

		getContentPane().add(
				new FixedColumnTablePanel(tableColumnV, tableValueV, 1),
				BorderLayout.CENTER);
		//
	}

}
