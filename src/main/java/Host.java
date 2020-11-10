import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Host implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        MyFrameworkHost myFramework = null;
        try {
            myFramework = new MyFrameworkHost();
        } catch (JsonProcessingException jsonProcessingException) {
            jsonProcessingException.printStackTrace();
        }
        myFramework.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

class MyFrameworkHost extends JFrame{

    public MyFrameworkHost() throws JsonProcessingException {

        setBounds(300,200,300,400);
        HostSheet mysheet = new HostSheet();
        add(mysheet);
        setVisible(true);
    }
}

class HostSheet extends JPanel implements Runnable {

    public HostSheet() throws JsonProcessingException {
        setLayout(null);
        JLabel text1 = new JLabel("Play");
        JButton button2 = new JButton("Connect");
        JButton button1 = new JButton("Send");
        Dimension dimension1 = text1.getPreferredSize();
        Dimension dimension3 = button1.getPreferredSize();
        Dimension dimension4 = button2.getPreferredSize();
        text1.setBounds(132,10,dimension1.width,dimension1.height);
        button1.setBounds(30,300,dimension3.width+10,dimension1.height+5);
        button2.setBounds(150,300,dimension4.width+10,dimension1.height+5);
        add(text1);
        add(button1);
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
            Turn turn = new Turn();
            while (true){
                Socket mySocket = myServerSocket.accept();
                ObjectInputStream packet = new ObjectInputStream(mySocket.getInputStream());
                JsonNode node = (JsonNode) packet.readObject();
                Card card = Json.fromJson(node, Card.class);
                //turn.Receives(card);
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }

    private class Send implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            Turn turn = new Turn();
            try {
                Socket mySocket = new Socket("127.0.0.1",8888);
                ObjectOutputStream send = new ObjectOutputStream(mySocket.getOutputStream());
                Card card = stack.get();
                JsonNode node = Json.toJson(card);
                turn.Sends(card);
                send.writeObject(node);
                mySocket.close();
            }catch (Exception ex){
                System.out.println(ex);
            }
        }
    }
    Deck deck = new Deck();
    Stack stack = deck.Deck1();
}