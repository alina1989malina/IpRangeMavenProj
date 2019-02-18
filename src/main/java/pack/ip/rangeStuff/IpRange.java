package pack.ip.rangeStuff;

import pack.ip.entity.IpAddress;

import java.util.Iterator;
import java.util.Optional;

import static pack.ip.utils.ErrorMessages.INCORRECT_START_IP;
import static pack.ip.utils.ErrorMessages.NULL_IP_MSG;

public class IpRange implements Iterable<IpAddress>{
    private IpAddress currentIp;
    private IpAddress lastIp;

    public IpRange(IpAddress currentIp, IpAddress lastIp){
        if (currentIp.compareTo(lastIp) >= 0)
            throw new IllegalArgumentException(INCORRECT_START_IP);
        setCurrentIp(currentIp);
        setLastIp(lastIp);
    }


    public void setCurrentIp(IpAddress currentIp) {
        this.currentIp = Optional.ofNullable(currentIp).orElseThrow(() -> new IllegalArgumentException(NULL_IP_MSG));
    }

    public void setLastIp(IpAddress lastIp) {
        this.lastIp = Optional.ofNullable(lastIp).orElseThrow(() -> new IllegalArgumentException(NULL_IP_MSG));
    }

    @Override
    public Iterator<IpAddress> iterator() {
        return new IpIterator(currentIp, lastIp);
    }

    private class IpIterator implements Iterator<IpAddress> {
        private IpAddress current;
        private final IpAddress last;

        IpIterator(IpAddress current, IpAddress last) {
            this.current = current;
            this.last = last;
        }

        @Override
        public boolean hasNext() {
            return current.getValue() + 1 < last.getValue();
        }

        @Override
        public IpAddress next() {
            current.setIp(current.getValue() + 1);
            return current;
        }
    }
}
