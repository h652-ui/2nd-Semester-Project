package sample;

public class review_list {
    String Consumer, Product, Cafe, Stars, Date;

    public review_list(String consumer, String product, String cafe, String stars, String date) {
        Consumer = consumer;
        Product = product;
        Cafe = cafe;
        Stars = stars;
        Date = date;
    }

    public String getConsumer() {
        return Consumer;
    }

    public void setConsumer(String consumer) {
        Consumer = consumer;
    }

    public String getProduct() {
        return Product;
    }

    public void setProduct(String product) {
        Product = product;
    }

    public String getCafe() {
        return Cafe;
    }

    public void setCafe(String cafe) {
        Cafe = cafe;
    }

    public String getStars() {
        return Stars;
    }

    public void setStars(String stars) {
        Stars = stars;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }
}
