package com.example.creatorstore.services;

import com.example.creatorstore.dto.OrderItemRequest;
import com.example.creatorstore.dto.OrderRequest;
import com.example.creatorstore.entities.Order;
import com.example.creatorstore.entities.Product;
import com.example.creatorstore.repositories.OrderRepository;
import com.example.creatorstore.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {
    private OrderRepository orderRepository;
    private ProductRepository productRepository;

    @Transactional
    public Order createOrder(OrderRequest orderRequest){
        Order order = new Order();
        order.setCustomerEmail(orderRequest.getCustomerEmail());
        order.setCustomerName(orderRequest.getCustomerName());
        order.setStatus("CONFIRMED");

        for (OrderItemRequest itemRequest : orderRequest.getItems()){
            Product product = productRepository.findById(itemRequest.getProductId())
                    .orElseThrow(() -> new RuntimeException(
                            "Product not found with id " + itemRequest.getProductId()
                    ));
            //Check the product
        }
        return order;
    }
}
