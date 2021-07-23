package sample;

public class resturant_order_list {
    public String u_name,p_name,u_hostel,u_product,u_phone;

    public String getU_name() {
        return u_name;
    }

    public void setU_name(String u_name) {
        this.u_name = u_name;
    }

    public String getP_name() {
        return p_name;
    }

    public void setP_name(String p_name) {
        this.p_name = p_name;
    }

    public String getU_hostel() {
        return u_hostel;
    }

    public void setU_hostel(String u_hostel) {
        this.u_hostel = u_hostel;
    }

    public String getU_product() {
        return u_product;
    }

    public void setU_product(String u_product) {
        this.u_product = u_product;
    }

    public String getU_phone() {
        return u_phone;
    }

    public void setU_phone(String u_phone) {
        this.u_phone = u_phone;
    }

    public int getU_price() {
        return u_price;
    }

    public void setU_price(int u_price) {
        this.u_price = u_price;
    }

    public int getU_quantity() {
        return u_quantity;
    }

    public void setU_quantity(int u_quantity) {
        this.u_quantity = u_quantity;
    }

    public int u_price,u_quantity;

    public resturant_order_list(String u_name, String p_name, String u_hostel, String u_product, String u_phone, int u_price, int u_quantity) {
        this.u_name = u_name;
        this.p_name = p_name;
        this.u_hostel = u_hostel;
        this.u_product = u_product;
        this.u_phone = u_phone;
        this.u_price = u_price;
        this.u_quantity = u_quantity;
    }
}
