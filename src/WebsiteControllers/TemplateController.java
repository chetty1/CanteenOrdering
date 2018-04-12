package WebsiteControllers;

import Model.Item;
import Model.Staff;
import Repositories.itemRepostory;
import Repositories.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by chett_000 on 2017/06/01.
 */
@Controller
public class TemplateController {

    @Autowired
    itemRepostory repo;


    @Autowired
    userRepository repository;


    public ModelAndView menu() throws ParseException {
        ModelAndView view = new ModelAndView("menu");


        Staff staff =repository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        view.addObject("balance","R"+staff.getBalance());
        String standard = "10:30";
        String lunch = "8:30";

        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        Date date1 = format.parse(standard);
        Date date2 = format.parse(lunch);
        Date date3 = format.parse(format.format(new Date()));
        long tea = (date2.getTime() - date3.getTime())/1000;
        if(tea<0){
            tea=1;
        }
        long standard1 = (date1.getTime() - date3.getTime())/1000;
        if(standard1<0){
            standard1=1;
        }
        view.addObject("timeLunch",standard1);
        view.addObject("timeTea",tea);

        return view;
    }

    @RequestMapping(value = "/tea")
    public ModelAndView view() throws ParseException {
        Staff staff =repository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());

        ModelAndView view = new ModelAndView("MenuTemplate");
        ArrayList<Item> item = new ArrayList<>();
        ArrayList<Item> standard = new ArrayList<>();
        Set<Item> duplicates = new HashSet<>();
        standard.addAll(repo.findAllByTimeAndIsTodayIsTrueAndNameNotOrNameNot("standard", "Large Chips", "Small Chips"));
        standard.addAll(repo.findAllByTimeAndIsTodayIsTrueAndNameNotOrNameNot("tea", "Large Chips", "Small Chips"));
        item.addAll(repo.findAllByTypeAndIsTodayIsTrue("sandwich"));


        duplicates.addAll(standard);
        duplicates.removeAll(item);


        view.addObject("sandwichList", item);

        view.addObject("largeChips", "large");
        view.addObject("name", "Large");
        view.addObject("largePriceid", "priceInputLarge");
        view.addObject("largePrice", "8");
        view.addObject("name2", "Small");
        view.addObject("smallPrice", "4");
        view.addObject("smallChips", "small");
        view.addObject("smallPriceid", "priceInputSmall");
        view.addObject("balance",staff.getBalance());
        view.addObject("takeaway", false);
        view.addObject("foodlist", duplicates);
        view.addObject("time", "tea");
        view.addObject("time1", "lunch");
        view.addObject("time2", "standard");

        SimpleDateFormat parser = new SimpleDateFormat("HH:mm");
        Date tea = parser.parse("08:30");
        Date userDate = parser.parse(parser.format(new Date()));
        System.out.println(staff.getName());
        if(( staff.getName().equals("Russell Gwynn") || staff.getName().equals("Ronny Pelucci"))) {
            return view;
        }


        if(userDate.before(tea)) {
            return view;
        }

        else {
            return menu();
        }
    }


    @RequestMapping(value = "/lunch")
    public ModelAndView lunchview() throws ParseException {
        ModelAndView view = new ModelAndView("MenuTemplate");
        ArrayList<Item> item = new ArrayList<>();
        ArrayList<Item> standard = new ArrayList<>();
        Set<Item> duplicates = new HashSet<>();
        standard.addAll(repo.findAllByTimeAndIsTodayIsTrueAndNameNotOrNameNot("standard", "Large Chips", "Small Chips"));
        standard.addAll(repo.findAllByTimeAndIsTodayIsTrueAndNameNotOrNameNot("lunch", "Large Chips", "Small Chips"));
        item.addAll(repo.findAllByTypeAndIsTodayIsTrue("sandwich"));
        Staff staff =repository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());


        System.out.println(standard);
        duplicates.addAll(standard);
        duplicates.removeAll(item);

        view.addObject("sandwichList", item);

        view.addObject("largeChips", "large");
        view.addObject("name", "Large");
        view.addObject("largePriceid", "priceInputLarge");
        view.addObject("largePrice", "8");
        view.addObject("name2", "Small");
        view.addObject("smallPrice", "4");
        view.addObject("smallChips", "small");
        view.addObject("smallPriceid", "priceInputSmall");
        view.addObject("takeaway", false);
        view.addObject("balance", staff.getBalance());

        view.addObject("foodlist", duplicates);
        view.addObject("time", "lunch");
        view.addObject("time1", "tea");
        view.addObject("time2", "standard");



        SimpleDateFormat parser = new SimpleDateFormat("HH:mm");
        Date lunch = parser.parse("08:30");
        Date userDate = parser.parse(parser.format(new Date()));

        if(( staff.getName().equals("Russell Gwynn") || staff.getName().equals("Ronny Pelucci")|| staff.getName().equals("Guest1")|| staff.getName().equals("Guest2")|| staff.getName().equals("Guest3"))) {
            return view;
        }


        if(userDate.before(lunch)) {
            return view;
        }

        else {
            return menu();
        }
    }

    @RequestMapping(value = "/standard")
    public ModelAndView standardview() throws ParseException {
        ModelAndView view = new ModelAndView("MenuTemplate");
        ArrayList<Item> item = new ArrayList<>();
        ArrayList<Item> standard = new ArrayList<>();
        Set<Item> duplicates = new HashSet<>();
        standard.addAll(repo.findAllByTimeAndIsTodayIsTrueAndNameNotOrNameNot("standard", "Large Chips", "Small Chips"));
        item.addAll(repo.findAllByTypeAndIsTodayIsTrue("sandwich"));

        duplicates.addAll(standard);
        duplicates.removeAll(item);

        view.addObject("sandwichList", item);
        view.addObject("largeChips", "large");
        view.addObject("name", "Large");
        view.addObject("largePriceid", "priceInputLarge");
        view.addObject("largePrice", "8");
        view.addObject("name2", "Small");
        view.addObject("smallPrice", "4");
        view.addObject("smallChips", "small");
        view.addObject("smallPriceid", "priceInputSmall");
        view.addObject("takeaway", false);
        view.addObject("balance", repository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).getBalance());
        view.addObject("foodlist", duplicates);
        view.addObject("time", "lunch");

        SimpleDateFormat parser = new SimpleDateFormat("HH:mm");
        Date standard1 = parser.parse("10:30");
        Date userDate = parser.parse(parser.format(new Date()));

        if(userDate.before(standard1)) {
            return view;
        }
        else {
            return menu();
        }
    }

    @RequestMapping(value = "/takeaway")
    public ModelAndView takeawayview() {
        ModelAndView view = new ModelAndView("MenuTemplate");
        ArrayList<Item> item = new ArrayList<>();
        item.addAll(repo.findAllByTimeAndIsTodayIsTrue("lunch"));


        view.addObject("foodlist", item);
        view.addObject("time", "takeaway");
        view.addObject("balance", repository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).getBalance());

        return view;
    }


}
