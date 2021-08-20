package Laptenkov;

/**
 * Simple priority queue. Can store any objects.
 */
public interface CustomPriorityQueue<T> {

    int size();

    boolean isEmpty();

    /**
     * Add item to tail.
     *
     * @throws IllegalStateException if queue is full
     */
    boolean add(T item);


    /**
     * Try to add item to tail.
     *
     * @return null if queue is full
     */
    boolean offer(T item);

    /**
     * Peek element with highest priority.
     *
     * @return null if empty
     */
    T peek();


    /**
     * Poll element with highest priority.
     *
     * @return null if empty
     */
    T poll();

    /**
     * Remove element with highest priority.
     *
     * @throws java.util.NoSuchElementException if empty
     */
    T remove();

    /**
     * Get content in format '[element1 element2 ... elementN ] or [] if empty.
     */
    String toString();

    /**
     * Copy list to array.
     */
    Object[] toArray();
}
