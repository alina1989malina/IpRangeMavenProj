package pack.ip.entity.implementation;




import org.junit.jupiter.api.Test;
import pack.ip.entity.IpAddress;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static pack.ip.utils.ErrorMessages.*;

public class Ipv4AddressTest {
    private IpAddress ipAddress = new Ipv4Address("0.0.0.0");

    @Test
    public void testInvalidIpString() {
        assertThrows(IllegalArgumentException.class, () -> ipAddress.setIp(null), NULL_IP_MSG);
        assertThrows(IllegalArgumentException.class, () -> ipAddress.setIp("255255255255"), INCORRECT_IP_FORMAT);
        assertThrows(IllegalArgumentException.class, () -> ipAddress.setIp("255255255255255255255255255255255255"), INCORRECT_IP_FORMAT);
        assertThrows(IllegalArgumentException.class, () -> ipAddress.setIp("255255255255255.255255255255255255255"), INCORRECT_IP_FORMAT);
        assertThrows(IllegalArgumentException.class, () -> ipAddress.setIp("255.255"), INCORRECT_IP_FORMAT);
        assertThrows(IllegalArgumentException.class, () -> ipAddress.setIp("#!255.255ssss.ggg.ggg"), INCORRECT_IP_FORMAT);
        assertThrows(IllegalArgumentException.class, () -> ipAddress.setIp("AAA.BBB.CCC.ggg"), INCORRECT_IP_FORMAT);
        assertThrows(IllegalArgumentException.class, () -> ipAddress.setIp("aaaaaaaabbbbbbbbbbbffff"), INCORRECT_IP_FORMAT);
        assertThrows(IllegalArgumentException.class, () -> ipAddress.setIp("256.255.255.255"), INCORRECT_IP_FORMAT);
        assertThrows(IllegalArgumentException.class, () -> ipAddress.setIp("000.000.000.000"), INCORRECT_IP_FORMAT);
        assertThrows(IllegalArgumentException.class, () -> ipAddress.setIp("000.000.000.010"), INCORRECT_IP_FORMAT);
    }

    @Test
    public void testInvalidIpLongValue() {
        assertThrows(IllegalArgumentException.class, () -> ipAddress.setIp(-1), INCORRECT_IP_LONG_VAL);
        assertThrows(IllegalArgumentException.class, () -> ipAddress.setIp(1L << 33), INCORRECT_IP_LONG_VAL);
    }

    @Test
    public void testIpStringToLong() {
        ipAddress.setIp("0.0.0.0");
        assertEquals( ipAddress.getValue(),0);

        ipAddress.setIp("0.0.1.0");
        assertEquals( ipAddress.getValue(),(1L << 8));

        ipAddress.setIp("0.1.0.0");
        assertEquals( ipAddress.getValue(),(1L << 16));

        ipAddress.setIp("255.255.255.255");
        assertEquals(ipAddress.getValue(), (1L << 32) - 1L);
    }

    @Test
    public void testLongToIpString() {
        ipAddress.setIp(0);
        assertEquals(ipAddress.toString(),"0.0.0.0");

        ipAddress.setIp(1L << 8);
        assertEquals(ipAddress.toString(),"0.0.1.0");

        ipAddress.setIp(1L << 16);
        assertEquals(ipAddress.toString(),"0.1.0.0");

        ipAddress.setIp((1L << 32) - 1L);
        assertEquals(ipAddress.toString(),"255.255.255.255");
    }

    @Test
    public void testIpComparison() {
        Random randomno = new Random();
        Long module = (1L << 32) - 1L;
        Long val1 = Math.abs(randomno.nextLong()) % module;
        Long val2 = Math.abs(randomno.nextLong()) % module;
        IpAddress ipAddress1 = new Ipv4Address(val1);
        IpAddress ipAddress2 = new Ipv4Address(val2);

        if (val1 > val2)
            assertTrue(ipAddress1.compareTo(ipAddress2) > 0);
        else
            assertTrue(ipAddress1.compareTo(ipAddress2) <= 0);
    }

}
