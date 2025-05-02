package uz.azamat.bot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import uz.azamat.ui.AdminUi;
import uz.azamat.ui.UserUi;

import java.util.ResourceBundle;

public class ECommerce extends TelegramLongPollingBot {
    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("settings");
    private final String username;

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
                AdminUi.getHandleCallBackQuery(callbackQuery, this::executeObj);
                return;
            }

            UserUi.getHandleCallBackQuery(callbackQuery, this::executeObj);
        } else if (update.hasMessage()) {
            Message message = update.getMessage();
            Long chatId = message.getChatId();

            if (isAdmin(chatId)) {
                AdminUi.getHandleMessage(message, this::executeObj);
                return;
            }

            UserUi.getHandleMessage(message, this::executeObj);
        }
    }

    private void executeObj(Object obj) {
        try {
            if (obj instanceof SendPhoto sendPhoto) {
                execute(sendPhoto);
            } else if (obj instanceof SendMessage sendMessage) {
                execute(sendMessage);
            }
        } catch (TelegramApiException ignored) {
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
