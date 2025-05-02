package uz.azamat.storage;

import uz.azamat.bot.AdminStates;
import uz.azamat.product.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public interface Storage {
    List<Product> existingProducts = new ArrayList<>();
    ConcurrentHashMap<Long, Product> temporaryProductsForAdmin = new ConcurrentHashMap<>();
    ConcurrentHashMap<Long, Product> temporaryProductsForCustomer = new ConcurrentHashMap<>();
    AdminStates adminState = null;
}
