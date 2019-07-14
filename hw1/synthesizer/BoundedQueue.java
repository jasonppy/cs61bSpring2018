package synthesizer;

public interface BoundedQueue<T> {
    /** return the size of the Buffer */
    int capacity();

    /** return number of items currently in the buffer */
    int fillCount();

    /** add item to the end*/
    void enqueue(T x);

    /** delete and return item from the front */
    T dequeue();

    /** return (but not delete) item from the front */
    T peek();


    default boolean isEmpty() {
        return fillCount() == 0;
    }

    default boolean isFull() {
        return fillCount() == capacity();
    }
}
