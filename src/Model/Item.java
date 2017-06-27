package Model;

import org.springframework.data.annotation.Id;

import java.util.Objects;

/**
 * Created by chett_000 on 2017/05/11.
 */
public class Item {

    @Id
    public String id;
    public String name;
    public String desc;
    public String price;
    public String time;
    public String picName;
    public String type;
    public boolean isToday;

    public Item(){

    }

    @Override
    public boolean equals(Object obj){

        Item item = (Item)obj;

        return (this.getId().equals(item.getId()));
    }

    @Override
    public int hashCode(){
        return Objects.hash(id);
    }



    public Item(String name, String desc, String price, String time, String picName, String type, boolean isToday) {
        this.name = name;
        this.desc = desc;
        this.price = price;
        this.time = time;
        this.picName = picName;
        this.type = type;
        this.isToday = isToday;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", price='" + price + '\'' +
                ", time='" + time + '\'' +
                ", picName='" + picName + '\'' +
                ", type='" + type + '\'' +
                ", isToday=" + isToday +
                '}';
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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPicName() {
        return picName;
    }

    public void setPicName(String picName) {
        this.picName = picName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isToday() {
        return isToday;
    }

    public void setToday(boolean today) {
        isToday = today;
    }
}
