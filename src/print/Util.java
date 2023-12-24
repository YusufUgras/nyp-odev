package print;

class Util {
    public static String padInsert(int len, String source, String paddEnd) {
        String out = source;
        for (int i = 0; i < len - 1; i++) {
            out += " ";
        }
        out += paddEnd;
        return out;
    }

    public static String repeat(String str, int times) {
        String out = str;
        for (int i = 0; i < times; i++) {
            out += str;
        }
        return out;
    }
}
