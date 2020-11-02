import javax.swing.*;
import java.awt.event.ActionListener;

public class Menu {

    public Menu(){

        MyFrameworkMenu myFramework = new MyFrameworkMenu();
        myFramework.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
class MyFrameworkMenu extends JFrame{

    public MyFrameworkMenu(){

        setBounds(150,200,150,200);
        MenuSheet mysheet = new MenuSheet();
        add(mysheet);
        setVisible(true);
    }
}
class MenuSheet extends JPanel{

    public MenuSheet(){
        JLabel text1 = new JLabel("--Main Menu--");
        add(text1);
        JButton button1 = new JButton("Host");
        add(button1);
        JButton button2 = new JButton("Join");
        add(button2);
        Host host = new Host();
        button1.addActionListener(host);
        Player player = new Player();
        button2.addActionListener(player);
    }
}
