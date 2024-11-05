package nl.pancompany.unicorn.unicorn.adapter.out.presenter.console;

import nl.pancompany.unicorn.common.events.MagicAbilityDto;
import nl.pancompany.unicorn.common.events.MagicAbilityObtained;
import nl.pancompany.unicorn.common.events.NewLegObtained;
import nl.pancompany.unicorn.common.model.UnicornId;
import nl.pancompany.unicorn.unicorn.application.domain.model.Leg;
import nl.pancompany.unicorn.unicorn.application.domain.model.Unicorn;
import nl.pancompany.unicorn.unicorn.application.port.in.GetUnicornUsecase;
import nl.pancompany.unicorn.unicorn.application.port.out.UnicornRepository.UnicornNotFoundException;
import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

import static java.lang.System.lineSeparator;
import static java.util.stream.Collectors.joining;
import static nl.pancompany.unicorn.common.Constants.INITIAL_UNICORN_UUID;
import static nl.pancompany.unicorn.unicorn.adapter.out.presenter.console.RainbowColor.*;

public class WalkingUnicorn {

    private static final String UNICORN_1 =
            """
                    \n                {{MANES-1}}/((({{MANES-2}}(((\\{{MANES-3}}\\\\\\
                     {{HORN-1}}=={{HORN-2}}=={{HORN-3}}=={{HORN-4}}=={{HORN-5}}=={{HORN-6}}=={{HORN-7}}=={{MANES-1}}(((({{MANES-2}}(((({{MANES-3}}((\\\\{{MANES-4}}\\\\\\
                                 {{HEAD}}((           {{MANES-4}}\\\\\\\\{{MANES-5}}\\\\{{MANES-6}}\\
                                 {{HEAD}}( ({{EYE}}*    {{HEAD}}_/      {{MANES-5}}\\\\\\{{MANES-6}}\\\\{{MANES-7}}\\\\
                                   {{HEAD}}\\    /  {{BODY}}\\      {{MANES-5}}\\\\{{MANES-6}}\\{{MANES-7}}\\\\\\{{BODY}}________________
                                    {{HEAD}}|  |   {{BODY}}|       </                  {{TAIL-1}}((\\\\\\\\
                                    {{HEAD}}o_|   {{BODY}}/        /                      {{BODY}}\\ {{TAIL-2}}\\\\\\\\    {{TAIL-5}}\\\\{{TAIL-6}}\\\\\\{{TAIL-7}}\\\\
                                         {{BODY}}|  {{LEG-FRONT-LEFT}}._    {{BODY}}(                        {{BODY}}\\ {{TAIL-3}}\\\\\\\\\\\\{{TAIL-4}}\\{{TAIL-5}}\\\\\\{{TAIL-6}}\\\\\\{{TAIL-7}}\\\\\\
                                         {{BODY}}| {{LEG-FRONT-LEFT}}/                       {{LEG-BACK-LEFT}}/       /    {{TAIL-3}}\\{{TAIL-4}}\\\\\\\\{{TAIL-5}}\\\\     {{TAIL-7}}\\\\
                                        {{LEG-FRONT-RIGHT}}.{{BODY}}\\{{LEG-FRONT-LEFT}}|.     /                {{LEG-BACK-LEFT}}/       /         {{TAIL-5}}\\\\\\
                                        {{LEG-FRONT-RIGHT}}|  {{LEG-FRONT-LEFT}}\\     |       {{BODY}}________{{LEG-BACK-LEFT}}(       / {{LEG-BACK-RIGHT}}\\
                                        {{LEG-FRONT-RIGHT}}/  /{{LEG-FRONT-LEFT}}\\   /{{BODY}}-------'         {{LEG-BACK-LEFT}}\\     /   {{LEG-BACK-RIGHT}}\\
                                       {{LEG-FRONT-RIGHT}}/ /   {{LEG-FRONT-LEFT}}|  |                  {{LEG-BACK-LEFT}}\\   \\ {{LEG-BACK-RIGHT}}\\_  \\
                                      {{LEG-FRONT-RIGHT}}( <    {{LEG-FRONT-LEFT}}( <                    {{LEG-BACK-LEFT}}>  /    {{LEG-BACK-RIGHT}}\\ \\
                                     {{LEG-FRONT-RIGHT}}/ /      {{LEG-FRONT-LEFT}}\\ \\                  {{LEG-BACK-LEFT}}/ /       {{LEG-BACK-RIGHT}}> )
                                    {{LEG-FRONT-RIGHT}}/ /        {{LEG-FRONT-LEFT}}\\ >                {{LEG-BACK-LEFT}}/ /       {{LEG-BACK-RIGHT}}/ /
                                   {{LEG-FRONT-RIGHT}}//           {{LEG-FRONT-LEFT}}\\\\_             {{LEG-BACK-LEFT}}_//       {{LEG-BACK-RIGHT}}_//
                                  {{LEG-FRONT-RIGHT}}/_|            {{LEG-FRONT-LEFT}}|_/           {{LEG-BACK-LEFT}}/_|       {{LEG-BACK-RIGHT}}/_|{{RESET}}\n
                    \r
                    """;

