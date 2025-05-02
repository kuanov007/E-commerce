package uz.azamat.ui;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.PhotoSize;
import uz.azamat.ButtonService;
import uz.azamat.bot.AdminStates;
import uz.azamat.bot.ButtonNames;
import uz.azamat.product.Product;
import uz.azamat.storage.Storage;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import static uz.azamat.bot.AdminStates.*;

public class AdminUi {

    private static AdminStates adminState = null;

    public static void getHandleCallBackQuery(CallbackQuery callbackQuery, Consumer<Object> consumer) {

    }

    public static void getHandleMessage(Message message, Consumer<Object> consumer) {
        Long chatId = message.getChatId();

        if (message.hasText()) {
            String text = message.getText();
            if (text.equals("/start")) {
                setMainMenu(consumer, chatId);
            } else {
                if (isClickAnyButton(text)) {
                    ButtonNames clickedButton = ButtonNames.valueOf(text);
                    switch (clickedButton) {
                        case BACK -> {

                        }
                        case ADD_PRODUCT -> {
                            consumer.accept(new SendMessage(String.valueOf(chatId), "Iltimos mahsulot nomini kiriting: "));
                            adminState = ENTER_PRODUCT_NAME;
                            return;
                        }
                        case DELETE_PRODUCT -> {

                        }
                        case UPDATE_PRODUCT -> {

                        }
                        case SHOW_ALL_PRODUCT -> {

                        }
                    }
                }
                if (adminState != null) {
                    switch (adminState) {
                        case ENTER_PRODUCT_NAME -> {
                            Product product = Storage.temporaryProductsForAdmin.getOrDefault(chatId, new Product());
                            product.setName(text);
                            Storage.temporaryProductsForAdmin.put(chatId, product);
                            consumer.accept(new SendMessage(String.valueOf(chatId), "Mahsulot narxini kiriting: "));
                            adminState = ENTER_PRODUCT_PRICE;
                        }
                        case ENTER_PRODUCT_PRICE -> {
                            try {
                                Product product = Storage.temporaryProductsForAdmin.getOrDefault(chatId, new Product());
                                int price = Integer.parseInt(text);
                                product.setPrice(price);
                                consumer.accept(new SendMessage(String.valueOf(chatId), "Mahsulot soni kiriting: "));

                                adminState = ENTER_PRODUCT_QUANTITY;
                            } catch (Exception e) {
                                consumer.accept(new SendMessage(String.valueOf(chatId), "Iltimos narxni faqat raqam shaklida kiriting: "));
                            }
                        }
                        case ENTER_PRODUCT_QUANTITY -> {
                            try {
                                Product product = Storage.temporaryProductsForAdmin.getOrDefault(chatId, new Product());
                                int quantity = Integer.parseInt(text);
                                product.setQuantity(quantity);
                                consumer.accept(new SendMessage(String.valueOf(chatId), "Mahsulot suratini jo'nating: "));

                                adminState = SET_PRODUCT_PHOTO;
                            } catch (Exception e) {
                                consumer.accept(new SendMessage(String.valueOf(chatId), "Iltimos sonini faqat raqam shaklida kiriting: "));
                            }
                        }
                        case SET_PRODUCT_PHOTO -> {
                            consumer.accept(new SendMessage(String.valueOf(chatId), "Iltimos surat jo'nating!"));
                        }
                    }
                } else {
                    consumer.accept(new SendMessage(String.valueOf(chatId), "Kerakli bo'limni tanlang: "));
                }
            }
        } else if (message.hasPhoto()) {
            if (adminState.equals(SET_PRODUCT_PHOTO)) {
                List<PhotoSize> photos = message.getPhoto();
                PhotoSize lastPhoto = photos.get(photos.size() - 1);
                String photoId = lastPhoto.getFileId();

                Product product = Storage.temporaryProductsForAdmin.get(chatId);
                product.setPhotoId(photoId);
                product.setSold(true);
                Storage.existingProducts.add(product);
                Storage.temporaryProductsForAdmin.remove(chatId);
                consumer.accept(new SendMessage(String.valueOf(message.getChatId()), "Mahsulot qo'shildi!"));

                adminState = null;

                setMainMenu(consumer, chatId);
            } else {
                consumer.accept(new SendMessage(String.valueOf(chatId), "Error: 404"));
            }
        } else {
            consumer.accept(new SendMessage(String.valueOf(message.getChatId()), "Error: 404"));
        }
    }

    private static boolean isClickAnyButton(String text) {
        ButtonNames[] values = ButtonNames.values();
        return Arrays.stream(values).anyMatch(buttonNames -> {
            return buttonNames.getString().equals(text);
        });
    }

    private static void setMainMenu(Consumer<Object> consumer, Long chatId) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText("Kerakli bo'limni tanlang:");
        sendMessage.setChatId(chatId);
        sendMessage.setReplyMarkup(ButtonService.getMainMenuButtonsForAdmin());
        consumer.accept(sendMessage);
    }
}
