package Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

/**
 * Created by chett_000 on 2017/06/08.
 */
@Document(collection = "Transaction")
public class Tranaction {

    @Id
    private String id;
    private Item food;
    private Staff user;
    private String date;
    private String time;
    private int quantity;
    private boolean isOrded;


    @Override
    public boolean equals(Object obj){

        Tranaction tran = (Tranaction)obj;

        return (this.getFood().getName().equals(tran.getFood().getName())) &&
                (this.getQuantity()==tran.getQuantity())&&
                (this.getUser().getUsername().equals(tran.getUser().getUsername()))&&
                (this.getDate().equals(tran.getDate()))&&
                (this.getTime().equals(tran.getTime())&&
                        (this.isOrded==tran.isOrded)) ;
    }

    @Override
    public int hashCode(){
        return Objects.hash(food.getName(),user.getUsername(),date,time,quantity,isOrded);
    }

    public Tranaction(){

    }

    public Tranaction( Item food, Staff user, String time, int quantity,boolean isOrded) {

       SimpleDateFormat date = new SimpleDateFormat("dd/MM/YYYY");
        this.id = id;
        this.food = food;
        this.user = user;
        this.date = date.format(new Date());
        this.time = time;
        this.quantity = quantity;
        this.isOrded = isOrded;
    }

    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Tranaction{" +
                "id='" + id + '\'' +
                ", food=" + food +
                ", user=" + user +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", quantity=" + quantity +
                ", isOrded=" + isOrded +
                '}';
    }

    public boolean isOrded() {
        return isOrded;
    }

    public void setOrded(boolean orded) {
        isOrded = orded;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getId() {

        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Item getFood() {
        return food;
    }

    public void setFood(Item food) {
        this.food = food;
    }

    public Staff getUser() {
        return user;
    }

    public void setUser(Staff user) {
        this.user = user;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