    private static final String UNICORN_2 =
            """
                \n                {{MANES-1}}/((({{MANES-2}}(((\\{{MANES-3}}\\\\\\
                 {{HORN-1}}=={{HORN-2}}=={{HORN-3}}=={{HORN-4}}=={{HORN-5}}=={{HORN-6}}=={{HORN-7}}=={{MANES-1}}(((({{MANES-2}}(((({{MANES-3}}((\\\\{{MANES-4}}\\\\\\
                             {{HEAD}}((           {{MANES-4}}\\\\\\\\{{MANES-5}}\\\\{{MANES-6}}\\
                             {{HEAD}}( ({{EYE}}*    {{HEAD}}_/      {{MANES-5}}\\\\\\{{MANES-6}}\\\\{{MANES-7}}\\\\
                               {{HEAD}}\\    /  {{BODY}}\\      {{MANES-5}}\\\\{{MANES-6}}\\{{MANES-7}}\\\\\\{{BODY}}________________
                                {{HEAD}}|  |   {{BODY}}|       </                  {{TAIL-1}}((\\\\\\\\
                                {{HEAD}}o_|   {{BODY}}/        /                      {{BODY}}\\ {{TAIL-2}}\\\\\\\\    {{TAIL-5}}\\\\{{TAIL-6}}\\\\\\{{TAIL-7}}\\\\
                                     {{BODY}}|  {{LEG-FRONT-LEFT}}._    {{BODY}}(                        {{BODY}}\\ {{TAIL-3}}\\\\\\\\\\\\{{TAIL-4}}\\{{TAIL-5}}\\\\\\{{TAIL-6}}\\\\\\{{TAIL-7}}\\\\\\
                                     {{BODY}}| {{LEG-FRONT-LEFT}}/                       {{LEG-BACK-LEFT}}(       /    {{TAIL-3}}\\{{TAIL-4}}\\\\\\\\{{TAIL-5}}\\\\     {{TAIL-7}}\\\\
                                     {{BODY}}\\{{LEG-FRONT-LEFT}}/     /                  {{LEG-BACK-LEFT}}\\       |        {{TAIL-5}}\\\\\\
                                     {{LEG-FRONT-LEFT}}/    _/         {{BODY}}___________{{LEG-BACK-LEFT}}|      \\
                                    {{LEG-FRONT-LEFT}}/    /{{BODY}}`---------'        {{LEG-BACK-RIGHT}}\\   {{LEG-BACK-LEFT}}\\      ;
                                   {{LEG-FRONT-LEFT}}(   /  {{LEG-FRONT-RIGHT}}|  |                {{LEG-BACK-RIGHT}}\\  \\ {{LEG-BACK-LEFT}}\\_   \\
                                   {{LEG-FRONT-LEFT}}/ /    {{LEG-FRONT-RIGHT}}( <                  {{LEG-BACK-RIGHT}}> |   {{LEG-BACK-LEFT}}|  \\
                                  {{LEG-FRONT-LEFT}}/ /      {{LEG-FRONT-RIGHT}}\\ \\                {{LEG-BACK-RIGHT}}/ /     {{LEG-BACK-LEFT}}`. \\
                                 {{LEG-FRONT-LEFT}}/ >        {{LEG-FRONT-RIGHT}}\\ >              {{LEG-BACK-RIGHT}}/ /        {{LEG-BACK-LEFT}}\\ \\
                               {{LEG-FRONT-LEFT}}_//           {{LEG-FRONT-RIGHT}}||            {{LEG-BACK-RIGHT}}_//           {{LEG-BACK-LEFT}}\\\\_
                              {{LEG-FRONT-LEFT}}/_|           {{LEG-FRONT-RIGHT}}/_|           {{LEG-BACK-RIGHT}}/_|             {{LEG-BACK-LEFT}}|_/{{RESET}}\n
                \r
                """;

