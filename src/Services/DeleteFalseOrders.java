package Services;

import Model.Item;
import Model.Staff;
import Model.Tranaction;
import Repositories.itemRepostory;
import Repositories.userRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import  Repositories.transactionRepository;

import java.util.ArrayList;

/**
 * Created by chett_000 on 2018/01/18.
 */





public class DeleteFalseOrders {
    @Autowired
    transactionRepository tranRepo;



    @PostConstruct
    public void DeleteFalseOrders() {
        ArrayList<Tranaction> tranactions = new ArrayList<>(tranRepo.findAll());

        for (int i = 0; i < tranactions.size(); i++) {
            Tranaction tran = tranactions.get(i);
            if (!tran.isOrded()) {
                tranRepo.delete(tran);
            }

        }




    }
}
