/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FireCore;

import static FireCore.SqlSiteSetUp.host;

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
import java.util.Arrays;
import javax.mail.MessagingException;

/**
 *
 * @author TANDE
 */
public class SendEmails {

    public static void forgotPassword() throws SQLException {

        Properties props = new Properties();

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

    public static void emailReport() throws MessagingException {
String[] value = new String[6];
        
       for (int i = 0; i < DisplaySearchData.jTable2_DisplayDataHistory.getRowCount(); i++){
       for(int j = 0; j < DisplaySearchData.jTable2_DisplayDataHistory.getColumnCount(); j++ ){
       
        
               
               value[j] = DisplaySearchData.jTable2_DisplayDataHistory.getModel().getValueAt(i, j).toString();
       
      
      if(j == 4){
      
      String test1 = value[0];
      String test2 = value[1];
      String test3 = value[2];
      String test4 = value[3];
      String test5 = value[4];
      
      String test6 = test1 + "  " + test2 + "  " + String.format("%-14s", test3) + "  " + String.format("%-26s", test4) + "    " + test5;
      
      
      
      
      
       System.out.println(test6);
      }
      
      
       
       
       
       
       }
       
       }
       
      
        
        
     
        
        
        
       

    }

}
