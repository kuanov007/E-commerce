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
                AdminUi.getHandleCallBackQuery(callbackQuery, botApiMethodMessage -> {
                    try {
                        execute(botApiMethodMessage);
                    } catch (TelegramApiException e) {
                        throw new RuntimeException(e);
                    }
                });
                return;
            }
            UserUi.getHandleCallBackQuery(callbackQuery, botApiMethodMessage -> {
                try {
                    execute(botApiMethodMessage);
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
            });
        } else if (update.hasMessage()) {
            Message message = update.getMessage();
            Long chatId = message.getChatId();
            if (isAdmin(chatId)) {
                AdminUi.getHandleMessage(message, partialBotApiMethod -> {
                    try {
                        if (partialBotApiMethod instanceof SendPhoto sendPhoto) {
                            execute(sendPhoto);
                        } else if (partialBotApiMethod instanceof SendMessage sendMessage) {
                            execute(sendMessage);
                        }
                    } catch (TelegramApiException e) {
                        System.out.println("Exception otdi: ");
                    }
                });
                return;
            }
            UserUi.getHandleMessage(message, botApiMethodMessage -> {
                try {
                    execute(botApiMethodMessage);
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
            });
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
