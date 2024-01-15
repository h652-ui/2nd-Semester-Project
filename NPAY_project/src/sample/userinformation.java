package sample;

import java.security.PublicKey;

public class userinformation {
    public static String name;
    public static String password;
    public static String u_name;
    public static String hostel;
    public static String phone;
    public static String o_table;
    public static String t_table;

    public static String getU_id() {
        return u_id;
    }

    public static void setU_id(String u_id) {
        userinformation.u_id = u_id;
    }

    public static String u_id;

    public static String getT_table() {
        t_table=name.replaceAll("[^a-zA-Z0-9]","");
        t_table+="_trans";
        return t_table;
    }

    public static String getO_table() {
        o_table=name.replaceAll("[^a-zA-Z0-9]","");
        o_table+="_order";
        return o_table;
    }

    public static String getU_name() {
        return u_name;
    }

    public static void setU_name(String u_name) {
        userinformation.u_name = u_name;
    }

    public static String getHostel() {
        return hostel;
    }

    public static void setHostel(String hostel) {
        userinformation.hostel = hostel;
    }

    public static String getPhone() {
        return phone;
    }

    public static void setPhone(String phone) {
        userinformation.phone = phone;
    }

    public static int wallet;

    public static int getWallet() {
        return wallet;
    }

    public static void setWallet(int wallet) {
        userinformation.wallet = wallet;
    }

    public static void setName(String n){
        name=n;
    }
    public static void setPassword(String p){
        password=p;
    }
    public static String getname(){
        return name;
    }
    public static String getPassword(){
        return password;
    }
}