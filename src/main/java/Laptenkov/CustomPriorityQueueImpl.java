package Laptenkov;

import java.util.NoSuchElementException;
import java.util.Comparator;

/**
 * Класс CustomPriorityQueueImpl<T> представляет очередь,
 * в которой элемент с наибольшим значением попадает в начало очереди.
 * Класс CustomPriorityQueueImpl реализует интерфейс CustomPriorityQueue<T>
 * Класс CustomPriorityQueueImpl может хранить объекты любого типа
 * Класс CustomPriorityQueueImpl имеет фиксированный размер
 * Динамическое расширение не предусмотрено
 * @param <T>
 */
public class CustomPriorityQueueImpl<T> implements CustomPriorityQueue<T>{

    private final int DEFAULT_CAPACITY = 20;
    private final Comparator<T> comparator;
    private Object[] data = new Object[DEFAULT_CAPACITY];
    int size = 0;

    /**
     * Конструктор пустого объекта {@link CustomPriorityQueueImpl}
     * очереди с приоритетом с дефолтным размером 20.
     */
    public CustomPriorityQueueImpl(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    /**
     * Конструктор пустого объекта {@link CustomPriorityQueueImpl}
     * очереди с приоритетом с задаваемым размером capacity.
     *
     */
    public CustomPriorityQueueImpl(int capacity, Comparator<T> comparator) {
        this.comparator = comparator;
        data = new Object[capacity];
    }

    /**
     * Метод {@link CustomPriorityQueueImpl#size()}
     * возвращает размер очереди с приоритетом
     * объекта {@link CustomPriorityQueueImpl}.
     *
     * @return целое число типа {@link int}
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Метод {@link CustomPriorityQueueImpl#isEmpty()} возвращает булево значение
     * при проверке объекта {@link CustomPriorityQueueImpl} очереди с приоритетом.
     *
     * @return <code>true</code> если размер не нулевой,
     * <code>false</code> если размер нулевой.
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Метод {@link CustomPriorityQueueImpl#add(T)}
     * безопасно добавляет объекта типа Т и возвращает булево значение
     * или выбрасывает исключение.
     *
     * @param item объекта типа Т.
     * @throws IllegalStateException если очередь заполнена.
     */
    @Override
    public boolean add(T item) {
        if (size == data.length) {
            throw new IllegalStateException("Очередь заполнена!");
        }

        int insertPos = 0;
        while (insertPos < size && comparator.compare(item, (T) data[insertPos]) > 0) {
            insertPos++;
        }

        for (int i = size - 1; i >= insertPos; --i) {
            data[i + 1] = data[i];
        }

        size++;
        data[insertPos] = item;
        return true;
    }

    /**
     * Метод {@link CustomPriorityQueueImpl#offer(T)}
     * безопасно добавляет объекта типа Т и возвращает булево значение.
     *
     * @param item объект типа Т
     * @return <code>true</code> если добавление прошло успешно,
     * <code>false</code> если очередь заполнена.
     */
    @Override
    public boolean offer(T item) {
        if (size == data.length) {
            return false;
        }
        add(item);
        return true;
    }

    /**
     * Метод {@link CustomPriorityQueueImpl#peek()}
     * безопасно возвращает head типа Т объекта очереди.
     *
     * @return null если очередь пустая.
     */
    @Override
    public T peek() {
        if (isEmpty()) {
            return null;
        }
        return (T) data[size - 1];
    }

    /**
     * Метод {@link CustomPriorityQueueImpl#poll()}
     * безопасно удаляет элемент first и возвращает его.
     *
     * @return если очередь пуста.
     */
    @Override
    public T poll() {
        if (isEmpty()) {
            return null;
        }

        return remove();
    }

    /**
     * Метод {@link CustomPriorityQueueImpl#remove()}
     * не безопасно удаляет элемент first и возвращает его.
     *
     * @throws NoSuchElementException если очередь пуста.
     */
    @Override
    public T remove() {
        if (isEmpty()) {
            throw new NoSuchElementException("Очередь пустая!");
        }

        T tmp = (T) data[size - 1];
        data[size - 1] = null;
        size--;

        return tmp;
    }

    /**
     * Метод {@link CustomPriorityQueue#toString()}
     * возвращает строковое представление очереди {@link CustomPriorityQueue}
     *
     * @return объект типа String в формате '[ element1 element2 ... elementN ]
     * или [ ] если очередь пустая.
     */
    @Override
    public String toString() {
        StringBuilder cb = new StringBuilder();

        cb.append("[");
        if (size != 0) {
            for (int i = 0; i < size; i++) {
                cb.append(" " + data[i]);
            }
        }
        cb.append(" ]");

        return cb.toString();
    }

    /**
     * Метод {@link CustomPriorityQueue<T>#toArray()}
     * возвращает копию текущего объекта
     * {@link CustomPriorityQueue<T>}.
     *
     * @return объект типа {@link Object[]}.
     */
    @Override
    public Object[] toArray() {
        Object[] newData = new Object[size];

        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }
        return newData;
    }

}