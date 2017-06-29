package WebsiteControllers;

import Model.Item;
import Model.Staff;
import Model.Tranaction;
import Repositories.itemRepostory;
import Repositories.transactionRepository;
import Repositories.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by chett_000 on 2017/05/10.
 */
@Controller
public class checkoutController {

    @Autowired
    userRepository repository;

    @Autowired
    itemRepostory repo;

    @Autowired
    transactionRepository transrepo;


    ArrayList<Tranaction> tranactions = new ArrayList<>();
    ArrayList<Tranaction> quantity = new ArrayList<>();
    ArrayList<Tranaction> orders ;

    int total;
    Staff staff;
    boolean checkout = false;

    @RequestMapping(value = "/checkout")
    public ModelAndView checkout() {
if (staff==null){
   staff= repository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
}

        Map<Tranaction, Integer> map = new HashMap<Tranaction, Integer>();
        total = 0;
        //quantity.clear();
        for (Tranaction temp : tranactions) {
            Integer count = map.get(temp);
            map.put(temp, (count == null) ? 1 : count + 1);
        }

        for (Map.Entry<Tranaction, Integer> entry : map.entrySet()) {

            Tranaction tran = entry.getKey();

            tran.setQuantity(entry.getValue());
            quantity.add(tran);
        }
        tranactions.clear();
        for (Tranaction tranaction : quantity) {
            total = total + (Integer.parseInt(tranaction.getFood().getPrice()) * tranaction.getQuantity());

        }

        ModelAndView view = new ModelAndView("checkout");
        view.addObject("transactionList", quantity);
        view.addObject("total", total);
        view.addObject("balance",staff.getBalance());

        return view;
    }

    @RequestMapping(value = "/clickdelete", method = RequestMethod.POST)
    public void delete(HttpServletRequest request, HttpServletResponse response) {
        String raw = request.getParameter("Id");
        String id = raw.substring(6);
        for (int i = 0; i < quantity.size(); i++) {
            if (id.equals(quantity.get(i).getId())) {
                quantity.remove(i);
            }
        }
    }

    @RequestMapping(value = "/clickcheckout", method = RequestMethod.POST)
    public
    @ResponseBody
    boolean clickcheckout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int before = staff.getBalance();
      orders  = new ArrayList<>();
        if ((before - total) > 0) {
            staff.setBalance(before - total);
            repository.save(staff);

            checkout = true;
transrepo.save(quantity);
            orders.clear();
            orders.addAll(quantity);
            quantity.clear();
            return true;
        } else {
            return false;
        }

    }


    @RequestMapping(value = "/{time}/clickbuy", method = RequestMethod.POST)
    public void ajax(HttpServletRequest request, HttpServletResponse response, @PathVariable String time) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Item item;
        if(request.getParameter("Id").equals("Large Chips")){
         item  = repo.findByName("Large Chips");

        }
        else if  (request.getParameter("Id").equals("Small Chips")){
             item = repo.findByName("Small Chips");

        }
        else
        {
            item = repo.findById(request.getParameter("Id"));
        }

        staff = repository.findByUsername(username);
        SimpleDateFormat date = new SimpleDateFormat("HHss");
        Tranaction trans = new Tranaction(staff.getId() + date.format(new Date()), item, staff, time, 1);
        tranactions.add(trans);

    }

    @RequestMapping(value = "/liveorders")
    public SseEmitter orders() throws IOException {
        SseEmitter emitter = new SseEmitter();
        if (checkout) {
            emitter.send(orders);
            checkout = false;
            emitter.complete();

        }
        else {
            emitter.complete();
        }

        return emitter;

    }
}
