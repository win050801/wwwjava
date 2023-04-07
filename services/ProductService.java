package com.example.productmanagement.services;

import com.example.productmanagement.entities.Product;
import com.example.productmanagement.repositories.ProductRepository;

import java.util.List;

public class ProductService {

    private ProductRepository productRepository;
    public ProductService(){ productRepository = new ProductRepository(); }
    public Product save(Product p) { return productRepository.save(p); }
    public List<Product> getAll() {  return productRepository.findAll(); }
    public Product getProductById(String id) {  return productRepository.getProductById(id); }
    public boolean removeProduct(String id) { return productRepository.removeProduct(id); }
    public boolean updateProduct(Product p) { return productRepository.updateProduct(p); }
}
