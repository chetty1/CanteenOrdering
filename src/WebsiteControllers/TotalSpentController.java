package WebsiteControllers;

import Model.Tranaction;
import Repositories.transactionRepository;
import Services.NormalDateComp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by chett_000 on 2018/01/23.
 */

@Controller
public class TotalSpentController {
@Autowired
    transactionRepository repo;

@RequestMapping(value = "/totalspent")
    public String view(){
    return"TotalSpent";
}

@RequestMapping(value = "/totalspent",method = RequestMethod.POST)
    public ModelAndView View(@RequestParam("before") String before, @RequestParam("after") String after) throws ParseException {

        ModelAndView view = new ModelAndView("TotalSpent");
Integer count=0;
 SimpleDateFormat format=   new SimpleDateFormat("dd/MM/yyyy");
    ArrayList<Tranaction> dateComp = new ArrayList<Tranaction>();

    ArrayList<Tranaction> list = (ArrayList<Tranaction>) repo.findAllByNotTime("cancelorder");
    for(int i =0;i<list.size();i++){
        if(format.parse(before).compareTo(format.parse(list.get(i).getDate()))==-1 && format.parse(after).compareTo(format.parse(list.get(i).getDate()))==1){
            dateComp.add(list.get(i));
        }
        else if (format.parse(before).compareTo(format.parse(list.get(i).getDate()))==0 ){
            dateComp.add(list.get(i));
        }
        else if (format.parse(after).compareTo(format.parse(list.get(i).getDate()))==0 ){
            dateComp.add(list.get(i));
        }
    }



    for(int i=0;i<dateComp.size();i++){
    Tranaction tran = dateComp.get(i);

    if(tran.getFood().getName().equals("Balance Added")){
        dateComp.remove(tran);
    }


}

/*for (int i=0;i<dateComp.size();i++){
    Tranaction tran = dateComp.get(i);
    if(tran.getTime().equals("cancelorder")){
     //   System.out.println(tran);
        dateComp.remove(i);
    }
}

    for (int i=0;i<dateComp.size();i++){
        Tranaction tran = dateComp.get(i);
        if(tran.getTime().equals("cancelorder")){
            //   System.out.println(tran);
            dateComp.remove(i);
        }
    }
*/

    TreeMap<String,Integer> userMap = new TreeMap<>();
    for(int i=0;i<dateComp.size();i++){
        Tranaction tran = dateComp.get(i);
        count = userMap.get(dateComp.get(i).getUser().getName());
        if(count==null){
           count= Integer.parseInt(tran.getFood().getPrice())*tran.getQuantity();
        }
        else{
            count = count +Integer.parseInt(tran.getFood().getPrice())*tran.getQuantity();
        }

        userMap.put(dateComp.get(i).getUser().getName(),count);


    }



    view.addObject("dateBefore",before);
    view.addObject("dateAfter",after);
    view.addObject("spentList",userMap);

    return view;
    }

}
