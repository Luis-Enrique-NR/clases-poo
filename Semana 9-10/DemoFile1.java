import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;

public class DemoFile1 {
//=============================================================
public static int TotalCifras(int NUM) {
int n;
    n = 0;
    if(NUM==0) {
       n = 1;
    }
    else {
       n = (NUM<0?1:0);
       while(NUM!=0) {
             NUM = (int)(NUM/10);
             n++;
       }
    }
    return n;
}
//=============================================================
public static String Space(int T) {
String S;
   S = "";
   for(int k=1;k<=T;k++) {
       S = S + " ";
   }
   return S;
}
//=============================================================
//========================================
//=============================================================
//=============================================================
public static byte[] StringToBuffer(String CADENA) {
int L = CADENA.length();
byte[] BUFFER = new byte[L];
    for(int i=0;i<=L-1;i++) {
        BUFFER[i] = (byte)CADENA.charAt(i);
    }
   return BUFFER;
}
//=============================================================
public static String BufferToString(byte[] BUFFER) {
String CADENA="";
int L = BUFFER.length;
    for(int i=0;i<=L-1;i++) {
        CADENA = CADENA + (char)BUFFER[i];
    }
    return CADENA;
}
//=============================================================
//=============================================================
public static void ViewMatrix(String Matrix, String TITLE,int M, int N, int W) throws IOException, InterruptedException {
      RandomAccessFile RAF = new RandomAccessFile(Matrix,"r"); 
      byte BUFFER[] = new byte[W];
      String E;
      long T = RAF.length();
      if(T/W == M*N){
          System.out.println("\n------------------------------");
          System.out.println(TITLE);
          System.out.println("------------------------------");
          for(int i=0;i<=M-1;i++) {
          for(int j=0;j<=N-1;j++) {
                RAF.seek((i*N+j)*W);
                RAF.read(BUFFER);
                E = BufferToString(BUFFER);
                System.out.print(Space(W-E.length())+E);
          }
          System.out.println();
        }
      } else {
          //ERROR
      }
      
}
//=============================================================
public static void CreateMatrix(String Matrix, int M, int N, int A, int B, int R, int W, boolean SwRAF) {
FileWriter FW;
int E;
  try {
      FW = new FileWriter(Matrix);
      for(int i=1;i<=M;i++) {
          for(int j=1;j<=N;j++) {
            //E = A + Math.random()*(B-A+1);
                E = A + R*(int)(Math.random()*(1+(B-A)/R)); //con incremento R
              FW.write(Space(W-TotalCifras(E))+E);
          }
          FW.write((SwRAF==true?"":"\n"));
      }
      FW.close();
  }
  catch(IOException ee) {
  }
}
//=============================================================
public static void ProductoMatrix(String Matrix1,int M1,int N1,String Matrix2,int M2,int N2,String Matrix3) throws IOException, InterruptedException {
RandomAccessFile RAF1 = new RandomAccessFile(Matrix1,"r");
RandomAccessFile RAF2 = new RandomAccessFile(Matrix2,"r"); 
RandomAccessFile RAF3 = new RandomAccessFile(Matrix3,"rw"); 
long T1;
long T2;
int W,SUM;
String E1,E2;
      if(N1==M2) {
         T1 = RAF1.length();
         T2 = RAF2.length();
         if(T1/(M1*N1)==T2/(M2*N2)) {
         	 W = (int)(T1/(M1*N1));
             byte BUFFER[] = new byte[W];
             for(int i=0;i<=M1-1;i++) {
                 for(int j=0;j<=N2-1;j++) {
                 	 SUM = 0;
                     for(int k=0;k<=N1-1;k++) { //N1==M2
                         
                         RAF1.seek((i*N1+k)*W);
                         RAF1.read(BUFFER);
                         E1 = BufferToString(BUFFER).trim();
                         
                         RAF2.seek((k*N2+j)*W);
                         RAF2.read(BUFFER);
                         E2 = BufferToString(BUFFER).trim();

                         SUM = SUM + Integer.parseInt(E1)*Integer.parseInt(E2);
                         BUFFER = StringToBuffer(Space(W-TotalCifras(SUM))+SUM);

                         RAF3.seek((i*N2+j)*W);
                         RAF3.write(BUFFER);
                     }

                 }
             }
         }
         else {
           //ERROR DE LECTURA
         }
      }
      else {
          //ERROR DE DIMENSIONES
      }
      RAF3.close();
      RAF2.close();
      RAF1.close();
}
//=============================================================
public static void main(String args[]) throws IOException, InterruptedException {
int W = 7;
int M1=7,N1=5,W1=7,A1=-10,B1=10,H1=2;
int M2=5,N2=7,W2=7,A2=-10,B2=10,H2=2;
   //ARCHIVOS DE ACCESO SECUENCIAL
   CreateMatrix("A.txt",M1,N1,A1,B1,H1,W1,false);
   CreateMatrix("B.txt",M2,N2,A2,B2,H2,W2,false);

   //ARCHIVOS DE ACCESO DIRECTO
   CreateMatrix("A.dat",M1,N1,A1,B1,H1,W1,true);
   CreateMatrix("B.dat",M2,N2,A2,B2,H2,W2,true);

   ProductoMatrix("A.dat",M1,N1,"B.dat",M2,N2,"AB.dat");
   ProductoMatrix("B.dat",M2,N2,"A.dat",M1,N1,"BA.dat");

   ViewMatrix("A.dat","Matriz A",M1,N1,W1);
   ViewMatrix("B.dat","Matriz B",M2,N2,W2);
   ViewMatrix("AB.dat","Producto AxB",M1,N2,W2);
   ViewMatrix("BA.dat","Producto BxA",M2,N1,W1);

   //CreateMatrix("Archivo_AccesoSecuencial.txt",10,5,-10,10,5,7,false);
   //CreateMatrix("Archivo_AccesoDirecto.dat"   ,10,5,-10,10,5,7,true);
}
//=============================================================
}
