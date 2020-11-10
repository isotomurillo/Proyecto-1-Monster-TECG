public class NodeList {

    public NodeList next;
    public Card card;
    public NodeList prev;

    public NodeList(Card card){
        this.next = null;
        this.card = card;
        this.prev = null;
    }
}
