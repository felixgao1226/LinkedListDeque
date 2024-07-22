import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class LinkedListDeque61B<T> implements Deque61B<T> {

    Node sentinel;

    private class Node {
        public Node prev;
        public T item;
        public Node next;

        private Node(T item, Node prev, Node next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }

    public LinkedListDeque61B() {
        sentinel = new Node(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
    }

    @Override
    public void addFirst(T x) {
        Node temp = sentinel.next;
        sentinel.next = new Node(x, sentinel, sentinel.next);
        temp.prev = sentinel.next;
    }

    @Override
    public void addLast(T x) {
        Node temp = sentinel.prev;
        sentinel.prev = new Node(x, temp, sentinel);
        temp.next = sentinel.prev;
    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        Node curr = sentinel.next;
        while (curr != sentinel) {
            returnList.add(curr.item);
            curr = curr.next;
        }
        return returnList;
    }

    @Override
    public boolean isEmpty() {
        return sentinel.next == sentinel;
    }

    @Override
    public int size() {
        int size = 0;
        Node curr = sentinel.next;
        while (curr != sentinel) {
            size += 1;
            curr = curr.next;
        }
        return size;
    }

    @Override
    public T removeFirst() {
        if (isEmpty()){
            return null;
        }
        Node first = sentinel.next;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        return first.item;
    }

    @Override
    public T removeLast() {
        if (isEmpty()){
            return null;
        }
        Node last = sentinel.prev;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        return last.item;
    }

    @Override
    public T get(int index) {
        Node curr = sentinel.next;
        int i = 0;
        while (curr.next != sentinel && index != i) {
            curr = curr.next;
            i++;
        }
        if (index == i) {
            return curr.item;
        } else {
            return null;
        }
    }

    @Override
    public T getRecursive(int index) {
        if (index < 0) {
            return null;
        } else if (index == 0){
            return sentinel.next.item;
        } else {
            sentinel.next = sentinel.next.next;
            sentinel.next.prev = sentinel;
            return getRecursive(index - 1);
        }
    }
}
