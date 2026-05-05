public class SimpleSLL {

    class Node {
        int data;
        Node next;

        Node(int d) {
            data = d;
        }
    }

    Node head;

    public void add(int d) {
        if (head == null) {
            head = new Node(d);
            return;
        }

        Node temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }

        temp.next = new Node(d);
    }

    public void display() {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + " -> ");
            temp = temp.next;
        }
        System.out.println("null");
    }
}