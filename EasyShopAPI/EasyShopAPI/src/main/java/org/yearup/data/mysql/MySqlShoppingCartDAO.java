package org.yearup.data.mysql;

import org.springframework.stereotype.Component;
import org.yearup.data.ShoppingCartDao;
import org.yearup.models.ShoppingCart;
import org.yearup.models.ShoppingCartItem;

import javax.sql.DataSource;

@Component
public class MySqlShoppingCartDAO extends MySqlDaoBase implements ShoppingCartDao {
    public MySqlShoppingCartDAO(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public ShoppingCart getByUserId(int userId) {
        return null;
    }

    @Override
    public void addProductToCart(int userId, ShoppingCartItem item) {

    }

    @Override
    public void removeProductFromCart(int productId, int userId) {

    }

    @Override
    public void updateProductQuantity(int productId, int userId, int quantity) {

    }

    @Override
    public void clearCart(int userId) {

    }
}
