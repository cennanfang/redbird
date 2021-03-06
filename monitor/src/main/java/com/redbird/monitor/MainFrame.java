package com.redbird.monitor;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SpringLayout;

import com.redbird.monitor.action.UIActionExcutor;
import com.redbird.monitor.action.UIActionHandler;
import com.redbird.monitor.config.Configuration;

/**
 * 
 * @author redbird
 *
 */
public class MainFrame {

	private JFrame frame;
	private Monitor monitor;
	private JTextField dbHostField;
	private JTextField dbUserField;
	private JPasswordField dbPasswdField;
	private Configuration config;
	private UIActionHandler uiActionHandler;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame window = new MainFrame();
					window.frame.setVisible(true);
					window.monitor = new Monitor(window.config);
					// 启动监听线程
					new Thread(window.monitor).start();
					window.frame.addWindowListener(new java.awt.event.WindowAdapter() {
						public void windowClosing(java.awt.event.WindowEvent evt) {
							window.exit();
							window.frame.setVisible(false);
							System.exit(0);
						}
					});

					window.frame.setTitle("来文处理提醒");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	protected void exit() {
		if (monitor != null) {
			monitor.stopAction();
		}
	}

	/**
	 * Create the application.
	 */
	public MainFrame() {
		this.config = new Configuration();
		this.config.init();
		this.uiActionHandler = new UIActionExcutor();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		frame.getContentPane().setLayout(springLayout);

		JLabel label = new JLabel("数据库连接信息：");
		springLayout.putConstraint(SpringLayout.NORTH, label, 10, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, label, 10, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(label);

		JLabel label_1 = new JLabel("　主机地址");
		springLayout.putConstraint(SpringLayout.NORTH, label_1, 22, SpringLayout.SOUTH, label);
		springLayout.putConstraint(SpringLayout.WEST, label_1, 10, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, label_1, -199, SpringLayout.SOUTH, frame.getContentPane());
		frame.getContentPane().add(label_1);

		dbHostField = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, dbHostField, -3, SpringLayout.NORTH, label_1);
		springLayout.putConstraint(SpringLayout.WEST, dbHostField, 12, SpringLayout.EAST, label_1);
		springLayout.putConstraint(SpringLayout.EAST, dbHostField, 165, SpringLayout.EAST, label_1);
		frame.getContentPane().add(dbHostField);
		dbHostField.setColumns(10);

		JLabel label_2 = new JLabel("　登陆账号");
		springLayout.putConstraint(SpringLayout.NORTH, label_2, 19, SpringLayout.SOUTH, label_1);
		springLayout.putConstraint(SpringLayout.WEST, label_2, 10, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, label_2, -162, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, label_2, 76, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(label_2);

		dbUserField = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, dbUserField, 15, SpringLayout.SOUTH, dbHostField);
		springLayout.putConstraint(SpringLayout.WEST, dbUserField, 0, SpringLayout.WEST, dbHostField);
		springLayout.putConstraint(SpringLayout.EAST, dbUserField, 0, SpringLayout.EAST, dbHostField);
		frame.getContentPane().add(dbUserField);
		dbUserField.setColumns(10);

		JLabel label_3 = new JLabel("　登陆密码");
		springLayout.putConstraint(SpringLayout.NORTH, label_3, 121, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, label_3, 0, SpringLayout.WEST, label);
		springLayout.putConstraint(SpringLayout.EAST, label_3, 0, SpringLayout.EAST, label_1);
		frame.getContentPane().add(label_3);

		dbPasswdField = new JPasswordField();
		springLayout.putConstraint(SpringLayout.NORTH, dbPasswdField, 14, SpringLayout.SOUTH, dbUserField);
		springLayout.putConstraint(SpringLayout.WEST, dbPasswdField, 0, SpringLayout.WEST, dbHostField);
		springLayout.putConstraint(SpringLayout.EAST, dbPasswdField, 0, SpringLayout.EAST, dbHostField);
		frame.getContentPane().add(dbPasswdField);

		JLabel label_4 = new JLabel("操          作：");
		springLayout.putConstraint(SpringLayout.WEST, label_4, 0, SpringLayout.WEST, label);
		frame.getContentPane().add(label_4);

		JButton dbConnTestBtn = new JButton("连接测试");
		springLayout.putConstraint(SpringLayout.WEST, dbConnTestBtn, 36, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, dbConnTestBtn, -10, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, label_4, -13, SpringLayout.NORTH, dbConnTestBtn);
		springLayout.putConstraint(SpringLayout.EAST, dbConnTestBtn, 11, SpringLayout.EAST, label);
		dbConnTestBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("点击：　连接测试");
				uiActionHandler.onClickDBConnBtn();
			}
		});
		frame.getContentPane().add(dbConnTestBtn);

		JButton playMiusicBtn = new JButton("播放测试");
		springLayout.putConstraint(SpringLayout.WEST, playMiusicBtn, 38, SpringLayout.EAST, dbConnTestBtn);
		springLayout.putConstraint(SpringLayout.SOUTH, playMiusicBtn, -10, SpringLayout.SOUTH, frame.getContentPane());
		frame.getContentPane().add(playMiusicBtn);
		playMiusicBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean flag;
				if ("播放测试".equals(playMiusicBtn.getText())) {
					playMiusicBtn.setText("关闭测试");
					flag = true;
				} else {
					playMiusicBtn.setText("播放测试");
					flag = false;
				}
				uiActionHandler.onClickPlayBtn(config, flag);
			}
		});

		JLabel label_5 = new JLabel("状态信息");
		springLayout.putConstraint(SpringLayout.WEST, label_5, 212, SpringLayout.EAST, label);
		springLayout.putConstraint(SpringLayout.EAST, label_5, -56, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(label_5);

		JLabel label_6 = new JLabel("数据库类型");
		springLayout.putConstraint(SpringLayout.NORTH, label_6, 24, SpringLayout.SOUTH, label_3);
		springLayout.putConstraint(SpringLayout.WEST, label_6, 0, SpringLayout.WEST, label);
		frame.getContentPane().add(label_6);

		Choice dbTypeChoice = new Choice();
		springLayout.putConstraint(SpringLayout.NORTH, dbTypeChoice, 18, SpringLayout.SOUTH, dbPasswdField);
		springLayout.putConstraint(SpringLayout.WEST, dbTypeChoice, 0, SpringLayout.WEST, dbHostField);
		springLayout.putConstraint(SpringLayout.EAST, dbTypeChoice, 0, SpringLayout.EAST, dbHostField);
		frame.getContentPane().add(dbTypeChoice);

		dbTypeChoice.addItem(Configuration.DB_TYPE_SQLSERVER_2000);
		dbTypeChoice.addItem(Configuration.DB_TYPE_MYSQL);

		dbHostField.setText(config.getDbHost());
		dbUserField.setText(config.getDbUser());
		dbPasswdField.setText(config.getDbPasswd());
		dbTypeChoice.select(config.getDbType());

		JButton settingBtn = new JButton("详细配置");
		springLayout.putConstraint(SpringLayout.EAST, playMiusicBtn, -41, SpringLayout.WEST, settingBtn);
		springLayout.putConstraint(SpringLayout.SOUTH, settingBtn, -10, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, settingBtn, 0, SpringLayout.EAST, label_5);
		frame.getContentPane().add(settingBtn);
		settingBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					DetailConfigDialog dialog = new DetailConfigDialog();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setTitle("详细配置");
					dialog.setVisible(true);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		

		JPanel consolePanel = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, consolePanel, 6, SpringLayout.SOUTH, label_5);
		springLayout.putConstraint(SpringLayout.WEST, consolePanel, 17, SpringLayout.EAST, dbHostField);
		springLayout.putConstraint(SpringLayout.SOUTH, consolePanel, 0, SpringLayout.SOUTH, label_4);
		springLayout.putConstraint(SpringLayout.EAST, consolePanel, -10, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(consolePanel);
		consolePanel.setLayout(new BorderLayout());
		
		JTextPane textPane = new JTextPane();
		textPane.setEditable(true);
		textPane.setText("sadfajljfalasdfljal;kjdf;lakjfd;lakjfdl;ajfdl;akjfdl;kajfdl;kjl;kasdjfl;akjdfl;ajfa;sdflkja;lf");
		JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(textPane);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        consolePanel.add(scrollPane, BorderLayout.CENTER);
	}
}
