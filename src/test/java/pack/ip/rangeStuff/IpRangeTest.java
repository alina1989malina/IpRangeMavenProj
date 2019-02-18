package pack.ip.rangeStuff;

import org.junit.jupiter.api.Test;
import pack.ip.entity.implementation.Ipv4Address;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static pack.ip.utils.ErrorMessages.INCORRECT_START_IP;
import static pack.ip.utils.ErrorMessages.NULL_IP_MSG;

public class IpRangeTest {
    //private IpRange range = new IpRange(new Ipv4Address("0.0.0.0"), new Ipv4Address("0.0.0.1"));

    @Test
    public void testInvalidStartInput() {
        assertThrows(IllegalArgumentException.class, () ->
                new IpRange(new Ipv4Address("0.0.0.1"), new Ipv4Address("0.0.0.0")), INCORRECT_START_IP);
    }

    @Test
    public void testNullInput() {
        assertThrows(IllegalArgumentException.class, () ->
                new IpRange(new Ipv4Address(null), new Ipv4Address("0.0.0.0")), NULL_IP_MSG);
        assertThrows(IllegalArgumentException.class, () ->
                new IpRange(new Ipv4Address("0.0.0.1"), new Ipv4Address(null)), NULL_IP_MSG);
    }

    @Test
    public void testIpIterator1() {
        IpRange ipRange = new IpRange(new Ipv4Address("0.0.0.0"), new Ipv4Address("0.0.0.3"));
        assertEquals(ipRange.iterator().next().getValue(),1L);
        assertEquals(ipRange.iterator().next().getValue(),2L);
        assertEquals(ipRange.iterator().next().getValue(),3L);
    }
}
