package uz.azamat;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import uz.azamat.bot.ButtonNames;

import java.util.ArrayList;
import java.util.List;

public interface ButtonService {
    static ReplyKeyboardMarkup getMainMenuButtonsForAdmin() {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();

        List<KeyboardRow> rows = new ArrayList<>();

        KeyboardRow firstRow = new KeyboardRow();
        KeyboardRow secondRow = new KeyboardRow();

        firstRow.add(ButtonNames.ADD_PRODUCT.getString());
        firstRow.add(ButtonNames.DELETE_PRODUCT.getString());

        secondRow.add(ButtonNames.UPDATE_PRODUCT.getString());
        secondRow.add(ButtonNames.SHOW_ALL_PRODUCT.getString());

        rows.add(firstRow);
        rows.add(secondRow);

        replyKeyboardMarkup.setKeyboard(rows);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);

        return replyKeyboardMarkup;
    }

    static ReplyKeyboardMarkup getMainMenuButtonsForCustomer() {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();

        List<KeyboardRow> rows = new ArrayList<>();

        KeyboardRow firstRow = new KeyboardRow();
        KeyboardRow secondRow = new KeyboardRow();

        firstRow.add(ButtonNames.BUY_PRODUCT.getString());
        firstRow.add(ButtonNames.SHOW_BASKET.getString());

        secondRow.add(ButtonNames.HISTORY.getString());

        rows.add(firstRow);
        rows.add(secondRow);

        replyKeyboardMarkup.setKeyboard(rows);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);

        return replyKeyboardMarkup;
    }

    static ReplyKeyboardMarkup setBackButton() {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();

        List<KeyboardRow> rows = new ArrayList<>();

        KeyboardRow firstRow = new KeyboardRow();
        firstRow.add(ButtonNames.BACK.getString());

        rows.add(firstRow);

        replyKeyboardMarkup.setKeyboard(rows);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);
        return replyKeyboardMarkup;
    }
}
