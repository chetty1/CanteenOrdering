package WebsiteControllers;

import Model.Item;
import Model.Tranaction;
import Repositories.itemRepostory;
import Repositories.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.resource.TransformedResource;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
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


    @RequestMapping(value="/tea")
    public ModelAndView view(){
        ModelAndView view = new ModelAndView("MenuTemplate");
        ArrayList<Item> item = new ArrayList<>();
        ArrayList<Item> standard = new ArrayList<>();
        Set<Item> duplicates = new HashSet<>();
        standard.addAll(repo.findAllByTimeAndIsTodayIsTrueAndNameNotOrNameNot("standard","Large Chips","Small Chips"));
        standard.addAll(repo.findAllByTimeAndIsTodayIsTrueAndNameNotOrNameNot("tea","Large Chips","Small Chips"));
        item.addAll(repo.findAllByTypeAndIsTodayIsTrue("sandwich"));



        duplicates.addAll(standard);
        duplicates.removeAll(item);


        view.addObject("sandwichList",item);

        view.addObject("largeChips","large");
        view.addObject("name","Large");
        view.addObject("largePriceid","priceInputLarge");
        view.addObject("largePrice","8");
        view.addObject("name2","Small");
        view.addObject("smallPrice","4");
        view.addObject("smallChips","small");
        view.addObject("smallPriceid","priceInputSmall");
view.addObject("balance",repository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).getBalance());
view.addObject("takeaway",false);
    view.addObject("foodlist",duplicates);
        view.addObject("time","tea");

        return view;
    }

    @RequestMapping(value="/lunch")
    public ModelAndView lunchview(){
        ModelAndView view = new ModelAndView("MenuTemplate");
        ArrayList<Item> item = new ArrayList<>();
        ArrayList<Item> standard = new ArrayList<>();
        Set<Item> duplicates = new HashSet<>();
        standard.addAll(repo.findAllByTimeAndIsTodayIsTrueAndNameNotOrNameNot("standard","Large Chips","Small Chips"));
        standard.addAll(repo.findAllByTimeAndIsTodayIsTrueAndNameNotOrNameNot("lunch","Large Chips","Small Chips"));
        item.addAll(repo.findAllByTypeAndIsTodayIsTrue("sandwich"));


        System.out.println(standard);
        duplicates.addAll(standard);
        duplicates.removeAll(item);

        view.addObject("sandwichList",item);

        view.addObject("largeChips","large");
        view.addObject("name","Large");
        view.addObject("largePriceid","priceInputLarge");
        view.addObject("largePrice","8");
        view.addObject("name2","Small");
        view.addObject("smallPrice","4");
        view.addObject("smallChips","small");
        view.addObject("smallPriceid","priceInputSmall");
view.addObject("takeaway",false);
        view.addObject("balance",repository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).getBalance());

        view.addObject("foodlist",duplicates);
        view.addObject("time","lunch");

        return view;
    }

    @RequestMapping(value="/standard")
    public ModelAndView standardview(){
        ModelAndView view = new ModelAndView("MenuTemplate");
        ArrayList<Item> item = new ArrayList<>();
        ArrayList<Item> standard = new ArrayList<>();
        Set<Item> duplicates = new HashSet<>();
       standard.addAll(repo.findAllByTimeAndIsTodayIsTrueAndNameNotOrNameNot("standard","Large Chips","Small Chips"));
        item.addAll(repo.findAllByTypeAndIsTodayIsTrue("sandwich"));

//System.out.print(repo.findAllByTimeAndIsTodayIsTrueAndNameNotOrNameNot("standard","Large Chips","Small Chips"));
        duplicates.addAll(standard);
        duplicates.removeAll(item);

        view.addObject("sandwichList",item);
        view.addObject("largeChips","large");
        view.addObject("name","Large");
        view.addObject("largePriceid","priceInputLarge");
        view.addObject("largePrice","8");
        view.addObject("name2","Small");
        view.addObject("smallPrice","4");
        view.addObject("smallChips","small");
        view.addObject("smallPriceid","priceInputSmall");
view.addObject("takeaway",false);
        view.addObject("balance",repository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).getBalance());
System.out.print(duplicates);
        view.addObject("foodlist",duplicates);
        view.addObject("time","lunch");

        return view;
    }
    @RequestMapping(value="/takeaway")
    public ModelAndView takeawayview(){
        ModelAndView view = new ModelAndView("MenuTemplate");
        ArrayList<Item> item = new ArrayList<>();
        item.addAll(repo.findAllByTimeAndIsTodayIsTrue("takeaway"));


        view.addObject("foodlist",item);
        view.addObject("time","takeaway");
        view.addObject("balance",repository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).getBalance());

        return view;
    }


}
