package sample;

public class resturant_trans_list {
    String u_name,product,hostel,phone;
    int price,quantity;

    public String getU_name() {
        return u_name;
    }

    public void setU_name(String u_name) {
        this.u_name = u_name;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getHostel() {
        return hostel;
    }

    public void setHostel(String hostel) {
        this.hostel = hostel;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public resturant_trans_list(String u_name, String product, String hostel, String phone, int price, int quantity) {
        this.u_name = u_name;
        this.product = product;
        this.hostel = hostel;
        this.phone = phone;
        this.price = price;
        this.quantity = quantity;
    }
}
