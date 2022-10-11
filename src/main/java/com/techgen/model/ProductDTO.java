package com.techgen.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    private List<Product> products;
    private int pageNo;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean last;

    @Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public class Product {
        private long id;
        private String sku;
        private String name;
        private String description;
        private boolean active;
        private String imageUrl;
        private LocalDateTime dateCreated;
        private LocalDateTime dateUpdated;
    }
}
