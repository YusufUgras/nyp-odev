package print;

import java.util.ArrayList;

// Cerceve formatinda konsola yazi yazdirmayi saglayan duzeni olusturan sinif
public class FrameFormat {

    private int maxLen = 0;

    private ArrayList<String> lines = new ArrayList<>();

    private String title = "";

    public FrameFormat() {
        this.appendLine("");
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void appendLine(String line) {
        String mLine = " " + line + " ";
        int lineLen = mLine.length();
        if (lineLen > maxLen)
            maxLen = lineLen;

        lines.add(mLine);
    }

    public String createFrame() {
        this.appendLine("");
        int cut = maxLen - title.length();
        int sideLen = (cut - (cut % 2)) / 2;
        String ret = Util.repeat("=", sideLen + 3) + title + Util.repeat("=", sideLen + 2) + ((cut % 2) == 1 ? "=" : "")
                + "\n";
        for (String line : lines) {
            ret += "|| " + line + Util.repeat(" ", maxLen - line.length()) + " ||\n";
        }
        ret += Util.repeat("=", maxLen + 6) + "\n";
        return ret;
    }
}
