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
import java.sql.SQLException;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

/**
 *
 * @author TANDE
 */
public class SqlDeletePanel {

    public static void removePanel(int jTreeIndexNum) {

        try {
            Connection con = DriverManager.getConnection(host);
            System.out.println("Connected");

            jTreeIndexNum = jTreeIndexNum - 1;
            String sql = " DELETE FROM db_accessadmin.Fire_Panel_Data where panelID = ?";
            PreparedStatement st1 = con.prepareStatement(sql);

            st1.setInt(1, jTreeIndexNum);

            st1.executeUpdate();

            st1.close();

            con.close();

            DefaultMutableTreeNode deleteNode = (DefaultMutableTreeNode) jTree1.getSelectionPath().getLastPathComponent();

            DefaultTreeModel model = (DefaultTreeModel) jTree1.getModel();

            model.removeNodeFromParent(deleteNode);

            model.reload();
             

            
  
            SqlUpdateDeletePanel.SqlUpdateDeletePanel(jTreeIndexNum);
            
            
            
          

            for (int i = 0; i < jTree1.getRowCount(); i++) {
                jTree1.expandRow(i);
            }

            SqlReadPanelList.jTreeSqlPanelDisply();

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        System.out.println("Connection Closed");

    }

}
