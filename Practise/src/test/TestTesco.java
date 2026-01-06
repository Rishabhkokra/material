package test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

//Tesco gets millions of orders every day with an average basket size of 100 items. Tesco Business has got some regulations around selling products online and in stores. These regulations are mandatory from legal and business perspective to enforce for all order transactions. 
//You are given an order with a list of products in the shopping cart/basket with productid, product Category and quantity. And also, Restriction Rules on Qty and Qty/Category. 
//Example: 
//Ordered items in the shopping cart/basket 
//Item-1 -> productid=1, category=Paracetamol, quantity=3 
//Item-2 -> productid=2, category=analgesic, quantity=3 
//Item-3 -> productid=3, category=chocolate, quantity=8 
//Item-4 -> productid=4, category= Paracetamol, quantity=2 
//Business Restriction rules: 
//Cannot buy more than 10 Quantity of any products - BulkBuyLimit 
//Cannot buy more than 5 Quantity of paracetamol products – BulkBuyLimitCategory 

//Write a restriction rule engine to run the restriction check against the shopping cart/basket and return the status as to MET/BREACHED indicating restriction status for the given restriction rules. 
//For the above given example, the restriction status returned would be MET.

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class TestTesco {

    // ---------------- Domain Models ----------------
    public static class CartItem {
        private final int productId;
        private final String category;
        private final int quantity;

        public CartItem(int productId, String category, int quantity) {
            this.productId = productId;
            this.category = category;
            this.quantity = quantity;
        }

        public int getProductId() { return productId; }
        public String getCategory() { return category; }
        public int getQuantity() { return quantity; }
    }

    // ---------------- Result Model ----------------
    public static class RuleResult {
        private final boolean breached;
        private final String message;

        private RuleResult(boolean breached, String message) {
            this.breached = breached;
            this.message = message;
        }

        public static RuleResult met() {
            return new RuleResult(false, "MET");
        }

        public static RuleResult breached(String message) {
            return new RuleResult(true, message);
        }

        public boolean isBreached() { return breached; }
        public String getMessage() { return message; }
    }

    // ---------------- Rule Contract (Strategy) ----------------
    public interface RestrictionRule {
        RuleResult validate(List<CartItem> items);
    }

    // ---------------- Rule Implementations ----------------

    /** Rule: Max quantity per individual product */
    public static class MaxQuantityPerProductRule implements RestrictionRule {
        private final int maxAllowed;

        public MaxQuantityPerProductRule(int maxAllowed) {
            this.maxAllowed = maxAllowed;
        }

        @Override
        public RuleResult validate(List<CartItem> items) {
            for (CartItem item : items) {
                if (item.getQuantity() > maxAllowed) {
                    return RuleResult.breached(
                            "Product " + item.getProductId()
                                    + " exceeds max allowed: " + maxAllowed
                    );
                }
            }
            return RuleResult.met();
        }
    }

    /** Rule: Max quantity aggregated by category */
    public static class MaxQuantityPerCategoryRule implements RestrictionRule {
        private final String category;
        private final int maxAllowed;

        public MaxQuantityPerCategoryRule(String category, int maxAllowed) {
            this.category = category;
            this.maxAllowed = maxAllowed;
        }

        @Override
        public RuleResult validate(List<CartItem> items) {
            int total = items.stream()
                    .filter(i -> i.getCategory().equalsIgnoreCase(category))
                    .mapToInt(CartItem::getQuantity)
                    .sum();

            if (total > maxAllowed) {
                return RuleResult.breached(
                        "Category " + category + " exceeds max allowed: " + maxAllowed
                );
            }
            return RuleResult.met();
        }
    }

    // ---------------- Rule Engine (Composite) ----------------
    public static class RestrictionRuleEngine {
        private final List<RestrictionRule> rules = new ArrayList<>();

        public void addRule(RestrictionRule rule) {
            rules.add(rule);
        }

        public RuleResult runRules(List<CartItem> items) {
            for (RestrictionRule rule : rules) {
                RuleResult result = rule.validate(items);
                if (result.isBreached()) {
                    return result;
                }
            }
            return RuleResult.met();
        }
    }

    // ---------------- Demo / Test ----------------
    public static void main(String[] args) {

        List<CartItem> basket = Arrays.asList(
                new CartItem(1, "Paracetamol", 3),
                new CartItem(2, "Analgesic", 3),
                new CartItem(3, "Chocolate", 8),
                new CartItem(4, "Paracetamol", 2)
        );

        RestrictionRuleEngine engine = new RestrictionRuleEngine();
        engine.addRule(new MaxQuantityPerProductRule(10));
        engine.addRule(new MaxQuantityPerCategoryRule("Paracetamol", 5));

        RuleResult result = engine.runRules(basket);
        System.out.println("Restriction Check Status: " + result.getMessage());
    }
}
