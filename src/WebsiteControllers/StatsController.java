package WebsiteControllers;

import Model.Tranaction;
import Model.WeatherObject;
import Repositories.transactionRepository;
import Repositories.weatherRepository;
import Services.DateComparator;
import Services.NormalDateComp;
import Services.Weather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by chett_000 on 2017/09/19.
 */

@Controller
public class StatsController {

    @Autowired
    transactionRepository repository;

    @Autowired
    weatherRepository weatherRepo;

    @RequestMapping(value ="/alltimestats")
    public ModelAndView view(){
        ModelAndView view = new ModelAndView("Statistics");


        HashMap<String,Integer> freq = new HashMap<>();

        ArrayList<Tranaction> stat = (ArrayList<Tranaction>) repository.findAll();

        for(Tranaction tran:stat) {
            Integer count = freq.get(tran.getFood().getName());
            if (tran.isOrded()) {

                freq.put(tran.getFood().getName(), (count == null) ? 1 : count + 1);
            }
        }
        view.addObject("statList",freq);
        return view;

    }


    @RequestMapping(value ="/teastats")
    public ModelAndView viewtea(){
        ModelAndView view = new ModelAndView("Statistics");


        HashMap<String,Integer> freq = new HashMap<>();

        ArrayList<Tranaction> stat = (ArrayList<Tranaction>) repository.findAll();

        for(Tranaction tran:stat) {
            Integer count = freq.get(tran.getFood().getName());
            if (tran.isOrded()&& tran.getTime().equals("tea")) {

                freq.put(tran.getFood().getName(), (count == null) ? 1 : count + 1);
            }
        }
        view.addObject("statList",freq);
        return view;

    }

    @RequestMapping(value ="/lunchstats")
    public ModelAndView viewlunch(){
        ModelAndView view = new ModelAndView("Statistics");


        HashMap<String,Integer> freq = new HashMap<>();

        ArrayList<Tranaction> stat = (ArrayList<Tranaction>) repository.findAll();

        for(Tranaction tran:stat) {
            Integer count = freq.get(tran.getFood().getName());
            if (tran.isOrded() && tran.getTime().equals("lunch")) {

                freq.put(tran.getFood().getName(), (count == null) ? 1 : count + 1);
            }
        }
        view.addObject("statList",freq);
        return view;

    }

    @RequestMapping(value ="/userstats")
    public ModelAndView viewuser(){
        ModelAndView view = new ModelAndView("Statistics");


        HashMap<String,Integer> freq = new HashMap<>();

        ArrayList<Tranaction> stat = (ArrayList<Tranaction>) repository.findAll();

        for(Tranaction tran:stat) {
            Integer count = freq.get(tran.getUser().getName());
            if (tran.isOrded() ) {

                freq.put(tran.getUser().getName(), (count == null) ? 1 : count + 1);
            }
        }
        view.addObject("statList",freq);
        return view;

    }

    @RequestMapping(value ="/daystats")
    public ModelAndView viewday(){
        ModelAndView view = new ModelAndView("LineGraphStats");


        Map<String,Integer> freq = new LinkedHashMap<>();

        ArrayList<Tranaction> stat = (ArrayList<Tranaction>) repository.findAll();
stat.sort(new NormalDateComp());



        for(Tranaction tran:stat) {
            Integer count = freq.get(tran.getDate());
            if (tran.isOrded() ) {

                freq.put(tran.getDate(), (count == null) ? 1 : count + 1);
            }
        }


        view.addObject("statList",freq);
        return view;

    }



    @RequestMapping(value ="/tempstats")
    public ModelAndView viewtemp(){


        ModelAndView view = new ModelAndView("LineGraphStats");


        Map<String,Integer> freq = new TreeMap<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });



        ArrayList<Tranaction> stat = (ArrayList<Tranaction>) repository.findAll();
        stat.sort(new NormalDateComp());





        for(Tranaction tran:stat) {
            WeatherObject weather =  weatherRepo.findByDate(tran.getDate());

            if (tran.isOrded() && weather!=null) {
                Integer count = freq.get(String.valueOf(Math.ceil(Double.parseDouble(weather.getTemp()))));

                freq.put(String.valueOf(Math.ceil(Double.parseDouble(weather.getTemp()))), (count == null) ? 1 : count + 1);
            }
        }





        view.addObject("statList",freq);
        return view;

    }


    @RequestMapping(value ="/weatherstats")
    public ModelAndView viewweather(){


        ModelAndView view = new ModelAndView("Statistics");


        Map<String,Integer> freq = new LinkedHashMap<>();



        ArrayList<Tranaction> stat = (ArrayList<Tranaction>) repository.findAll();
        stat.sort(new NormalDateComp());





        for(Tranaction tran:stat) {
            WeatherObject weather =  weatherRepo.findByDate(tran.getDate());

            if (tran.isOrded() && weather!=null) {
                Integer count = freq.get(weather.getWeather());

                freq.put(weather.getWeather(), (count == null) ? 1 : count + 1);
            }
        }





        view.addObject("statList",freq);
        return view;

    }


}
