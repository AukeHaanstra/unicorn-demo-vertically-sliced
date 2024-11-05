package nl.pancompany.unicorn.unicorn.adapter.in.web.model;

import java.util.Set;

public record UnicornView(String unicornId, String name, Set<LegView> legs) {
}
