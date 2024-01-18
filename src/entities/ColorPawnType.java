package entities;

import java.awt.Color;

public enum ColorPawnType {
    
    RED("#FF0000"),
    BLUE("#0000FF"),
    YELLOW("#FFFF00"),
    GREEN("#00FF00");

    private String hexCode;

    ColorPawnType(String hexCode) {
        this.hexCode = hexCode;
    }

    public String getHexCode() {
        return hexCode;
    }

    public Color toColor() {
        return Color.decode(hexCode);
    }
}
