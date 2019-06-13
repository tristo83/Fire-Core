package FireCore;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package FireCore;

import java.awt.print.PrinterException;
import java.util.logging.Level;
import java.util.logging.Logger;
import jssc.SerialPort;
import jssc.SerialPortException;


/**
 *
 * @author TANDE
 */
public class CommsPort {
  public static String newline = "\r\n";
    public static SerialPort serialPort = new SerialPort("COM17");

    public static void commsPortConnect() throws SerialPortException, PrinterException {
      

        MainPage.displayPanelInfo.setText("Port Name: " + serialPort.getPortName() + newline);

        boolean openPort = serialPort.openPort();

        MainPage.displayPanelInfo.append("Port Open: " + openPort + newline);

         serialPort.setParams(19200, 8, 1, 0);
         
         readPanelInnfo();
         


    }
    
    
    
  public static void closePort(){
  
        try {
            
            boolean closePort = serialPort.closePort();
            MainPage.displayPanelInfo.append("Port Closed: " + closePort + newline);
        } catch (SerialPortException ex) {
            Logger.getLogger(CommsPort.class.getName()).log(Level.SEVERE, null, ex);
        }
  
  
  
  }



public static void readPanelInnfo(){


   
          while(serialPort.isOpened()){
              
              try {
                  serialPort.writeInt(13);
              } catch (SerialPortException ex) {
                  Logger.getLogger(CommsPort.class.getName()).log(Level.SEVERE, null, ex);
              }
              String response = null;
              System.out.println(response);
              
              
          }     


}



  
//
//    public static void carriageReturn(int carriageReturnKey) throws SerialPortException {
//        final String newline = "\r";
//        final String removeChars = "->";
//
//
//
//        carriageReturnKey = carriageReturnKey + 3;
//
//        if (carriageReturnKey == 13) {
//
//            serialPort.writeInt(carriageReturnKey);
//
//            for (String line : MainPage.displayPanelInfo.getText().split("\\n")) {
//
//                if (line.equals("->LEVEL3")) {
//
//                    StringBuilder sb = new StringBuilder(line);
//
//                    int count = sb.length();
//                    System.out.println(count);
//                    sb = sb.deleteCharAt(0);
//                    sb = sb.deleteCharAt(0);
//
//                    final String tristan = sb.toString();
//
//                    serialPort.writeString(tristan);
//
////                    String test = serialPort.readString();
////                    int test1 = test.length();
////
////                    test = test.substring(0, test.length() - 0);
////
////                    MainPage.displayPanelInfo.append(test);
////
////                    MainPage.displayPanelInfo.setCaretPosition(MainPage.displayPanelInfo.getCaretPosition() + 1);
//
//                } else if (line.equals("->333333")) {
//
//                    StringBuilder sb = new StringBuilder(line);
//
//                    int count = sb.length();
//                    System.out.println(count);
//                    sb = sb.deleteCharAt(0);
//                    sb = sb.deleteCharAt(0);
//
//                    final String tristan = sb.toString();
//
//                    serialPort.writeString(tristan);
//
////                    String test = serialPort.readString();
////                    int test1 = test.length();
////
////                    test = test.substring(0, test.length() - 0);
////
////                    MainPage.displayPanelInfo.append(test);
////
////                    MainPage.displayPanelInfo.setCaretPosition(MainPage.displayPanelInfo.getCaretPosition() + 1);
//
//                } else if (line.equals("main>>H")) {
//
//                    StringBuilder sb = new StringBuilder(line);
//
//                    int count = sb.length();
//                    System.out.println(count);
//                    sb = sb.delete(0, 6);
//
//                    final String tristan = sb.toString();
//
//                    serialPort.writeString(tristan);
//
////                    String test = serialPort.readString();
////                    int test1 = test.length();
////
////                    test = test.substring(0, test.length() - 0);
////
////                    MainPage.displayPanelInfo.append(test);
////
////                    MainPage.displayPanelInfo.setCaretPosition(MainPage.displayPanelInfo.getCaretPosition() + 1);
//
//                }else if(line.equals("main>>DG")){
//                
//                StringBuilder sb = new StringBuilder(line);
//
//                    int count = sb.length();
//                    System.out.println(count);
//                    sb = sb.delete(0, 6);
//
//                    final String tristan = sb.toString();
//
//                    serialPort.writeString(tristan);
//
////                    String test = serialPort.readString();
////                    int test1 = test.length();
////
////                    test = test.substring(0, test.length() - 0);
////
////                    MainPage.displayPanelInfo.append(test);
////
////                    MainPage.displayPanelInfo.setCaretPosition(MainPage.displayPanelInfo.getCaretPosition() + 1);
//                    
//                
//                
//                }else if(line.equals("diagnostics>>H")){
//                
//                StringBuilder sb = new StringBuilder(line);
//
//                    int count = sb.length();
//                    System.out.println(count);
//                    sb = sb.delete(0, 13);
//
//                    final String tristan = sb.toString();
//                
//                serialPort.writeString(tristan);
//
////                    String test = serialPort.readString();
////                    int test1 = test.length();
////
////                    test = test.substring(0, test.length() - 0);
////
////                    MainPage.displayPanelInfo.append(test);
////
////                    MainPage.displayPanelInfo.setCaretPosition(MainPage.displayPanelInfo.getCaretPosition() + 1);
//                
//                
//                }
//
//                if(line.equals("diagnostics>>T")){
//                
//                StringBuilder sb = new StringBuilder(line);
//
//                    int count = sb.length();
//                    System.out.println(count);
//                    sb = sb.delete(0, 13);
//
//                    final String tristan = sb.toString();
//                
//                serialPort.writeString(tristan);
//                
//     
//
//                
//                printHistory();
//         
//               
//      
//      
//      
//
//                }
//
//            }
//
//print();
//
//
//        } 
//
//    }
//
//    public static void print() throws SerialPortException{
//    
//    String test = serialPort.readString();
//            
//  
//       
//   test = test.substring(0, test.length() - 0);
//   
//
//            MainPage.displayPanelInfo.append(test);
//
//            MainPage.displayPanelInfo.setCaretPosition(MainPage.displayPanelInfo.getCaretPosition() + 1);
//   
//   
//    
//    
//    
//
//            
//    
//    
//    }
//    
//    
//    
//    
//    
//    
//    public static void printHistory(){
//    String test = null;
//    int hi = 13;
//        
//        try {
//            
//           
//            test = serialPort.readString();
//        } catch (SerialPortException ex) {
//            Logger.getLogger(CommsPort.class.getName()).log(Level.SEVERE, null, ex);
//        }
//       
//        
//        while(hi == 13){
//        int test1 = test.length();
//
//            test = test.substring(0, test.length() - 0);
//
//            MainPage.displayPanelInfo.append(test);
//
//            MainPage.displayPanelInfo.setCaretPosition(MainPage.displayPanelInfo.getCaretPosition() + 1);
//        try {
//            test = serialPort.readString();
//        } catch (SerialPortException ex) {
//            Logger.getLogger(CommsPort.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//        }
// 
//        
//            
//    
//    }
//      
//      
      }
//        
//        
//    
//    
//    
//  
//
//
//
//
