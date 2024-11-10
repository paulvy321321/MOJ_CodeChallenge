package org.dts.oyster.fare;

import lombok.Data;
import org.dts.oyster.enums.UserTravelMode;
import org.dts.oyster.model.UserJourney;

import java.util.HashSet;
import java.util.Set;

@Data
public class Rule {
    private final Set<Combination> combinations = new HashSet<>();
    private UserTravelMode mode;
    private Double amount;

    public boolean check(UserJourney journey) {
        return combinations.stream().anyMatch((combination) -> combination.check(journey))
                || (journey.getMode().equals(this.getMode()) && combinations.isEmpty());
    }

    public synchronized void addCombination(Combination combination) {
        combinations.add(combination);
    }
}
