package org.dts.oyster.fare;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.dts.oyster.model.UserJourney;
import org.dts.oyster.enums.Zone;


@Data
@AllArgsConstructor
public class Combination {
    private Zone startZone;
    private Zone endZone;

    public boolean check(UserJourney journey) {
        return journey.getStart().getZones().contains(startZone) && journey.getEnd().getZones().contains(endZone);
    }
}
