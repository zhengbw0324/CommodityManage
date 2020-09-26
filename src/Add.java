import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.text.html.parser.DTDConstants.ID;
//执行添加商品信息的类
public class Add {
    JFrame errframe;//出错页面
    JFrame sucframe;//添加成功页面
    String errstring="";//异常提示
    Integer ID;
    Double numprice;
    JPanel panel;

    public void addgoods(String id,String name,String price,String introduction) {

        try {
            ID = Integer.parseInt(id);
        } catch (NumberFormatException numberFormatException) {
            errstring += "ID只能包含数字字符;";
        }//判断id输入是否合理

        for(int i=0;i<Interface.goodsList.size();i++){
            if(Interface.goodsList.get(i).getId()==ID){
                errstring += "  ID已存在; ";
            }
        }//判断当前ID是否存在

        if(name==null){
            errstring+="    商品名称不能为空;";
        }//商品名称不可为空

        try {
            numprice=Double.parseDouble(price);
        } catch (NumberFormatException numberFormatException) {
            errstring += "  价格应为小数或整数;";
        }//判断商品价格输入是否合法

        if (errstring != "") {
            //若异常提示不为空则添加出错
            errframe = new JFrame("添加商品出错");
            JLabel errlabel = new JLabel(errstring);//显示出错信息

            //重新输入按钮
            JButton readd = new JButton("重新输入");
            readd.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    errframe.dispose();
                    new Interface().readd(e);
                }
            });

            //完成页面布局
            errframe.getContentPane().add(BorderLayout.NORTH,errlabel);
            errframe.getContentPane().add(BorderLayout.SOUTH,readd);
            errframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            errframe.setSize(500,500);
            errframe.setVisible(true);
        }else {
            //没有异常提示，可以进行添加操作
            Goods goods=new Goods(ID,name,numprice,introduction);

            if(!Interface.goodsList.add(goods)){
                System.out.println("添加失败");
                return;
            }

            sucframe=new JFrame("添加成功");

            //完成添加后继续操作或退出
            JButton go_on=new JButton("继续操作");
            JButton exit=new JButton("退出");

            go_on.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    sucframe.dispose();
                    new Interface().go();
                }
            });

            exit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.exit(-1);
                }
            });
            //将两个按钮添加至面板
            panel=new JPanel();
            panel.add(go_on);
            panel.add(exit);

            //完成页面布局
            sucframe.getContentPane().add(BorderLayout.NORTH,panel);
            sucframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            sucframe.setSize(500,500);
            sucframe.setVisible(true);
        }
    }

}
