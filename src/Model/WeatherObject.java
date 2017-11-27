package Model;

import org.springframework.data.annotation.Id;

/**
 * Created by chett_000 on 2017/09/20.
 */
public class WeatherObject {

    @Id
    private String id;
    private String temp;
    private String weather;
    private  String date;

    public WeatherObject(String temp, String weather, String date) {
        this.temp = temp;
        this.weather = weather;
        this.date = date;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
