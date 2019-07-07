/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FireCore;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import static FireCore.ForgotPassword.emailTextFeild;
import static FireCore.SqlSiteSetUp.host;


import java.util.Arrays;
import javax.mail.MessagingException;

/**
 *
 * @author TANDE
 */
public class SendEmails {

    static Properties props = new Properties();

    public static void forgotPassword() throws SQLException {

        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", 587);
        props.put("mail.smtp.user", "carmichael.tristan@gmail.com");
        props.put("mail.smtp.auth", true);
        props.put("mail.smtp.starttls.enable", true);
        props.put("mail.smtp.debug", true);
        props.put("mail.smtp.socketFactory.port", 587);
        props.put("mail.smtp.Factory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");

        try {

            Connection con = DriverManager.getConnection(host);
            System.out.println("Connected");
            String sql = "select password from db_accessadmin.UsersTable where emailAddress ='" + emailTextFeild.getText().trim() + "'";
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet result = st.executeQuery();

            if (result.next()) {

                String fetchedPassword = result.getString("password");
                Session session = Session.getDefaultInstance(props, null);
                session.setDebug(true);
                MimeMessage message = new MimeMessage(session);
                message.setText("Your password is: " + fetchedPassword);
                message.setSubject("Password for your account");
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailTextFeild.getText().trim()));
                message.saveChanges();
                Transport transport = session.getTransport("smtp");
                transport.connect("smtp.gmail.com", "carmichael.tristan@gmail.com", "79tristy83");
                transport.sendMessage(message, message.getAllRecipients());
                transport.close();
                System.out.println("Password sent");

            }

        } catch (Exception e) {

            System.out.println(e);

        }

    }

//    public static void emailReport() throws MessagingException {
//        String[] tableInfo = new String[6];
//        String date = tableInfo[0];
//        String time = null;
//        String pointZone = null;
//        String pntZnNum = null;
//        String condition = null;
//        String description = null;
//        String test6 = null;
//        int k = 0;
//
//        String[] emailString = new String[DisplaySearchData.jTable2_DisplayDataHistory.getRowCount() -1];
//
//        System.out.println(DisplaySearchData.jTable2_DisplayDataHistory.getRowCount());
//
//        for (int i = 0; i < DisplaySearchData.jTable2_DisplayDataHistory.getRowCount(); i++) {
//
//            for (int j = 0; j < DisplaySearchData.jTable2_DisplayDataHistory.getColumnCount(); j++) {
//
//                tableInfo[j] = DisplaySearchData.jTable2_DisplayDataHistory.getModel().getValueAt(i, j).toString();
//
//                if (j == 5) {
//
//                    date = tableInfo[0];
//                    time = tableInfo[1];
//                    pointZone = tableInfo[2];
//                    pntZnNum = tableInfo[3];
//                    condition = tableInfo[4];
//                    description = tableInfo[5];
//
//                    test6 = date + "  " + time + "  " + pointZone + "  " + String.format("%-14s", pntZnNum) + "  " + String.format("%-26s", condition) + "    " + description;
//
//                    int m = 0;
//
//                    for (;k <= i; k++) {
//
//                        emailString[k] = test6;
//                        
//                        System.out.println(emailString[k]);
//                        
//
//                    }
//                
//                }
//                
//            }
//
//        }
//
//        props.put("mail.smtp.host", "smtp.gmail.com");
//        props.put("mail.smtp.port", 587);
//        props.put("mail.smtp.user", "carmichael.tristan@gmail.com");
//        props.put("mail.smtp.auth", true);
//        props.put("mail.smtp.starttls.enable", true);
//        props.put("mail.smtp.debug", true);
//        props.put("mail.smtp.socketFactory.port", 587);
//        props.put("mail.smtp.Factory.class", "javax.net.ssl.SSLSocketFactory");
//        props.put("mail.smtp.socketFactory.fallback", "false");
//        Session session = Session.getDefaultInstance(props, null);
//        session.setDebug(true);
//        MimeMessage message = new MimeMessage(session);
//        System.out.println(emailString[0]);
//        System.out.println(emailString[1]);
//        message.setText(emailString[0] + "\n");
//
//        message.setSubject("Database Search");
//        message.addRecipient(Message.RecipientType.TO, new InternetAddress("carmichael.tristan@gmail.com"));
//        message.saveChanges();
//        Transport transport = session.getTransport("smtp");
//        transport.connect("smtp.gmail.com", "carmichael.tristan@gmail.com", "79tristy83");
//        transport.sendMessage(message, message.getAllRecipients());
//        transport.close();
//    }

    
 public static void emailReport(){
 
 
 
 
 }   
    
    
}
