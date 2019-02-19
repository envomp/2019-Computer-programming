//Help
package ee.taltech.iti0202.stock.stock;

import ee.taltech.iti0202.stock.exceptions.StockException;
import ee.taltech.iti0202.stock.product.Product;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;


public class Stock {
    private String stockName;
    private int stockCapacity;

    public Stock(String name, int capacity) {
        stockName = name;
        stockCapacity = capacity;
    }

    private final List<Product> productlist = new ArrayList<>();

    public void addProduct(Product product) throws StockException {
        if (productlist.contains(product)) {
            throw new StockException(StockException.Reason.STOCK_ALREADY_CONTAINS_PRODUCT);
        } else if (isFull()) {
            throw new StockException(StockException.Reason.STOCK_IS_FULL);
        } else if (product.getPrice() < 0) {
            throw new StockException(StockException.Reason.NEGATIVE_PRICE);
        } else {
            productlist.add(product);
        }
    }

    public Optional<Product> getProduct(String name) {
        Optional<Product> smallest = Optional.empty();
        for (Product potato : productlist) {
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
            productlist.remove(removed.get());
        }

        return removed;
    }

    public List<Product> getProducts() {
        productlist.sort(Comparator.comparingInt(p -> p.actualcount));
        return productlist;
    }

    public List<Product> getProducts(String name) {
        List<Product> bananas = new ArrayList<>();
        for (Product element : productlist) {
            if (element.getName().equals(name)) {
                bananas.add(element);
            }
        }
        return bananas;
    }

    public int getTotalPrice() {
        int total = 0;
        for (Product item : productlist) {
            total += item.getPrice();
        }
        return total;
    }

    public boolean isFull() {
        return productlist.size() >= stockCapacity;
    }
}
