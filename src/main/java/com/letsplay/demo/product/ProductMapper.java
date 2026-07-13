package com.letsplay.demo.product;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.letsplay.demo.product.DTOs.CreateRequest;
import com.letsplay.demo.product.DTOs.ProductResponse;
import com.letsplay.demo.product.DTOs.UpdateRequest;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductResponse toResponse(Product product);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "userId", ignore = true)
    Product toEntity(CreateRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "userId", ignore = true)
    void updateEntityFromRequest(UpdateRequest request, @MappingTarget Product product);
}
