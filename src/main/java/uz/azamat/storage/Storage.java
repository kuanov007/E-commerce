package uz.azamat.storage;

import uz.azamat.bot.History;
import uz.azamat.product.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public interface Storage {
    List<Product> existingProducts = new ArrayList<>();
    ConcurrentHashMap<Long, Product> temporaryProductsForAdmin = new ConcurrentHashMap<>();
    ConcurrentHashMap<Long, Product> temporaryProductsForCustomer = new ConcurrentHashMap<>();
    List<History> histories = new ArrayList<>();

    static List<Product> getProductsSubList(int page) {
        List<Product> existingProducts = Storage.existingProducts;
        int start = (page - 1) * 10;
        if (start < existingProducts.size()) {
            return existingProducts.subList(start, Math.min(page * 10 - 1, existingProducts.size()));
        }
        return null;
    }
}
