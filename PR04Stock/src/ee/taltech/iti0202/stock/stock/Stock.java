
package ee.taltech.iti0202.stock.stock;

import ee.taltech.iti0202.stock.exceptions.StockException;
import ee.taltech.iti0202.stock.product.Product;

import java.util.*;


public class Stock {
    private String stockName;
    private int stockCapacity;

    public Stock(String name, int capacity) {
        stockName = name;
        stockCapacity = capacity;
    }

    private final List<Product> LIST_OF_PRODUCTS = new ArrayList<>();

    public void addProduct(Product product) throws StockException {
        if (LIST_OF_PRODUCTS.contains(product)) {
            throw new StockException(StockException.Reason.STOCK_ALREADY_CONTAINS_PRODUCT);
        } else if (isFull()) {
            throw new StockException(StockException.Reason.STOCK_IS_FULL);
        } else {
            LIST_OF_PRODUCTS.add(product);
        }
    }

    public Optional<Product> getProduct(String name) {
        Optional<Product> smallest = Optional.empty();
        for (Product potato : LIST_OF_PRODUCTS) {
            if (potato.getName().equals(name)) {
                if (smallest.isPresent()) {
                    if (potato.getPrice() < smallest.get().getPrice()) {
                        smallest = Optional.of(potato);
                    }
                    smallest = Optional.of(potato);
                } else {
                    smallest = Optional.of(potato);
                }

            }
        }
        return smallest;
    }

    public Optional<Product> removeProduct(String name) {
        Optional<Product> removed = getProduct(name);
        if (removed.isPresent()) {
            LIST_OF_PRODUCTS.remove(removed.get());
        }

        return removed;
    }

    public List<Product> getProducts() {
        Collections.sort(LIST_OF_PRODUCTS, Comparator.comparing(Product::getNextId));
        return LIST_OF_PRODUCTS;
    }

    public List<Product> getProducts(String name) {
        List<Product> bananas = new ArrayList<>();
        for (Product element : LIST_OF_PRODUCTS) {
            if (element.getName().equals(name)) {
                bananas.add(element);
            }
        }
        return bananas;
    }

    public int getTotalPrice() {
        int total = 0;
        for (Product item : LIST_OF_PRODUCTS) {
            total += item.getPrice();
        }
        return total;
    }

    public boolean isFull() {
        return LIST_OF_PRODUCTS.size() >= stockCapacity;
    }
}
