import example.bot.BotLogic;
import example.bot.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Тестирование класса BotLogic
 */
public class BotLogicTest {

    private BotLogic botLogic;
    private TestBot testBot;
    private User user;

    /**
     * Очистка botLogic, testBot, user путем создания новых экземпляров классов
     */
    @BeforeEach
    public void setUp() {
        testBot = new TestBot();
        botLogic = new BotLogic(testBot);
        user = new User(0L);
    }

    /**
     * Тестирование команды /test, отвечая правильно
     */
    @Test
    public void testTestCommandRightAnswer() {
        botLogic.processCommand(user, "/test");
        Assertions.assertEquals("Вычислите степень: 10^2", testBot.getLastMessage());
        botLogic.processCommand(user, "100");
        Assertions.assertEquals("Правильный ответ!", testBot.getMessages().get(1));
        Assertions.assertEquals("Сколько будет 2 + 2 * 2", testBot.getLastMessage());

        botLogic.processCommand(user, "6");
        Assertions.assertEquals("Правильный ответ!", testBot.getMessages().get(3));
        Assertions.assertEquals("Тест завершен", testBot.getLastMessage());
    }

    /**
     * Тестирование команды /test, отвечая неверно
     */
    @Test
    public void testTestCommandWrongAnswer() {
        botLogic.processCommand(user, "/test");
        Assertions.assertEquals("Вычислите степень: 10^2", testBot.getLastMessage());
        botLogic.processCommand(user, "1000");
        Assertions.assertEquals("Вы ошиблись, верный ответ: 100", testBot.getMessages().get(1));
        Assertions.assertEquals("Сколько будет 2 + 2 * 2", testBot.getLastMessage());
        botLogic.processCommand(user, "6");
        Assertions.assertEquals("Правильный ответ!", testBot.getMessages().get(3));
        Assertions.assertEquals("Тест завершен", testBot.getLastMessage());
    }

    /**
     * Тестирование команды /notify
     */
    @Test
    public void testNotifyCommand() throws InterruptedException{
        botLogic.processCommand(user, "/notify");
        Assertions.assertEquals("Введите текст напоминания", testBot.getLastMessage());
        botLogic.processCommand(user, "Текст");
        Assertions.assertEquals("Через сколько секунд напомнить?", testBot.getLastMessage());
        botLogic.processCommand(user, "1");
        Assertions.assertEquals("Напоминание установлено", testBot.getLastMessage());
        Assertions.assertEquals(3, testBot.getMessages().size());
        Thread.sleep(1020);
        Assertions.assertEquals("Сработало напоминание: 'Текст'",
                testBot.getLastMessage());
    }

    /**
     * Тестирование команды /repeat
     */
    @Test
    public void testRepeatCommand() {
        botLogic.processCommand(user, "/test");
        botLogic.processCommand(user, "1000");
        botLogic.processCommand(user, "6");
        botLogic.processCommand(user, "/repeat");
        Assertions.assertEquals("Вычислите степень: 10^2", testBot.getLastMessage());
        botLogic.processCommand(user, "100");
        Assertions.assertEquals("Правильный ответ!", testBot.getMessages().get(6));
        Assertions.assertEquals("Тест завершен", testBot.getLastMessage());
        botLogic.processCommand(user, "/repeat");
        Assertions.assertEquals("Нет вопросов для повторения", testBot.getLastMessage());
    }
}
