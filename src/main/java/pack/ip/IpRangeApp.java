package pack.ip;

import pack.ip.entity.implementation.Ipv4Address;
import pack.ip.rangeStuff.IpRange;

import java.util.Scanner;

public class IpRangeApp
{
    public static void main( String[] args ) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Input start IPv4 address:");
        String ip1 = sc.nextLine();
        System.out.println("Input last IPv4 address:");
        String ip2 = sc.nextLine();

        try {
            //IpRange ipRange = new IpRange("192.168.0.1", "192.168.0.5");
            System.out.println("Exclusive range of inbetween IP addresses:");
            IpRange ipRange = new IpRange(new Ipv4Address("0.0.0.1"), new Ipv4Address("0.0.0.4"));
            ipRange.forEach(System.out::println);
        }
        catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }
}
