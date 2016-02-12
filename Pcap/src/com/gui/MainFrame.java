package com.gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import com.action.MainAction;
import com.action.MainAction2;
import com.action.MainAction3;
import com.baseFrame.BaseFrame;
import com.model.FiveElementNode;
import com.util.ConstantsUtil;
import com.util.WindowUtil;

public class MainFrame extends BaseFrame {
	
	private JPanel mainJPanel;
	private JLabel inputPacpJLabel,outPutTcpUDPJLabel,outPutDataJLable,outPutHTTPJLabel;
	private JTextField inputPacpJTextField,outPutTcpUDPJTextField,outPutDataJTextField,outPutHttpJTextField;
	private JButton inputPacpJButton,outPutTcpUDPJButton,oneJButton,outPutDataJButton,twoJButton,outPutHttpJButton,threeJButton;
	private JScrollPane dataJScrollPane;
	private JTable dataJTable;
	private Table_Model dataTableModel;
	
	Font font=new Font("宋体",Font.PLAIN,20);
	
	
	public MainFrame() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(800, 800);
		setTitle("M201576141柴静宇");
		setVisible(true);
		setResizable(false);
		WindowUtil.centreWindow(this);
		initComponents();
		setLocation();
		initListener();
	}
	
	@Override
	public void initComponents() {
		mainJPanel = new JPanel();
		mainJPanel.setLayout(null);
		this.setContentPane(mainJPanel);
		
		inputPacpJLabel = new JLabel("pcap目录");
		inputPacpJLabel.setFont(font);
		
		inputPacpJTextField = new JTextField(20);
		inputPacpJTextField.setEnabled(false);
		inputPacpJTextField.setFont(font);
		
		inputPacpJButton = new JButton("选择");
		inputPacpJButton.setFont(font);
		
		outPutTcpUDPJLabel = new JLabel("输出目录");
		outPutTcpUDPJLabel.setFont(font);
		
		outPutTcpUDPJTextField = new JTextField(20);
		outPutTcpUDPJTextField.setFont(font);
		outPutTcpUDPJTextField.setEnabled(false);
		
		outPutTcpUDPJButton = new JButton("选择");
		outPutTcpUDPJButton.setFont(font);
		
		outPutDataJLable = new JLabel("输出二");
		outPutDataJLable.setFont(font);
		
		oneJButton = new JButton("按钮一");
		oneJButton.setFont(font);
		
		outPutDataJTextField = new JTextField(20);
		outPutDataJTextField.setFont(font);
		outPutDataJTextField.setEnabled(false);
		
		outPutDataJButton = new JButton("选择");
		outPutDataJButton.setFont(font);
		
		twoJButton = new JButton("按钮二");
		twoJButton.setFont(font);
		
		outPutHTTPJLabel = new JLabel("输出三");
		outPutHTTPJLabel.setFont(font);
		
		outPutHttpJTextField = new JTextField(20);
		outPutHttpJTextField.setEnabled(false);
		outPutHttpJTextField.setFont(font);
		
		outPutHttpJButton = new JButton("选择");
		outPutHttpJButton.setFont(font);
		
		threeJButton = new JButton("按钮三");
		threeJButton.setFont(font);
		
//		dataJScrollPane = new JScrollPane();
		
		
		mainJPanel.add(inputPacpJLabel);
		mainJPanel.add(inputPacpJTextField);
		mainJPanel.add(inputPacpJButton);
		mainJPanel.add(outPutTcpUDPJLabel);
		mainJPanel.add(outPutTcpUDPJTextField);
		mainJPanel.add(outPutTcpUDPJButton);
		mainJPanel.add(oneJButton);
		mainJPanel.add(outPutDataJLable);
		mainJPanel.add(outPutDataJTextField);
		mainJPanel.add(outPutDataJButton);
		mainJPanel.add(twoJButton);
		mainJPanel.add(outPutHTTPJLabel);
		mainJPanel.add(outPutHttpJTextField);
		mainJPanel.add(outPutHttpJButton);
		mainJPanel.add(threeJButton);
//		mainJPanel.add(dataJScrollPane);
	}
	
	@Override
	public void setLocation() {
		inputPacpJLabel.setBounds(50, 20, 100, 20);
		inputPacpJTextField.setBounds(170, 20, 400, 20);
		inputPacpJButton.setBounds(600, 20, 100, 20);
		
		oneJButton.setBounds(650, 60, 100, 20);
		
		outPutTcpUDPJLabel.setBounds(50, 100, 100, 20);
		outPutTcpUDPJTextField.setBounds(170, 100, 400, 20);
		outPutTcpUDPJButton.setBounds(600, 100, 100, 20);
		
		twoJButton.setBounds(650, 140, 100, 20);
		
		outPutDataJLable.setBounds(50, 180, 100, 20);
		outPutDataJTextField.setBounds(170, 180, 400, 20);
		outPutDataJButton.setBounds(600, 180, 100, 20);
		
		outPutHTTPJLabel.setBounds(50, 650, 100, 20);
		outPutHttpJTextField.setBounds(170, 650, 400, 20);
		outPutHttpJButton.setBounds(600, 650, 100, 20);
		
		threeJButton.setBounds(650, 620, 100, 20);
		
//		dataJScrollPane.setBounds(0, 210, 790, 390);
	}
	
	
	@Override
	public void initListener() {
		
		inputPacpJButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				fileChooser.showDialog(new JLabel(), "保存");
				ConstantsUtil.pcapSavePath = fileChooser.getSelectedFile();
				if(ConstantsUtil.pcapSavePath == null)
					return ;
				inputPacpJTextField.setText(ConstantsUtil.pcapSavePath.getAbsolutePath());
			}
		});
		
		
		outPutTcpUDPJButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				fileChooser.showDialog(new JLabel(), "保存");
				ConstantsUtil.outPutSavePath = fileChooser.getSelectedFile();
				if(ConstantsUtil.outPutSavePath == null)
						return ;
				outPutTcpUDPJTextField.setText(ConstantsUtil.outPutSavePath.getAbsolutePath());
			}
		});
		
		oneJButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if(ConstantsUtil.pcapSavePath == null || ConstantsUtil.outPutSavePath == null) {
					showErrorDialog(MainFrame.this, "请选择pcap输入文件或Tcp/UDP输出目录");
					return ;
				}
				
				long start = System.currentTimeMillis();
				MainAction ma = new MainAction();
				 long end = System.currentTimeMillis();
				 showRightDialog(MainFrame.this, "程序执行了：" + ((end - start) / 1000)+"s");
				 System.out.println("程序执行了：" + ((end - start) / 1000)+"s");
				
				 File file = ConstantsUtil.outPutSavePath;
					int len = file.listFiles().length;
					dataTableModel = new Table_Model(len);
					dataJTable = new JTable(dataTableModel);
					
					
					for (int i = 0; i < len; i++) {
						File f = file.listFiles()[i];
						String fileName = f.getName();
						Pattern pattern = Pattern.compile("\\[(\\S+)\\]\\[(\\S+)\\]\\[(\\S+)\\]\\[(\\S+)\\]\\[(\\S+)\\]\\.pcap");
						Matcher matcher = pattern.matcher(fileName);
						if(matcher.matches()) {
							String pro = matcher.group(1);
							String ip1 = matcher.group(2);
							String port1 = matcher.group(3);
							String ip2 = matcher.group(4);
							String port2 = matcher.group(5);
							String[] row = {pro,ip1,port1,ip2,port2};
							dataTableModel.addRow(row);
						}
						
					}
					
					dataJScrollPane = new JScrollPane(dataJTable);
					mainJPanel.add(dataJScrollPane);
					dataJScrollPane.setBounds(0, 210, 790, 390);
					
					
			}
		});
		
		outPutDataJButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				fileChooser.showDialog(new JLabel(), "保存");
				ConstantsUtil.outPutDataPath = fileChooser.getSelectedFile();
				outPutDataJTextField.setText(ConstantsUtil.outPutDataPath.getAbsolutePath());
			}
		});
		
		twoJButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if(ConstantsUtil.outPutDataPath == null || ConstantsUtil.outPutSavePath == null) {
					showErrorDialog(MainFrame.this, "请选择Tcp/UDP输出目录或负载信息输出目录");
					return ;
				}
						long start = System.currentTimeMillis();
						MainAction2 ma2 = new MainAction2();
						long end = System.currentTimeMillis();
						showRightDialog(MainFrame.this, "程序执行了：" + ((end - start) / 1000)+"s");
						System.out.println("程序执行了：" + ((end - start) / 1000)+"s");
			}
		});
		
		outPutHttpJButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				fileChooser.showDialog(new JLabel(), "保存");
				ConstantsUtil.outPutHttpPath = fileChooser.getSelectedFile();
				outPutHttpJTextField.setText(ConstantsUtil.outPutHttpPath.getAbsolutePath());
			}
		});
		
		threeJButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(ConstantsUtil.outPutDataPath == null || ConstantsUtil.outPutHttpPath == null) {
					showErrorDialog(MainFrame.this, "请选择Tcp/UDP输出目录或Http输出目录");
					return ;
				}
				long start = System.currentTimeMillis();
				MainAction3 ma = new MainAction3();
				long end = System.currentTimeMillis();
				showRightDialog(MainFrame.this, "程序执行了：" + ((end - start) / 1000)+"s");
				System.out.println("程序执行了：" + ((end - start) / 1000)+"s");
			}
		});
		
		
	}
	
	public static void main(String[] args) {
		new MainFrame();
	}

	
}
class Table_Model extends AbstractTableModel {
	
	private Vector content = null;
	private String[] title_name = { "协议", "ip1", "port1", "ip2", "port2"  };
	
	public Table_Model(int len) {
		content = new Vector(len);
	}

	public int getRowCount() {
		return content.size();
	}
	
	public void addRow(String[] row) {
		Vector v = new Vector(5);
		v.add(row[0]);
		v.add(row[1]);
		v.add(row[2]);
		v.add(row[3]);
		v.add(row[4]);
		content.add(v);
	}

	public void setValueAt(Object value, int row, int col) {
        ((Vector) content.get(row)).remove(col);
        ((Vector) content.get(row)).add(col, value);
        this.fireTableCellUpdated(row, col);
    }

    public String getColumnName(int col) {
        return title_name[col];
    }

    public int getColumnCount() {
        return title_name.length;
    }


    public Object getValueAt(int row, int col) {
        return ((Vector) content.get(row)).get(col);
    }

    public Class getColumnClass(int col) {
        return getValueAt(0, col).getClass();
    }
	
}