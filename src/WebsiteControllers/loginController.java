package WebsiteControllers;

import Model.Staff;
import Repositories.userRepository;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.header.writers.CacheControlHeadersWriter;
import org.springframework.stereotype.Controller;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Request;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by chett_000 on 2017/05/10.
 */
@Controller

public class loginController {

    @Autowired
    userRepository repository;



    @RequestMapping(value="/login")
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response) throws MqttException, ParseException {
ModelAndView view = new ModelAndView("login");
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


    @RequestMapping(value = "/loginrfid")
    public @ResponseBody Staff rfid(HttpServletRequest request, HttpServletResponse response){

        return repository.findByPassword(request.getParameter("rfid"));
    }


}
