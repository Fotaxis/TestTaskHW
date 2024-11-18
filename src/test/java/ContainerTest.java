import example.container.Container;
import example.container.Item;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *  Тестирование класса Container на добавление и удаление
 */
public class ContainerTest {

    private Container container;

    /**
     *  Очистка container путем создания нового
     */
    @BeforeEach
    public void setUp() {
        container = new Container();
    }

    /**
     *  Проверка на добавление Item
     */
    @Test
    public void addItemTest() {
        Item itemToAdd = new Item(1234);
        Assertions.assertTrue(container.add(itemToAdd));
        Item receivedItem = container.get(0);
        Assertions.assertEquals(1, container.size());
        Assertions.assertEquals(itemToAdd.getNum(), receivedItem.getNum());
    }

    /**
     *  Проверка на удаление добавленного Item
     */
    @Test
    public void deleteItemTest() {
        Item item = new Item(1234);
        container.add(item);
        boolean isRemoved = container.remove(item);
        Assertions.assertTrue(isRemoved);
        Assertions.assertEquals(0, container.size());
        Assertions.assertFalse(container.contains(item));
    }

    /**
     *  Проверка на удаление не добавленного Item
     */
    @Test
    public void deleteNotAddedItemTest() {
        Item itemToAdd = new Item(1234);
        Item itemNotToAdd = new Item(4321);
        container.add(itemToAdd);
        Assertions.assertFalse(container.remove(itemNotToAdd));
        Assertions.assertEquals(1, container.size());
        Assertions.assertTrue(container.contains(itemToAdd));
    }
}
