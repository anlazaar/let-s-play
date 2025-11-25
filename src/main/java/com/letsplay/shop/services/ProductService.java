package com.letsplay.shop.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.letsplay.shop.dto.ProductPatchRequest;
import com.letsplay.shop.models.Product;
import com.letsplay.shop.repositories.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepo;

    public Product createProduct(Product product) {
        return productRepo.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    public Product getProductById(String id) {
        return productRepo.findById(id).orElseThrow(() -> new RuntimeException("No Product found"));
    }

    public Product updateProduct(String id, Product newProductInfo) {
        Product existing = getProductById(id);

        existing.setName(newProductInfo.getName());
        existing.setPrice(newProductInfo.getPrice());
        existing.setQuantity(newProductInfo.getQuantity());

        return productRepo.save(existing);
    }

    public Product patchProduct(String id, ProductPatchRequest newProduct) {
        Product product = productRepo.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));

        if (newProduct.getName() != null)
            product.setName(newProduct.getName());
        if (newProduct.getPrice() != null)
            product.setPrice(newProduct.getPrice());
        if (newProduct.getQuantity() != null)
            product.setQuantity(newProduct.getQuantity());

        return productRepo.save(product);
    }

    public void deleteProduct(String id) {
        productRepo.deleteById(id);
    }
}
