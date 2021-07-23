package sample;

public class trans_list {
    String product,cafe,status;
    int quantity,price;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getQuantity() {
        return quantity;
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

    public trans_list(String product, String cafe, String status, int quantity, int price) {
        this.product = product;
        this.cafe = cafe;
        this.status = status;
        this.quantity = quantity;
        this.price = price;
    }
}
