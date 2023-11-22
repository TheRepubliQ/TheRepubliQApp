package br.edu.ifsp.tcc.apprepublic.utils;

public class Mascara {

    public static String MASCARA_DATA = "##/##/####";

    public static String unmask(String s) {
        return s.replaceAll("[.]", "")
                .replaceAll("[-]", "")
                .replaceAll("[/]", "")
                .replaceAll("[(]", "")
                .replaceAll("[)]", "")
                .replaceAll(" ", "")
                .replaceAll(",", "");
    }

    public static boolean isASign(char c) {
        return c == '.' || c == '-' || c == '/' || c == '(' || c == ')' || c == ',' || c == ' ';
    }

    public static String mask(String mask, String text) {
        int i = 0;
        StringBuilder mascara = new StringBuilder();
        for (char m : mask.toCharArray()) {
            if (m != '#') {
                mascara.append(m);
                continue;
            }
            try {
                mascara.append(text.charAt(i));
            } catch (Exception e) {
                break;
            }
            i++;
        }

        return mascara.toString();
    }
}
