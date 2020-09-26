import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Delete {
    JFrame deleteframe;
    int count;//记录删除条数
    Integer ID;
    JFrame deletebyidframe;

    JLabel delete_label;//用来显示删除记录条数

    JButton returnbotton=new JButton("返回");

    JButton go_on=new JButton("继续操作");
    JButton exit=new JButton("退出");
    JPanel bottonpanel=new JPanel();//用来添加继续操作和退出按钮


    //初始化一些按钮
    public void init(){
        //返回按钮
        returnbotton.addActionListener(new Delete.return_Listener());

        //继续操作以及退出按钮
        go_on.addActionListener(new Delete.go_on_Listener());
        exit.addActionListener(new Delete.exit_Listener());
        bottonpanel.add(go_on);
        bottonpanel.add(exit);
    }


    //删除所有商品
    public void deleteall(){
        init();
        count=0;
        deleteframe=new JFrame("删除结果");

        count=Interface.goodsList.size();
        if(count!=0){
            Interface.goodsList.clear();//清除goodList
            delete_label=new JLabel("成功删除"+count+ "条商品信息");
        }else{
            delete_label=new JLabel("没有商品信息");
        }

        //完成页面布局
        deleteframe.getContentPane().add(BorderLayout.NORTH,delete_label);
        deleteframe.getContentPane().add(BorderLayout.SOUTH,bottonpanel);
        deleteframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        deleteframe.setSize(500,500);
        deleteframe.setVisible(true);
    }

    //通过ID删除商品
    public void deleteid(){
        init();
        deleteframe=new JFrame("根据ID删除");
        JPanel panel=new JPanel();

        //接收输入
        JLabel id_lable=new JLabel("商品ID");
        panel.add(id_lable);
        JTextField id_text=new JTextField(20);
        panel.add(id_text);

        //提交按钮
        JButton submit=new JButton("删除");
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteframe.dispose();
                new Delete().deletebyid(id_text.getText());
            }
        });
        panel.add(submit);

        //完成页面布局
        deleteframe.getContentPane().add(BorderLayout.NORTH,panel);
        deleteframe.getContentPane().add(BorderLayout.SOUTH,returnbotton);
        deleteframe.setSize(500,500);
        deleteframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        deleteframe.setVisible(true);

    }

    public void deletebyid(String id){
        init();
        count=0;//记录删除信息条数
        boolean flag=false;//判断异常类型
        try{
            ID=Integer.parseInt(id);
        }catch (NumberFormatException numberFormatException){
            flag=true;
        }//判断输入是否合理

        for(int i=0;i<Interface.goodsList.size();i++){
            Goods goods=Interface.goodsList.get(i);
            if(ID.equals(goods.getId())) {
                Interface.goodsList.remove(i);
                count++;
            }
        }//进行查询

        if(count!=0){
            //查询成功显示结果
            JLabel delete_label=new JLabel("成功删除"+count+ "条商品信息");

            deletebyidframe=new JFrame("删除结果");

            deletebyidframe.getContentPane().add(BorderLayout.NORTH,delete_label);
            deletebyidframe.getContentPane().add(BorderLayout.SOUTH,bottonpanel);
            deletebyidframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            deletebyidframe.setSize(500,500);
            deletebyidframe.setVisible(true);
        }else {
            if(flag){
                //ID输入不合理
                deletebyidframe=new JFrame("ID只能包含数字字符");
            }else {
                //ID输入合理但未找到商品
                deletebyidframe=new JFrame("该商品不存在");
            }

            deletebyidframe.getContentPane().add(BorderLayout.NORTH,bottonpanel);
            deletebyidframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            deletebyidframe.setSize(500,500);
            deletebyidframe.setVisible(true);
        }
    }

    class return_Listener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(deleteframe!=null){
                deleteframe.dispose();
            }
            new Interface().redelete(e);
        }
    }

    class go_on_Listener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(deleteframe!=null){
                deleteframe.dispose();
                new Interface().go();
            }
            if(deletebyidframe!=null){
                deletebyidframe.dispose();
                new Delete().deleteid();
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
