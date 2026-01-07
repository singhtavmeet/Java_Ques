
package com.example.order_management;

import com.example.order_management.dao.OrderDao;
import com.example.order_management.dao.OrderDaoImpl;
import com.example.order_management.entity.Order;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;


public class Main {

    private static final Scanner SC = new Scanner(System.in);

    public static void main(String[] args) {
        OrderDao dao = new OrderDaoImpl();
        System.out.println("=== Order Management (Switch Menu) ===");

        while (true) {
            printMenu();
            int choice = readInt("Choose an option: ");

            switch (choice) {
                case 1 -> addOrderFlow(dao);
                case 2 -> updateOrderFlow(dao);
                case 3 -> deleteOrderFlow(dao);
                case 4 -> getAllOrdersFlow(dao);
                case 0 -> {
                    System.out.println("Bye!");
                    return;
                }
                default -> System.out.println("Invalid option. Try again.");
            }
        }
    }

    /* ------------------ Menu flows ------------------ */

    private static void addOrderFlow(OrderDao dao) {
        try {
            String name = readString("Customer name: ");
            BigDecimal amount = readBigDecimal("Total amount: ");

            List<Order> result = dao.addOrder(new Order(name, amount));
            if (result.isEmpty()) {
                System.out.println("Add failed.");
            } else {
                System.out.println("Added: " + result.get(0));
            }
        } catch (Exception e) {
            System.err.println("Error adding order: " + e.getMessage());
        }
    }

    private static void updateOrderFlow(OrderDao dao) {
        try {
            long id = readLong("Order ID to update: ");
            String name = readString("New customer name: ");
            BigDecimal amount = readBigDecimal("New total amount: ");

            List<Order> result = dao.updateOrderById(id, new Order(name, amount));
            if (result.isEmpty()) {
                System.out.println("No order updated (ID not found).");
            } else {
                System.out.println("Updated: " + result.get(0));
            }
        } catch (Exception e) {
            System.err.println("Error updating order: " + e.getMessage());
        }
    }

    private static void deleteOrderFlow(OrderDao dao) {
        try {
            long id = readLong("Order ID to delete: ");
            List<Order> result = dao.deleteOrderById(id);
            if (result.isEmpty()) {
                System.out.println("No order deleted (ID not found).");
            } else {
                System.out.println("Deleted snapshot: " + result.get(0));
            }
        } catch (Exception e) {
            System.err.println("Error deleting order: " + e.getMessage());
        }
    }

    private static void getAllOrdersFlow(OrderDao dao) {
        try {
            List<Order> all = dao.getAllOrders();
            if (all.isEmpty()) {
                System.out.println("No orders found.");
            } else {
                System.out.println("All orders (" + all.size() + "):");
                all.forEach(o -> System.out.println("  " + o));
            }
        } catch (Exception e) {
            System.err.println("Error fetching orders: " + e.getMessage());
        }
    }

    /* ------------------ IO helpers ------------------ */

    private static void printMenu() {
        System.out.println("\n-----------------------------");
        System.out.println("1) Add order");
        System.out.println("2) Update order by ID");
        System.out.println("3) Delete order by ID");
        System.out.println("4) Get all orders");
        System.out.println("0) Exit");
        System.out.println("-----------------------------");
    }

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                int v = Integer.parseInt(SC.nextLine().trim());
                return v;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid integer.");
            }
        }
    }

    private static long readLong(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                long v = Long.parseLong(SC.nextLine().trim());
                return v;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid long value.");
            }
        }
    }

    private static String readString(String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = SC.nextLine().trim();
            if (!s.isBlank()) return s;
            System.out.println("Value cannot be blank.");
        }
    }

    private static BigDecimal readBigDecimal(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                BigDecimal v = new BigDecimal(SC.nextLine().trim());
                if (v.compareTo(BigDecimal.ZERO) < 0) {
                    System.out.println("Amount must be non-negative.");
                    continue;
                }
                return v;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid decimal value (e.g., 199.99).");
            }
        }
    }
}
