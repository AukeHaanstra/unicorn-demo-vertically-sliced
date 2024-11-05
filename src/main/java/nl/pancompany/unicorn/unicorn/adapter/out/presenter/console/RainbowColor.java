package nl.pancompany.unicorn.unicorn.adapter.out.presenter.console;

import nl.pancompany.unicorn.common.model.Color;

// ANSI escape codes for pastel rainbow colors
public enum RainbowColor {
    BOLD_BRIGHT_WHITE("\u001B[1m\u001B[97m"),
    BOLD_LIGHT_PASTEL_VIOLET_PINK("\u001B[1m\u001B[38;5;225m"),
    BOLD_PASTEL_RED("\u001B[1m\u001B[38;5;210m"),
    BOLD_PASTEL_ORANGE("\u001B[1m\u001B[38;5;215m"),
    BOLD_PASTEL_YELLOW("\u001B[1m\u001B[38;5;229m"),
    BOLD_PASTEL_GREEN("\u001B[1m\u001B[38;5;151m"),
    BOLD_PASTEL_BLUE("\u001B[1m\u001B[38;5;110m"),
    BOLD_PASTEL_INDIGO("\u001B[1m\u001B[38;5;111m"),
    BOLD_PASTEL_VIOLET("\u001B[1m\u001B[38;5;183m");

    private final String code;

    RainbowColor(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public String toString() {
        return code;
    }

    public static RainbowColor valueOf(Color color) {
        return valueOf("BOLD_PASTEL_"+color.name());
    }
}
