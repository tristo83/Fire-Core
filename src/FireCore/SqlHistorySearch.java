/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FireCore;

import static FireCore.DisplaySearchData.jTable2_DisplayDataHistory;
import static FireCore.MainPage.jTable1_DisplayData;
import static FireCore.SqlSiteSetUp.host;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author TANDE
 */
public class SqlHistorySearch {

    private static String date;
    private static String time;
    private static String pointZone;
    private static String pointNum;
    private static String pointStatus;
    
    private static String description;

    static void getAllPanelHistory(String panelNum, String pointZoneData, String condition) throws SQLException {
        Connection con = DriverManager.getConnection(host);
        System.out.println("Connected");
        String query = "SELECT * FROM" + "db_accessadmin.";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);
    }

    static void getLoopHistory(String panelNum, String  pointZoneData, String condition) throws SQLException {

        if (condition.equals("Fault")) {

            sqlReadFaultData(panelNum, pointZoneData, condition);

        } else if (condition.equals("Alarm")) {

            sqlReadAlarmData(panelNum, pointZoneData, condition);

        } else if (condition.equals("Disable")) {

            sqlReadDisableData(panelNum, pointZoneData, condition);

        }

    }

    static void getZoneHistory(String panelNum, String zoneNum, String condition) throws SQLException {
int panNum = 1;
String zoneNum2 = "2";

        String query = "SELECT * FROM db_accessadmin.MX1_FirePanel_Zone WHERE panelID=" + panNum + "AND pointNum LIKE'%" + zoneNum2 + "%'"  + "AND pointStatus LIKE'%" + condition + "%'";

        sqlReadDataHistory(query);

    }

    private static void sqlReadFaultData(String panelNum, String loopNum, String condition) throws SQLException {

      int panNum = 1;
        String deviceFail = "Device Fail";

        String query = "SELECT * FROM db_accessadmin.MX1_FirePanel_Points WHERE panelID=" + panNum + "AND pointStatus LIKE'%" + condition + "%' OR pointStatus LIKE'%" + deviceFail + "%'";
        sqlReadDataHistory(query);

    }

    private static void sqlReadAlarmData(String panelNum, String loopNum, String condition) throws SQLException {

         int panNum = 1;
        String alarmClr = "Alarm Clr";
        String operate = "Operate";

        Connection con = DriverManager.getConnection(host);

        System.out.println("Connected");
        String query = "SELECT * FROM db_accessadmin.MX1_FirePanel_Points WHERE panelID=" + panNum + " AND pointStatus LIKE '%" + condition + "%'OR pointStatus LIKE'%" + alarmClr + "%'OR pointStatus LIKE'%" + operate + "%'" ;
        sqlReadDataHistory(query);

    }

    private static void sqlReadDisableData(String panelNum, String loopNum, String condition) throws SQLException {

        int panNum = 1;
        String inputDeactivated = "Input deactivated";
        String deOperated = "De-operate";

        String query = "SELECT * FROM db_accessadmin.MX1_FirePanel_Points WHERE panelID=" + panNum + " AND pointStatus='%" + condition + "%' AND pointStatus='%" + inputDeactivated + "%'OR pointStatus='%" + deOperated + "%'";
        sqlReadDataHistory(query);

    }

    private static void sqlReadDataHistory(String query) throws SQLException {

        Connection con = DriverManager.getConnection(host);
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);

        while (rs.next()) {

            date = rs.getString("date");
            time = rs.getString("time");
            pointZone = rs.getString("pntZone");
            pointNum = rs.getString("pointNum");
            pointStatus = rs.getString("pointStatus");
            description = rs.getString("description");

            DefaultTableModel model;
            model = (DefaultTableModel) jTable2_DisplayDataHistory.getModel();

            model.insertRow(model.getRowCount(), new Object[]{date, time, pointZone, pointNum, pointStatus, description});

            DisplaySearchData.jTable2_DisplayDataHistory.setModel(model);

        }
        con.close();
        st.close();
        rs.close();

    }

}


