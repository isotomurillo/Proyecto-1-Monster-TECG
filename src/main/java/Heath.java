public class Heath {
    public int health;
    public int attack;

    public int getHealth() {
        return this.health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int lessHeath (int attack){
        if (health-attack <= 0){
            this.health=0;
            return this.health;
        }else {
            this.health-=attack;
            return this.health;
        }
    }
}
