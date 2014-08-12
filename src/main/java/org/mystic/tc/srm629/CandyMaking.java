package org.mystic.tc.srm629;

public class CandyMaking {

    public double findSuitableDensity(int[] containerVolume, int[] desiredWeight) {
        int n = containerVolume.length;
        double[] perfectD = new double[n];
        double maxD = Double.MIN_VALUE;
        double minD = Double.MAX_VALUE;
        for (int i = 0; i < n; ++i) {
            perfectD[i] = (desiredWeight[i] * 1.0d) / (containerVolume[i] * 1.0d);
            maxD = Math.max(maxD, perfectD[i]);
            minD = Math.min(minD, perfectD[i]);
        }
        double a = minD;
        double b = maxD;
        double len = (b - a);
        double fi = 1.61803d;
        while (len >= 5e-11) {
            double x1 = b - ((b - a) * 1.0d) / fi;
            double x2 = a + ((b - a) * 1.0d) / fi;
            double fx1 = f(containerVolume, desiredWeight, x1);
            double fx2 = f(containerVolume, desiredWeight, x2);
            if (fx1 < fx2) {
                b = x2;
            } else {
                a = x1;
            }
            len = (b - a);
        }
        return f(containerVolume, desiredWeight, (a + b) / 2.0d);
    }

    private double f(int[] c, int[] w, double x) {
        double ans = 0.0d;
        int n = c.length;
        for (int i = 0; i < n; ++i) {
            ans += Math.abs(1.0d * w[i] - 1.0d * x * c[i]);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] p0;
        int[] p1;
        p0 = new int[]{10, 20, 40};
        p1 = new int[]{4000, 2000, 1000};
        CandyMaking c = new CandyMaking();
        System.out.println(c.findSuitableDensity(p0, p1));
    }
}