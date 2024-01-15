package sample;

public class order_list {
    String product,cafe, Order_Time, Order_ID;
    int quantity,price;

    public order_list(String product, String cafe, String Order_Time, int quantity, int price, String Order_ID) {
        this.product = product;
        this.cafe = cafe;
        this.quantity = quantity;
        this.price = price;
        this.Order_Time = Order_Time;
        this.Order_ID = Order_ID;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getCafe() {
        return cafe;
    }

    public void setCafe(String cafe) {
        this.cafe = cafe;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getOrder_Time() {
        return Order_Time;
    }

    public void setOrder_Time(String order_Time) {
        Order_Time = order_Time;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getOrder_ID() {
        return Order_ID;
    }

    public void setOrder_ID(String order_ID) {
        Order_ID = order_ID;
    }
}
