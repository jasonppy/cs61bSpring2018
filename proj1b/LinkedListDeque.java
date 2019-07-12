/** This is a double-linked list
 *  with method add(constant time), remove(constant time),
 *  get(both iteratively and recursively) and size(constant time)
 *  source: https://sp18.datastructur.es/materials/proj/proj1a/proj1a
 *  @author: Jason
 *  */
public class LinkedListDeque<T> implements Deque<T> {
    private class Node {
        private Node prev;
        private T item;
        private Node next;

        private Node(Node p, T i, Node n) {
            prev = p;
            item = i;
            next = n;
        }

        private T get(int index) {
            if (next == this) {
                return null;
            } else if (index == 0) {
                return item;
            } else {
                return next.get(index - 1);
            }
        }
    }
    private int size;
    private Node sentinel;

    @Override
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }
    @Override
    public int size() {
        return size;
    }
    public LinkedListDeque() {
        size = 0;
        sentinel = new Node(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }
    @Override
    public void printDeque() {
        Node node = sentinel.next;
        while (node != sentinel) {
            System.out.print(node.item + "\n");
            node = node.next;
        }
    }
    @Override
    public void addFirst(T item) {
        size += 1;
        sentinel.next = new Node(sentinel, item, sentinel.next);
        sentinel.next.next.prev = sentinel.next;
    }
    @Override
    public void addLast(T item) {
        size += 1;
        sentinel.prev = new Node(sentinel.prev, item, sentinel);
        sentinel.prev.prev.next = sentinel.prev;
    }
    @Override
    public T removeFirst() {
        if (this.isEmpty()) {
            return null;
        }
        size -= 1;
        T firstItem = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        return firstItem;
    }
    @Override
    public T removeLast() {
        if (this.isEmpty()) {
            return null;
        }
        size -= 1;
        T lastItem = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        return lastItem;
    }
    @Override
    public T get(int index) {
        if (index > size - 1) {
            return null;
        }
        int i = index;
        Node N = sentinel.next;
        while (i > 0) {
            N = N.next;
            i--;
        }
        return N.item;

    }

    public T getRecursive(int index) {
        if (this.isEmpty() || index < 0) {
            return null;
        }
        return sentinel.next.get(index);
    }

//    public static void main(String[] args) {
//        LinkedListDeque<Integer> L = new LinkedListDeque<>();
//        L.addFirst(1);
//        L.addLast(2);
//        System.out.println(L.get(0));
//        System.out.println(L.getRecursive(0));
//        System.out.println(L.sentinel.next.next.item);
//        int lastItem = L.removeLast();
//        System.out.println(lastItem);
//        System.out.println(L.sentinel.next.next.item);
//        L.removeLast();
//        System.out.println(L.removeLast());
//        System.out.println(L.size);
//
//
//    }

}
