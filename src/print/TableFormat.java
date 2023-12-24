package print;

import java.util.ArrayList;

public class TableFormat {
    private int maxFieldLen = 0;
    private int maxValueLen = 0;
    private ArrayList<String> fields = new ArrayList<>();
    private ArrayList<String> values = new ArrayList<>();
    private String title = "";

    public void setTitle(String title) {
        this.title = title;
    }

    public void appendCell(String field, String value) {
        String fieldStr = field.strip();
        String valueStr = value.strip();
        int fieldLen = field.length();
        int valueLen = value.length();
        if (fieldLen > maxFieldLen)
            maxFieldLen = fieldLen;
        if (valueLen > maxValueLen)
            maxValueLen = valueLen;
        fields.add(fieldStr);
        values.add(valueStr);
    }

    public String createTable() {
        int cut = maxFieldLen + maxValueLen - title.length();
        int sideLen = (cut - (cut % 2)) / 2;
        String ret = Util.repeat("=", sideLen + 3) + title + Util.repeat("=", sideLen + 4) + ((cut % 2) == 1 ? " " : "")
                + "\n";
        for (int i = 0; i < fields.size(); i++) {
            ret += "| " + fields.get(i) + Util.repeat(" ", maxFieldLen - fields.get(i).length());
            ret += " | " + values.get(i) + Util.repeat(" ", maxValueLen - values.get(i).length()) + " |\n";
            ret += Util.repeat("-", maxFieldLen + maxValueLen + 8) + "\n";
        }
        return ret;

    }
}
