package WebsiteControllers;

import Model.Item;
import Repositories.itemRepostory;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.gridfs.GridFSDBFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by chett_000 on 2017/06/02.
 */
@Controller
public class EditItemController {

    @Autowired
    itemRepostory repostory;

    Item item;
@Autowired
    GridFsTemplate template;
    @RequestMapping(value = "{id}/edititem",method = RequestMethod.GET)
    public ModelAndView view(@PathVariable String id) {

        item = repostory.findById(id);
        System.out.print(item);
        ModelAndView view = new ModelAndView("editItem");
        view.addObject("name", item.getName());
        view.addObject("price", item.getPrice());
        view.addObject(item.getTime(), "checked");
        view.addObject(item.getType(), "checked");
        view.addObject("desc",item.getDesc());
        view.addObject("phname",item.getPicName());
view.addObject("id",id);
        return view;
    }


    @RequestMapping(value = "/edititem",method = RequestMethod.POST)
    public ModelAndView save( @RequestParam("img") MultipartFile multipartFile, @RequestParam("emailid") String name, @RequestParam("mem_name") String price, @RequestParam("time") String time, @RequestParam("sandwich1") String sandwich, @RequestParam("desc") String desc) throws IOException {



        ModelAndView view = new ModelAndView("editItem");
        item.setName(name);
        item.setPrice(price);
        item.setTime(time);
        item.setType(sandwich);
        item.setDesc(desc);
        GridFSDBFile gridFsFile = template.findOne(new Query(Criteria.where("filename").is(item.getPicName())));

        System.out.println(multipartFile.getOriginalFilename());

        if (gridFsFile==null&&!multipartFile.isEmpty()){
            DBObject metaData = new BasicDBObject();
            metaData.put("name", multipartFile.getOriginalFilename());

            template.store(multipartFile.getInputStream(), multipartFile.getOriginalFilename(), multipartFile.getContentType(), metaData);

            item.setPicName(multipartFile.getOriginalFilename());
        }
        else if(!multipartFile.getOriginalFilename().equals(gridFsFile.getFilename())&&!multipartFile.isEmpty()){
            InputStream inputStream = multipartFile.getInputStream();

            template.delete(new Query(Criteria.where("metadata.name").is(item.getPicName())));
            DBObject metaData = new BasicDBObject();
            metaData.put("name", multipartFile.getOriginalFilename());

            template.store(inputStream, multipartFile.getOriginalFilename(), multipartFile.getContentType(), metaData);

            item.setPicName(multipartFile.getOriginalFilename());
        }
        repostory.save(item);
        System.out.println(item);
        return view;
    }


}
