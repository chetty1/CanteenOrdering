package WebsiteControllers;

import Model.Tranaction;
import Repositories.transactionRepository;
import com.sun.javafx.sg.prism.NGShape;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by chett_000 on 2018/01/29.
 */
@Controller
public class quantitiesController {
    @Autowired
    transactionRepository repo;


    @RequestMapping(value="/quantities")
    public ModelAndView view(){
        ModelAndView view = new ModelAndView("Quantities");


        ArrayList<Tranaction> list =(ArrayList<Tranaction>) repo.findAllByDate( new SimpleDateFormat("dd/MM/YYYY").format(new Date()));

        for(int i=0;i<list.size();i++){
            Tranaction tran = list.get(i);

            if(tran.getFood().getName().equals("Balance Added")){
                list.remove(tran);

            }

            if(tran.getFood().getDesc().equals("Order Cancelled")){
                list.remove(tran);
            }
        }

        System.out.print(list);

        HashMap<String, Integer> teaMap = convertToMap(list,"tea");
        HashMap<String, Integer> lunchMap = convertToMap(list,"lunch");
        HashMap<String, Integer> takeawayMap = convertToMap(list,"takeaway");




        view.addObject("teaList",teaMap);
        view.addObject("lunchList",lunchMap);
        view.addObject("takeawayList",takeawayMap);


        return view;
    }

    public HashMap<String,Integer> convertToMap(ArrayList<Tranaction> list,String time){
        HashMap<String, Integer> userMap = new HashMap<String, Integer>();
        for(int i=0;i<list.size();i++) {
            Tranaction tran = list.get(i);
            if (tran.getTime().equals(time)) {
                Integer count = userMap.get(list.get(i).getFood().getName());
                if (count == null) {
                    count = tran.getQuantity();
                } else {
                    count = count +  tran.getQuantity();
                }
                userMap.put(list.get(i).getFood().getName(), count);
            }
        }
        return userMap;
    }
}
