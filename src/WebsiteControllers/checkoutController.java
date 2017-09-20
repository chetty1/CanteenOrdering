package WebsiteControllers;

import Model.Item;
import Model.Staff;
import Model.Tranaction;
import Repositories.itemRepostory;
import Repositories.transactionRepository;
import Repositories.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
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


    ArrayList<Tranaction> quantity;
    ArrayList<Tranaction> orders;

    int total;
    boolean checkout = false;

    @RequestMapping(value = "/checkout")
    public ModelAndView checkout(HttpServletRequest request) {
        quantity = new ArrayList<>();

        Staff staff1 = repository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());


        Map<Tranaction, Integer> map = new HashMap<Tranaction, Integer>();
        total = 0;


        for (Tranaction temp : transrepo.findAllByUserAndDate(staff1, new SimpleDateFormat("dd/MM/YYYY").format(new Date()))) {
            Integer count = map.get(temp);
            if (!temp.isOrded()) {

                map.put(temp, (count == null) ? 1 : count + 1);
            }

            if (count!=null){
                transrepo.delete(temp.getId());
            }
        }

        for (Map.Entry<Tranaction, Integer> entry : map.entrySet()) {

            Tranaction tran = entry.getKey();

            tran.setQuantity(entry.getValue());
            transrepo.save(tran);
            quantity.add(tran);
        }
        for (Tranaction tranaction : quantity) {
            total = total + (Integer.parseInt(tranaction.getFood().getPrice()) * tranaction.getQuantity());

        }

        ModelAndView view = new ModelAndView("checkout");
        view.addObject("transactionList", quantity);
        view.addObject("total", total);
        view.addObject("balance", staff1.getBalance());

        return view;
    }


    @RequestMapping(value = "/clickdelete", method = RequestMethod.POST)
    public @ResponseBody boolean delete(HttpServletRequest request, HttpServletResponse response) {
        String raw = request.getParameter("Id");
        String id = raw.substring(6);

        Tranaction tran =transrepo.findById(id);
        System.out.println(tran);

       transrepo.delete(tran.getId());
        return true;
    }


    @RequestMapping(value = "/clickcheckout", method = RequestMethod.POST)
    public
    @ResponseBody
    boolean clickcheckout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Staff staff = repository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        int before = staff.getBalance();
        orders = new ArrayList<>();
        int tot = 0;
        ArrayList<Tranaction> list = (ArrayList<Tranaction>) transrepo.findAllByUserAndDate(staff, new SimpleDateFormat("dd/MM/YYYY").format(new Date()));
        for (int i = 0; i < list.size(); i++) {
            Tranaction tran = list.get(i);

            if (tran.isOrded()) {
                list.remove(i);
            }
        }


        for (int j = 0; j < list.size(); j++) {
            tot = tot + (Integer.parseInt(list.get(j).getFood().getPrice())*list.get(j).getQuantity());
        }
        if ((before - tot) > 0) {

            staff.setBalance(before - tot);

            repository.save(staff);



            for (int i = 0; i < list.size(); i++) {
                Tranaction tran = list.get(i);
                //tran.setUser(staff);
                tran.setOrded(true);

            }
            transrepo.save(list);

            System.out.println(list);
            orders.clear();
            orders.addAll(list);
            quantity.clear();
            list.clear();
            checkout = true;

            return true;
        } else {
            quantity.clear();
            orders.clear();
            list.clear();
            return false;
        }

       /* if ((before - total) > 0) {

            for (Tranaction temp : transrepo.findAllByUserAndDate(staff,new SimpleDateFormat("dd/MM/YYYY").format(new Date()))) {

                if(!temp.isOrded()) {
                    map.put(temp, (count == null) ? 1 : count + 1);
                }
            }
            staff.setBalance(before - total);

            repository.save(staff);

            checkout = true;


            for (int i=0;i<quantity.size();i++) {
                Tranaction tran = quantity.get(i);
                tran.setUser(staff);
                tran.setOrded(true);

            }
            transrepo.save(quantity);

            System.out.println(quantity);
            orders.clear();
            orders.addAll(quantity);
            quantity.clear();
            return true;
        } else {
            quantity.clear();
            orders.clear();

            return false;
        }*/

    }


    @RequestMapping(value = "/{time}/clickbuy", method = RequestMethod.POST)
    public void ajax(HttpServletRequest request, HttpServletResponse response, @PathVariable String time) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Item item;
        if (request.getParameter("Id").equals("Large Chips")) {
            item = repo.findByName("Large Chips");

        } else if (request.getParameter("Id").equals("Small Chips")) {
            item = repo.findByName("Small Chips");

        } else {
            item = repo.findById(request.getParameter("Id"));
        }

        Staff staff = repository.findByUsername(username);
        SimpleDateFormat date = new SimpleDateFormat("HHss");
        Tranaction trans = new Tranaction(staff.getId() + date.format(new Date()), item, staff, time, 1, false);

        transrepo.save(trans);
    }

    @RequestMapping(value = "/liveorders")
    public SseEmitter orders() throws IOException {
        SseEmitter emitter = new SseEmitter();


        if (checkout) {
            emitter.send(orders);
            checkout = false;

            emitter.complete();

        } else {
            emitter.complete();
        }

        return emitter;

    }
}
