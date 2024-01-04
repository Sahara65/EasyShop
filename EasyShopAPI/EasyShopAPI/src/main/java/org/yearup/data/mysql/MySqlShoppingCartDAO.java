package org.yearup.data.mysql;

import org.springframework.stereotype.Component;
import org.yearup.data.ShoppingCartDao;
import org.yearup.models.ShoppingCart;
import org.yearup.models.ShoppingCartItem;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

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
    public void addProductToCart(int userId, int productId) {

        try (Connection connection = getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO shopping_cart (user_id, product_id) VALUES (?, ?)",
                    PreparedStatement.RETURN_GENERATED_KEYS);

            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, productId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void removeProductFromCart(int productId, int userId) {

        try (Connection connection = getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "DELETE FROM shopping_cart WHERE product_id = ? AND user_id = ?",
                    PreparedStatement.RETURN_GENERATED_KEYS);

            preparedStatement.setInt(1, productId);
            preparedStatement.setInt(2, userId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateProductQuantity(int productId, int userId, int quantity) {

        try (Connection connection = getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE shopping_cart SET quantity = ? WHERE product_id = ? AND user_id = ?",
                    PreparedStatement.RETURN_GENERATED_KEYS);

            preparedStatement.setInt(1, quantity);
            preparedStatement.setInt(2, productId);
            preparedStatement.setInt(3, userId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void clearCart(int userId) {

        try (Connection connection = getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM shopping_cart WHERE user_id = ?",
                    PreparedStatement.RETURN_GENERATED_KEYS);

            preparedStatement.setInt(1, userId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }
}
