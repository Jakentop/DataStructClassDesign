package UI;

import java.awt.EventQueue;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

import Mode.Snake;
import Mode.Sets;

import java.awt.Color;
import java.awt.BorderLayout;

public class StartGame {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StartGame window = new StartGame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public StartGame() {
		frame = new JFrame();
		frame.setTitle("贪吃蛇");
		frame.setResizable(false);//每次拖动都会重绘所以固定
		frame.getContentPane().add(new WelPanel(frame));
		//获取屏幕的宽高方便居中显示
		int screenWidth=(int)((int)java.awt.Toolkit.getDefaultToolkit().getScreenSize().width*0.5)-(int)(Sets.map_size*0.5);
        int screenHeight = (int)((int)java.awt.Toolkit.getDefaultToolkit().getScreenSize().height*0.5)-(int)(Sets.map_size*0.5); 
		//界面大小
		frame.setBounds(screenWidth, screenHeight, Sets.map_size, Sets.map_size+20);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// 为当前窗体注册键盘事件
		frame.addKeyListener(new KeyAdapter()
		{
			public void keyPressed(KeyEvent e) {
				// esc返回主界面
				if (e.getKeyCode() == e.VK_ESCAPE)
				{
					frame.getContentPane().removeAll();
					frame.getContentPane().add(new WelPanel(frame));
					frame.getContentPane().setVisible(false);
					frame.getContentPane().setVisible(true);
				}

			}
		});
	}

}
