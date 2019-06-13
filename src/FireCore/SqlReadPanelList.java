/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FireCore;


import static FireCore.MainPage.jTree1;
import static FireCore.SqlSiteSetUp.host;
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
public class SqlReadPanelList {

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
