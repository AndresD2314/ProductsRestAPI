package com.javeriana.productos.dto;

import com.javeriana.productos.entities.Category;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
public class ProductDTO {
    private Long id;
    private Double price;
    private Date creationDate;
    private Integer stock;
    private Category category;
}
