package sample;

public class user_list {
    String username,password,status;
    int wallet;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getWallet() {
        return wallet;
    }

    public void setWallet(int wallet) {
        this.wallet = wallet;
    }

    public user_list(String username, String password, String status, int wallet) {
        this.username = username;
        this.password = password;
        this.status = status;
        this.wallet = wallet;
    }
}
