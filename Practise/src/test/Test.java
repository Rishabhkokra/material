package test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class Test {

	private enum Category {

		PARACETAMOL(5), ANALGESIC(10), CHOCOLATE(10);

		int allowedQuantity;

		Category(int i) {
			this.allowedQuantity = i;
		}

		public int getAllowedQuantity() {
			return this.allowedQuantity;
		}

	}

	public static void main(String[] args) {
		OrderItem item1=new OrderItem(1, 3,Category.PARACETAMOL);
		OrderItem item2=new OrderItem(2, 3,Category.ANALGESIC);
		OrderItem item3=new OrderItem(3, 8,Category.CHOCOLATE);
		OrderItem item4=new OrderItem(4, 4,Category.PARACETAMOL);
		
		List<OrderItem> itemList=Arrays.asList(item1,item2,item3,item4);
		
		Order order=new Order(itemList);
		
		if(!order.validate())
			System.out.println("BREACHED");
		else
			System.out.println("MET");

	}

	private static class Order {

		private List<OrderItem> items;

		public List<OrderItem> getItems() {
			return items;
		}

		public void setItems(List<OrderItem> items) {
			this.items = items;
		}

		public Order(List<OrderItem> items) {
			super();
			this.items = items;
		}

		public boolean validate() {
			if (this.items != null && !this.items.isEmpty()) {

				Map<Category, List<OrderItem>> groupOrderItems = this.items.stream()
						.collect(Collectors.groupingBy(OrderItem::getCategory));
				
				for(Entry<Category,List<OrderItem>> map:groupOrderItems.entrySet()) {
					if(map.getKey().getAllowedQuantity()<map.getValue().size()) {
						return false;
					}
				}
			}

			return true;
		}
	}

	private static class OrderItem {

		private int productId;
		private int quantity;
		private Category category;

		public int getProductId() {
			return productId;
		}

		public void setProductId(int productId) {
			this.productId = productId;
		}

		public int getQuantity() {
			return quantity;
		}

		public void setQuantity(int quantity) {
			this.quantity = quantity;
		}

		public Category getCategory() {
			return category;
		}

		public void setCategory(Category category) {
			this.category = category;
		}

		public OrderItem(int productId, int quantity, Category category) {
			super();
			this.productId = productId;
			this.quantity = quantity;
			this.category = category;
		}
		
		

	}

}