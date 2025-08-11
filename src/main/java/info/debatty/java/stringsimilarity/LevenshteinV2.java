package info.debatty.java.stringsimilarity;

public class LevenshteinV2 {

    public static class Result {
        public int distance;
        public int[][] dp;
        public String alignedA;
        public String alignedB;
    }

    public static Result compute(String a, String b) {
        int m = a.length();
        int n = b.length();
        int[][] dp = new int[m + 1][n + 1];
        String[][] back = new String[m + 1][n + 1];

        // init
        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;
            back[i][0] = "up";
        }
        for (int j = 0; j <= n; j++) {
            dp[0][j] = j;
            back[0][j] = "left";
        }
        back[0][0] = null;

        // fill (substitution cost = 1 when different, 0 when same)
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (a.charAt(i - 1) == b.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                    back[i][j] = "diag"; // match
                } else {
                    int del = dp[i - 1][j] + 1;
                    int ins = dp[i][j - 1] + 1;
                    int sub = dp[i - 1][j - 1] + 1;
                    int min = Math.min(Math.min(del, ins), sub);
                    dp[i][j] = min;
                    if (min == sub) back[i][j] = "sub";
                    else if (min == del) back[i][j] = "up";
                    else back[i][j] = "left";
                }
            }
        }

        // traceback
        int i = m, j = n;
        StringBuilder alignedA = new StringBuilder();
        StringBuilder alignedB = new StringBuilder();
        while (i > 0 || j > 0) {
            String mv = (i >= 0 && j >= 0) ? back[i][j] : null;
            if ("diag".equals(mv) || "sub".equals(mv)) {
                alignedA.insert(0, i > 0 ? a.charAt(i - 1) : '-');
                alignedB.insert(0, j > 0 ? b.charAt(j - 1) : '-');
                i--;
                j--;
            } else if ("up".equals(mv)) {
                alignedA.insert(0, i > 0 ? a.charAt(i - 1) : '-');
                alignedB.insert(0, '-');
                i--;
            } else if ("left".equals(mv)) {
                alignedA.insert(0, '-');
                alignedB.insert(0, j > 0 ? b.charAt(j - 1) : '-');
                j--;
            } else {
                if (i > 0) {
                    alignedA.insert(0, a.charAt(i - 1));
                    alignedB.insert(0, '-');
                    i--;
                } else if (j > 0) {
                    alignedA.insert(0, '-');
                    alignedB.insert(0, b.charAt(j - 1));
                    j--;
                } else break;
            }
        }

        Result res = new Result();
        res.distance = dp[m][n];
        res.dp = dp;
        res.alignedA = alignedA.toString();
        res.alignedB = alignedB.toString();
        return res;
    }

    public static void printMatrix(int[][] dp, String a, String b) {
        System.out.print("   ");
        for (int j = 0; j < b.length(); j++) {
            System.out.print("  " + b.charAt(j));
        }
        System.out.println();
        for (int i = 0; i < dp.length; i++) {
            if (i == 0) System.out.print("  ");
            else System.out.print(a.charAt(i - 1) + " ");
            for (int j = 0; j < dp[i].length; j++) {
                System.out.printf("%2d ", dp[i][j]);
            }
            System.out.println();
        }
    }
}



