package com.tesco.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.*;

public class FindContainers {

//	Tesco has a fleet of vehicles to deliver orders to the customer. Assigning the right set of orders to different sized vehicles is crucial for efficient delivery of orders. Different vehicle can fit different container sizes. 
//	 
//	Given c containers, along with their volumes [l,b,h], catalogue of product with its volume requirement (l,b,h) and an order with p products and its quantity. 
//	 
//	Example: 
//	Containers: 
//	SMALL -> id=1, length=10, breadth=20, height=30 
//	MEDIUM -> id=2, length=50, breadth=60, height=70 
//	LARGE -> id=3, length=100, breadth=200, height=300 
//	 
//	Product: 
//	productId=1, length=2, breadth=4, height=10 
//	productId=2, length=10, breadth=30, height=4 
//	productId=3, length=5, breadth=6, height=7 
//	 
//	Order: 
//	productId=1, quantity=3 
//	productId=3, quantity=7 

//	Order: 
//	productId=2, quantity=3 
//	productId=3, quantity=8

//	Order: 
//	productId=2, quantity=3 
//	productId=3, quantity=7 
//	 
//	 
//	 Determine if that order fits in any of the given c containers and return the ID of the container that can be used. 
//
//	Given n orders, determine the maximum number of orders that can be fit into a particular container.

	


	    // ---------------- Domain Models ----------------

	    public static class Container {
	        private final int id;
	        private final int length;
	        private final int breadth;
	        private final int height;

	        public Container(int id, int length, int breadth, int height) {
	            this.id = id;
	            this.length = length;
	            this.breadth = breadth;
	            this.height = height;
	        }

	        public int getId() { return id; }
	        public long getVolume() { return (long) length * breadth * height; }
	    }

	    public static class Product {
	        private final int productId;
	        private final int length;
	        private final int breadth;
	        private final int height;

	        public Product(int productId, int length, int breadth, int height) {
	            this.productId = productId;
	            this.length = length;
	            this.breadth = breadth;
	            this.height = height;
	        }

	        public long getVolume() { return (long) length * breadth * height; }
	        public int getProductId() { return productId; }
	    }

	    public static class OrderedItem {
	        private final int productId;
	        private final int quantity;

	        public OrderedItem(int productId, int quantity) {
	            this.productId = productId;
	            this.quantity = quantity;
	        }

	        public int getProductId() { return productId; }
	        public int getQuantity() { return quantity; }
	    }

	    public static class Order {
	        private final List<OrderedItem> items;

	        public Order(List<OrderedItem> items) {
	            this.items = items;
	        }
	        public List<OrderedItem> getItems() { return items; }
	    }

	    // ---------------- Strategy: Order Volume Calculator ----------------

	    public interface VolumeCalculator {
	        long calculate(Order order, Map<Integer, Product> catalog);
	    }

	    public static class SimpleVolumeCalculator implements VolumeCalculator {
	        @Override
	        public long calculate(Order order, Map<Integer, Product> catalog) {
	            long totalVolume = 0;
	            for (OrderedItem item : order.getItems()) {
	                Product p = catalog.get(item.getProductId());
	                totalVolume += p.getVolume() * item.getQuantity();
	            }
	            return totalVolume;
	        }
	    }

	    // ---------------- Engine ----------------

	    public static class ContainerFitEngine {
	        private final VolumeCalculator calculator;

	        public ContainerFitEngine(VolumeCalculator calculator) {
	            this.calculator = calculator;
	        }

	        public Integer findBestContainer(Order order,
	                                         List<Container> containers,
	                                         Map<Integer, Product> catalog) {

	            long orderVolume = calculator.calculate(order, catalog);

	            return containers.stream()
	                    .filter(c -> c.getVolume() >= orderVolume)
	                    .sorted(Comparator.comparingLong(Container::getVolume))
	                    .map(Container::getId)
	                    .findFirst()
	                    .orElse(null); // No fit found
	        }

	        public int maxOrdersFit(List<Order> orders,
	                                Container container,
	                                Map<Integer, Product> catalog) {

	            List<Long> orderVolumes = new ArrayList<>();
	            for (Order order : orders) {
	                orderVolumes.add(calculator.calculate(order, catalog));
	            }

	            orderVolumes.sort(Long::compare);

	            long usedVolume = 0;
	            int count = 0;

	            for (long v : orderVolumes) {
	                if (usedVolume + v <= container.getVolume()) {
	                    usedVolume += v;
	                    count++;
	                }
	            }
	            return count;
	        }
	    }

	    // ---------------- Test / Demo ----------------

	    public static void main(String[] args) {

	        // Containers
	        List<Container> containers = Arrays.asList(
	            new Container(1, 10, 20, 30),   // Small
	            new Container(2, 50, 60, 70),   // Medium
	            new Container(3, 100, 200, 300) // Large
	        );

	        // Product catalogue
	        Map<Integer, Product> catalog = new HashMap<>();
	        catalog.put(1, new Product(1, 2, 4, 10));
	        catalog.put(2, new Product(2, 10, 30, 4));
	        catalog.put(3, new Product(3, 5, 6, 7));

	        // Orders
	        Order orderA = new Order(Arrays.asList(
	                new OrderedItem(1, 3),
	                new OrderedItem(3, 7)
	        ));
	        Order orderB = new Order(Arrays.asList(
	                new OrderedItem(2, 3),
	                new OrderedItem(3, 8)
	        ));
	        Order orderC = new Order(Arrays.asList(
	                new OrderedItem(2, 3),
	                new OrderedItem(3, 7)
	        ));

	        List<Order> multipleOrders = Arrays.asList(orderA, orderB, orderC);

	        ContainerFitEngine engine = new ContainerFitEngine(new SimpleVolumeCalculator());

	        // --- Single Order Check ---
	        System.out.println("Order A fits in Container ID: " +
	                engine.findBestContainer(orderA, containers, catalog));
	        System.out.println("Order B fits in Container ID: " +
	                engine.findBestContainer(orderB, containers, catalog));
	        System.out.println("Order C fits in Container ID: " +
	                engine.findBestContainer(orderC, containers, catalog));

	        // --- Max Orders in Medium Container ---
	        System.out.println("Max Orders in Medium Container: " +
	                engine.maxOrdersFit(multipleOrders, containers.get(1), catalog));
	    }
	}

