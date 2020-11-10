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
        int e = 0;
        while (e != 4){
            list.insertLast(stack.get());
            e++;
        }
        JLabel text1 = new JLabel("Play");
        textField1 = new JTextField(8);
        JButton button1 = new JButton("Get Card");
        JButton button2 = new JButton("Send");
        JButton button3 = new JButton("Pass");
        textArea1 = new JTextArea(8,10);
        textArea2 = new JTextArea(15,15);
        textArea3 = new JTextArea(5,10);
        Dimension dimension1 = text1.getPreferredSize();
        Dimension dimension2 = textField1.getPreferredSize();
        Dimension dimension3 = button1.getPreferredSize();
        Dimension dimension4 = button2.getPreferredSize();
        Dimension dimension5 = textArea1.getPreferredSize();
        Dimension dimension6 = textArea2.getPreferredSize();
        Dimension dimension7 = textArea3.getPreferredSize();
        text1.setBounds(132,10,dimension1.width,dimension1.height);
        button1.setBounds(30,300,dimension3.width+10,dimension3.height);
        button2.setBounds(150,300,dimension4.width+10,dimension4.height);
        button3.setBounds(150,330, dimension4.width+10,dimension4.height);
        textArea1.setBounds(165,50,dimension5.width, dimension5.height);
        textArea2.setBounds(0,50,dimension6.width, dimension6.height);
        textArea3.setBounds(165,180,dimension7.width, dimension7.height);
        textField1.setBounds(30,330, dimension2.width,dimension2.height);
        add(text1);
        add(textField1);
        add(button1);
        add(button2);
        add(button3);
        add(textArea1);
        add(textArea2);
        add(textArea3);
        mana.setMana(200);
        ShowCardsHost();
        UpdatePlayer();
        Pass pass = new Pass();
        Send connect = new Send();
        UpdateHand updateHand = new UpdateHand();
        button2.addActionListener(connect);
        button1.addActionListener(updateHand);
        button3.addActionListener(pass);
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
                JsonNode node = (JsonNode) packet.readObject();
                Card card = Json.fromJson(node, Card.class);
                health.Receives(card);
                mana.moreMana();
                UpdatePlayer();
                textArea2.append(card.type + " " + card.action + "\n" );
                Turn(card.mana);
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }

    private void Turn(int x){
        turn = true;
        get = true;
    }

    private class Pass implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            turn = false;
            get = false;
            try {
                OpenFile openFile = new OpenFile();
                JsonNode node = Json.parse(openFile.OpenFiles("Pass.json"));
                Card card = Json.fromJson(node.get("Attributes"), Card.class);
                JsonNode node1 = Json.toJson(card);
                Socket mySocket = new Socket("127.0.0.1",8888);
                ObjectOutputStream send = new ObjectOutputStream(mySocket.getOutputStream());
                send.writeObject(node1);
                textArea2.append(card.type + " " + card.action + "\n" );
                mySocket.close();
            }catch (Exception ex){
                System.out.println(ex);
            }
        }
    }

    private void ShowCardsHost(){
        int num = list.size;
        int i = 0;
        textArea1.setText("");
        textArea1.append("Cards:" + "\n");
        while (i != num){
            textArea1.append(list.returnName(i) + "\n");
            i++;
        }
    }

    private void GetCard(){
        Card card = stack.get();
        list.insertLast(card);
    }

    private int PlayCard(){
        int num = Integer.parseInt(textField1.getText());
        return num;
    }

    private void UpdatePlayer(){
        int mananum = mana.getMana();
        int healthLife = health.getLife();
        textArea3.setText("");
        textArea3.append("Health: " + healthLife + "\n");
        textArea3.append("Mana: " + mananum );
    }

    private class Send implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if (turn){
                Card card = list.get(PlayCard());
                if (mana.getMana() >= card.mana){
                    turn = false;
                    try {
                        Socket mySocket = new Socket("127.0.0.1",8888);
                        ObjectOutputStream send = new ObjectOutputStream(mySocket.getOutputStream());
                        JsonNode node = Json.toJson(card);
                        health.Sends(card);
                        send.writeObject(node);
                        textArea2.append(card.type + " " + card.action + "\n" );
                        mana.lessMana(card.mana);
                        UpdatePlayer();
                        ShowCardsHost();
                        mySocket.close();
                    }catch (Exception ex){
                        System.out.println(ex);
                    }
                }else {
                    list.insertLast(card);
                    turn = false;
                }
            }else{
                System.out.println("No es tu turno");
            }
        }
    }

    private class UpdateHand implements ActionListener{
        private UpdateHand() throws JsonProcessingException {
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (get){
                GetCard();
                ShowCardsHost();
                get = false;
            }else {
                System.out.println("No es el momento");
            }
        }
    }

    JTextArea textArea1;
    JTextArea textArea2;
    JTextArea textArea3;
    JTextField textField1;
    Boolean turn = false;
    Boolean get = false;
    Health health = new Health();
    Deck deck =new Deck();
    List list = new List();
    Stack stack = deck.Deck1();
    Mana mana = new Mana();
}