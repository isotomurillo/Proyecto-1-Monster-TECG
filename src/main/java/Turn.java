public class Turn {

    int life;
    boolean Freeze = false;
    boolean Power = false;

    public Turn(){
        life = 1000;
    }

    public void Receives(Card card){
        if (card.type.contentEquals("minion")){
            life = life - (int)card.action;
            System.out.println(life);
        }else {
            System.out.println("Cloe es gei");
        }
    }
    public void Sends(Card card){

        if (card.type.contentEquals("minion")){
            System.out.println("minion");
        }else {
            if (card.type.contentEquals("spell")){
                switch ((String)card.action){
                    case "heal":
                        if (life < 1000){
                            life += 100;
                            System.out.println("heal");
                        }
                        break;
                    case "heal+":
                        if (life < 1000){
                            life += 200;
                            System.out.println("heal+");
                        }
                        break;
                    case "power":
                        Power = true;
                        System.out.println("yes");
                        break;
                    case "steal":
                        System.out.println("steal");
                }
            }else{

            }
        }
    }
}
