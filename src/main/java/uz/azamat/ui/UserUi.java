package uz.azamat.ui;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.function.Consumer;

public class UserUi {
    public static void getHandleCallBackQuery(CallbackQuery callbackQuery, Consumer<Object> consumer) {

    }

    public static void getHandleMessage(Message message, Consumer<Object> consumer) {
        Long chatId = message.getChatId();
        if (message.hasText()) {

        } else if (message.hasPhoto()) {

        } else {
            consumer.accept(new SendMessage(String.valueOf(message.getChatId()), "Error: 404"));
        }
    }
}
