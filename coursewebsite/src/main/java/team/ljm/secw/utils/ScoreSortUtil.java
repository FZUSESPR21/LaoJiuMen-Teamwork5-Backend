package team.ljm.secw.utils;

import java.util.HashMap;
import java.util.List;

public class ScoreSortUtil {

    public static int A = 0;
    public static int B = 0;
    public static int C = 0;
    public static int D = 0;
    public static int E = 0;
    public static int F = 0;
    public static int G = 0;
    public static int H = 0;
    public static int I = 0;

    public static HashMap<String, Integer> sortScore(List<Integer> scoreList) {
        HashMap<String, Integer> map = new HashMap<>();
        for (Integer score:scoreList) {
            if (score >= 0 && score < 60) {
                G++;
            } else if (score >= 60 && score < 70) {
                F++;
            } else if (score >= 70 && score < 80) {
                E++;
            } else if (score >= 80 && score < 85) {
                D++;
            } else if (score >= 85 && score < 90) {
                C++;
            } else if (score >= 90 && score < 100) {
                B++;
            } else if (score == 100) {
                A++;
            } else if (score == -2) {
                H++;
            } else if (score == -1) {
                I++;
            }
        }
        map.put("100", A);
        map.put("90-99", B);
        map.put("85-89", C);
        map.put("80-84", D);
        map.put("70-79", E);
        map.put("60-69", F);
        map.put("0-59", G);
        if (H != 0) {
            map.put("缺交", H);
        }
        if (I != 0) {
            map.put("未评分", I);
        }
        return map;
    }
}
