package ee.taltech.iti0202.stock.product;

import ee.taltech.iti0202.stock.exceptions.StockException;

public class Product {
    public static int count = 0;
    public int actualcount = 0;
    static int productNextId = 0;
    private String productName;
    private int productPrice;

    public Product(String name, int price) throws StockException {
        if (price < 0) {
            throw new StockException(StockException.Reason.NEGATIVE_PRICE);
        }
        count += 1;
        actualcount += 1;
        productName = name;
        productPrice = price;
    }

    public static int getNextId() {
        productNextId += 1;
        return productNextId;
    }

    public int getId() {
        return count;
    }

    public String getName() {
        return productName;
    }

    public int getPrice() {
        return productPrice;
    }
}
