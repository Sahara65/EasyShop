package org.yearup.data;

import org.yearup.models.ShoppingCart;
import org.yearup.models.ShoppingCartItem;

public interface ShoppingCartDao {
    ShoppingCart getByUserId(int userId);

    void addProductToCart(int userId, int productId);
    void removeProductFromCart(int productId, int userId);
    void updateProductQuantity(int productId, int userId, int quantity);
    void clearCart(int userId);
}