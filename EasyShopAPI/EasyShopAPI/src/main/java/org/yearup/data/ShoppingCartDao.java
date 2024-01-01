package org.yearup.data;

import org.yearup.models.ShoppingCart;

public interface ShoppingCartDao {
    ShoppingCart getByUserId(int userId);
    ShoppingCart addItem(int productId, int userId);
    ShoppingCart updateProductInCart(int productId, int userId, int quantity);
    ShoppingCart removeItem(int productId, int userId);
    ShoppingCart clearCart(int userId);
}
