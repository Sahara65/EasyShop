package org.yearup.data.mysql;

import org.springframework.stereotype.Component;
import org.yearup.data.ShoppingCartDao;
import org.yearup.models.*;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
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

        ShoppingCart shoppingCart = new ShoppingCart();

        try (Connection connection = getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement("""
                    SELECT p.*, sc.quantity, sc.user_id
                    FROM products AS p
                    JOIN shopping_cart AS sc ON p.product_id = sc.product_id
                    WHERE user_id = ?
                    """);

            preparedStatement.setInt(1, userId);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                ShoppingCartItem shoppingCartItem = mapRow(resultSet);
                shoppingCart.add(shoppingCartItem);
            }
            return shoppingCart;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ShoppingCart addProductToCart(int userId, int productId) {

        ShoppingCart shoppingCart = getByUserId(userId);

        try (Connection connection = getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO shopping_cart (user_id, product_id) VALUES (?, ?)",
                    PreparedStatement.RETURN_GENERATED_KEYS);

            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, productId);
            preparedStatement.executeUpdate();

            return shoppingCart;

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

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "DELETE FROM shopping_cart WHERE user_id = ?",
                    PreparedStatement.RETURN_GENERATED_KEYS);

            preparedStatement.setInt(1, userId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    protected static ShoppingCartItem mapRow(ResultSet row) throws SQLException {
        int productId = row.getInt("product_id");
        String name = row.getString("name");
        BigDecimal price = row.getBigDecimal("price");
        int categoryId = row.getInt("category_id");
        String description = row.getString("description");
        String color = row.getString("color");
        int stock = row.getInt("stock");
        boolean isFeatured = row.getBoolean("featured");
        String imageUrl = row.getString("image_url");

        Product product = new Product(productId, name, price, categoryId, description, color, stock, isFeatured, imageUrl);
        ShoppingCartItem shoppingCartItem = new ShoppingCartItem();
        shoppingCartItem.setProduct(product);
        shoppingCartItem.setQuantity(row.getInt("quantity"));

        return shoppingCartItem;
    }
}
