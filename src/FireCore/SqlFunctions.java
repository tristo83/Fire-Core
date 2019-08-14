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








}
