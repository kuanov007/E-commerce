package uz.azamat.bot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.*;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import uz.azamat.ui.AdminUi;
import uz.azamat.ui.UserUi;

import java.util.ResourceBundle;
import java.util.function.Function;

public class ECommerce extends TelegramLongPollingBot {
    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("settings");
    private final String username;
    private final Function<Object, Integer> executeFlow = (object -> {
        try {
            if (object instanceof SendMessage sendMessage) {
                return execute(sendMessage).getMessageId();
            } else if (object instanceof SendPhoto sendPhoto) {
                return execute(sendPhoto).getMessageId();
            } else if (object instanceof SendVoice sendVoice) {
                return execute(sendVoice).getMessageId();
            } else if (object instanceof SendVideo sendVideo) {
                return execute(sendVideo).getMessageId();
            } else if (object instanceof SendAudio sendAudio) {
                return execute(sendAudio).getMessageId();
            } else if (object instanceof SendDocument sendDocument) {
                return execute(sendDocument).getMessageId();
            }
            return null;
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    });

    public ECommerce(String botToken, String username) {
        super(botToken);
        this.username = username;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasCallbackQuery()) {
            CallbackQuery callbackQuery = update.getCallbackQuery();
            Long chatId = callbackQuery.getFrom().getId();

            if (isAdmin(chatId)) {
                AdminUi.getHandleCallBackQuery(callbackQuery, executeFlow);
                return;
            }

            UserUi.getHandleCallBackQuery(callbackQuery, executeFlow);
        } else if (update.hasMessage()) {
            Message message = update.getMessage();
            Long chatId = message.getChatId();

            if (isAdmin(chatId)) {
                AdminUi.getHandleMessage(message, executeFlow);
                return;
            }

            UserUi.getHandleMessage(message, executeFlow);
        }
    }

    private boolean isAdmin(Long chatId) {
        return (resourceBundle.getString("AdminChatId").equals(String.valueOf(chatId)));
    }

    @Override
    public String getBotUsername() {
        return username;
    }
}
