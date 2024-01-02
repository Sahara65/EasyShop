//package org.yearup.controllers;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.server.ResponseStatusException;
//import org.yearup.data.ProductDao;
//import org.yearup.data.ShoppingCartDao;
//import org.yearup.data.UserDao;
//import org.yearup.models.ShoppingCart;
//import org.yearup.models.User;
//
//import java.security.Principal;
//
//// only logged in users should have access to these actions
//@RestController
//@RequestMapping("/cart")
//@CrossOrigin
//@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
//public class ShoppingCartController {
//
//    private final ShoppingCartDao shoppingCartDao;
//    private final UserDao userDao;
//    private final ProductDao productDao;
//
//    @Autowired
//    public ShoppingCartController(ShoppingCartDao shoppingCartDao, UserDao userDao, ProductDao productDao) {
//        this.shoppingCartDao = shoppingCartDao;
//        this.userDao = userDao;
//        this.productDao = productDao;
//    }
//
//    @RequestMapping("/products")
//    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
//    public ShoppingCart getCart(Principal principal) {
//
//        try {
//
//            String userName = principal.getName();
//            User user = userDao.getByUserName(userName);
//            int userId = user.getId();
//
//            return shoppingCartDao.getByUserId(userId);
//
//        } catch (Exception e) {
//            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Oops... our bad.");
//        }
//    }
//
//    // add a POST method to add a product to the cart - the url should be
//    // https://localhost:8080/cart/products/15 (15 is the productId to be added
//    @PostMapping("/products/{productId}")
//    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
//    public ShoppingCart addProductToCart(Principal principal, @PathVariable int productId) {
//        try {
//
//            String userName = principal.getName();
//            User user = userDao.getByUserName(userName);
//            int userId = user.getId();
//
//        // TODO - Update this return
//            return null;
//
//        } catch (Exception e) {
//            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Oops... our bad.");
//        }
//
//    }
//
//
//
//    // add a PUT method to update an existing product in the cart - the url should be
//    // https://localhost:8080/cart/products/15 (15 is the productId to be updated)
//    // the BODY should be a ShoppingCartItem - quantity is the only value that will be updated
//    @PutMapping("/products/{productId}")
//    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
//    public ShoppingCart updateProductInCart(Principal principal, @PathVariable int productId) {
//        try {
//            String userName = principal.getName();
//            User user = userDao.getByUserName(userName);
//            int userId = user.getId();
//
//            // TODO - Update this return
//            return null;
//
//        } catch (Exception e) {
//            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Oops... our bad.");
//        }
//    }
//
//    // add a DELETE method to clear all products from the current users cart
//    // https://localhost:8080/cart
//    @DeleteMapping("/cart")
//    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
//    public void clearCart(Principal principal) {
//        try {
//            String userName = principal.getName();
//            User user = userDao.getByUserName(userName);
//            int userId = user.getId();
//
//
//
//        } catch (Exception e) {
//            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Oops... our bad.");
//        }
//    }
//}
