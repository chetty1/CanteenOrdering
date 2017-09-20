package WebsiteControllers;

import Model.Tranaction;
import Repositories.transactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by chett_000 on 2017/09/19.
 */

@Controller
public class StatsController {

    @Autowired
    transactionRepository repository;

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
}
