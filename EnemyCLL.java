public class EnemyCLL{//used to link enemies in a loop, allowing the game to rotate between enemies continuously across rounds.
    private Enemy head;
    private int size = 0;

    public void add(Enemy e) {
    if (head == null) {
        head = e;
        head.setNext(head);
        size++;
        return;
    }

    Enemy temp = head;
    while (temp.getNext() != head) {
        temp = temp.getNext();
    }

    temp.setNext(e);
    e.setNext(head);
    size++;
}
public int getSize() {
    return size;
}

    public Enemy getNextEnemy(Enemy current) {
        return current.getNext(); 
    }

    public Enemy getHead() {
        return head;
    }
    public void displayCycleOnce() {
    if (head == null) return;

    Enemy temp = head;
    do {
        System.out.println(temp.name);
        temp = temp.getNext();
    } while (temp != head);
} 
 
}