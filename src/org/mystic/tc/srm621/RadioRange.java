package org.mystic.tc.srm621;

public class RadioRange {
    public double RadiusProbability(int[] X, int[] Y, int[] R, int Z) {
        int n = X.length;
        double r1 = Math.min(Z, Math.max(0, dist(X[0], Y[0]) - R[0]));
        double r2 = Math.min(Z, dist(X[0], Y[0]) + R[0]);
        for (int i = 1; i < n; ++i) {
            double dist = dist(X[i], Y[i]);
            double newR1 = Math.min(Z, Math.max(0, dist - R[i]));
            double newR2 = Math.min(Z, dist + R[i]);
            r1 = Math.min(r1, newR1);
            r2 = Math.max(r2, newR2);
        }
        return (1.0d * Z - r2 + r1) / (1.0d * Z);
    }

    public double dist(long x, long y) {
        return Math.sqrt(x * x + y * y);
    }
}