    public static final String RESET = "\u001B[0m";
    private final AtomicBoolean initialized = new AtomicBoolean(false);
    private final GetUnicornUsecase getUnicornUsecase;
    private final CyclingColors cyclingColors = new CyclingColors();
    private final MovingPicture movingUnicorn = new MovingPicture(UNICORN_1 ,UNICORN_2, 80, 5);
    private String unicornName = "";
    private RainbowColor frontLeftLegColor;
    private RainbowColor frontRightLegColor;
    private RainbowColor backLeftLegColor;
    private RainbowColor backRightLegColor;
    private final Set<MagicAbilityDto> magicAbilities = new HashSet<>();


    public WalkingUnicorn(GetUnicornUsecase getUnicornUsecase) {
        this.getUnicornUsecase = getUnicornUsecase;
    }

    @Scheduled(fixedDelay = 1000)
    public void initializeIfUnicornExists() {
        if (!initialized.get()) {
            Unicorn.UnicornDto unicornDto = null;
            try {
                unicornDto = getUnicornUsecase.getUnicorn(UnicornId.of(INITIAL_UNICORN_UUID));
                initialized.set(true);

                unicornName = unicornDto.name();

                for (Leg.LegDto leg : unicornDto.legs()) {
                    setLeg(leg);
                }

                Thread.ofVirtual()
                        .name("walkingUnicorn")
                        .start(this::startUnicorn);
            } catch (UnicornNotFoundException e) {
                // remains uninitialized
            }
        }
    }

    private void setLeg(Leg.LegDto leg) {
        switch (leg.legPosition()) {
            case FRONT_LEFT -> frontLeftLegColor = RainbowColor.valueOf(leg.color());
            case FRONT_RIGHT -> frontRightLegColor = RainbowColor.valueOf(leg.color());
            case BACK_LEFT -> backLeftLegColor = RainbowColor.valueOf(leg.color());
            case BACK_RIGHT -> backRightLegColor = RainbowColor.valueOf(leg.color());
            default -> throw new IllegalArgumentException("Unknown leg position");
        }
    }

    @ApplicationModuleListener
    void on(NewLegObtained newLegObtained) {
        if (newLegObtained.unicornId().equals(UnicornId.of(INITIAL_UNICORN_UUID))) {
            setLeg(new Leg.LegDto(newLegObtained.legPosition(), newLegObtained.color()));
        }
    }

    @ApplicationModuleListener
    void on(MagicAbilityObtained magicAbilityObtained) {
        if (magicAbilityObtained.unicornId().equals(UnicornId.of(INITIAL_UNICORN_UUID))) {
            magicAbilities.remove(magicAbilityObtained.obtainedMagicAbility());
            magicAbilities.add(magicAbilityObtained.obtainedMagicAbility());
        }
    }

    public void startUnicorn()  {
        while(!Thread.interrupted()) {
            printUnicorn();
            sleep();
            cyclingColors.cycle();
        }
        throw new UnicornDemoInterruptedException("interrupted");
    }

