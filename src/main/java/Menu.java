import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Menu {

    public Menu(){

        MyFrameworkMenu myFramework = new MyFrameworkMenu();
        myFramework.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
class MyFrameworkMenu extends JFrame{

    public MyFrameworkMenu(){

        setBounds(500,200,200,200);
        MenuSheet mysheet = new MenuSheet();
        add(mysheet);
        setVisible(true);
    }
}
class MenuSheet extends JPanel{

    public MenuSheet(){
        setLayout(null);
        JLabel text1 = new JLabel("Main Menu");
        JButton button1 = new JButton("Host");
        JButton button2 = new JButton("Join");
        Dimension dimension1 = text1.getPreferredSize();
        Dimension dimension2 = button2.getPreferredSize();
        Dimension dimension3 = button1.getPreferredSize();
        text1.setBounds(60,20,dimension1.width+10,dimension1.height);
        button2.setBounds(60,50,dimension2.width,dimension2.height);
        button1.setBounds(60,80,dimension3.width,dimension3.height);
        add(button1);
        add(text1);
        add(button2);
        Host host = new Host();
        button1.addActionListener(host);
        Player player = new Player();
        button2.addActionListener(player);
    }
}
