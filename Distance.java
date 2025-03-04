// Dynamic programming implementation of the Levenshtein distance algorithm
// based on https://www.geeksforgeeks.org/java-program-to-implement-levenshtein-distance-computing-algorithm/
// Time complexity: O(m*n) where m is the length of str1 and n is the length of str2

public class Distance {
    static int compute(String str1, String str2) {
        int[][] d = new int[str1.length() + 1][str2.length() + 1];
        for (int i = 0; i <= str1.length(); i++) {
            for (int j = 0; j <= str2.length(); j++) {
                if (i == 0) {
                    d[i][j] = j;
                } else if (j == 0) {
                    d[i][j] = i;
                } else {
                    d[i][j] = min(d[i - 1][j - 1] + cost(str1.charAt(i - 1), str2.charAt(j - 1)),
                            d[i - 1][j] + 1, d[i][j - 1] + 1);
                }
            }
        }
        return d[str1.length()][str2.length()];
    }

    static int cost(char a, char b) {
        return a == b ? 0 : 1;
    }

    static int min(int a, int b, int c) {
        return Math.min(a, Math.min(b, c));
    }

}
