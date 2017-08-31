package Services;

import Model.Staff;
import Repositories.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.annotation.PostConstruct;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by chett_000 on 2017/06/28.
 */

public class Email {


    @Autowired
    userRepository repository;

    @Autowired
    JavaMailSender mailSender;

@PostConstruct
    public void complileCsv() {

        try {
            ArrayList<Staff> list = new ArrayList<>(repository.findAll());
            System.out.println("yolo");

            SimpleDateFormat date = new SimpleDateFormat("ddMMYYYY");
            PrintWriter writer = new PrintWriter(new File("backup" + date.format(new Date()) + ".csv"), "UTF-8");
            for (Staff staff : list) {
                writer.println(staff.getBalancestring());

            }
            writer.close();

            SimpleMailMessage mail = new SimpleMailMessage();

            mail.setFrom("npscanteen123@gmail.com");
            mail.setTo("npscanteen123@gmail.com");
            mail.setSubject("Backup");

            MimeMessage mesg = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mesg, true);
            helper.setFrom(mail.getFrom());
            helper.setTo(mail.getTo());
            FileSystemResource file = new FileSystemResource("backup" + date.format(new Date()) + ".csv");
            helper.addAttachment(file.getFilename(), file);
            mailSender.send(mesg);
        } catch (Exception e) {
            // do something
            System.out.println(e);

        }


    }


}
