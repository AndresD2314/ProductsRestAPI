package com.javeriana.productos.service;

import com.javeriana.productos.dto.CategoryDTO;
import com.javeriana.productos.entities.Category;
import java.util.List;

public interface CategoryService {
    List<CategoryDTO> getAllCategories();

    CategoryDTO getCategoryById(Long id);

    CategoryDTO createCategory(CategoryDTO category);

    CategoryDTO updateCategory(Long id, CategoryDTO category);

    void deleteCategory(Long id);
}
