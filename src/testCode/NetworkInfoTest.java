package testCode;
// Java program to find IP address of your computer
// java.net.InetAddress class provides method to get
// IP of any host name
import java.net.*;
import java.io.*;
import java.util.*;
import java.net.InetAddress;
 
public class NetworkInfoTest
{
    public static void main(String args[]) throws Exception
    
    //TODO:NONE OF THIS WORKS AS INTENDED
    {/*
        // Returns the instance of InetAddress containing
        // local host name and address
        InetAddress localhost = InetAddress.getLocalHost();
        System.out.println("System IP Address : " +
                      (localhost.getHostAddress()).trim());
        System.out.println("System IP Address : " +
                (localhost.getHostName()).trim());
 */
    	
    	
    	ArrayList<String>ssids=new ArrayList<String>();
        ArrayList<String>signals=new ArrayList<String>();
        ProcessBuilder builder = new ProcessBuilder(
                "cmd.exe", "/c", "netsh wlan show all");
        builder.redirectErrorStream(true);
        Process p = builder.start();
        BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line;
        while (true) {
            line = r.readLine();
            if (line.contains("SSID")||line.contains("Signal")){
                if(!line.contains("BSSID"))
                    if(line.contains("SSID")&&!line.contains("name")&&!line.contains("SSIDs"))
                    {
                        line=line.substring(8);
                        ssids.add(line);

                    }
                    if(line.contains("Signal"))
                    {
                        line=line.substring(30);
                        signals.add(line);

                    }

                    if(signals.size()==7)
                    {
                        break;
                    }

            }

        }
        for (int i=1;i<ssids.size();i++)
        {
            System.out.println("SSID name == "+ssids.get(i)+"   and its signal == "+signals.get(i)  );
        }
        
    }
	
}