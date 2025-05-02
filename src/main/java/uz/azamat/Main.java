package uz.azamat;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import uz.azamat.bot.ECommerce;

import java.util.ResourceBundle;

public class Main {
    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("settings");

    public static void main(String[] args) {
        try {
            TelegramBotsApi bot = new TelegramBotsApi(DefaultBotSession.class);
            ECommerce ecommerce = new ECommerce(
                    resourceBundle.getString("bot.token"),
                    resourceBundle.getString("bot.username")
            );
            bot.registerBot(ecommerce);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}