
package com.example.order_management.dao;

import com.example.order_management.entity.Order;
import com.example.order_management.util.DBConnection;
import org.junit.jupiter.api.*;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OrderDaoImplTest {

    private static OrderDao dao;

    @BeforeAll
    static void setup() throws Exception {
        dao = new OrderDaoImpl();

        // Ensure 'orders' table exists in the selected schema (orderdb_test recommended for tests)
        try (Connection conn = DBConnection.getConnection();
             Statement st = conn.createStatement()) {
            st.execute("""
                CREATE TABLE IF NOT EXISTS orders (
                  id BIGINT PRIMARY KEY AUTO_INCREMENT,
                  customer_name VARCHAR(100) NOT NULL,
                  total_amount DECIMAL(10,2) NOT NULL
                )
            """);
        }
    }

    @BeforeEach
    void clean() throws Exception {
        // Truncate table to isolate test cases
        try (Connection conn = DBConnection.getConnection();
             Statement st = conn.createStatement()) {
            st.execute("TRUNCATE TABLE orders");
        }
    }

    /* -------------------------- addOrder -------------------------- */

    @org.junit.jupiter.api.Order(1)
    @Test
    void addOrder_positive_returnsListWithInsertedOrder() throws Exception {
        List<Order> added = dao.addOrder(new Order("Bob", new BigDecimal("99.50")));
        assertEquals(1, added.size(), "Expected exactly one inserted order to be returned");
        Order o = added.get(0);
        assertNotNull(o.getId(), "Inserted order should have generated ID");
        assertEquals("Bob", o.getCustomerName());
        assertEquals(new BigDecimal("99.50"), o.getTotalAmount());
    }

    @org.junit.jupiter.api.Order(2)
    @Test
    void addOrder_negative_invalidCustomerName_throwsIAE() {
        assertThrows(IllegalArgumentException.class,
            () -> dao.addOrder(new Order("", new BigDecimal("10.00"))),
            "Blank customerName should throw IllegalArgumentException");
    }

    /* ----------------------- updateOrderById ----------------------- */

    @org.junit.jupiter.api.Order(3)
    @Test
    void updateOrderById_positive_existingIdReturnsUpdatedOrder() throws Exception {
        Long id = dao.addOrder(new Order("Carol", new BigDecimal("120.00"))).get(0).getId();

        List<Order> updated = dao.updateOrderById(id, new Order("Carol Updated", new BigDecimal("150.00")));
        assertEquals(1, updated.size(), "Expected one updated order to be returned");
        Order o = updated.get(0);
        assertEquals(id, o.getId(), "Updated order ID should match the requested ID");
        assertEquals("Carol Updated", o.getCustomerName());
        assertEquals(new BigDecimal("150.00"), o.getTotalAmount());
    }

    @org.junit.jupiter.api.Order(4)
    @Test
    void updateOrderById_negative_nonexistentIdReturnsEmptyList() throws Exception {
        List<Order> updated = dao.updateOrderById(99999L, new Order("Nobody", new BigDecimal("10.00")));
        assertTrue(updated.isEmpty(), "Updating a non-existent ID should return an empty list");
    }

    /* ----------------------- deleteOrderById ----------------------- */

    @org.junit.jupiter.api.Order(5)
    @Test
    void deleteOrderById_positive_existingIdReturnsDeletedOrder() throws Exception {
        Long id = dao.addOrder(new Order("Dave", new BigDecimal("45.00"))).get(0).getId();

        List<Order> deleted = dao.deleteOrderById(id);
        assertEquals(1, deleted.size(), "Expected one deleted order snapshot");
        Order o = deleted.get(0);
        assertEquals(id, o.getId());
        assertEquals("Dave", o.getCustomerName());
        assertEquals(new BigDecimal("45.00"), o.getTotalAmount());

        // Ensure the row was actually deleted
        assertTrue(dao.getAllOrders().isEmpty(), "After delete, getAllOrders should be empty");
    }

    @org.junit.jupiter.api.Order(6)
    @Test
    void deleteOrderById_negative_nonexistentIdReturnsEmptyList() throws Exception {
        List<Order> deleted = dao.deleteOrderById(123456L);
        assertTrue(deleted.isEmpty(), "Deleting a non-existent ID should return an empty list");
    }

    /* -------------------------- getAllOrders -------------------------- */

    @org.junit.jupiter.api.Order(7)
    @Test
    void getAllOrders_positive_returnsAllRows() throws Exception {
        dao.addOrder(new Order("Eve", new BigDecimal("10.00")));
        dao.addOrder(new Order("Frank", new BigDecimal("20.00")));

        List<Order> all = dao.getAllOrders();
        assertEquals(2, all.size(), "Expected two orders returned");
        // No strict ordering assertion; DB may not guarantee order without ORDER BY
    }

    @org.junit.jupiter.api.Order(8)
    @Test
    void getAllOrders_negative_emptyTable_returnsEmptyList() throws Exception {
        // Table is truncated in @BeforeEach, so it's empty
        List<Order> all = dao.getAllOrders();
        assertTrue(all.isEmpty(), "Empty table should return an empty list");
    }
}
