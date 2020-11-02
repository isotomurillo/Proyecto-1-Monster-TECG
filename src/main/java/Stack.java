public class Stack {

    Node head;
    int size;

    public Stack(){
        head = null;
        size = 0;
    }

    public void insert(Card card){
        Node New = new Node(card);
        if (this.head == null){
            this.head = New;
            size ++;
        }else{
            New.next = this.head;
            this.head = New;
            size ++;
        }
    }

    public Card get(){
        Node temp = this.head;
        this.head = temp.next;
        size --;
        return temp.card;
    }
}
