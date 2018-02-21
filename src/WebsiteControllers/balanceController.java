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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by chett_000 on 2017/06/03.
 */
@Controller
public class balanceController {


    @Autowired
    userRepository repository;

    @Autowired
    transactionRepository repo;


    @RequestMapping(value = "/balance")
    public String view(){
        return "changeBalance";
    }


    @RequestMapping(value = "/balance" , method = RequestMethod.POST)
    public ModelAndView search(@RequestParam("search")String search){
        ModelAndView view = new ModelAndView("changeBalance");
        List<Staff> staffList = new ArrayList<>(repository.findAllByNameLike(search));
        view.addObject("balanceList",staffList);
        return view;
    }

    @RequestMapping(value = "/change", method = RequestMethod.POST)
    public void change(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
          String amount=request.getParameter("amount");

         Staff staff=  repository.findById(id);
        staff.setBalance(staff.getBalance()+Integer.parseInt(amount));

        SimpleDateFormat date = new SimpleDateFormat("HHss");
        Item balance = new Item("Balance Added","Balance added to account",amount,"","","",false);
        Tranaction tran = new Tranaction(balance,staff,"",1,true);
        repo.save(tran);
        repository.save(staff);
    }



    @RequestMapping(value = "/correct", method = RequestMethod.POST)
    public void correct(HttpServletRequest request, HttpServletResponse response) {
    ArrayList<Staff> list = (ArrayList<Staff>) repository.findAll();

        for(int i=0;i<list.size();i++){
            list.get(i).setBalance(500);
        }
        repository.save(list);

    }
}
