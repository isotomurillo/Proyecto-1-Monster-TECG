public class Heath {
    public int health;
    // attack ataque de carta
    // moreH aumento HP


    public int getHealth() {
        return this.health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void lessHeath (int attack){
        if (health-attack <= 0){
            setHealth(0);
        }else {
            setHealth(this.health-attack);
        }
    }

    public void moreHealth(int moreH){
        if (this.health+moreH>=1000){
            setHealth(1000);
        } else {
            setHealth(health+=moreH);
        }
    }
}
