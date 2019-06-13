/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FireCore;

import static FireCore.MainPage.jTable1_DisplayData;
import static FireCore.MainPage.jTree1;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author TANDE
 */
public class IpConnection {

    static Socket smtpSocket = null;
    static DataOutputStream os = null;
    static DataInputStream in = null;
    static BufferedReader reader;
    static Scanner scanner;
    static Timer time = new Timer();

    public static void IpConnect(int portNumInput, String ipAddress) throws IOException, SQLException {

        try {
            smtpSocket = new Socket(InetAddress.getByName(ipAddress), portNumInput);

            os = new DataOutputStream(smtpSocket.getOutputStream());
            reader = new BufferedReader(new InputStreamReader(smtpSocket.getInputStream()));
            in = new DataInputStream(smtpSocket.getInputStream());
            scanner = new Scanner(smtpSocket.getInputStream());

            System.out.println("Connected with ip " + ipAddress + " and port " + portNumInput);

            if (smtpSocket.isConnected()) {

                Icon firePanel = new ImageIcon("src/icons/Untitled.png");

                DefaultTreeCellRenderer renderer = new DefaultTreeCellRenderer();

                renderer.setLeafIcon(firePanel);

                jTree1.setCellRenderer(renderer);

                writeData();

            }

        } catch (UnknownHostException ex) {
            System.out.println(ex);
        } catch (IOException ex) {
            System.out.println(ex);
        }

    }

    public static void writeData() throws IOException, SQLException {

        String history;
        int histroyString;

        TimerTask task = new TimerTask() {

            public void run() {

                int red = -1;
                byte[] buffer = new byte[7 * 1024]; // a read buffer of 5KiB
                byte[] redData;
                StringBuilder clientData = new StringBuilder();
                String redDataText = null;
                
                
                try {
                    while ((red = smtpSocket.getInputStream().read(buffer)) > -1) {
                        redData = new byte[red];
                        System.arraycopy(buffer, 0, redData, 0, red);
                        
                        try {
                            redDataText = new String(redData, "UTF-8"); // assumption that client sends data UTF-8 encoded
                        } catch (UnsupportedEncodingException ex) {
                            Logger.getLogger(IpConnection.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        Date todaysDate = new Date();
                        
                        DateFormat df = new SimpleDateFormat("dd/MM/yy");
                        DateFormat df1 = new SimpleDateFormat("hh:mm:ss a");
                        String testDateString = df.format(todaysDate);
                        String testTime = df1.format(todaysDate);
                        
                        SqlAddPanelHistory.sqlPanelHistory(redDataText, testDateString, testTime );

//                        System.out.println(redDataText);
//             clientData.append(redDataText);
                    }
                } catch (IOException ex) {
                    Logger.getLogger(IpConnection.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(IpConnection.class.getName()).log(Level.SEVERE, null, ex);
                    System.out.println(ex);
                }

            }

        };
        time.scheduleAtFixedRate(task, 0, 1000);

    }

      
    public static void closeConnections() throws IOException, IOException {

        smtpSocket.close();
        reader.close();
        scanner.close();
        os.close();
        time.cancel();

        if (smtpSocket.isClosed()) {

            Icon firePanel = new ImageIcon("src/icons/notConnected.png");

            DefaultTreeCellRenderer renderer = new DefaultTreeCellRenderer();

            renderer.setLeafIcon(firePanel);

            jTree1.setCellRenderer(renderer);

        }

    }
    
public static void getSysDateTime(){

 




}    

}
