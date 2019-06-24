package UI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import AI.SnakeAi;
import Mode.Node;
import Mode.Snake;
import Mode.Sets;

//这是窗体中的容器类
public class SnakePanel extends JPanel implements Runnable {

	Snake snake;
	/** AI */
	SnakeAi ai;
	/** 当前的父类容器，用于绑定事件 */
	JFrame father;
	/** 是否停止了 */
	boolean IsEnd = false;
	/** 是否为play模式 */
	boolean IsPlay = false;
	/** dir方向 */
	int dir = 6;// dir默认向左走
	/**
	 * Create the panel.
	 */
	// 创建了一个属性
	public SnakePanel(JFrame father) {
		this.father = father;
		snake = new Snake();
		Node n = new Node(10, 10);// 蛇的初始位置
		snake.getS().add(n);
		snake.setFirst(n);
		snake.setLast(n);
		snake.setTail(new Node(0, 10));// last的后一个节点
		snake.setFood(new Node(80, 80));// 食物初始位置
		ai = new SnakeAi();
	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		g.setColor(Color.orange);
		g.drawRect(10, 10, Snake.map_size, Snake.map_size);// 地图范围

		g.setColor(Color.white);
		paintSnake(g, snake);

		g.setColor(Color.white);
		paintFood(g, snake.getFood());

		// 建立play模式由于此模式无需AI层返回数据，所以我们直接建立在了视图层中，当前调用当前上下文

		if (IsPlay) {
	    //将当前上下文传递
		SnakePanel sthis=this;	
		//为当前窗体注册键盘事件
		father.addKeyListener(new KeyAdapter()
		{
			public void keyPressed(KeyEvent e) {
				//上
				if(e.getKeyCode()==e.VK_UP&&dir!=2)
				{
					dir=8;
					sthis.repaint();
					System.out.println("上");
				}

				//右
				if(e.getKeyCode()==e.VK_RIGHT&&dir!=4)
				{
					dir=6;
					if(snake.getFirst().getX()>=Snake.map_size)
					{
						dir=-1;
					}
					else
					sthis.repaint();
					System.out.println("右");
				}

				//下
				if(e.getKeyCode()==e.VK_DOWN&&dir!=8)
				{
					dir=2;
					sthis.repaint();
					System.out.println("下");
				}

				//左
				if(e.getKeyCode()==e.VK_LEFT&&dir!=6)
				{
					dir=4;
					sthis.repaint();
					System.out.println("左");
				}
				

			}
			
		
		});
		//#region 判断是否撞边
		switch(dir)
		{
			case 8://上
			if(snake.getFirst().getY()<=10)
			{
				dir=-1;
			}
			break;
			case 6://右
			if(snake.getFirst().getX()>=Snake.map_size)
			{
				dir=-1;
			}
			break;
			case 2://下
			if(snake.getFirst().getY()>=Snake.map_size)
			{
				dir=-1;
			}
			break;
			case 4://左
			if(snake.getFirst().getX()<=10)
			{
				dir=-1;
			}
			break;
		}
//#endregion

//#region 判断是否撞自己
		if(snake.getS().indexOf(snake.getFirst())!=0)
		{
			dir=-1;
		}
//#endregion
		} else {
			// 读取设置来选择策略：play ,play1,play2
			switch(Sets.show_mode)
			{
				case 0:dir = ai.play(snake, snake.getFood());break;
				case 1:dir = ai.play1(snake, snake.getFood());break;
				case 2:dir = ai.play2(snake, snake.getFood());break;
			}
		}

		if (dir == -1) {
			System.out.println("GG");// 结束的提示框！
			IsEnd = true;// 在下次重绘时便会结束
		} else {
			snake.move(dir);
		}
	}

	/**
	 * 画蛇
	 * 
	 * @param g
	 * @param snake
	 */
	public void paintSnake(Graphics g, Snake snake) {
		for (Node n : snake.getS()) {
			if (n.toString().equals(snake.getFirst().toString())) {
				g.setColor(Sets.snk_first_color);// 为了方便看蛇头颜色
			}
			if (n.toString().equals(snake.getLast().toString())
					&& !snake.getFirst().toString().equals(snake.getLast().toString())) {
				g.setColor(Sets.snk_last_color);// 蛇尾蓝色
			}
			g.fillRect(n.getX(), n.getY(), Snake.size, Snake.size);
			g.setColor(Sets.snk_body_color);// 蛇身白色
		}
	}

	/**
	 * 画食物
	 * 
	 * @param g
	 * @param food
	 */
	public void paintFood(Graphics g, Node food) {
		g.setColor(Sets.food_color);
		g.fillOval(food.getX(), food.getY(), snake.size, snake.size);
	}

	@Override
	public void run() {
		while (!IsEnd) {
			// TODO Auto-generated method stub
			try {
				Thread.sleep(IsPlay?Sets.get_sleep_time(1)+200:Sets.get_sleep_time(1));// 延迟速度
				this.repaint();
				this.setFocusable(true);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		JOptionPane.showMessageDialog(null, "炸了！", "我有点转不过弯┭┮﹏┭┮", JOptionPane.ERROR_MESSAGE);
		father.getContentPane().removeAll();
		father.getContentPane().add(new WelPanel(father));
		father.getContentPane().setVisible(false);
		father.getContentPane().setVisible(true);
	}
}
