import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Host implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        MyFrameworkHost myFramework = new MyFrameworkHost();
        myFramework.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

class MyFrameworkHost extends JFrame{

    public MyFrameworkHost(){

        setBounds(300,200,300,200);
        HostSheet mysheet = new HostSheet();
        add(mysheet);
        setVisible(true);
    }
}

class HostSheet extends JPanel implements Runnable {

    public HostSheet() {
        JLabel text1 = new JLabel("--Host--");
        add(text1);
        JButton button1 = new JButton("Host");
        add(button1);
        JButton button2 = new JButton("Join");
        add(button2);
        Send send = new Send();
        button1.addActionListener(send);
        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        try{
            ServerSocket myServerSocket = new ServerSocket(9999);
            while (true){
                Socket mySocket = myServerSocket.accept();
                ObjectInputStream packet = new ObjectInputStream(mySocket.getInputStream());
                System.out.println(packet.readObject());
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }

    private class Send implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Socket mySocket = new Socket("127.0.0.1",8888);
                ObjectOutputStream send = new ObjectOutputStream(mySocket.getOutputStream());
                send.writeObject("Hello2");
                mySocket.close();
            }catch (Exception ex){
                System.out.println(ex);
            }
        }
    }
}