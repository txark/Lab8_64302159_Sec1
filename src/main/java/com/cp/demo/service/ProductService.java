package com.cp.demo.service;

import com.cp.demo.model.Product;
import com.cp.demo.model.Review;
import com.cp.demo.repository.ProductRepository;
import com.cp.demo.strategy.*;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public Product saveProduct(Product product) {
        if (product.getDetail() != null) {
            product.getDetail().setProduct(product);
        }
        if (product.getReviews() != null) {
            for (Review review : product.getReviews()) {
                review.setProduct(product);
            }
        }
        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public double calculateFinalPrice(Product product) {
        DiscountContext context = new DiscountContext();
        String type = product.getDiscountType();
        if ("MEMBER".equals(type)) {
            context.setStrategy(new MemberDiscountStrategy());
        } else if ("SEASONAL".equals(type)) {
            context.setStrategy(new SeasonalSaleStrategy());
        } else {
            context.setStrategy(new NoDiscountStrategy());
        }
        return context.executeDiscount(product.getPrice());
    }
}