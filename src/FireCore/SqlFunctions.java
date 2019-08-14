/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FireCore;


import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import static FireCore.MainPage.jTree1;
import static FireCore.SqlFunctions.host;
import static java.lang.Integer.parseInt;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import static FireCore.SqlFunctions.host;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;import static FireCore.MainPage.jTree1;
import static FireCore.SqlFunctions.host;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;



/**
 *
 * @author TANDE
 */
public class SqlFunctions {


    public static String host = "jdbc:sqlserver://127.0.0.1:1433;databaseName=FirePanel;user=FirePanel;password=elisha83";

    public static void sqlSiteSetUp(String mainSiteName, int panNumSetUp) throws ClassNotFoundException {

//Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        try {
            Connection con = DriverManager.getConnection(host);
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
                    String sql = "DELETE FROM db_accessadmin.Fire_Panel_Data";
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
    public static void SqlPanelSetUp(String mainSiteName, int panNumSetUp) {

        try {
            Connection con = DriverManager.getConnection(host);
            String sql = "INSERT INTO db_accessadmin.Fire_Panel_Data" + "(panelID,mainSiteName, sidNum)" + "values (?,?,?)";
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
//        String mainSiteName = PanelSetUpPage.mainSiteNameInput.getText();
        int id;
        DefaultMutableTreeNode selectNode;

        try {
            Connection con = DriverManager.getConnection(host);
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
            Connection con = DriverManager.getConnection(host);
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
            
            
            Connection con = DriverManager.getConnection(host);
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


}
