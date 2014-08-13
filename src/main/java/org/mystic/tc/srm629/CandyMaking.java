package org.mystic.tc.srm629;

public class CandyMaking {

    public double findSuitableDensity(int[] containerVolume, int[] desiredWeight) {
        int n = containerVolume.length;
        double[] perfectD = new double[n];
        double min = Double.MAX_VALUE;
        for (int i = 0; i < n; ++i) {
            perfectD[i] = (desiredWeight[i] * 1.0d) / (containerVolume[i] * 1.0d);
        }
        for (int i = 0; i < n; ++i) {
            double fi = f(containerVolume, desiredWeight, perfectD[i]);
            min = Math.min(min, fi);
        }
        return min;
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