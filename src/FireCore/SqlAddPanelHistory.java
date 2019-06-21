/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FireCore;


import static FireCore.MainPage.jTable1_DisplayData;
import static FireCore.SqlSiteSetUp.host;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author TANDE
 */
public class SqlAddPanelHistory {

    private static String date;
    private static String time;
    private static String pntZn;
    private static String pointNum;
    private static String pointStatus;
    private static String description;
    private static int stringLength;
    private static int nodeNum;
    private static int loopNum;
    private static int zoneNum;
    private static String sql;
    private static String endOfData = "***  MX1 V1.62 \"PN1290 MX1 AU V4.1\" Event Log  ***";

    public static void sqlPanelHistory(String redDataText, String testDateString, String testTime) throws SQLException, IOException {

        if (!redDataText.equals("")) {

            String[] words = redDataText.split("\\r" + "\\n");

            for (int i = 0; i < words.length; i++) {

                if (words[i].equals("null")) {

                    break;
                } else if (words[i].contentEquals(endOfData)) {

                    break;

                }

                if (words[i].contains("Pnt")) {
                    System.out.println(words[i]);

                    if (words[i].length() <= 51) {

                        String tristan = words[i];
                        System.out.println(tristan);

                        break;
                    }

                    date = testDateString;
                    time = testTime;
                    pntZn = words[i].substring(18, 21);
                    stringLength = words[i].length();
                    pointNum = words[i].substring(25, 35).trim();
                    pointStatus = words[i].substring(39, 58).trim();
                    description = words[i].substring(59, stringLength);
                    

//                    sqlPntEvtCheck();
                    updateTable();
//                    System.out.println(date);
//                    System.out.println(time);
//                    System.out.println(pntZn);
//                    System.out.println(pointNum);
//                    System.out.println(pointStatus);
//                    System.out.println(description);

                    sql = "INSERT INTO db_accessadmin.MX1_FirePanel_Points" + "(panelID, date , time,pointNum, pntZone, pointStatus, description)" + "values (?,?,?,?,?,?)";

                    sqlWritePointData(sql);

//                    MainPage.displayPanelInfo.append(String.format("%-12s%-13s%-15s%-27s%-30s%-30s%n",date,time,pntZn,pointNum,pointStatus,description));
                } else if (words[i].contains("Zone")) {

                    System.out.println(words[i]);
                    stringLength = words[i].length();

                    System.out.println(stringLength);

                    if (words[i].length() <= 51) {

                        String tristan = words[i];
                        System.out.println(tristan);

                        break;
                    }

                    date = testDateString;
                    time = testTime;
                    pntZn = words[i].substring(18, 22).trim();
                    pointNum = words[i].substring(25, 35).trim();;
                    pointStatus = words[i].substring(39, 57).trim();
                    description = words[i].substring(59, stringLength);
                   
                    

//                    sqlZoneEvtCheck();
                    updateTable();
//                    MainPage.displayPanelInfo.append(String.format("%-12s%-13s%-13s%-33s%-33s%-25s%n",date,time,pntZn,pointNum,pointStatus,description));
                    sql = "INSERT INTO db_accessadmin.MX1_FirePanel_Zone" + "(panelID,  date , time, pntZone, pointNum,pointStatus, description)" + "values (?,?,?,?,?,?)";

                    sqlWriteZoneData(sql);

//                    System.out.println(date);
//                    System.out.println(time);
//                    System.out.println(pntZn);
//                    System.out.println(pointNum);
//                    System.out.println(pointStatus);
//                    System.out.println(description);

                }

            }

        }

    }

    public static void sqlPntEvtCheck() throws SQLException {

//        Connection con = DriverManager.getConnection(host);
//        System.out.println("Connected");
//        String query = "SELECT * FROM db_accessadmin.MX1_Pnt_Events_Text";
//        Statement st = con.createStatement();
//        ResultSet rs = st.executeQuery(query);
//        int i = 1;
//        while (rs.next()) {
//            System.out.println(i);
//            String pntEvtText = rs.getString("pntEvtText");
//            String pntEvtCat = rs.getString("pntEvtCat");
//
//            if (pointStatus.equals(pntEvtText) && pntEvtCat.equals("Disable")) {// This is to go to the isolates table
//
//                sql = "INSERT INTO db_accessadmin.MX1_FirePanel_Disable" + "(panelID, date , time, pointNum, pointStatus, description)" + "values (?,?,?,?,?,?)";
//
//                sqlWriteData(sql);
//                break;
//
//            } else if (pointStatus.equals(pntEvtText) && pntEvtCat.equals("Alarm")) {// This is to go to the Alarm table
//                sql = "INSERT INTO db_accessadmin.MX1_FirePanel_Alarm" + "(panelID, date , time, pointNum, pointStatus, description)" + "values (?,?,?,?,?,?)";
//
//                sqlWriteData(sql);
//                break;
//
//            } else if (pointStatus.equals(pntEvtText) && pntEvtCat.equals("Fault")) {// This is to go to the Fault table
//
//                sql = "INSERT INTO db_accessadmin.MX1_FirePanel_Fault" + "(panelID, date , time, pointNum, pointStatus, description)" + "values (?,?,?,?,?,?)";
//
//                sqlWriteData(sql);
//                break;
//
//            } else if (pointStatus.equals(pntEvtText) && pntEvtCat.equals("System")) {
//
//                sql = "INSERT INTO db_accessadmin.MX1_FirePanel_System_Data" + "(panelID, date , time, pointNum, pointStatus, description)" + "values (?,?,?,?,?,?)";
//
//                sqlWriteData(sql);
//                break;
//
//            }
//            i++;
//        }
    }

