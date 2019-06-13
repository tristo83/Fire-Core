/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FireCore;


import static FireCore.PopulateCalendar.cd;
import java.util.Calendar;
import java.util.Locale;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author TANDE
 */
public class PopulateCalendar {
  static int year = 0;
        static String month; 
        
static Calendar cd = Calendar.getInstance();


    public static void GetCalendarInfo() {

        

        DefaultTableModel dtm = (DefaultTableModel) CalendarPage.jTable1.getModel();
        
        cd.set(Calendar.DAY_OF_MONTH, 1);

        month = cd.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH);
        
        year = cd.get(Calendar.YEAR);
        CalendarPage.jLabel1.setText(month + " " + year);
        
        

        int startDay = cd.get(Calendar.DAY_OF_WEEK);
        int numberOfDays = cd.getActualMaximum(Calendar.DAY_OF_MONTH);
        int weeks = cd.getActualMaximum(Calendar.WEEK_OF_MONTH);
        
      dtm.setRowCount(0);
      dtm.setRowCount(6);
        
        int i = startDay - 1;
        for (int day = 1; day <= numberOfDays; day++) {

            CalendarPage.jTable1.setValueAt(day, i / 7, i % 7);
            i = i + 1;

        }
     
int t = cd.get(Calendar.DATE);


        
        
    }
    
 public static void calendarBack(){
 
 cd.add(Calendar.MONTH, -1);
 
 GetCalendarInfo();
 }  

public static void calendarforward(){

cd.add(Calendar.MONTH, +1);

GetCalendarInfo();

}

public static void resetCalendar(){
    Calendar cd1 = Calendar.getInstance();
DefaultTableModel dtm = (DefaultTableModel) CalendarPage.jTable1.getModel();
   cd1.set(Calendar.DAY_OF_MONTH, 1);
    
      String month2 = cd1.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH);
      int year2 = cd1.get(Calendar.YEAR);
        CalendarPage.jLabel1.setText(month2 + " " + year2);
        
        
       int startDay2 = cd1.get(Calendar.DAY_OF_WEEK);
        int numberOfDays2 = cd1.getActualMaximum(Calendar.DAY_OF_MONTH);
        int weeks2 = cd1.getActualMaximum(Calendar.WEEK_OF_MONTH); 
        
        dtm.setRowCount(0);
      dtm.setRowCount(6);
        
        int i = startDay2 - 1;
        for (int day = 1; day <= numberOfDays2; day++) {

            CalendarPage.jTable1.setValueAt(day, i / 7, i % 7);
            i = i + 1;

        }
        
}

}
