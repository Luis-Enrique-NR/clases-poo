package com.mycompany.sgcv;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class GetHostName_GetAddressIP {
    public static String GetHostName(){
        String HostName = "";
        try{
            InetAddress IA = InetAddress.getLocalHost();
            HostName = IA.getHostName();
        } catch (UnknownHostException E){
        }
        return HostName;
    }
    
    public static String GetAddressIP(){
        String AddressIP = "";
        try {
            InetAddress IA = InetAddress.getLocalHost();
            AddressIP = IA.getHostAddress();
        } catch (UnknownHostException E){
        }
        return AddressIP;
    }
}
