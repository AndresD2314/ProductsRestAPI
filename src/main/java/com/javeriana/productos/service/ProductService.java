package com.javeriana.productos.service;

import com.javeriana.productos.dto.ProductDTO;
import com.javeriana.productos.entities.Product;

import java.util.List;

public interface ProductService {
    List<ProductDTO> getAllProducts();

    ProductDTO getProductById(Long id);

    ProductDTO createProduct(ProductDTO product);

    ProductDTO updateProduct(Long id, ProductDTO product);

    void deleteProduct(Long id);
}
