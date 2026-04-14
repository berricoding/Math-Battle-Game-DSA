   //Singular Linked List
   //A singly linked list is used to store and display leaderboard data, allowing flexible insertion without fixed size.

public class Leaderboard {
    private Node head;
    private Node tail;

   public void add(Player p) {
    Node newNode = new Node(p);

    if (head == null) {
        head = newNode;
        tail = newNode;
        return;
    }

    tail.next = newNode;
    newNode.prev = tail;
    tail = newNode;
}

    public void display() {
        System.out.println("\nLeaderboard:");
        Node temp = head;

        while (temp != null) {
           System.out.println(temp.data.getName() + " : " + temp.data.getHp() + " HP");
            temp = temp.next;
        }
    }
    public void displayReverse() {
    System.out.println("\nLeaderboard (Reverse):");
    Node temp = tail;

    while (temp != null) {
       System.out.println(temp.data.getName() + " : " + temp.data.getHp() + " HP");
        temp = temp.prev;
    }
}
}