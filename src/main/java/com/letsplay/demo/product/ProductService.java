package com.letsplay.demo.product;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.letsplay.demo.exception.custom.ForbiddenException;
import com.letsplay.demo.exception.custom.NotFoundException;
import com.letsplay.demo.product.DTOs.CreateRequest;
import com.letsplay.demo.product.DTOs.ProductResponse;
import com.letsplay.demo.product.DTOs.UpdateRequest;

import java.util.List;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductResponse createProduct(CreateRequest req) {
        Product product = productMapper.toEntity(req);
        product.setUserId(getCurrentUUID());
        return productMapper.toResponse(productRepository.save(product));
    }

    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll().stream()
                .map(productMapper::toResponse)
                .toList();
    }

    public ProductResponse getProductById(String id) {
        return productMapper.toResponse(getEntityById(id));
    }

    private Product getEntityById(String id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Product not found"));
    }

    public List<ProductResponse> getProductsByUser(String userId) {
        return productRepository.findByUserId(userId).stream()
                .map(productMapper::toResponse)
                .toList();
    }

    public ProductResponse updateProduct(String id, UpdateRequest updated) {
        Product product = getEntityById(id);

        if (!isCurrentOwnerOrAdmin(product.getUserId())) {
            throw new ForbiddenException("Sorry! You are not the owner of this product");
        }

        productMapper.updateEntityFromRequest(updated, product);

        return productMapper.toResponse(productRepository.save(product));
    }

    public void deleteProduct(String id) {
        Product product = getEntityById(id);

        if (!isCurrentOwnerOrAdmin(product.getUserId())) {
            throw new ForbiddenException("Sorry! You are not the owner of this product");
        }

        productRepository.delete(product);
    }

    private boolean isCurrentOwnerOrAdmin(String userId) {
        return userId.equals(getCurrentUUID()) || getCurrentRole().equals("ROLE_ADMIN");
    }

    private String getCurrentUUID() {
        return (String) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
    }

    private String getCurrentRole() {
        return SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getAuthorities()
                .stream()
                .findFirst()
                .map(GrantedAuthority::getAuthority)
                .orElse(null);
    }
}