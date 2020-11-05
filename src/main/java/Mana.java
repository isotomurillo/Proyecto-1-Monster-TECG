public class Mana {
    public int mana;
    public int cmana;  //cmana es el valor de mana de la carta

    public void setMana(int mana) {
        this.mana = mana;
    }

    public int getMana() {
        return mana;
    }

    public int lessmana(int cmana){
        if (mana>cmana){
            this.mana-=cmana;
        }
        return this.mana;
    }

    public boolean verMana(int cmana){
        return mana>=cmana;
    }
}
