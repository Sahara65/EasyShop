package org.yearup.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.yearup.data.CategoryDao;
import org.yearup.data.ProductDao;
import org.yearup.models.Category;
import org.yearup.models.Product;

import java.util.List;

@RestController
@RequestMapping("/categories")
@CrossOrigin
public class CategoriesController {

    private final CategoryDao categoryDao;
    private final ProductDao productDao;

    @Autowired
    public CategoriesController(CategoryDao categoryDao, ProductDao productDao) {
        this.categoryDao = categoryDao;
        this.productDao = productDao;
    }

    @GetMapping("categories")
    @PreAuthorize("permitAll()")
    public List<Category> getAll() {

        return categoryDao.getAllCategories();
    }


    @GetMapping("{id}")
    @PreAuthorize("permitAll()")
    public Category getById(@PathVariable int id) {

        return categoryDao.getById(id);
    }

    @GetMapping("{categoryId}/products")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<Product> getProductsById(@PathVariable int categoryId) {

        return productDao.listByCategoryId(categoryId);
    }

    @PostMapping()
    @PreAuthorize("permitAll()")
    public Category addCategory(@RequestBody Category category) {

        return categoryDao.create(category);
    }

    @PutMapping("{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void updateCategory(@PathVariable int id, @RequestBody Category category) {

        categoryDao.update(id, category);
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('admin')")
    public void deleteCategory(@PathVariable int id) {

        categoryDao.delete(id);
    }
}
