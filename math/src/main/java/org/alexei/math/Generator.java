package org.alexei.math;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by user on 8/23/2016.
 */
public interface Generator {
    List<BigInteger> next() throws InterruptedException;
}
