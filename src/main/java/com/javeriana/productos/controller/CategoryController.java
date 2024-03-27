package com.javeriana.productos.controller;

import com.javeriana.productos.dto.CategoryDTO;
import com.javeriana.productos.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import com.javeriana.productos.service.implementation.CategoryServiceImplementacion;
import com.javeriana.productos.entities.Category;

import java.util.List;

@RestController
@RequestMapping("api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
        List<CategoryDTO> categories = categoryService.getAllCategories();
        if (categories != null && !categories.isEmpty())
            return new ResponseEntity<>(categories, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable Long id) {
        CategoryDTO category = categoryService.getCategoryById(id);
        if (category == null)
            return new ResponseEntity<>(category, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/categoryEntry")
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryDTO category) {
        return new ResponseEntity<>( categoryService.createCategory( category ), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable Long id, @RequestBody CategoryDTO category) {
        CategoryDTO updatedCategory = categoryService.updateCategory(id, category);
        if (updatedCategory == null)
            return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        CategoryDTO currentCategory = categoryService.getCategoryById(id);
        if (currentCategory == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
            categoryService.deleteCategory(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
