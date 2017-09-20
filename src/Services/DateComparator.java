package Services;

import Model.Tranaction;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

/**
 * Created by chett_000 on 2017/09/18.
 */
public class DateComparator implements Comparator<Tranaction> {

    @Override
    public int compare(Tranaction o1, Tranaction o2) {

        try {
            Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(o1.getDate());
            Date date2 = new SimpleDateFormat("dd/MM/yyy").parse(o2.getDate());

            return -1*date1.compareTo(date2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return -1;

    }
}
