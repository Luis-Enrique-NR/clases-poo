package com.mycompany.sgcv;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class GestionArchivos {
    public FileReader FR;
    public FileWriter FW;
    public BufferedReader BR;
    
    public int leerIntentos(){
        int intentos=0;
        try{
            FR = new FileReader("INTENTOS.txt");
            BR = new BufferedReader(FR);
            
            String linea=BR.readLine();
            if(linea != null && !linea.trim().isEmpty()){
                intentos = Integer.parseInt(linea.trim());
            }
            
            BR.close();
            FR.close();
        } catch (IOException | NumberFormatException e){
            intentos=0;
        }
        return intentos;
    }
    
    public void guardarIntentos(int intentos){
        try{
            FW= new FileWriter("INTENTOS.txt",false);
            PrintWriter PW = new PrintWriter(FW);
            
            PW.println(intentos);
            PW.close();
            FW.close();
        } catch (IOException e){
            System.out.println("Error al guardar intentos.");
        }
    }
    
    public void registrarHost(String hostName, String ipAddress){
        try{
            FW= new FileWriter("BLOQUEADOS.txt",true);
            PrintWriter PW = new PrintWriter(FW);
            
            PW.println("HOST BLOQUEADO POR 3 INTENTOS FALLIDOS:");
            PW.println("Hostname: " + hostName);
            PW.println("IP Address: " + ipAddress);
            
            PW.close();
            FW.close();
        } catch(IOException e){
            System.out.println("Error al registrar el bloqueo.");
        }
    }
}
