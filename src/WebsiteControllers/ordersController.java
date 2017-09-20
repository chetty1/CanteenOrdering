package WebsiteControllers;

import Model.Tranaction;
import Repositories.transactionRepository;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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





    @RequestMapping(value = "/orders")
    public ModelAndView Orders() throws ParseException {
        List<Tranaction> teaList = new ArrayList<>();

        List<Tranaction> lunchList = new ArrayList<>();
        List<Tranaction> takeawayList = new ArrayList<>();
        ModelAndView view = new ModelAndView("Orders");
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/YYYY");
        List<Tranaction> repolist = repo.findAllByDate(format.format(new Date()));
        for (int i=0;i<repolist.size();i++) {
            Tranaction tran = repolist.get(i);
            if (tran.getTime().equals("tea")&&tran.isOrded()) {
                teaList.add(tran);
            } else if (tran.getTime().equals("lunch")&&tran.isOrded()) {
                lunchList.add(tran);
            }
            else if (tran.getTime().equals("takeaway")&&tran.isOrded()){
                takeawayList.add(tran);
            }
        }

        SimpleDateFormat parser = new SimpleDateFormat("HH:mm");
        Date tea = parser.parse("10:15");
        Date lunch = parser.parse("13:30");
        Date takeaway = parser.parse("16:30");



            Date userDate = parser.parse(parser.format(new Date()));
            if (userDate.after(tea) ) {
                teaList.clear();

            }
             if (userDate.after(lunch)){
                lunchList.clear();


            }
             if (userDate.after(takeaway)){
                takeawayList.clear();
            }


        view.addObject("takeawayList",takeawayList);
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
        for (int i=0;i<repolist.size();i++) {
         Tranaction tran = repolist.get(i);
             if (tran.getTime().equals("takeaway")&&tran.isOrded()){
                takeawayList.add(tran);
            }
        }

        SimpleDateFormat parser = new SimpleDateFormat("HH:mm");

        Date takeaway = parser.parse("16:30");



        Date userDate = parser.parse(parser.format(new Date()));

        if (userDate.after(takeaway)){
            takeawayList.clear();
        }


        view.addObject("takeawayList",takeawayList);


        return view;
    }




}
