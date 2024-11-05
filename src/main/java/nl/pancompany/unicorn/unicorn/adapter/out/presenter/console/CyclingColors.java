package nl.pancompany.unicorn.unicorn.adapter.out.presenter.console;

import lombok.Getter;

import static nl.pancompany.unicorn.unicorn.adapter.out.presenter.console.RainbowColor.*;

@Getter
public class CyclingColors {
    private RainbowColor color1 = BOLD_PASTEL_RED;
    private RainbowColor color2 = BOLD_PASTEL_ORANGE;
    private RainbowColor color3 = BOLD_PASTEL_YELLOW;
    private RainbowColor color4 = BOLD_PASTEL_GREEN;
    private RainbowColor color5 = BOLD_PASTEL_BLUE;
    private RainbowColor color6 = BOLD_PASTEL_INDIGO;
    private RainbowColor color7 = BOLD_PASTEL_VIOLET;

    public void cycle() {
        RainbowColor memory = color7;
        color7 = color6;
        color6 = color5;
        color5 = color4;
        color4 = color3;
        color3 = color2;
        color2 = color1;
        color1 = memory;
    }
}
