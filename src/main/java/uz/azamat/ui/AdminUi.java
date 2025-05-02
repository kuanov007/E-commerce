package uz.azamat.ui;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.PhotoSize;
import uz.azamat.ButtonService;
import uz.azamat.product.Product;
import uz.azamat.storage.Storage;

import java.util.List;
import java.util.function.Consumer;

public class AdminUi {
    public static void getHandleCallBackQuery(CallbackQuery callbackQuery, Consumer<Object> consumer) {

    }

    public static void getHandleMessage(Message message, Consumer<Object> consumer) {
        Long chatId = message.getChatId();

        if (message.hasText()) {
            String text = message.getText();
            if (text.equals("/start")) {
                SendMessage sendMessage = new SendMessage();
                sendMessage.setText("Kerakli bo'limni tanlang:");
                sendMessage.setChatId(chatId);
                sendMessage.setReplyMarkup(ButtonService.getMainMenuButtonsForAdmin());
                consumer.accept(sendMessage);
            }
        } else if (message.hasPhoto()) {
            List<PhotoSize> photos = message.getPhoto();
            String photoId = photos.get(photos.size() - 1).getFileId();

            Product orDefault = Storage.temporaryProductsForAdmin.getOrDefault(chatId, new Product());
            orDefault.setPhotoId(photoId);
            Storage.temporaryProductsForAdmin.put(chatId, orDefault);
        } else {
            consumer.accept(new SendMessage(String.valueOf(message.getChatId()), "Error: 404"));
        }
    }
}
