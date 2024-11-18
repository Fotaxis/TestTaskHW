import example.bot.Bot;

import java.util.ArrayList;
import java.util.List;

/**
 * Бот, сохраняющий все свои сообщения в списке
 */
public class TestBot implements Bot {

    private final List<String> messages = new ArrayList<>();

    @Override
    public void sendMessage(Long chatId, String message) {
        messages.add(message);
    }

    /**
     *  Получить список сообщений от бота
     */
    public List<String> getMessages() {
        return messages;
    }

    /**
     *  Получить последнее сообщение от бота
     */
    public String getLastMessage() {
        return messages.getLast();
    }
}
