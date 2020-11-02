import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Player implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        MyFrameworkPlayer myFramework = new MyFrameworkPlayer();
        myFramework.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
class MyFrameworkPlayer extends JFrame {

    public MyFrameworkPlayer(){

        setBounds(300,200,300,200);
        PlayerSheet mysheet = new PlayerSheet();
        add(mysheet);
        setVisible(true);
    }
}
class PlayerSheet extends JPanel implements Runnable{

    public PlayerSheet() {
        JLabel text1 = new JLabel("--Play--");
        add(text1);
        textField1 = new JTextField(8);
        add(textField1);
        JButton button2 = new JButton("Connect");
        add(button2);
        JButton button1 = new JButton("Send");
        add(button1);

        Connect connect = new Connect();
        button2.addActionListener(connect);
        Thread thread = new Thread(this);
        thread.start();

    }

    @Override
    public void run() {
        try{
            ServerSocket myServerSocket = new ServerSocket(8888);

            while (true){
                Socket mySocket = myServerSocket.accept();
                ObjectInputStream packet = new ObjectInputStream(mySocket.getInputStream());
                System.out.println(packet.readObject());
            }

        }catch (Exception e){
            System.out.println(e);
        }
    }

    private class Connect implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Socket mySocket = new Socket(textField1.getText(),9999);
                ObjectOutputStream send = new ObjectOutputStream(mySocket.getOutputStream());
                send.writeObject("Hello");
                mySocket.close();
            }catch (Exception ex){
                System.out.println(ex);
            }
        }
    }
    JTextField textField1;
}


