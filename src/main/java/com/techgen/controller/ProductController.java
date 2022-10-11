package com.techgen.controller;

import com.techgen.model.ProductDTO;
import com.techgen.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/search")
    public ResponseEntity<ProductDTO> searchProducts(@RequestParam("searchStr") String searchStr,
                                                     @RequestParam(value = "page-no", defaultValue = "0", required = false) int pageNumber,
                                                     @RequestParam(value = "page-size", defaultValue = "10", required = false) int pageSize,
                                                     @RequestParam(value = "sort-by", defaultValue = "id", required = false) String sortBy,
                                                     @RequestParam(value = "sort-dir", defaultValue = "asc", required = false) String sortDir) {
        return ResponseEntity.ok(productService.searchProductsByNameOrDescription(searchStr, pageNumber, pageSize, sortBy, sortDir));
    }

    @PostMapping("")
    public ResponseEntity<ProductDTO> searchProducts(@RequestBody ProductDTO product) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.createProduct(product));
    }
}
