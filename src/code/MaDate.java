package code;

import java.sql.Date;
import java.util.Calendar;

/**
 * Created by BMMed on 11/03/2017.
 */
public  class MaDate  {

    public static   Date getMaDate(int year, int m, int d){
    Calendar calendar = Calendar.getInstance();
    calendar.set(year,m,d);
    Date res = new Date(calendar.getTime().getTime());
    return res;
}
    public static   Date getSysDate(){
        Calendar calendar = Calendar.getInstance();
        Date res = new Date(calendar.getTime().getTime());
        return res;
    }
}
