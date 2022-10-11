package com.techgen.service.impl;

import com.techgen.entity.Product;
import com.techgen.model.ProductDTO;
import com.techgen.repository.ProductRepository;
import com.techgen.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final ModelMapper modelMapper;

    public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ProductDTO searchProductsByNameOrDescription(String searchStr, int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Product> page = productRepository.searchProductsByNameOrDescription(searchStr, pageable);
        List<Product> products = page.getContent();
        ProductDTO productDTO = new ProductDTO();
        if (!ObjectUtils.isEmpty(products)) {
            List<ProductDTO.Product> productDTOs = new ArrayList<>();
            products.forEach(product -> {
                ProductDTO.Product innerProduct = new ProductDTO().new Product();
                innerProduct.setId(product.getId());
                innerProduct.setName(product.getName());
                innerProduct.setDescription(product.getDescription());
                innerProduct.setSku(product.getSku());
                innerProduct.setImageUrl(product.getImageUrl());
                innerProduct.setActive(product.isActive());
                innerProduct.setDateCreated(product.getDateCreated());
                innerProduct.setDateUpdated(product.getDateUpdated());
                productDTOs.add(innerProduct);
            });
            productDTO.setProducts(productDTOs);
            productDTO.setPageNo(page.getNumber());
            productDTO.setPageSize(page.getSize());
            productDTO.setTotalElements(page.getTotalElements());
            productDTO.setTotalPages(page.getTotalPages());
            productDTO.setLast(page.isLast());
            return productDTO;
        }
        return productDTO;
    }

    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {
        Product product = modelMapper.map(productDTO, Product.class);
        product = productRepository.save(product);
        return modelMapper.map(product, ProductDTO.class);
    }
}
