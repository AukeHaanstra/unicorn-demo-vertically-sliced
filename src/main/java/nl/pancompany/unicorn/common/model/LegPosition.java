package nl.pancompany.unicorn.common.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum LegPosition {
    FRONT_LEFT("front left"), FRONT_RIGHT("front right"), BACK_LEFT("back left"), BACK_RIGHT("back right");

    private final String description;
}
