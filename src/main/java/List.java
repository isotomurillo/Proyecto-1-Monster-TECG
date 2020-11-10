public class List {
    private NodeList head;
    private NodeList tail;
    public int size;

    public List(){
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public void insertLast(Card data){
        NodeList nuevo = new NodeList(data);
        if (this.head == null){
            this.head = nuevo;
            this.tail = nuevo;
        }else{
            NodeList temp = this.tail;
            this.tail.next = nuevo;
            this.head.prev = nuevo;
            this.tail = nuevo;
            nuevo.prev = temp;
            nuevo.next = this.head;
        }
        this.size ++;
    }

    public Card get(int data){
        if (data <= this.size){
            NodeList temp = this.head;
            if (data == 0){
                size --;
                NodeList temp2 = temp.next;
                NodeList temp3 = temp.prev;
                temp3.next = temp2;
                temp2.prev = temp3;
                this.head = temp2;
            }else if (data == this.size){
                size --;
                temp = this.tail;
                NodeList temp2 = temp.next;
                NodeList temp3 = temp.prev;
                temp3.next = temp2;
                temp2.prev = temp3;
                this.tail = temp3;
            }else {
                size --;
                int e = 0;
                while (e != data){
                    temp = temp.next;
                    e++;
                }
                NodeList temp2 = temp.next;
                NodeList temp3 = temp.prev;
                temp3.next = temp2;
                temp2.prev = temp3;
            }
            return temp.card;
        }
        return null;
    }
    
    public String returnName(int num){
        if (num <= size){
            NodeList temp = this.head;
            Card card;
            String name = null;
            int e = 0;
            while (e != num){
                temp = temp.next;
                e++;
            }
            card = temp.card;
            name = card.action + " " + card.mana;
            return name;
        }else return null;
    }
}
