
package com.example.order_management.entity;

import java.math.BigDecimal;
import java.util.Objects;

public class Order {
    private Long id;
    private String customerName;
    private BigDecimal totalAmount;

    public Order() {
        
    }

    public Order(Long id, String customerName, BigDecimal totalAmount) {
        this.id = id;
        this.customerName = customerName;
        this.totalAmount = totalAmount;
    }

    public Order(String customerName, BigDecimal totalAmount) {
        this.customerName = customerName;
        this.totalAmount = totalAmount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customerName='" + customerName + '\'' +
                ", totalAmount=" + totalAmount +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id) &&
               Objects.equals(customerName, order.customerName) &&
               Objects.equals(totalAmount, order.totalAmount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customerName, totalAmount);
    }
}

