package WebsiteControllers;

import Model.Staff;
import Repositories.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chett_000 on 2017/06/03.
 */
@Controller
public class balanceController {


    @Autowired
    userRepository repository;


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
        repository.save(staff);
    }


}
