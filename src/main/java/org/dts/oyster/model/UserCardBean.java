package org.dts.oyster.model;



import org.dts.oyster.enums.UserTravelMode;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class UserCardBean {

    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    private Double balance = 0.0;
    private UserJourney journeyInProgress;

    public synchronized void credit(Double amount) {
        balance += amount;
    }

    public synchronized void debit(Double amount) {
        balance -= amount;
    }

    public Double getBalance() {
        return balance;
    }

    public void swipeCard(Station station, UserTravelMode mode) {
        if (null != journeyInProgress) {
            UserJourney journey = UserJourney.builder().start(journeyInProgress.getStart()).end(station).mode(journeyInProgress.getMode()).isJourneyComplete(true).build();
            UserJourney journeyInProgressOldValue = this.journeyInProgress;
            journeyInProgress = journey;
            pcs.firePropertyChange("journeyInProgress", journeyInProgressOldValue, journeyInProgress); //notify propertyChangeListener
            journeyInProgress = null;
        } else {
            UserJourney journey = UserJourney.builder().start(station).mode(mode).isJourneyComplete(false).build();
            UserJourney journeyInProgressOldValue = this.journeyInProgress;
            journeyInProgress = journey;
            pcs.firePropertyChange("journeyInProgress", journeyInProgressOldValue, journeyInProgress); //notify propertyChangeListener
        }
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }

}
