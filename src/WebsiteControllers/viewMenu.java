package WebsiteControllers;

import Model.Item;
import Repositories.itemRepostory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

/**
 * Created by chett_000 on 2017/06/02.
 */
@Controller
public class viewMenu {

    @Autowired
    itemRepostory repository;

    @RequestMapping(value = "/viewmenu")
    public ModelAndView view() {

        ModelAndView view = new ModelAndView("viewMenu");

        ArrayList<Item> itemlist = (ArrayList<Item>) repository.findAll();

        view.addObject("itemList", itemlist);

        return view;
    }

    @RequestMapping(value = "/check")
    public void check(HttpServletRequest request, HttpServletResponse response) {
        Item item = repository.findById(request.getParameter("id"));
        item.setToday(Boolean.valueOf(request.getParameter("checked")));
        repository.save(item);
    }

    @RequestMapping(value = "/itemremove", method = RequestMethod.POST)
    public void remove(HttpServletRequest request, HttpServletResponse response) {

        Item item = repository.findById(request.getParameter("id"));

        if (item != null) {
            repository.delete(item);
        }

    }


}