    public static void sqlZoneEvtCheck() throws SQLException {

//        Connection con = DriverManager.getConnection(host);
//        System.out.println("Connected");
//        String query = "SELECT * FROM db_accessadmin.MX1_Zone_Events_Text";
//        Statement st = con.createStatement();
//        ResultSet rs = st.executeQuery(query);
//        int i = 1;
//        while (rs.next()) {
//
//            String znEvtText = rs.getString("EventText");
//            String znEvtCat = rs.getString("EventCat");
//
//            System.out.println(i);
//            if (pointStatus.equals(znEvtText) && znEvtCat.equals("Disable")) {// This is to go to the isolates table
//
//                sql = "INSERT INTO db_accessadmin.MX1_FirePanel_Disable" + "(panelID, date , time, pointNum, pointStatus, description)" + "values (?,?,?,?,?,?)";
//
//                sqlWriteData(sql);
//                st.close();
//                rs.close();
//                break;
//
//            } else if (pointStatus.equals(znEvtText) && znEvtCat.equals("Alarm")) {// This is to go to the Alarm table
//                sql = "INSERT INTO db_accessadmin.MX1_FirePanel_Alarm" + "(panelID, date , time, pointNum, pointStatus, description)" + "values (?,?,?,?,?,?)";
//
//                sqlWriteData(sql);
//                st.close();
//                rs.close();
//                break;
//            } else if (pointStatus.equals(znEvtText) && znEvtCat.equals("Fault")) {// This is to go to the Fault table
//
//                sql = "INSERT INTO db_accessadmin.MX1_FirePanel_Fault" + "(panelID, date , time, pointNum, pointStatus, description)" + "values (?,?,?,?,?,?)";
//
//                sqlWriteData(sql);
//                st.close();
//                rs.close();
//                break;
//            } else if (pointStatus.equals(znEvtText) && znEvtCat.equals("System")) {// This is to go to the Systems table
//
//                sql = "INSERT INTO db_accessadmin.MX1_FirePanel_System_Data" + "(panelID, date , time, pointNum, pointStatus, description)" + "values (?,?,?,?,?,?)";
//
//                sqlWriteData(sql);
//                st.close();
//                rs.close();
//                break;
//
//            }
//            i++;
//        }
    }

    public static void sqlWritePointData(String sql) throws SQLException {
        int pna = 1;

        Connection con = DriverManager.getConnection(host);
        System.out.println("Connected");

//            
        PreparedStatement st1 = con.prepareStatement(sql);

        st1.setInt(1, pna);
        st1.setString(2, date.trim());
        st1.setString(3, time.trim());
        st1.setString(4, pntZn.trim());
        st1.setString(5, pointNum.trim());
        st1.setString(6, pointStatus.trim());
        st1.setString(7, description.trim());

        st1.executeUpdate();

        st1.close();

        con.close();

    }
    
    public static void sqlWriteZoneData(String sql) throws SQLException{
    
    int pna = 1;

        Connection con = DriverManager.getConnection(host);
        System.out.println("Connected");

//            
        PreparedStatement st1 = con.prepareStatement(sql);

        st1.setInt(1, pna);
        st1.setString(2, date.trim());
        st1.setString(3, time.trim());
        st1.setString(4, pointNum.trim());
        st1.setString(5, pointStatus.trim());
        st1.setString(6, description.trim());

        st1.executeUpdate();

        st1.close();

        con.close();

    
    
    
    }

    public static void updateTable() {
        DefaultTableModel model;
        model = (DefaultTableModel) jTable1_DisplayData.getModel();

        model.insertRow(model.getRowCount(), new Object[]{date, time, pntZn, pointNum, pointStatus, description});

        MainPage.jTable1_DisplayData.setModel(model);

    }

}
