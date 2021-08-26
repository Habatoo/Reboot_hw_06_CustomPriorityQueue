package Laptenkov;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Класс для тестирования public методов класса {@link CustomPriorityQueueImpl<Object>}.
 *
 * @author habatoo
 */
class CustomPriorityQueueImplTest {

    private CustomPriorityQueueImpl<String> customEmptyPriorityQueue;
    private CustomPriorityQueueImpl<String> customNotEmptyPriorityQueue;

    /**
     * Инициализация экземпляров тестируемого класса {@link CustomPriorityQueueImpl}.
     */
    @BeforeEach
    void setUp() {
        customEmptyPriorityQueue = new CustomPriorityQueueImpl<>(String::compareTo);

        customNotEmptyPriorityQueue = new CustomPriorityQueueImpl<>(String::compareTo);
        customNotEmptyPriorityQueue.add("a_first");
        customNotEmptyPriorityQueue.add("b_second");
        customNotEmptyPriorityQueue.add("c_third");
        customNotEmptyPriorityQueue.add("d_last");
    }

    /**
     * Очистка экземпляров тестируемого класса {@link CustomPriorityQueueImpl}.
     */
    @AfterEach
    void tearDown() {
        customEmptyPriorityQueue = null;
        customNotEmptyPriorityQueue = null;
    }

    /**
     * Метод {@link CustomPriorityQueueImplTest#size_Test()}
     * проверяет метод {@link CustomPriorityQueueImpl#size()}.
     * Проверяет размер не пустого экземпляра {@link CustomPriorityQueueImpl}.
     * Сценарий, при котором пустой экземпляр возвращает величину
     * объекта равную 0, не пустой равный 4.
     */
    @Test
    void size_Test() {
        Assertions.assertEquals(0, customEmptyPriorityQueue.size());
        Assertions.assertEquals(4, customNotEmptyPriorityQueue.size());
    }

    /**
     * Метод  {@link CustomPriorityQueueImplTest#isEmptyEmpty_Test()}
     * проверяет метод {@link CustomPriorityQueueImpl#isEmpty()}.
     * Проверяет на пустоту экземпляр объекта {@link CustomPriorityQueueImpl}.
     * Сценарий, при котором пустой экземпляр возвращает <code>true</code>,
     * не пустой экземпляр возвращает <code>false</code>.
     */
    @Test
    void isEmptyEmpty_Test() {
        Assertions.assertEquals(true, customEmptyPriorityQueue.isEmpty());
        Assertions.assertEquals(false, customNotEmptyPriorityQueue.isEmpty());
    }

    /**
     * Метод {@link CustomPriorityQueueImplTest#addSuccess_Test()}
     * Проверяет проверяет метод {@link CustomPriorityQueueImpl#add(Object)}.
     * Сценарий, при котором объект успешно отрабатывает добавление
     * не пустого объекта типа Т и возвращает <code>true</code>.
     */
    @Test
    void addSuccess_Test() {
        Assertions.assertEquals(4, customNotEmptyPriorityQueue.size());
        Assertions.assertEquals(true, customNotEmptyPriorityQueue.add("add"));
        Assertions.assertEquals(5, customNotEmptyPriorityQueue.size());
    }

    /**
     * Метод {@link CustomPriorityQueueImplTest#addFail_Test()}
     * Проверяет проверяет метод {@link CustomPriorityQueueImpl#add(Object)}.
     * Сценарий, при котором объект не успешно отрабатывает добавление
     * не пустого объекта типа Т в заполненную очередь и
     * выбрасывает IllegalStateException.
     */
    @Test
    void addFail_Test() {
        for (int i = 0; i < 16; i++) {
            customNotEmptyPriorityQueue.add("add" + i);
        }
        Throwable throwable = Assertions.assertThrows(
                IllegalStateException.class, () -> customNotEmptyPriorityQueue.add("fail"));
        Assertions.assertEquals(
                "Очередь заполнена!", throwable.getMessage());
    }

    /**
     * Метод {@link CustomPriorityQueueImplTest#offerSuccess_Test()}
     * Проверяет проверяет метод {@link CustomPriorityQueueImpl#offer(Object)}.
     * Сценарий, при котором объект успешно отрабатывает добавление
     * не пустого объекта типа Т и возвращает <code>true</code>.
     */
    @Test
    void offerSuccess_Test() {
        Assertions.assertEquals(4, customNotEmptyPriorityQueue.size());
        Assertions.assertEquals(true, customNotEmptyPriorityQueue.offer("add"));
        Assertions.assertEquals(5, customNotEmptyPriorityQueue.size());
    }

