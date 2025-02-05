package blinket.com.product.entity;


import blinket.com.product.enums.ProductCategory;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

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


    @Column(name = "is_active",updatable = true)
    private Boolean is_active;



}
