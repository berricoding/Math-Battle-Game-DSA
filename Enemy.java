//Eneemy class inherited Character
public class Enemy extends Character {
    Enemy next; 
    public Enemy(int hp) {
        super("Enemy", hp);
    }
}