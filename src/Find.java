import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Find {
    JFrame findframe;
    JTextArea resulttext=new JTextArea();//用于显示查询结果
    String result;

    JFrame idframe;//ID查询页面
    Integer ID;

    JFrame priceframe;//价格查询页面
    Double Price;

    JButton returnbotton=new JButton("返回");

    JButton go_on=new JButton("继续操作");
    JButton exit=new JButton("退出");
    JPanel bottonpanel=new JPanel();
    //初始化一些按钮
    public void init(){
        //返回按钮
        returnbotton.addActionListener(new return_Listener());

        //继续操作以及退出按钮
        go_on.addActionListener(new go_on_Listener());
        exit.addActionListener(new exit_Listener());
        bottonpanel.add(go_on);
        bottonpanel.add(exit);

        //用于显示结果，不可编辑
        resulttext.setEditable(false);
    }

    //查询全部商品信息并显示结果
    public void findall(){
        init();
        findframe=new JFrame("查询结果");
        result="ID\t商品名称\t商品价格\t商品简介\t\r\n";
        for(int i=0;i<Interface.goodsList.size();i++){
            Goods goods=Interface.goodsList.get(i);
            //添加查询结果
            result+=goods.getId()  + "\t" +goods.getName()+ "\t" +goods.getPrice() +"\t" +goods.getIntrduction()+ "\t\r\n";
        }
        resulttext.setText(result);

        //完成页面布局并显示结果
        findframe.getContentPane().add(BorderLayout.CENTER,resulttext);
        findframe.getContentPane().add(BorderLayout.SOUTH,bottonpanel);
        findframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        findframe.setSize(500,500);
        findframe.setVisible(true);
    }

    //通过ID查询商品
    public void findid(){
        init();
        findframe=new JFrame("根据ID查询");
        JPanel panel=new JPanel();

        //输入ID
        JLabel id_lable=new JLabel("商品ID");
        panel.add(id_lable);
        JTextField id_text=new JTextField(20);
        panel.add(id_text);

        //提交按钮
        JButton submit=new JButton("查询");
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                findframe.dispose();
                new Find().findbyid(id_text.getText());
            }
        });
        panel.add(submit);

        findframe.getContentPane().add(BorderLayout.NORTH,panel);
        findframe.getContentPane().add(BorderLayout.SOUTH,returnbotton);
        findframe.setSize(500,500);
        findframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        findframe.setVisible(true);
    }


    //通过价格查询，接收用户输入
    public void findprice(){
        init();
        findframe=new JFrame("查询价格低于输入的商品");
        JPanel panel=new JPanel();

        //输入price
        JLabel price_lable=new JLabel("价格");
        panel.add(price_lable);
        JTextField price_text=new JTextField(20);
        panel.add(price_text);

        //提交按钮
        JButton submit=new JButton("查询");
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                findframe.dispose();
                new Find().findbyprice(price_text.getText());
            }
        });
        panel.add(submit);

        findframe.getContentPane().add(BorderLayout.NORTH,panel);
        findframe.getContentPane().add(BorderLayout.SOUTH,returnbotton);
        findframe.setSize(500,500);
        findframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        findframe.setVisible(true);

    }



    //接收输入ID并进行查询，同时显示结果
    public void findbyid(String id){
        init();
        boolean flag=false;//标记是否拥有查询结果
        boolean flag_=false;//用于判断异常类型
        result="ID\t商品名称\t商品价格\t商品简介\t\r\n";

        try{
            ID=Integer.parseInt(id);
        }catch (NumberFormatException numberFormatException){
            flag_=true;
        }//判断输入是否合理

        for(int i=0;i<Interface.goodsList.size();i++){
            Goods goods=Interface.goodsList.get(i);
            if(ID.equals(goods.getId())) {
                flag=true;
                result += goods.getId() + "\t" + goods.getName() + "\t" + goods.getPrice() + "\t" + goods.getIntrduction() + "\t\r\n";
            }
        }//进行查询

        if(flag){
            //查询成功显示结果
            idframe=new JFrame("查询结果");
            resulttext.setText(result);

            idframe.getContentPane().add(BorderLayout.CENTER,resulttext);
            idframe.getContentPane().add(BorderLayout.SOUTH,bottonpanel);
            idframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            idframe.setSize(500,500);
            idframe.setVisible(true);
        }else {
            //出现异常，判断异常类型
            if(flag_){
                idframe=new JFrame("ID只能包含数字字符");
            }else {
                idframe=new JFrame("没有找到该商品");
            }
            idframe.getContentPane().add(BorderLayout.NORTH,bottonpanel);
            idframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            idframe.setSize(500,500);
            idframe.setVisible(true);
        }
    }


    //查询价格低于输入值的商品并显示结果
    public void findbyprice(String price){
        init();
        boolean flag=false;//标记是否拥有查询结果
        boolean flag_=false;//判断异常类型
        result="ID\t商品名称\t商品价格\t商品简介\t\r\n";

        try{
            Price=Double.parseDouble(price);
        }catch (NumberFormatException numberFormatException){
            flag_=true;
        }//判断输入是否合理

        for(int i=0;i<Interface.goodsList.size();i++){
            Goods goods=Interface.goodsList.get(i);
            if(goods.getPrice()<=Price) {
                flag=true;
                result += goods.getId() + "\t" + goods.getName() + "\t" + goods.getPrice() + "\t" + goods.getIntrduction() + "\t\r\n";
            }
        }//进行查询

        if(flag){
            //查询成功显示结果
            priceframe=new JFrame("查询结果");
            resulttext.setText(result);

            priceframe.getContentPane().add(BorderLayout.CENTER,resulttext);
            priceframe.getContentPane().add(BorderLayout.SOUTH,bottonpanel);
            priceframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            priceframe.setSize(500,500);
            priceframe.setVisible(true);
        }else {
            //出现异常，判断异常类型
           if(flag_){
               priceframe=new JFrame("价格应为小数或整数");
           }else {
               priceframe=new JFrame("没有找到满足条件的商品");
           }
           priceframe.getContentPane().add(BorderLayout.NORTH,bottonpanel);
           priceframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
           priceframe.setSize(500,500);
           priceframe.setVisible(true);
        }

    }

    class return_Listener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(findframe!=null){
                findframe.dispose();
            }
            new Interface().reselect(e);
        }
    }

    class go_on_Listener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(findframe!=null){
                findframe.dispose();
                new Interface().go();
            }
            if(idframe!=null){
                idframe.dispose();
                new Find().findid();
            }
            if(priceframe!=null){
                priceframe.dispose();
                new Find().findprice();
            }
        }
    }

    class exit_Listener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(-1);
        }
    }

}

