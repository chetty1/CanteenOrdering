package WebsiteControllers;

import Model.Tranaction;
import Repositories.transactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

/**
 * Created by chett_000 on 2017/06/02.
 */
@Controller
public class historyController {


    @Autowired
    transactionRepository repository;

    ArrayList<Tranaction> list;
    ArrayList<Tranaction> faclist;

    @RequestMapping(value = "/history")
    public ModelAndView view() {
        ModelAndView view = new ModelAndView("History");

         list = new ArrayList<>(repository.findAllByUser(SecurityContextHolder.getContext().getAuthentication().getName()));
        view.addObject("historyList", list);
        view.addObject("history","history");
        view.addObject("search","Date");

        return view;
    }

    @RequestMapping(value = "/history",method = RequestMethod.POST)
    public ModelAndView viewsearch(@RequestParam("search") String search) {
        ModelAndView view = new ModelAndView("History");

        ArrayList<Tranaction> list1 = new ArrayList<>();
        for (Tranaction tran: list ) {
            if(tran.getDate().contains(search)){
                list1.add(tran);
            }
        }
        view.addObject("historyList", list1);
        view.addObject("history","history");
        view.addObject("search","Date");

        return view;
    }

    @RequestMapping(value = "/factoryhistory")
    public ModelAndView factoryview() {
        ModelAndView view = new ModelAndView("History");

        faclist = new ArrayList<>(repository.findAll());
        view.addObject("historyList",faclist );
        view.addObject("history","factoryhistory");
        view.addObject("search","Name");
        return view;
    }


    @RequestMapping(value = "/factoryhistory",method = RequestMethod.POST)
    public ModelAndView factorysearchview(@RequestParam("search") String search) {
        ModelAndView view = new ModelAndView("History");
        ArrayList<Tranaction> list2 = new ArrayList<>();
         for (Tranaction tran: faclist ) {
            if(tran.getUser().getName().contains(search)){
                list2.add(tran);
            }
        }
        view.addObject("historyList", list2);
        System.out.println(list2);
        view.addObject("history","factoryhistory");
        view.addObject("search","Name");
        return view;
    }
}