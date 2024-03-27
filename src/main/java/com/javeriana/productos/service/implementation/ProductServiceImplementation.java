package com.javeriana.productos.service.implementation;

import com.javeriana.productos.dto.ProductDTO;
import com.javeriana.productos.entities.Product;
import com.javeriana.productos.repository.ProductRepository;
import com.javeriana.productos.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImplementation implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<ProductDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(product -> mapToDTO(product)).collect(Collectors.toList());
    }

    @Override
    public ProductDTO getProductById(Long id) {
        Product product = productRepository.findById( id ).orElse( null );

        if ( product == null )
            return null;

        return mapToDTO( product );
    }

    @Override
    public ProductDTO createProduct(ProductDTO product) {
        Product currentProduct = mapToEntity( product );
        Product newProduct = productRepository.save( currentProduct );

        return mapToDTO( newProduct );
    }

    @Override
    public ProductDTO updateProduct(Long id, ProductDTO product) {
        Product currentProduct = productRepository.findById( id ).orElse( null );

        if ( currentProduct == null )
            return null;

        currentProduct.setCreationDate( product.getCreationDate() );
        currentProduct.setCategory( product.getCategory() );
        currentProduct.setPrice( product.getPrice() );
        currentProduct.setStock(product.getStock() );

        Product updatedProduct = productRepository.save( currentProduct );
        return mapToDTO( updatedProduct );
    }

    @Override
    public void deleteProduct(Long id) {
        Product product = productRepository.findById( id ).orElse( null );

        productRepository.delete( product );
    }

    private ProductDTO mapToDTO ( Product product ) {
        ProductDTO productDTO = new ProductDTO();

        productDTO.setCategory( product.getCategory() );
        productDTO.setStock( productDTO.getStock() );
        productDTO.setPrice( productDTO.getPrice() );
        productDTO.setCreationDate( productDTO.getCreationDate() );
        productDTO.setId( product.getId() );

        return productDTO;
    }

    private Product mapToEntity ( ProductDTO productDTO ) {
        Product product = new Product();

        product.setPrice( productDTO.getPrice() );
        product.setStock( productDTO.getStock() );
        product.setCategory( productDTO.getCategory() );
        product.setCreationDate( productDTO.getCreationDate() );

        return product;
    }
}
