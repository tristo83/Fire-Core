/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FireCore;

import static FireCore.MainPage.jTree1;
import static FireCore.SiteSetUpPage.mainSiteName;
import static FireCore.SqlSiteSetUp.host;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.tree.TreeSelectionModel;

/**
 *
 * @author TANDE
 */
public class SqlShowPanInfo {

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

            Connection con = DriverManager.getConnection(host);
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

}
