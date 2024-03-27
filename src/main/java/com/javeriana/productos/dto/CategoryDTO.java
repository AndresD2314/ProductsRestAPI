package com.javeriana.productos.dto;

import com.javeriana.productos.entities.Product;
import lombok.Data;

import java.util.List;

@Data
public class CategoryDTO {
    private Long id;
    private String name;
    private List<ProductDTO> products;
}
