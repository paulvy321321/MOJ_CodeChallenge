# MOJ_CodeChallenge
-----------------------
We'd like you to model the following public transport fare system, which is a limited version of London’s Oyster card system. At the end of the test, you should aim to demonstrate a user loading a card with £30, taking the following trips, and then viewing the balance.
• Tube: Holborn to Earl’s Court
• 328 bus from Earl’s Court to Chelsea
• Tube: Earl’s Court to Hammersmith
We don’t expect a full solution in one hour, but are interested in your thought processes, how you communicate your ideas, your code structure and your approach to testing. Please treat this as a remote pairing exercise.
Operation
When the user passes through the inward barrier at the station, their oyster card is charged the maximum fare.
When they pass out of the barrier at the exit station, the fare is calculated and the maximum fare transaction removed and replaced with the real transaction (in this way, if the user doesn’t swipe out, they are charged the maximum fare).
Similarly, if they swipe out without swiping in, they are charged the maximum fare.
They will be refused entry if the balance on the card is not at least the minimum fare for that station.
All bus journeys are charged at the same price.
Data
For the purposes of this test use the following data:

Stations and zones:
Station Zones
* Holborn 1
* Earl’s Court 1,2
* Wimbledon 3
* Hammersmith 2

Fares:
* Anywhere in Zone 1 £2.50
* Any one zone outside zone 1 £2.00
* Any two zones including zone 1 £3.00
* Any two zones excluding zone 1 £2.25
* Any three zones £3.20
* Any bus journey £1.80
* The maximum possible fare is therefore £3.20.


Solution:
Using java.beans API {PropertyChangeSupport,PropertyChangeEvent,PropertyChangeListener}
jdk 11, Gradle,IntelliJ,GIT

Logic Flow:


* Create a RuleSystem instance. All the rules used to determine the fares between any two stations.Every rule has a corresponding fare amount.
* Set a maximum fare between any two stations.
* Specify a start-zone and an end-zone, then add this combination in the rule.
* When evaluating a rule, every possible combination of the rule would be compared to the specified journey, if any of them applied, the rule would be considered applicable.
* Load the rule (after adding any combinations to it) to the rule system.The fare for a journey is then determined by FareCalculator using the rule system.
* Using a travel-mode (Tube or Bus) to travel between two stations is called a journey.
* The journey is tracked using a user card. It is verified whether a journey is in progress or not when swiped.
* In the event that a journey is in progress. We prepare a journey instance and link it to this card with max fare.
* When swiped once more, and a journey is not in progress.Current station is set as end station of the journey.
* Card is credited back with the maximum amount charged.
* The fare amount for this journey is determined using the applicable rules.
* Card is charged for this fare Amount.Deducted the amount from the user card balance. 
* Print the user card's current balance as result.
