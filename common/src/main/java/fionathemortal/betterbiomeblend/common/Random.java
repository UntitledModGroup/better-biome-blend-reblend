package fionathemortal.betterbiomeblend.common;

public class Random {
    public static int noise(int input, int seed) {
        int seededValue = (input ^ seed) + seed;
        return (214013 * seededValue + 2531011) >> 8;
    }
}