    public void printUnicorn() {
        String coloredUnicornAsciiArt = movingUnicorn.getCurrentFrame()
                .replaceAll("\\{\\{HEAD\\}\\}", BOLD_BRIGHT_WHITE.getCode())
                .replaceAll("\\{\\{EYE\\}\\}", BOLD_PASTEL_BLUE.getCode())
                .replaceAll("\\{\\{BODY\\}\\}", BOLD_BRIGHT_WHITE.getCode())
                .replaceAll("\\{\\{MANES-1\\}\\}", cyclingColors.getColor1().getCode())
                .replaceAll("\\{\\{MANES-2\\}\\}", cyclingColors.getColor2().getCode())
                .replaceAll("\\{\\{MANES-3\\}\\}", cyclingColors.getColor3().getCode())
                .replaceAll("\\{\\{MANES-4\\}\\}", cyclingColors.getColor4().getCode())
                .replaceAll("\\{\\{MANES-5\\}\\}", cyclingColors.getColor5().getCode())
                .replaceAll("\\{\\{MANES-6\\}\\}", cyclingColors.getColor6().getCode())
                .replaceAll("\\{\\{MANES-7\\}\\}", cyclingColors.getColor7().getCode())
                .replaceAll("\\{\\{HORN-1\\}\\}", cyclingColors.getColor1().getCode())
                .replaceAll("\\{\\{HORN-2\\}\\}", cyclingColors.getColor2().getCode())
                .replaceAll("\\{\\{HORN-3\\}\\}", cyclingColors.getColor3().getCode())
                .replaceAll("\\{\\{HORN-4\\}\\}", cyclingColors.getColor4().getCode())
                .replaceAll("\\{\\{HORN-5\\}\\}", cyclingColors.getColor5().getCode())
                .replaceAll("\\{\\{HORN-6\\}\\}", cyclingColors.getColor6().getCode())
                .replaceAll("\\{\\{HORN-7\\}\\}", cyclingColors.getColor7().getCode())
                .replaceAll("\\{\\{TAIL-1\\}\\}", cyclingColors.getColor1().getCode())
                .replaceAll("\\{\\{TAIL-2\\}\\}", cyclingColors.getColor2().getCode())
                .replaceAll("\\{\\{TAIL-3\\}\\}", cyclingColors.getColor3().getCode())
                .replaceAll("\\{\\{TAIL-4\\}\\}", cyclingColors.getColor4().getCode())
                .replaceAll("\\{\\{TAIL-5\\}\\}", cyclingColors.getColor5().getCode())
                .replaceAll("\\{\\{TAIL-6\\}\\}", cyclingColors.getColor6().getCode())
                .replaceAll("\\{\\{TAIL-7\\}\\}", cyclingColors.getColor7().getCode())
                .replaceAll("\\{\\{LEG-FRONT-LEFT\\}\\}", frontLeftLegColor.getCode())
                .replaceAll("\\{\\{LEG-FRONT-RIGHT\\}\\}", frontRightLegColor.getCode())
                .replaceAll("\\{\\{LEG-BACK-LEFT\\}\\}", backLeftLegColor.getCode())
                .replaceAll("\\{\\{LEG-BACK-RIGHT\\}\\}", backRightLegColor.getCode())
                .replaceAll("\\{\\{RESET\\}\\}", RESET)
                + lineSeparator().repeat(2)
                + BOLD_PASTEL_ORANGE.getCode()
                + " ".repeat(40) + "~ " + unicornName + " ~"
                + " ".repeat(5) + BOLD_LIGHT_PASTEL_VIOLET_PINK + "Magic abilities:" + lineSeparator()
                + magicAbilities.stream()
                    .map(MagicAbilityDto::magicAbility)
                    .flatMap(Optional::stream)
                    .distinct()
                    .map(ability -> " ".repeat(80) + "* "+  ability)
                    .collect(joining(lineSeparator()))
                + RESET + lineSeparator();
        clearScreen();
        System.out.print(coloredUnicornAsciiArt);
        System.out.flush();
    }

    private static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private static void sleep() {
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            throw new UnicornDemoInterruptedException(e);
        }
    }

    public static class UnicornDemoInterruptedException extends RuntimeException {
        public UnicornDemoInterruptedException(String message) {
            super(message);
        }

        public UnicornDemoInterruptedException(Throwable cause) {
            super(cause);
        }
    }


}