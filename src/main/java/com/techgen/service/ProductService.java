package com.techgen.service;

import com.techgen.model.ProductDTO;

import java.util.List;

public interface ProductService {

    ProductDTO searchProductsByNameOrDescription(String searchStr, int pageNo, int pageSize, String sortBy, String sortDir);

    ProductDTO createProduct(ProductDTO productDTO);
}
