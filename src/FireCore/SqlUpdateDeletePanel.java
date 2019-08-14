///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package FireCore;
//
//
//import static FireCore.SqlFunctions.host;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//
//
///**
// *
// * @author TANDE
// */
//public class SqlUpdateDeletePanel {
//
//    public static void SqlUpdateDeletePanel(int jTreeIndexNum) {
//        int panelId1 = 0;
//        int test2 = jTreeIndexNum;
//        int test3 = jTreeIndexNum;
//      
//        try {
//            Connection con = DriverManager.getConnection(host);
//            System.out.println("Connected");
//
//            String query1 = "Select count(panelID) from db_accessadmin.Fire_Panel_Data";
//            Statement st = con.createStatement();
//            ResultSet rs1 = st.executeQuery(query1);
//
//            if (rs1.next()) {
//                panelId1 = rs1.getInt(1);
//
//                System.out.println(panelId1);
//            }
//
//            String sql = "update db_accessadmin.Fire_Panel_Data set panelID = ? where panelID = ?";
//            PreparedStatement st1 = con.prepareStatement(sql);
//            
//            
//            while (test3 <= panelId1) {
//
//                test2 ++; 
//
//                st1.setInt(1, jTreeIndexNum);
//
//                st1.setInt(2, test2);
//                st1.executeUpdate();
//                
//                jTreeIndexNum ++;
//                test3 ++;
//
//            }
//            
//            st1.close();
//            con.close();
//            
//         
//            
//            SqlReadPanelList.jTreeSqlPanelDisply();
//            
//            
//            
//
//        } catch (SQLException ex) {
//            System.out.println(ex);
//        }
//        System.out.println("Connection Closed");
//
//    }
//
//}
