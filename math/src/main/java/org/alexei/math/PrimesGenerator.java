package org.alexei.math;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadFactory;

/**
 * Created by user on 8/21/2016.
 */
public class PrimesGenerator implements Generator{
    private static final short[] INIT_PRIMES = {
        2,3,5,7,11,13,19,23,29,31,37,41,43,47,53,59,61,67,71
    };
    private static final BigInteger TWO = BigInteger.valueOf(2);
    private int cacheIndex = 0;
    private static final int DEFAULT_STEP = 10;
    private final List<BigInteger> primes = new ArrayList<>();
    private final BigInteger step;
    private BigInteger lastValue;
    PrimesGenerator() {
        this(DEFAULT_STEP);
    }
    PrimesGenerator(int step) {
        this.step = BigInteger.valueOf(step);
    }
    private boolean isNumberPrime(BigInteger number) throws InterruptedException {
        for (BigInteger prime: primes) {
            if (Thread.currentThread().isInterrupted()) {
                throw new InterruptedException();
            }
            if (number.mod(prime).equals(BigInteger.ZERO)) return false;
        }
        return true;
    }
    private List<BigInteger> getFromCache(){
        List<BigInteger> result = new LinkedList<>();
        int maxIndex = cacheIndex + step.intValue();
        for (;cacheIndex< maxIndex && cacheIndex< INIT_PRIMES.length; cacheIndex++) {
            int prime = INIT_PRIMES[cacheIndex];
            result.add(BigInteger.valueOf(prime));
            primes.add(BigInteger.valueOf(prime));
        }
        return result;
    }
    @Override
    public List<BigInteger> next() throws InterruptedException {
        if (cacheIndex < INIT_PRIMES.length) {
            List<BigInteger> result = getFromCache();
            lastValue = result.get(result.size() - 1);
            return result;
        }
        BigInteger maxParam = lastValue.add(step);
        List<BigInteger> result = new LinkedList<>();
        for (BigInteger i = lastValue; i.compareTo(maxParam) < 1; i = i.add(TWO)) {
            if (isNumberPrime(i)) {
                result.add(i);
                primes.add(i);
            }
        }
        lastValue = maxParam;
        return result;
    }
}
