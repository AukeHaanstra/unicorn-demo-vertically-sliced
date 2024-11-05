package nl.pancompany.unicorn.animals.bear;

import lombok.RequiredArgsConstructor;
import nl.pancompany.unicorn.animals.rabbit.Rabbit;
import nl.pancompany.unicorn.animals.salmon.Salmon;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BearMouth {

    private final Rabbit rabbit;
    private final Salmon salmon;
}
