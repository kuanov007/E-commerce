package uz.azamat;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import uz.azamat.bot.ButtonNames;
import uz.azamat.product.Product;
import uz.azamat.storage.Storage;

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

    static InlineKeyboardMarkup getInlineButtonsProductsByPage(int page) {
        List<Product> productsSubList = Storage.getProductsSubList(page);

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        List<List<InlineKeyboardButton>> rows = new ArrayList<>();
        List<InlineKeyboardButton> row = new ArrayList<>();

        for (int i = 0; i < productsSubList.size(); i++) {
            if (i % 5 == 0) {
                rows.add(row);
                row = new ArrayList<>();
            }

            InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton();
            inlineKeyboardButton.setText(String.valueOf(i + 1));
            inlineKeyboardButton.setCallbackData(String.valueOf(i + 1));
            row.add(inlineKeyboardButton);
        }

        List<InlineKeyboardButton> operationButtons = getInlineKeyboardButtons(page);
        rows.add(operationButtons);

        inlineKeyboardMarkup.setKeyboard(rows);
        return inlineKeyboardMarkup;
    }

    private static List<InlineKeyboardButton> getInlineKeyboardButtons(int page) {
        List<InlineKeyboardButton> operationButtons = new ArrayList<>();

        if (page > 1) {
            InlineKeyboardButton previousButton = new InlineKeyboardButton();
            previousButton.setText("Previous");
            previousButton.setCallbackData("previous");
            operationButtons.add(previousButton);
        }
        InlineKeyboardButton deleteButton = new InlineKeyboardButton();
        deleteButton.setText("X");
        deleteButton.setCallbackData("delete");
        operationButtons.add(deleteButton);
        if ((page + 1) * 10 - 1 < Storage.existingProducts.size()) {
            InlineKeyboardButton nextButton = new InlineKeyboardButton();
            nextButton.setText("Next");
            nextButton.setCallbackData("next");
            operationButtons.add(nextButton);
        }
        return operationButtons;
    }

}
