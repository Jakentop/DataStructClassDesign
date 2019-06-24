package UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Mode.Sets;

public class WelPanel extends JPanel {

    public WelPanel(JFrame father) {
        // 调用父类构造
        super();
        // 设置界面大小
        this.setSize(500, 600);
        // 设置为绝对布局
        this.setLayout(null);

        // 添加组件

        // #region

        // #region 图片
        JLabel img = new JLabel(GetPic("./src/Img/img.png"));
        img.setBounds(91, 91, 82, 86);
        this.add(img);
        // #endregion

        // #region 标题
        JLabel title = new JLabel(GetPic("./src/Img/title.png"));
        title.setBounds(222, 70, 176, 127);
        this.add(title);
        // #endregion

        // #region show
        JLabel show = new JLabel(GetPic("./src/Img/show.png"));
        show.setBounds(97, 270, 88, 43);
        // 添加事件
        show.addMouseListener(new MouseListener() {

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // 离开按钮
                show.setIcon(GetPic("./src/Img/show.png"));
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                // 放到按钮上
                show.setIcon(GetPic("./src/Img/show1.png"));
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                // 点击
                SnakePanel panel = new SnakePanel(father);
                new Thread(panel).start(); 
                panel.setBackground(Color.BLACK);
                father.getContentPane().removeAll();
                father.getContentPane().add(panel, BorderLayout.CENTER);
                father.getContentPane().setVisible(false);
                father.getContentPane().setVisible(true);
            }
        });
        this.add(show);
        // #endregion

        // #region paly
        JLabel play = new JLabel(GetPic("./src/Img/play.png"));
        play.setBounds(99, 393, 82, 43);
        // 添加事件
        play.addMouseListener(new MouseListener() {

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // 离开按钮
                play.setIcon(GetPic("./src/Img/play.png"));
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                // 放到按钮上
                play.setIcon(GetPic("./src/Img/play1.png"));
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                // 点击
                SnakePanel panel = new SnakePanel(father);
                panel.IsPlay=true;
                new Thread(panel).start(); 
                panel.setBackground(Color.BLACK);
                father.getContentPane().removeAll();
                father.getContentPane().add(panel, BorderLayout.CENTER);
                father.getContentPane().setVisible(false);
                father.getContentPane().setVisible(true);
            }
        });

        this.add(play);

        // #endregion

        // #region set
        JLabel set = new JLabel(GetPic("./src/Img/set.png"));
        set.setBounds(306, 343, 57, 43);
        // 添加事件
        set.addMouseListener(new MouseListener() {

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // 离开按钮
                set.setIcon(GetPic("./src/Img/set.png"));
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                // 放到按钮上
                set.setIcon(GetPic("./src/Img/set1.png"));
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                // 点击

                //创建Set容器
                SetsPanel Sets=new SetsPanel(father);
                father.getContentPane().removeAll();
                father.getContentPane().add(Sets);
                father.getContentPane().setVisible(false);
                father.getContentPane().setVisible(true);

            }
        });

        this.add(set);
        // #endregion

        // #endregion

    }

    private ImageIcon GetPic(String path) {
        BufferedImage pic = null;
        try {
            pic = ImageIO.read(new File(path));
        } catch (IOException e) {
            System.out.println("您别调皮了，把图片放回去！！！！！");
            System.out.println("您丢失了，" + path);
            return null;
        }
        
        return new ImageIcon(pic);
    }

}