    /**
     * Метод {@link CustomPriorityQueueImplTest#offerSuccess_Test()}
     * Проверяет проверяет метод {@link CustomPriorityQueueImpl#offer(Object)}.
     * Сценарий, при котором объект не успешно отрабатывает добавление
     * не пустого объекта типа Т в заполненную очередь и возвращает <code>false</code>.
     */
    @Test
    void offerFail_Test() {
        for (int i = 0; i < 16; i++) {
            customNotEmptyPriorityQueue.offer("add" + i);
        }
        Assertions.assertEquals(
                20, customNotEmptyPriorityQueue.size());
        Assertions.assertEquals(
                false, customNotEmptyPriorityQueue.offer("fail"));
    }

    /**
     * Метод {@link CustomPriorityQueueImplTest#peek_Test()}
     * Проверяет проверяет метод {@link CustomPriorityQueueImpl#peek()}.
     * Сценарий, при котором объект не успешно отрабатывает возвращение
     * не пустого объекта типа Т из пустой очереди и возвращает <code>null</code>.
     * Сценарий, при котором объект успешно отрабатывает добавление
     * не пустого объекта типа Т в не заполненную очередь и возвращает "first".
     */
    @Test
    void peek_Test() {
        Assertions.assertEquals(null, customEmptyPriorityQueue.peek());
        Assertions.assertEquals("d_last", customNotEmptyPriorityQueue.peek());
    }

    /**
     * Метод {@link CustomPriorityQueueImplTest#peek_Test()}
     * Проверяет проверяет метод {@link CustomPriorityQueueImpl#poll()}.
     * Сценарий, при котором объект не успешно отрабатывает удаление
     * не пустого объекта типа Т из пустой очереди и возвращает <code>null</code>.
     * Сценарий, при котором объект успешно отрабатывает удаление
     * не пустого объекта типа Т из не пустой очереди и возвращает "first".
     */
    @Test
    void poll_Test() {
        Assertions.assertEquals(null, customEmptyPriorityQueue.poll());
        Assertions.assertEquals(4, customNotEmptyPriorityQueue.size());
        Assertions.assertEquals("d_last", customNotEmptyPriorityQueue.poll());
        Assertions.assertEquals(3, customNotEmptyPriorityQueue.size());
    }

    /**
     * Метод {@link CustomPriorityQueueImplTest#remove_Test()}
     * Проверяет проверяет метод {@link CustomPriorityQueueImpl#remove()}.
     * Сценарий, при котором объект не успешно отрабатывает удаление
     * не пустого объекта типа Т из пустой очереди и выбрасывает NoSuchElementException.
     * Сценарий, при котором объект успешно отрабатывает удаление
     * не пустого объекта типа Т из не пустой очереди и возвращает "d_last".
     */
    @Test
    void remove_Test() {
        Throwable throwable = Assertions.assertThrows(
                NoSuchElementException.class, () -> customEmptyPriorityQueue.remove());
        Assertions.assertEquals(
                "Очередь пустая!", throwable.getMessage());

        Assertions.assertEquals(4, customNotEmptyPriorityQueue.size());
        Assertions.assertEquals("d_last", customNotEmptyPriorityQueue.remove());
        Assertions.assertEquals(3, customNotEmptyPriorityQueue.size());
    }

    /**
     * Метод {@link CustomPriorityQueueImplTest#toString()}
     * Проверяет отображение экземпляр объекта {@link CustomPriorityQueueImpl}.
     * Сценарий, при котором не пустой экземпляр проверяется на отображение
     * тестовых строк.
     */
    @Test
    public void toString_Test() {
        Assertions.assertEquals(
                "[ a_first b_second c_third d_last ]", customNotEmptyPriorityQueue.toString());
        Assertions.assertEquals(
                "[ ]", customEmptyPriorityQueue.toString());
    }

    /**
     * Метод {@link CustomPriorityQueueImplTest#toArray_Test} проверяет создание копии связанного списка.
     * Сценарий проверяет идентичность созданной копии и исходной очереди.
     */
    @Test
    void toArray_Test() {
        Assert.assertEquals(
                Arrays.asList("a_first", "b_second", "c_third", "d_last"),
                Arrays.asList(customNotEmptyPriorityQueue.toArray()));
    }

}