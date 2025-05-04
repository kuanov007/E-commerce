package uz.azamat.ui;

import uz.azamat.product.Product;
import uz.azamat.storage.Storage;

import java.util.List;

public class Pagination {

    public static String getSubString(int page) {
        List<Product> productsSubList = Storage.getProductsSubList(page);
        StringBuilder sb = new StringBuilder();

        sb.append((page - 1) * 10).append(" - ").append(Math.min(page * 10 - 1, Storage.existingProducts.size()))
                .append(" / ").append(Storage.existingProducts.size());

        for (int i = 0; i < productsSubList.size(); i++) {
            sb.append(i + 1).append(") ").append(productsSubList.get(i).getName()).append("\n");
        }

        return sb.toString();
    }


}
