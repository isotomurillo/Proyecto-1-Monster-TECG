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

public class Player implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        MyFrameworkPlayer myFramework = null;
        try {
            myFramework = new MyFrameworkPlayer();
        } catch (JsonProcessingException jsonProcessingException) {
            jsonProcessingException.printStackTrace();
        }
        myFramework.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
class MyFrameworkPlayer extends JFrame {

    public MyFrameworkPlayer() throws JsonProcessingException {

        setBounds(300,200,300,400);
        PlayerSheet mysheet = new PlayerSheet();
        add(mysheet);
        setVisible(true);
    }
}
class PlayerSheet extends JPanel implements Runnable{

    public PlayerSheet() throws JsonProcessingException {
        setLayout(null);
        int e = 0;
        while (e != 4){
            list.insertLast(stack.get());
            e++;
        }
        JLabel text1 = new JLabel("Play");
        textField1 = new JTextField(8);
        JButton button2 = new JButton("Connect");
        JButton button1 = new JButton("Send");
        Dimension dimension1 = text1.getPreferredSize();
        Dimension dimension2 = textField1.getPreferredSize();
        Dimension dimension3 = button1.getPreferredSize();
        Dimension dimension4 = button2.getPreferredSize();
        text1.setBounds(132,10,dimension1.width,dimension1.height);
        textField1.setBounds(100,30,dimension2.width,dimension2.height);
        button1.setBounds(30,300,dimension3.width+10,dimension1.height+5);
        button2.setBounds(150,300,dimension4.width+10,dimension1.height+5);
        add(text1);
        add(textField1);
        add(button1);
        add(button2);
        Connect connect = new Connect();
        button2.addActionListener(connect);
        Thread thread = new Thread(this);
        thread.start();

    }

    @Override
    public void run() {
        try{
            ServerSocket myServerSocket = new ServerSocket(8888);
            Turn turn = new Turn();
            while (true){
                Socket mySocket = myServerSocket.accept();
                ObjectInputStream packetS = new ObjectInputStream(mySocket.getInputStream());
                JsonNode node = (JsonNode) packetS.readObject();
                Card card = Json.fromJson(node, Card.class);
                //turn.Receives(card);
            }

        }catch (Exception e){
            System.out.println(e);
        }
    }

    private class Connect implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            Turn turn = new Turn();
            try {
                Socket mySocket = new Socket(textField1.getText(),9999);
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
    JTextField textField1;
    Deck deck =new Deck();
    List list = new List();
    Stack stack = deck.Deck1();
}


