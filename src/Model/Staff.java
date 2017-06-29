package Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

/**
 * Created by chett_000 on 2017/05/10.
 */

@Document(collection = "Staff")
public class Staff {
public Staff(){

}
    @Id
    public String id;
    public String name;
    public String username;
    public String password;
    public int balance;
    public ArrayList<String> role;


    public Staff(String name, String username, String password, ArrayList<String> role, int balance) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.role = role;
        this.balance=balance;
    }

    @Override
    public String toString() {
        return "Staff{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", balance=" + balance +
                ", role='" + role + '\'' +
                '}';
    }

    public String getBalancestring(){
        return"username,"+username+",balance,"+balance;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    public ArrayList<String> getRole() {
        return role;
    }

    public void setRole(ArrayList<String> role) {
        this.role = role;
    }
}
