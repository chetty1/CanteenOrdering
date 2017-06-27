package WebsiteControllers;

import Model.Staff;
import Repositories.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chett_000 on 2017/06/03.
 */
@Controller
public class registerUser {

    @Autowired
    userRepository repo;

    List<Staff> staff = new ArrayList<>();
    @RequestMapping(value = "/register")
    public String view(){
      //  staff=repo.findAll();


       // System.out.println(staff);
        return "registerUser";
    }
@RequestMapping(value = "/reguser", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity reguser(HttpServletRequest request, HttpServletResponse response){
    String username=request.getParameter("username");
    String name=request.getParameter("name");
    String password=request.getParameter("password");
    String confpassword=request.getParameter("confpassword");
String type =  request.getParameter("type");
    String role =  request.getParameter("role");

    ArrayList<String> roles = new ArrayList<>();




System.out.print(username);
if(password.equals(confpassword)&&!password.isEmpty()&& !role.isEmpty()) {
    roles.add(role);
    if (!type.isEmpty()) {
        roles.add(type);
    }

    Staff staff=new Staff(name,username,password,roles,0);
    repo.save(staff);

    System.out.println(staff);

    return new ResponseEntity(HttpStatus.OK);

}
else
{
    return new ResponseEntity(HttpStatus.BAD_REQUEST);
}

}

}
