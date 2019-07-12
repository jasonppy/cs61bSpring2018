public interface Deque<T> {
    boolean isEmpty();
    T get(int index);
    int size();
    void printDeque();
    void addFirst(T item);
    void addLast(T item);
    T removeFirst();
    T removeLast();
}