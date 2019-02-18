package pack.ip.entity;

import java.util.Optional;

import static pack.ip.utils.ErrorMessages.INCORRECT_IP_LONG_VAL;
import static pack.ip.utils.ErrorMessages.NULL_IP_MSG;

//can be extended for Ipv6
public abstract class IpAddress implements Comparable<IpAddress> {
    protected long ipValue;

    public IpAddress(long ip){
        setIp(ip);
    }

    public IpAddress(String ip){
        setIp(ip);
    }

    public long getValue() {
        return ipValue;
    }

    public void setIp(long ip) {
        if (verifyIpLongValue(ip))
            throw new IllegalArgumentException(INCORRECT_IP_LONG_VAL);
        this.ipValue = ip;
    }

    protected abstract boolean verifyIpLongValue(long ip);

    public void setIp(String ip) {
        this.ipValue = Optional.ofNullable(ip).map(this::parse).orElseThrow(() -> new IllegalArgumentException(NULL_IP_MSG));
        if (!ip.equals(this.toString())) throw new IllegalArgumentException(INCORRECT_IP_LONG_VAL);
    }

    protected abstract long parse(String ip);

    @Override
    public int compareTo(IpAddress ipAddress) {
        return Long.compare(ipValue, ipAddress.getValue());
    }
}
