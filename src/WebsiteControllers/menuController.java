package WebsiteControllers;

import Model.Item;
import Model.Staff;
import Repositories.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import  Repositories.itemRepostory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by chett_000 on 2017/05/10.
 */
@Controller
public class menuController {

    @Autowired
    public userRepository repository;
@Autowired
public itemRepostory repo;


    @RequestMapping(value = "/menu")
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




}
