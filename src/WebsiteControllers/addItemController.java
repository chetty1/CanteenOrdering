package WebsiteControllers;

import Model.Item;
import Repositories.itemRepostory;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.gridfs.GridFSDBFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Example;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by chett_000 on 2017/06/13.
 */

@Controller
@MultipartConfig(fileSizeThreshold = 10204 * 10024, maxFileSize = 10024 * 10024 * 5, maxRequestSize = 10240 * 10024 * 5 * 5)
public class addItemController {

    @Autowired
    GridFsTemplate template;

    @Autowired
    itemRepostory repostory;

    @RequestMapping(value = "/additem")
    public String view() throws FileNotFoundException {


        return "addItem";

    }

    @RequestMapping(value = "/additem", method = RequestMethod.POST)
    public ModelAndView save(HttpServletRequest request, HttpServletResponse response, @RequestParam("img") MultipartFile multipartFile, @RequestParam("emailid") String name, @RequestParam("mem_name") String price, @RequestParam("time") String time, @RequestParam("sandwich1") String sandwich, @RequestParam("desc") String desc) throws IOException {
        ModelAndView view = new ModelAndView("addItem");


        System.out.println(time);


        Item item = new Item(name, desc, price, time, multipartFile.getOriginalFilename(), sandwich, true);
        repostory.save(item);

        System.out.println(item.toString());

        System.out.println(multipartFile.getContentType());
        InputStream inputStream = multipartFile.getInputStream();

        DBObject metaData = new BasicDBObject();
        metaData.put("name", multipartFile.getOriginalFilename());

        template.store(inputStream, multipartFile.getOriginalFilename(), multipartFile.getContentType(), metaData);
        return view;

    }


   @RequestMapping(value = "/{name:.+}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<InputStreamResource> getImage(@PathVariable String name) {
        GridFSDBFile gridFsFile = template.findOne(new Query(Criteria.where("filename").is(name)));

        return ResponseEntity.ok()
                .contentLength(gridFsFile.getLength())
                .contentType(MediaType.parseMediaType(gridFsFile.getContentType()))
                .body(new InputStreamResource(gridFsFile.getInputStream()));
    }


}
