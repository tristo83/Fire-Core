/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FireCore;

import static FireCore.MainPage.jTree1;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

/**
 *
 * @author TANDE
 */
public class JtreeBuild {

    public static void JtreeBuild(String mainSiteName, int panNumSetUp) {

        DefaultMutableTreeNode siteNetwork = new DefaultMutableTreeNode("Site Network");
        DefaultMutableTreeNode siteName = new DefaultMutableTreeNode(mainSiteName);

        for (int x = 1; x <= panNumSetUp; x++) {

            DefaultMutableTreeNode panName = new DefaultMutableTreeNode("Panel " + x);
            siteName.add(panName);
            siteNetwork.add(siteName);
            
         
         
 
        }
       
        DefaultTreeModel dtm = new DefaultTreeModel(siteNetwork);
        MainPage.jTree1.setModel(dtm);
          for (int i = 0; i < jTree1.getRowCount(); i++) {
                jTree1.expandRow(i);
            }
    }



}
