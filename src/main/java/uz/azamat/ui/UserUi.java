package uz.azamat.ui;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.function.Function;

public class UserUi {
    public static void getHandleCallBackQuery(CallbackQuery callbackQuery, Function<Object, Integer> function) {

    }

    public static void getHandleMessage(Message message, Function<Object, Integer> function) {
        Long chatId = message.getChatId();
        if (message.hasText()) {

        } else if (message.hasPhoto()) {

        } else {
            function.apply(new SendMessage(String.valueOf(message.getChatId()), "Error: 404"));
        }
    }
}
