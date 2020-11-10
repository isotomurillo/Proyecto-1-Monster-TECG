public class Mana {
    public int mana;
    //cmana es el valor de mana de la carta
    public int more=200;


    public void setMana(int mana) {
        this.mana = mana;
    }

    public int getMana() {
        return mana;
    }

    public void lessMana(int cmana){
        if (mana>cmana){
            setMana(mana-=cmana);
        }
    }

    public void moreMana(){
        if (mana+more>=1000){
            setMana(1000);
        } else{
            setMana(mana+=more);
        }

    }

    public boolean verMana(int cmana){
        return mana>=cmana;
    }
}
