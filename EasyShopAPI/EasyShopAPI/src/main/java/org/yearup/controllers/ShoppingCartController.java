package org.yearup.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.yearup.data.ProductDao;
import org.yearup.data.ShoppingCartDao;
import org.yearup.data.UserDao;
import org.yearup.models.ShoppingCart;
import org.yearup.models.ShoppingCartItem;
import org.yearup.models.User;

import java.security.Principal;

import static org.yearup.controllers.CategoriesController.throwInternalServerErrorResponse;

@RestController
@RequestMapping("/cart")
@CrossOrigin
@PreAuthorize("isAuthenticated()")
public class ShoppingCartController {

    private final ShoppingCartDao shoppingCartDao;
    private final UserDao userDao;

    @Autowired
    public ShoppingCartController(ShoppingCartDao shoppingCartDao, UserDao userDao, ProductDao productDao) {
        this.shoppingCartDao = shoppingCartDao;
        this.userDao = userDao;
    }

    @GetMapping()
    public ShoppingCart getCart(Principal principal) {

        String userName = principal.getName();
        User user = userDao.getByUserName(userName);
        int userId = user.getId();

        return shoppingCartDao.getByUserId(userId);
    }

    @PostMapping("/products/{productId}")
    public ShoppingCart addProductToCart(Principal principal, @PathVariable int productId) {

        String userName = principal.getName();
        User user = userDao.getByUserName(userName);
        int userId = user.getId();

        ShoppingCart cart = shoppingCartDao.getByUserId(userId);

        if (cart.contains(productId)) {
            ShoppingCartItem item = cart.get(productId);
            item.setQuantity(item.getQuantity() + 1);

            shoppingCartDao.updateProductQuantity(productId, userId, item.getQuantity());

        } else {
            shoppingCartDao.addProductToCart(userId, productId);
        }

        return shoppingCartDao.getByUserId(userId);
    }

    @PutMapping("/products/{productId}")
    public ShoppingCart updateProductQuantity(Principal principal, @PathVariable int productId, @RequestParam int quantity) {

        String userName = principal.getName();
        User user = userDao.getByUserName(userName);
        int userId = user.getId();

        shoppingCartDao.updateProductQuantity(productId, userId, quantity);

        return shoppingCartDao.getByUserId(userId);
    }

    @DeleteMapping("/products/{productId}")
    public void removeProductFromCart(Principal principal, @PathVariable int productId) {

        String userName = principal.getName();
        User user = userDao.getByUserName(userName);
        int userId = user.getId();

        shoppingCartDao.removeProductFromCart(productId, userId);
    }

    @DeleteMapping()
    public ShoppingCart clearCart(Principal principal) {

        String userName = principal.getName();
        User user = userDao.getByUserName(userName);
        int userId = user.getId();

        shoppingCartDao.clearCart(userId);

        return shoppingCartDao.getByUserId(userId);
    }
}
