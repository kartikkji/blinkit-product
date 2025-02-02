package blinket.com.product.entity;


import blinket.com.product.enums.ProductCategory;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "products_table")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "category",nullable = false)
    private ProductCategory category;

//    @Column(name = "sku")
//    private String sku;
//
//    @Column(name = "price")
//    private float price;

//    @Column(name = "stock_quantity")
//    private Integer stock_quantity;

    @Column(name = "is_active",updatable = true)
    private Boolean is_active;


}
