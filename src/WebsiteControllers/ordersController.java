package WebsiteControllers;

import Model.Item;
import Model.Staff;
import Model.Tranaction;
import Repositories.transactionRepository;
import Repositories.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by chett_000 on 2017/05/10.
 */

@Controller
public class ordersController {

    @Autowired
    transactionRepository repo;


    @Autowired
    userRepository userRepo;


    @RequestMapping(value = "/orders")
    public ModelAndView Orders() throws ParseException {
        List<Tranaction> teaList = new ArrayList<>();

        List<Tranaction> lunchList = new ArrayList<>();
        ModelAndView view = new ModelAndView("Orders");
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/YYYY");
        List<Tranaction> repolist = repo.findAllByDate(format.format(new Date()));
        for (int i = 0; i < repolist.size(); i++) {
            Tranaction tran = repolist.get(i);
            if (tran.getTime().equals("tea") && tran.isOrded()) {
                teaList.add(tran);
            } else if (tran.getTime().equals("lunch") && tran.isOrded()) {
                lunchList.add(tran);
            }

        }

        SimpleDateFormat parser = new SimpleDateFormat("HH:mm");
        Date tea = parser.parse("10:15");
        Date lunch = parser.parse("13:30");


        Date userDate = parser.parse(parser.format(new Date()));
        if (userDate.after(tea)) {
            teaList.clear();

        }
        if (userDate.after(lunch)) {
            lunchList.clear();


        }


        view.addObject("teaList", teaList);
        view.addObject("lunchList", lunchList);

        return view;
    }

    @RequestMapping(value = "/takeawayOrders")
    public ModelAndView Takeaway() throws ParseException {

        List<Tranaction> takeawayList = new ArrayList<>();
        ModelAndView view = new ModelAndView("Takeaway");
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/YYYY");
        List<Tranaction> repolist = repo.findAllByDate(format.format(new Date()));
        for (int i = 0; i < repolist.size(); i++) {
            Tranaction tran = repolist.get(i);
            if (tran.getTime().equals("takeaway") && tran.isOrded()) {
                takeawayList.add(tran);
            }
        }

        SimpleDateFormat parser = new SimpleDateFormat("HH:mm");

        Date takeaway = parser.parse("16:30");


        Date userDate = parser.parse(parser.format(new Date()));

        if (userDate.after(takeaway)) {
            takeawayList.clear();
        }


        view.addObject("takeawayList", takeawayList);


        return view;
    }


    @RequestMapping(value = "/cancelorders", method = RequestMethod.POST)
    public void Cancel(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        Tranaction tran = repo.findById(id);

        System.out.println(tran.getQuantity());

        Item item = new Item(tran.getFood().getName() + "-Order Cancelled", "Order Cancelled", Double.toString(tran.getQuantity() * Double.parseDouble(tran.getFood().getPrice())), "", "", "", false);

        Staff staff = userRepo.findByUsername(tran.getUser().getUsername());
         double   balancebefore = staff.getBalance();

        repo.delete(tran);
        double balanceafter = (tran.getQuantity() * Double.parseDouble(tran.getFood().getPrice())) + balancebefore;
        staff.setBalance(balanceafter);
        userRepo.save(staff);
        Tranaction cancel = new Tranaction(item, staff, "cancelorder", 1, true);
        repo.save(cancel);


    }


}
