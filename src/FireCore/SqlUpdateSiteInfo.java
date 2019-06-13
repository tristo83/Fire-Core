/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FireCore;

import static FireCore.MainPage.jTree1;
import static FireCore.SqlSiteSetUp.host;
import static java.lang.Integer.parseInt;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

/**
 *
 * @author TANDE
 */
public class SqlUpdateSiteInfo {

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

}
