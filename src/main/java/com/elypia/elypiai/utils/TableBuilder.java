package com.elypia.elypiai.utils;

public class TableBuilder {

    // Table data
    private String[] headings;
    private Object[][] data;

    // Table config
    private boolean asciiOnly;
    private boolean numbersAlignRight;

    public TableBuilder(String... headings) {
        this(headings, null);
    }

    public TableBuilder(Object[][] data) {
        this(null, data);
    }

    public TableBuilder(String[] headings, Object[][] data) {
        setHeadings(headings);
        setData(data);
        asciiOnly = false;
    }

    public String build() {
        return build(Integer.MAX_VALUE);
    }

    public String build(int maxLength) {
        if (maxLength <= 0)
            throw new IllegalArgumentException("Max length can not be less than one.");

        StringBuilder builder = new StringBuilder();

        String format = "";

        for (int i = 0; i < getColumns(); i++)
            format += "%s10";


        return builder.toString();
    }

    public String[] getHeadings() {
        return headings;
    }

    public void setHeadings(String... headings) {
        if (headings == null)
            return;

        int length = headings.length;

        if (length == 0)
            throw new IllegalArgumentException("Headings array must contain at least one String.");

        if (data != null) {
            if (length != data.length)
                throw new IllegalArgumentException("Headings length must be equal to the number of columns. [x][ ]");
        }
    }

    public Object[][] getData() {
        return data;
    }

    public void setData(Object[][] data) {
        if (data == null)
            return;

        int length = data.length;

        if (length == 0 || data[0].length == 0)
            throw new IllegalArgumentException("Unable to build table with no data.");

        if (headings != null) {
            if (length != headings.length)
                throw new IllegalArgumentException("Column length [x][ ] must be equal to the number of headings.");
        }
    }

    public int getColumns() {
        return headings.length;
    }

    public boolean isAsciiOnly() {
        return asciiOnly;
    }

    public void setAsciiOnly(boolean asciiOnly) {
        this.asciiOnly = asciiOnly;
    }

    @Override
    public String toString() {
        return build();
    }
}
