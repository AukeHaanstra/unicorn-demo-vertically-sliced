package nl.pancompany.unicorn.unicorn.adapter.out.presenter.console;

import lombok.RequiredArgsConstructor;

import java.util.Arrays;

import static java.lang.System.lineSeparator;
import static java.util.stream.Collectors.joining;

@RequiredArgsConstructor
public class MovingPicture {
    private final String frame1;
    private final String frame2;
    private String currentFrame;
    private final int startingPosition;
    private final int step;
    private int padding;

    public String getCurrentFrame() {
        if (currentFrame == null) {
            currentFrame = frame1;
        } else if (currentFrame.equals(frame1)) {
            currentFrame = frame2;
        } else if (currentFrame.equals(frame2)) {
            currentFrame = frame1;
        }

        padding -= step;
        if (padding <= 0) {
            padding = startingPosition;
        }

        return padLines(currentFrame, padding);
    }

    public static String padLines(String multiLine, int n) {
        return Arrays.stream(multiLine.split("\n"))
                .map(string -> padLeft(string, n))
                .collect(joining(lineSeparator()));
    }

    public static String padLeft(String s, int n) {
        return " ".repeat(n) + s;
    }

}
