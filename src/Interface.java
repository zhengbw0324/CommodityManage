import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Interface {
    public static List<Goods> goodsList=new ArrayList<Goods>();
    JFrame frame;//初始界面
    JFrame addframe;//添加操作页面
    JFrame selectframe;//查询操作页面
    JFrame deleteframe;//删除操作页面
    JButton returnbotton;//返回按钮
    JTextField id_text;//添加操作时接收ID输入
    JTextField name_text;//添加操作时接收商品名称
    JTextField price_text;//添加操作时接收商品价格输入
    JTextField introduction_text;//添加操作时接收商品简介输入
    public static void main(String[] args){
        Interface ui = new Interface();
        ui.go();
    }

    //初始界面显示
    public void go(){
        frame=new JFrame("商品信息管理系统");

        //添加、查询、删除三个选项按钮
        JButton addbotton=new JButton("新增商品");
        JButton selectbotton=new JButton("查询商品信息");
        JButton deletebotton=new JButton("删除商品信息");
        addbotton.addActionListener(new add_Listener(){});
        selectbotton.addActionListener(new select_Listener());
        deletebotton.addActionListener(new delete_Listener());

        JPanel panel=new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        panel.add(addbotton);
        panel.add(selectbotton);
        panel.add(deletebotton);

        frame.getContentPane().add(BorderLayout.WEST,panel);
        frame.setSize(500,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    //出错时重新进入添加页面
    public void readd(ActionEvent e){
        new add_Listener().actionPerformed(e);
    }

    //用于返回查询页面
    public void reselect(ActionEvent e){
        new select_Listener().actionPerformed(e);
    }

    //用于返回删除界面
    public void redelete(ActionEvent e){
        new delete_Listener().actionPerformed(e);
    }

    class add_Listener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(frame!=null){
                frame.dispose();
            }//关闭原窗口

            //创建新窗口
            addframe=new JFrame("商品信息添加");
            JPanel addpanel=new JPanel(new BorderLayout());

            //返回按钮
            returnbotton=new JButton("返回");
            returnbotton.addActionListener(new return_Listener());



            //输入商品信息
            Box label_box=new Box(BoxLayout.Y_AXIS);
            Box text_box=new Box(BoxLayout.Y_AXIS);

            Font font=new Font(Font.DIALOG,Font.PLAIN,14);
            JLabel id_label=new JLabel("商品ID:");
            id_label.setFont(font);
            label_box.add(id_label);
            id_text=new JTextField(20);
            text_box.add(id_text);

            JLabel name_label=new JLabel("商品名称:");
            name_label.setFont(font);
            label_box.add(name_label);
            name_text=new JTextField(20);
            text_box.add(name_text);

            JLabel price_label=new JLabel("商品价格:");
            price_label.setFont(font);
            label_box.add(price_label);
            price_text=new JTextField(20);
            text_box.add(price_text);

            JLabel introduction_label=new JLabel("商品简介:");
            introduction_label.setFont(font);
            label_box.add(introduction_label);
            introduction_text=new JTextField(20);
            text_box.add(introduction_text);



            //提交按钮
            JButton submit=new JButton("添加商品");
            submit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    addframe.dispose();
                    new Add().addgoods(id_text.getText(),name_text.getText(),price_text.getText(),introduction_text.getText());
                }
            });
            text_box.add(submit);


            //完成页面布局
            addpanel.add(BorderLayout.WEST,label_box);
            addpanel.add(BorderLayout.CENTER,text_box);
            addframe.getContentPane().add(BorderLayout.SOUTH,returnbotton);
            addframe.getContentPane().add(BorderLayout.NORTH,addpanel);
            addframe.setSize(500,500);
            addframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            addframe.setVisible(true);
        }
    }

    class select_Listener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            //关闭原来页面
            if(frame!=null){
                frame.dispose();
            }

            selectframe=new JFrame("商品信息查询");
            JPanel selectpanel=new JPanel();

            //返回按钮
            returnbotton=new JButton("返回");
            returnbotton.addActionListener(new return_Listener());

            //查询全部商品信息
            JButton findall=new JButton("查询全部商品信息");
            findall.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    selectframe.dispose();
                    new Find().findall();
                }
            });
            selectpanel.add(findall);

            //通过ID查询单个商品信息
            JButton find_id=new JButton("通过ID查询");
            find_id.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    selectframe.dispose();
                    new Find().findid();
                }
            });
            selectpanel.add(find_id);

            //产寻价格低于输入值的商品
            JButton find_price=new JButton("查询价格低于输入值的商品");
            find_price.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    selectframe.dispose();
                    new Find().findprice();
                }
            });
            selectpanel.add(find_price);

            //完成页面布局
            selectframe.getContentPane().add(BorderLayout.SOUTH,returnbotton);
            selectframe.getContentPane().add(BorderLayout.NORTH,selectpanel);
            selectframe.setSize(500,500);
            selectframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            selectframe.setVisible(true);
        }
    }

    class delete_Listener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(frame!=null){
                frame.dispose();
            }
            deleteframe=new JFrame("商品信息删除");
            JPanel deletepanel=new JPanel();

            //返回按钮
            returnbotton=new JButton("返回");
            returnbotton.addActionListener(new return_Listener());

            //删除全部商品信息
            JButton deleteall=new JButton("删除全部商品信息");
            deleteall.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    deleteframe.dispose();
                    new Delete().deleteall();
                }
            });
            deletepanel.add(deleteall);

            //通过ID删除单个商品信息
            JButton delete_id=new JButton("根据ID删除");
            delete_id.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    deleteframe.dispose();
                    new Delete().deleteid();
                }
            });
            deletepanel.add(delete_id);

            //完成商品布局
            deleteframe.getContentPane().add(BorderLayout.SOUTH,returnbotton);
            deleteframe.getContentPane().add(BorderLayout.NORTH,deletepanel);
            deleteframe.setSize(500,500);
            deleteframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            deleteframe.setVisible(true);
        }
    }

    class return_Listener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(addframe!=null){
                addframe.dispose();
            }
            if(selectframe!=null){
                selectframe.dispose();
            }
            if(deleteframe!=null){
                deleteframe.dispose();
            }
            Interface ui = new Interface();
            ui.go();
        }
    }
}
