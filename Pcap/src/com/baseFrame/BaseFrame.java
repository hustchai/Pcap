package com.baseFrame;

import java.awt.Component;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public  class BaseFrame extends JFrame {
	
	private static final long serialVersionUID = -6589116552540201625L;

	/**初始化组件*/
	public  void initComponents() {}
	
	/**初始化事件*/
	public void initListener() {}
	
	/**设置组件的位置*/
	public void setLocation() {}
	
	/**显示错误信息*/
	public void showErrorDialog(Component pC,String message) {
		JOptionPane.showMessageDialog(pC, message, "错误", JOptionPane.ERROR_MESSAGE);
	}
	
	/**显示提示信息*/
	public void showAlertDialog(Component pC,String message) {
		JOptionPane.showMessageDialog(pC, message,"注意",JOptionPane.WARNING_MESSAGE);
	}
	
	/**显示正确信息*/
	public void showRightDialog(Component pC,String message) {
		JOptionPane.showMessageDialog(pC, message, "正确", JOptionPane.PLAIN_MESSAGE);
	}
	
	
}
