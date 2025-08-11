package info.debatty.java.stringsimilarity.examples;

import info.debatty.java.stringsimilarity.*;

public class Examples {

    public static void main(String[] args) {

        // ===== Levenshtein with Traceback =====
        String s1 = "Schneider";
        String s2 = "Moti";

        System.out.println("\n\n============ Levenshtein with Traceback ============");
        System.out.println("A = " + s1 + ", B = " + s2);

        LevenshteinV2.Result result = LevenshteinV2.compute(s1, s2);

        System.out.println("\n** The matrix is: **");
        LevenshteinV2.printMatrix(result.dp, s1, s2);

        System.out.println("\n** Traceback **");
        System.out.println(result.alignedA);
        System.out.println(result.alignedB);

        System.out.println("\nDistance: " + result.distance);
        NormalizedLevenshtein normalized = new NormalizedLevenshtein();
        System.out.println("Similarity: " + normalized.similarity(s1, s2));

        /*
         * ===== More examples =====
         *
         * // Normalized Levenshtein
         * Levenshtein levenshtein = new Levenshtein();
         * System.out.println(levenshtein.distance(s1, s2));
         * System.out.println(normalized.distance(s1, s2));
         *
         * // Jaccard
         * Jaccard j2 = new Jaccard(2);
         * System.out.println(j2.similarity("ABCDE", "ABCDF"));
         *
         * // Jaro-Winkler
         * JaroWinkler jw = new JaroWinkler();
         * System.out.println(jw.similarity("My string", "My tsring"));
         *
         * // Cosine
         * Cosine cos = new Cosine(3);
         * System.out.println(cos.similarity("ABC", "ABCE"));
         *
         * // Damerau
         * Damerau damerau = new Damerau();
         * System.out.println(damerau.distance("ABCDEF", "ABDCEF"));
         *
         * // Optimal String Alignment
         * OptimalStringAlignment osa = new OptimalStringAlignment();
         * System.out.println(osa.distance("CA", "ABC"));
         *
         * // Longest Common Subsequence
         * LongestCommonSubsequence lcs = new LongestCommonSubsequence();
         * System.out.println(lcs.distance("AGCAT", "GAC"));
         *
         * // NGram
         * NGram twogram = new NGram(2);
         * System.out.println(twogram.distance("ABCD", "ABTUIO"));
         *
         * // QGram
         * QGram dig = new QGram(2);
         * System.out.println(dig.distance("ABCD", "ABCE"));
         *
         * // Sorensen-Dice
         * SorensenDice sd = new SorensenDice(2);
         * System.out.println(sd.similarity("ABCDE", "ABCDFG"));
         *
         * // Weighted Levenshtein
         * WeightedLevenshtein wl = new WeightedLevenshtein((c1, c2) -> {
         *     if (c1 == 't' && c2 == 'r') {
         *         return 0.5;
         *     }
         *     return 1.0;
         * });
         * System.out.println(wl.distance("String1", "Srring2"));
         */
    }
}
