package org.dts.oyster.fare;

import lombok.AllArgsConstructor;
import org.dts.oyster.model.UserCardBean;
import org.dts.oyster.model.UserJourney;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Optional;

@AllArgsConstructor
public class UserCardListener implements PropertyChangeListener {
    private FareCalculator fareCalculator;

    private synchronized void transact(UserCardBean card, UserJourney journey) {
        Double maxFare = fareCalculator.getRuleSystem().getMaxFare();
        Double fareAmount = Optional.of(fareCalculator.calculate(journey)).orElse(maxFare);    // Fare Amount is either the calculated fareAmount or the max Fare
        card.debit(fareAmount);
    }

    public void propertyChange(PropertyChangeEvent event) {
        UserCardBean card = (UserCardBean) event.getSource();

        UserJourney journey = (UserJourney) event.getNewValue();
        if (journey.isJourneyComplete()) {
            card.credit(fareCalculator.getRuleSystem().getMaxFare());
            transact(card, journey);
        } else {
            card.debit(fareCalculator.getRuleSystem().getMaxFare());
        }
    }
}
