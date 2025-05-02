package uz.azamat.ui;

import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.methods.botapimethods.BotApiMethodMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.PhotoSize;
import uz.azamat.product.Product;
import uz.azamat.storage.Storage;

import java.util.List;
import java.util.function.Consumer;

public class UserUi {
    public static void getHandleCallBackQuery(CallbackQuery callbackQuery, Consumer<BotApiMethodMessage> consumer) {

    }

    public static void getHandleMessage(Message message, Consumer<PartialBotApiMethod> consumer) {
        Long chatId = message.getChatId();
        if (message.hasText()) {
            String text = message.getText();
            if(text.equals("/start")){
                consumer.accept(new SendMessage(String.valueOf(chatId), "Admin" + message.getFrom().getUserName()));
            }else{

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
