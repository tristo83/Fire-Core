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

}
