package com.rest.api.controllers;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import com.rest.api.dtos.ProductDto;
import com.rest.api.models.ProductModel;
import com.rest.api.repository.ProductRepositorie;
import com.rest.api.models.ApiResponse;


@RestController
@RequestMapping("products")
public class ProductsController {

    @Autowired
    ProductRepositorie repository;

    // Pega produto por id
    @GetMapping(value = "/{id}")
    public ResponseEntity<ApiResponse<ProductModel>> getProduct(@PathVariable(value = "id") Integer id) {
        Optional<ProductModel> product = repository.findById(id);
        
        if (product.isPresent()) {
            ApiResponse<ProductModel> response = new ApiResponse<>(product.get(), "Product found");
            return ResponseEntity.ok(response);
        } 
        ApiResponse<ProductModel> response = new ApiResponse<>(null, "Product not found");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    // Adiciona produto
    @PostMapping(value = "/add")
    public ResponseEntity<ApiResponse<ProductModel>> addProduct(@RequestBody ProductDto dto) {
        var product = new ProductModel();
        BeanUtils.copyProperties(dto, product);
        ProductModel savedProduct = repository.save(product);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedProduct.getId())
                .toUri();

        ApiResponse<ProductModel> response = new ApiResponse<>(savedProduct, "Product created successfully");
        return ResponseEntity.created(location).body(response);
    }

    // Atualiza produto por id
    @PutMapping(value = "/put/{id}")
    public ResponseEntity<ApiResponse<ProductModel>> putProduct(@PathVariable(value = "id") Integer id, @RequestBody ProductDto dto) {
        Optional<ProductModel> product = repository.findById(id);
        
        if (product.isEmpty()) {
            ApiResponse<ProductModel> response = new ApiResponse<>(null, "Product not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        var productResponse = product.get();
        BeanUtils.copyProperties(dto, productResponse);
        repository.save(productResponse);
        ApiResponse<ProductModel> response = new ApiResponse<>(productResponse, "Product updated successfully");
        return ResponseEntity.ok(response);
    }

    // Deleta produto pelo id
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteProduct(@PathVariable(value = "id") Integer id) {
        Optional<ProductModel> product = repository.findById(id);
        
        if (product.isEmpty()) {
            ApiResponse<Void> response = new ApiResponse<>(null, "Product not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        repository.delete(product.get());
        ApiResponse<Void> response = new ApiResponse<>(null, "Product deleted successfully");
        return ResponseEntity.ok(response);
    }

    // Pega todos os produtos
    @GetMapping(value = "/all")
    public ResponseEntity<ApiResponse<List<ProductModel>>> getAllProducts() {
        List<ProductModel> listProducts = repository.findAll();
        ApiResponse<List<ProductModel>> response = new ApiResponse<>(listProducts, "Products retrieved successfully");
        return ResponseEntity.ok(response);
    }
}
