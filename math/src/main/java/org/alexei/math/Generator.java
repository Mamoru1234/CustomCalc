package org.alexei.math;

import java.math.BigInteger;
import java.util.List;

public interface Generator {
    List<BigInteger> next() throws InterruptedException;
}
