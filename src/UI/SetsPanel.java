package UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

import javax.swing.*;

import Mode.Sets;

public class SetsPanel extends JPanel {
    public SetsPanel(JFrame father) {
        super();
        // 设置界面大小
        this.setSize(500, 600);
        // 设置界面布局
        this.setLayout(new BorderLayout());

        // 标题布局
        this.add(new JLabel("设置", JLabel.CENTER), BorderLayout.NORTH);

        // 配置中央Panel放置 设置的主体
        JPanel pmain = new JPanel();

        // 统一的网格内布局
        GridBagConstraints con = new GridBagConstraints();
        GridBagLayout layout = new GridBagLayout();

        pmain.setLayout(layout);

        JLabel label;

        // #regionregion 设置标签和对应输入框
        label = new JLabel("演示模式:");
        con.ipadx = 100;
        con.insets = new Insets(5, 5, 5, 5);
        con.gridy = 0;
        con.gridx = 0;
        layout.setConstraints(label, con);
        pmain.add(label);
        CombColorLabel _1_mode = new CombColorLabel(2);// 添加combbox
        _1_mode.SetIndex(Sets.show_mode);

        con.gridy = 0;
        con.gridx = 1;
        layout.setConstraints(_1_mode.get(), con);
        pmain.add(_1_mode.get());

        label = new JLabel("蛇身颜色:");
        con.gridy = 1;
        con.gridx = 0;
        layout.setConstraints(label, con);
        pmain.add(label);
        CombColorLabel _2_sbc = new CombColorLabel(0);// 添加combbox
        _2_sbc.SetIndex(Sets.snk_body_color);
        con.gridy = 1;
        con.gridx = 1;
        layout.setConstraints(_2_sbc.get(), con);
        pmain.add(_2_sbc.get());

        label = new JLabel("蛇头颜色:");
        con.gridy = 2;
        con.gridx = 0;
        layout.setConstraints(label, con);
        pmain.add(label);
        CombColorLabel _3_sbc = new CombColorLabel(0);// 添加combbox
        _3_sbc.SetIndex(Sets.snk_first_color);
        con.gridy = 2;
        con.gridx = 1;
        layout.setConstraints(_3_sbc.get(), con);
        pmain.add(_3_sbc.get());

        label = new JLabel("蛇尾颜色:");
        con.gridy = 3;
        con.gridx = 0;
        layout.setConstraints(label, con);
        pmain.add(label);
        CombColorLabel _4_sbc = new CombColorLabel(0);// 添加combbox
        _4_sbc.SetIndex(Sets.snk_last_color);
        con.gridy = 3;
        con.gridx = 1;
        layout.setConstraints(_4_sbc.get(), con);
        pmain.add(_4_sbc.get());

        label = new JLabel("食物颜色:");
        con.gridy = 4;
        con.gridx = 0;
        layout.setConstraints(label, con);
        pmain.add(label);
        CombColorLabel _5_sbc = new CombColorLabel(0);// 添加combbox
        _5_sbc.SetIndex(Sets.food_color);
        con.gridy = 4;
        con.gridx = 1;
        layout.setConstraints(_5_sbc.get(), con);
        pmain.add(_5_sbc.get());

        label = new JLabel("机器模式蛇运行速度:");
        con.gridy = 5;
        con.gridx = 0;
        layout.setConstraints(label, con);
        pmain.add(label);
        CombColorLabel _6_sbc = new CombColorLabel(1);// 添加combbox
        _6_sbc.SetIndex(Sets.get_sleep_time(0));
        con.gridy = 5;
        con.gridx = 1;
        layout.setConstraints(_6_sbc.get(), con);
        pmain.add(_6_sbc.get());

        // 添加设置主题到设置面板中
        this.add(pmain, BorderLayout.CENTER);

        // #endregion

        // #region 添加提交、重置、返回按钮

        // 设置界面布局
        JPanel pfun = new JPanel();
        pfun.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 35));
        // 配置Button
        JButton submit = new JButton("提交");
        submit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Sets.show_mode =(int)_1_mode.GetSelected();
                Sets.snk_body_color = (Color) _2_sbc.GetSelected();
                Sets.snk_first_color = (Color) _3_sbc.GetSelected();
                Sets.snk_last_color = (Color) _4_sbc.GetSelected();
                Sets.food_color = (Color) _5_sbc.GetSelected();
                Sets.set_sleep_time((int) _6_sbc.GetSelected());
                //执行保存外部文件
                if(new Sets().SaveData())
                {
                    JOptionPane.showMessageDialog(null, "更新成功(由于我们懒所以不会保存设置)", "完成", JOptionPane.PLAIN_MESSAGE);
                }

                

            }
        });
        pfun.add(submit);

        JButton reset = new JButton("重置");
        reset.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                _1_mode.SetIndex(Sets.show_mode);
                _2_sbc.SetIndex(Sets.snk_body_color);
                _3_sbc.SetIndex(Sets.snk_first_color);
                _4_sbc.SetIndex(Sets.snk_last_color);
                _5_sbc.SetIndex(Sets.food_color);
                _6_sbc.SetIndex(Sets.get_sleep_time(0));
            }
        });
        pfun.add(reset);

        JButton cancel = new JButton("返回");
        cancel.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // ...关闭界面方法！
                 //创建Wel容器
                 WelPanel Wel=new WelPanel(father);
                 father.getContentPane().removeAll();
                 father.getContentPane().add(Wel);
                 father.setVisible(false);
                 father.setVisible(true);

            }
        });
        pfun.add(cancel);

        this.add(pfun, BorderLayout.SOUTH);

        // #endregion

    }

}

class CombColorLabel {
    // 模式
    private int mode;
    // 值的索引
    private Object[] listVal;
    // 该对象中的下拉框
    private JComboBox<String> Comb;

    /**
     * mode负责传入comb的类型
     * 
     * @param mode
     */
    public CombColorLabel(int mode) {
        this.mode = mode;
        String[] listData;
        if (mode == 0) {
            listData = new String[] { "红", "黄", "蓝", "绿", "白" };
            this.listVal = new Object[] { Color.red, Color.yellow, Color.blue, Color.green, Color.white };
        } else if (mode == 1) {
            listData = new String[] { "快", "正常", "慢", "非常慢" };
            this.listVal = new Object[] { 0, 1, 2, 3 };
        }
            else if(mode ==2)
            {
            listData = new String[] { "最初版本", "改进1", "改进2"};
            this.listVal = new Object[] { 0, 1, 2};
            }
         else
            listData = null;

        this.Comb = new JComboBox<String>();
        // 添加默认值
        for (String t : listData) {
            Comb.addItem(t);
        }

    }

    /**
     * 获取当前的JComboBox对象
     * 
     * @return
     */
    public JComboBox<String> get() {
        return Comb;
    }

    /**
     * 负责封装了设置当前Com的方法
     * 
     * @param obj
     */
    public void SetIndex(Object obj) {
        int i = 0;
        for (i = 0; i < this.listVal.length; i++) {
            if (obj == this.listVal[i]) {
                break;
            }
        }
        if(i==this.listVal.length) i=this.listVal.length-1;
        this.Comb.setSelectedIndex(i);
    }

    public Object GetSelected() {
        return this.listVal[this.Comb.getSelectedIndex()];
    }

}
