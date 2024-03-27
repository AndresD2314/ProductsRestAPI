package com.javeriana.productos.service.implementation;

import com.javeriana.productos.dto.CategoryDTO;
import com.javeriana.productos.dto.ProductDTO;
import com.javeriana.productos.entities.Category;
import com.javeriana.productos.entities.Product;
import com.javeriana.productos.service.CategoryService;
import com.javeriana.productos.repository.CategoryRepository;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImplementacion implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<CategoryDTO> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream().map( category ->  mapToDTO( category ) ).collect(Collectors.toList());
    }

    @Override
    public CategoryDTO getCategoryById(Long id) {
        Category category = categoryRepository.findById( id ).orElse( null );

        if ( category == null )
            return null;

        return mapToDTO( category );
    }

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Category newCategory = mapToEntity( categoryDTO );
        Category savedCategory = categoryRepository.save( newCategory );
        return mapToDTO( savedCategory );
    }

    @Override
    public CategoryDTO updateCategory(Long id, CategoryDTO categoryDTO) {
        Category currentCategory = categoryRepository.findById(id).orElse(null);

        if (currentCategory == null)
            return null;

        currentCategory.setName(categoryDTO.getName());
        currentCategory.setProducts(categoryDTO.getProducts().stream()
                .map(this::mapProductDTOToEntity)
                .collect(Collectors.toList()));

        Category updatedCategory = categoryRepository.save(currentCategory);
        return mapToDTO(updatedCategory);
    }

    @Override
    public void deleteCategory(Long id) {
        Category category = categoryRepository.findById( id ).orElse( null );
        categoryRepository.delete( category );

    }

    private CategoryDTO mapToDTO( Category category ) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId( category.getId() );
        categoryDTO.setName( category.getName() );

        List<ProductDTO> productDTOList = category.getProducts()
                .stream()
                .map(product -> mapProductEntityToDTO(product))
                .collect(Collectors.toList());

        categoryDTO.setProducts(productDTOList);

        return categoryDTO;
    }

    private Category mapToEntity(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setName(categoryDTO.getName());

        // Convertir la lista de ProductDTO a Product
        List<Product> productList = categoryDTO.getProducts()
                .stream()
                .map(productDTO -> mapProductDTOToEntity(productDTO))
                .collect(Collectors.toList());

        category.setProducts(productList);

        return category;
    }

    private ProductDTO mapProductEntityToDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();

        productDTO.setCategory( product.getCategory() );
        productDTO.setStock( productDTO.getStock() );
        productDTO.setPrice( productDTO.getPrice() );
        productDTO.setCreationDate( productDTO.getCreationDate() );
        productDTO.setId( product.getId() );

        return productDTO;
    }

    private Product mapProductDTOToEntity(ProductDTO productDTO) {
        Product product = new Product();

        product.setPrice( productDTO.getPrice() );
        product.setStock( productDTO.getStock() );
        product.setCategory( productDTO.getCategory() );
        product.setCreationDate( productDTO.getCreationDate() );

        return product;
    }
}
