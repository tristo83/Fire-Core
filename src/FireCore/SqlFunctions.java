/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FireCore;


import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.tree.DefaultTreeCellRenderer;
import static java.lang.Integer.parseInt;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import static FireCore.MainPage.jTree1;
import javax.swing.tree.TreeSelectionModel;
import static FireCore.DisplaySearchData.jTable2_DisplayDataHistory;
import java.sql.ResultSet;
import java.sql.Statement;
import static FireCore.MainPage.jTable1_DisplayData;
import static FireCore.SqlFunctions.host;
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
public class SqlFunctions {
    
private static String sql;
static String host = "jdbc:sqlserver://127.0.0.1:1433;databaseName=FirePanel;user=FirePanel;password=elisha83";
private static Connection con;
private static int panelID1;
private static String ipAddress1;
    
    
    public static void sqlSiteSetUp(String mainSiteName, int panNumSetUp) throws ClassNotFoundException, IOException {

//Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        try {
            con = DriverManager.getConnection(host);
            System.out.println("Connected");

            String query1 = "Select count(panelID) from db_accessadmin.Fire_Panel_Data";
            Statement st1 = con.createStatement();
            ResultSet rs1 = st1.executeQuery(query1);

            int panelId1 = 0;

            if (rs1.next()) {
                panelId1 = rs1.getInt(1);
                rs1.close();
                System.out.println(panelId1);
            }

            if (panelId1 > 0) {

               

                    Statement st2 = con.createStatement();
                    sql = "DELETE FROM db_accessadmin.Fire_Panel_Data";
                    st2.executeUpdate(sql);
                    SqlPanelSetUp(mainSiteName, panNumSetUp);
                    

              
            } else if (panelId1 == 0) {

                SqlPanelSetUp(mainSiteName, panNumSetUp);
                
 

            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }

    /**
     *
     * @param mainSiteName
     * @param panNumSetUp
     */
    public static void SqlPanelSetUp(String mainSiteName, int panNumSetUp){

        try {
            con = DriverManager.getConnection(host);
            sql = "INSERT INTO db_accessadmin.Fire_Panel_Data" + "(panelID,mainSiteName, sidNum)" + "values (?,?,?)";
            PreparedStatement st = con.prepareStatement(sql);

            


            for (int x = 1; x <= panNumSetUp; x++) {

                st.setInt(1, x);
                st.setString(2, mainSiteName);

                st.setInt(3, 0);
                

                st.executeUpdate();

            }

            st.close();
            con.close();

            System.out.println("Connection Closed");

        } catch (SQLException ex) {

            System.out.println(ex);

        }

    }


public static void SqlUpdateSiteInfo(String bldNameInput, int sidNumInput, String ipAddress, int portNumInput, String manufacturer ) {

        int panNumSetUp = parseInt(PanelSetUpPage.panelNumDisplay.getText());
        String mainSiteName = PanelSetUpPage.mainSiteNameInput.getText();
        int id;
        DefaultMutableTreeNode selectNode;

        try {
            con = DriverManager.getConnection(host);
            System.out.println("Connected");

            String query = "SELECT * FROM db_accessadmin.Fire_Panel_Data";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                id = rs.getInt("panelID");

                if (id == panNumSetUp) {

                    String sql = "update db_accessadmin.Fire_Panel_Data set bldName = ?, sidNum = ?, portNum = ?, ipAddress = ?, manufacturer = ?  where panelID = ?";
                    PreparedStatement st1 = con.prepareStatement(sql);

                    st1.setString(1, bldNameInput);
                    st1.setInt(2, sidNumInput);
                    st1.setInt(3, portNumInput);
                    st1.setString(4, ipAddress);
                    st1.setString(5, manufacturer);
                    st1.setInt(6, id);
                    
                    st1.executeUpdate();

                    st1.close();
                    st.close();
                    con.close();
                    
                    
                    

                    if (bldNameInput.equals("")) {

                        selectNode = (DefaultMutableTreeNode) jTree1.getSelectionPath().getLastPathComponent();

                        selectNode.setUserObject("Panel " + panNumSetUp);

                        DefaultTreeModel model = (DefaultTreeModel) jTree1.getModel();

                        model.reload();
                        
                   
                        
        
                        

                    } else {
                        selectNode = (DefaultMutableTreeNode) jTree1.getSelectionPath().getLastPathComponent();
                        selectNode.setUserObject(bldNameInput);

                        DefaultTreeModel model = (DefaultTreeModel) jTree1.getModel();

                        model.reload();
                        
                        
                        
              
 
                    }

                    break;
                }

            }

            for (int i = 0; i < jTree1.getRowCount(); i++) {
                jTree1.expandRow(i);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        System.out.println("Connection Closed");

    }


public static void SqlUpdateDeletePanel(int jTreeIndexNum) {
        int panelId1 = 0;
        int test2 = jTreeIndexNum;
        int test3 = jTreeIndexNum;
      
        try {
            con = DriverManager.getConnection(host);
            System.out.println("Connected");

            String query1 = "Select count(panelID) from db_accessadmin.Fire_Panel_Data";
            Statement st = con.createStatement();
            ResultSet rs1 = st.executeQuery(query1);

            if (rs1.next()) {
                panelId1 = rs1.getInt(1);

                System.out.println(panelId1);
            }

            String sql = "update db_accessadmin.Fire_Panel_Data set panelID = ? where panelID = ?";
            PreparedStatement st1 = con.prepareStatement(sql);
            
            
            while (test3 <= panelId1) {

                test2 ++; 

                st1.setInt(1, jTreeIndexNum);

                st1.setInt(2, test2);
                st1.executeUpdate();
                
                jTreeIndexNum ++;
                test3 ++;

            }
            
            st1.close();
            con.close();
            
         
            
            jTreeSqlPanelDisply();
            
            
            

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        System.out.println("Connection Closed");

    }

public static void jTreeSqlPanelDisply() {

        try {
            int panelId1 = 0;
            String panelId2 = null;

            String mainSiteName = null;
            String bldName = null;
            DefaultMutableTreeNode siteNetwork;
            DefaultMutableTreeNode siteName = null;
            
            
            con = DriverManager.getConnection(host);
            System.out.println("Connected");

            String query2 = "SELECT * FROM db_accessadmin.Fire_Panel_Data";
            Statement st2 = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs2 = st2.executeQuery(query2);

            siteNetwork = new DefaultMutableTreeNode("Site Network");
            
            
            if (rs2.next()) {

            mainSiteName = rs2.getString("mainSiteName");
            siteName = new DefaultMutableTreeNode(mainSiteName);
            siteNetwork.add(siteName);
            
            }
            

            while (rs2.previous()) {

            }

            while (rs2.next()) {

                panelId1 = rs2.getInt("panelID");

                bldName = rs2.getString("bldName");

                if (bldName == null) {

                    DefaultMutableTreeNode panName = new DefaultMutableTreeNode("Panel " + panelId1);
                    siteName.add(panName);
                    DefaultTreeModel dtm = new DefaultTreeModel(siteNetwork);
                    MainPage.jTree1.setModel(dtm);
                    
               
                   

                } else if (bldName != null) {

                    DefaultMutableTreeNode panName = new DefaultMutableTreeNode(bldName);
                    siteName.add(panName);
                    DefaultTreeModel dtm = new DefaultTreeModel(siteNetwork);
                    MainPage.jTree1.setModel(dtm);
                    
               
                  

                }

            }

            DefaultTreeModel dtm = new DefaultTreeModel(siteNetwork);
            MainPage.jTree1.setModel(dtm);

            rs2.close();

            for (int i = 0; i < jTree1.getRowCount(); i++) {
                jTree1.expandRow(i);
            }

            con.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
        
        
        Icon firePanel = new ImageIcon("src/icons/notConnected.png");
       

            DefaultTreeCellRenderer renderer = new DefaultTreeCellRenderer();

            renderer.setLeafIcon(firePanel);
            
            jTree1.setCellRenderer(renderer);


    }

public static void removePanel(int jTreeIndexNum) {

        try {
            con = DriverManager.getConnection(host);
            System.out.println("Connected");

            jTreeIndexNum = jTreeIndexNum - 1;
            sql = " DELETE FROM db_accessadmin.Fire_Panel_Data where panelID = ?";
            PreparedStatement st1 = con.prepareStatement(sql);

            st1.setInt(1, jTreeIndexNum);

            st1.executeUpdate();

            st1.close();

            con.close();

            DefaultMutableTreeNode deleteNode = (DefaultMutableTreeNode) jTree1.getSelectionPath().getLastPathComponent();

            DefaultTreeModel model = (DefaultTreeModel) jTree1.getModel();

            model.removeNodeFromParent(deleteNode);

            model.reload();
             

            
  
            SqlUpdateDeletePanel(jTreeIndexNum);
            
            
            
          

            for (int i = 0; i < jTree1.getRowCount(); i++) {
                jTree1.expandRow(i);
            }

            jTreeSqlPanelDisply();

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        System.out.println("Connection Closed");

    }
public static void ShowPanInfo() {

        TreeSelectionModel model = jTree1.getSelectionModel();

        int test = model.getSelectionRows()[0];

        test = test - 1;

        try {

            int id;
            String sitename;
            String bldName;
            int sidNumber = 0;
            int portNum;
            String ipAddress;

            con = DriverManager.getConnection(host);
            System.out.println("Connected");
            String query = "SELECT * FROM db_accessadmin.Fire_Panel_Data";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                id = rs.getInt("panelID");

                
                

                if (id == test) {

                    System.out.println(id);
                    sitename = rs.getString("mainSiteName");
                    System.out.println(sitename);
                    bldName = rs.getString("bldName");
                    
                    sidNumber = rs.getInt("sidNum");
                    portNum = rs.getInt("portNum");
                    ipAddress = rs.getString("ipAddress");
                   
                    PanelSetUpPage.mainSiteNameInput.setText(sitename);
                    PanelSetUpPage.panelNumDisplay.setText(Integer.toString(id));
                    PanelSetUpPage.bldNamFeild.setText(bldName);
                    PanelSetUpPage.selectSidNum.setText(Integer.toString(sidNumber));
                    PanelSetUpPage.PorttNumInput.setText(Integer.toString(portNum));
                    PanelSetUpPage.ipAddressInput.setText(ipAddress);
                    

                    st.close();
                    con.close();

                    

                    break;

                } else if (test == 0) {

                    break;
                }

            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
        

    }

    private static String date;
    private static String time;
    private static String pointZone;
    private static String pointNum;
    private static String pointStatus;
    
    private static String description;

    static void getAllPanelHistory(String panelNum, String pointZoneData, String condition) throws SQLException {
        con = DriverManager.getConnection(host);
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

        con = DriverManager.getConnection(host);

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

        con = DriverManager.getConnection(host);
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

    
    private static String pntZn;
    private static int stringLength;
    private static int nodeNum;
    private static int loopNum;
    private static int zoneNum;
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

                    sql = "INSERT INTO db_accessadmin.MX1_FirePanel_Points" + "(panelID, date , time, pntZone, pointNum, pointStatus, description)" + "values (?,?,?,?,?,?,?)";

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
                    sql = "INSERT INTO db_accessadmin.MX1_FirePanel_Zone" + "(panelID,  date , time, pntZone, pointNum,pointStatus, description)" + "values (?,?,?,?,?,?,?)";

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

        con = DriverManager.getConnection(host);
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

        con = DriverManager.getConnection(host);
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

    public static void updateTable() {
        DefaultTableModel model;
        model = (DefaultTableModel) jTable1_DisplayData.getModel();

        model.insertRow(model.getRowCount(), new Object[]{date, time, pntZn, pointNum, pointStatus, description});

        MainPage.jTable1_DisplayData.setModel(model);

    }
    
   public static void CheckIpAndPanId(String ipAddress, int panelID, int portNumInput) throws SQLException, IOException{
   
   
            con = DriverManager.getConnection(host);
            System.out.println("Connected");

            String query1 = "SELECT * FROM db_accessadmin.Fire_Panel_Data WHERE panelID=" + panelID + "AND ipAddress LIKE'%" + ipAddress + "%'";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query1);
      
            while (rs.next()) {

            panelID1 = rs.getInt("panelID");
            ipAddress1 = rs.getString("ipAddress");
   
        }
            IpConnection.IpConnect(portNumInput, ipAddress, panelID);
            
        con.close();
        st.close();
        rs.close();
   
   }


}
