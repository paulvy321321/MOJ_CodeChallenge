package org.dts.oyster.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.dts.oyster.enums.UserTravelMode;


@Builder
@Data
@AllArgsConstructor
public class UserJourney {
    private Station start;
    private Station end;
    private UserTravelMode mode;
    private boolean isJourneyComplete;

}
