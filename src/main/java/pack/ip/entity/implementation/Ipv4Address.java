package pack.ip.entity.implementation;

import pack.ip.entity.IpAddress;

import static pack.ip.utils.ErrorMessages.INCORRECT_IP_FORMAT;
import static pack.ip.utils.ErrorMessages.NULL_IP_MSG;

public class Ipv4Address extends IpAddress {
    public static final Ipv4Address MAX_IP_VAL = new Ipv4Address("255.255.255.255");
    public static final Ipv4Address MIN_IP_VAL = new Ipv4Address("0.0.0.0");

    public  static final int IPV4_ARRAY_SIZE = 4;

    public Ipv4Address(long ip){
        super(ip);
    }

    public Ipv4Address(String ip){
        super(ip);
    }

    @Override
    protected boolean verifyIpLongValue(long ip){
        return ip < MIN_IP_VAL.getValue() || ip > MAX_IP_VAL.getValue();
    }

    @Override
    protected long parse(String ip){
        if (ip == null)
            throw new IllegalArgumentException(NULL_IP_MSG);

        String[] ipArray = ip.split("\\.");

        if (ipArray.length != IPV4_ARRAY_SIZE)
            throw new IllegalArgumentException(INCORRECT_IP_FORMAT);

        long ipVal = 0;
        for(int ind = 0; ind < IPV4_ARRAY_SIZE; ind++){
            try {
                long ipByte = Long.parseLong(ipArray[ind]);
                if (ipByte < 0 || ipByte > 255)
                    throw new IllegalArgumentException(INCORRECT_IP_FORMAT);
                ipVal |= (ipByte << (8 * (3 - ind)));
            }
            catch (NumberFormatException e){
                throw new IllegalArgumentException(INCORRECT_IP_FORMAT);
            }
        }

        return ipVal;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for(int ind = 0; ind < IPV4_ARRAY_SIZE; ind++)
            sb.append((ipValue >> (8 * (3 - ind))) & 0xFF).append(".");
        return sb.deleteCharAt(sb.length()-1).toString();
    }
}
