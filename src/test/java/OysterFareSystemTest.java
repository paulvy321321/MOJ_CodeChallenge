
import com.google.common.collect.Sets;
import org.dts.oyster.enums.UserTravelMode;
import org.dts.oyster.enums.Zone;
import org.dts.oyster.model.*;
import org.dts.oyster.fare.*;
import org.junit.Before;
import org.junit.Test;


public class OysterFareSystemTest {
	private RuleSystem ruleSystem = new RuleSystem();

	private FareCalculator fareCalculator = new FareCalculator();

	@Before
	public void setup() {
		//The maximum possible fare is therefore £3.20.
		ruleSystem.setMaxFare(3.2);

		// Rule 1 -> Anywhere in Zone 1 £2.50
		Rule rule1 = new Rule();
		rule1.setAmount(2.5);
		Combination combination = new Combination(Zone.ONE, Zone.ONE);
		rule1.addCombination(combination);
		ruleSystem.loadRule(rule1);

		// Rule 2 -> Any one zone outside zone 1 £2.00
		Rule rule2 = new Rule();
		rule2.setAmount(2.0);
		rule2.addCombination(new Combination(Zone.TWO, Zone.TWO));
		rule2.addCombination(new Combination(Zone.THREE, Zone.THREE));
		ruleSystem.loadRule(rule2);

		// Rule 3 -> Any two zones including zone 1 £3.00
		Rule rule3 = new Rule();
		rule3.setAmount(3.0);
		rule3.addCombination(new Combination(Zone.ONE, Zone.TWO));
		rule3.addCombination(new Combination(Zone.TWO, Zone.ONE));
		rule3.addCombination(new Combination(Zone.ONE, Zone.THREE));
		rule3.addCombination(new Combination(Zone.THREE, Zone.ONE));
		ruleSystem.loadRule(rule3);

		// Any two zones excluding zone 1 £2.25
		Rule rule4 = new Rule();
		rule4.setAmount(2.25);
		rule4.addCombination(new Combination(Zone.TWO, Zone.THREE));
		rule4.addCombination(new Combination(Zone.THREE, Zone.TWO));
		ruleSystem.loadRule(rule4);

		// Rule 5 -> Any three zones £3.20
		Rule rule5 = new Rule();
		rule5.setAmount(3.2);
		ruleSystem.loadRule(rule5);

		// Rule 6 -> Any bus journey £1.80
		Rule rule6 = new Rule();
		rule6.setMode(UserTravelMode.Bus);
		rule6.setAmount(1.8);
		ruleSystem.loadRule(rule6);

		fareCalculator.setRuleSystem(ruleSystem);
	}

	@Test
	public void testJourney() {
		UserCardListener userCardListener = new UserCardListener(fareCalculator);
		UserCardBean userCardBean = new UserCardBean();
		userCardBean.addPropertyChangeListener(userCardListener);
		userCardBean.credit(30.0);

		//• Tube: Holborn to Earl’s Court
		userCardBean.swipeCard(new Station("Holborn", Sets.newHashSet(Zone.ONE)), UserTravelMode.Tube);
		userCardBean.swipeCard(new Station("Earl's Court", Sets.newHashSet(Zone.ONE, Zone.TWO)), null);
		System.out.println("User card balance after Tube: Holborn to Earl’s Court journey :  " + userCardBean.getBalance());

		//• 328 bus from Earl’s Court to Chelsea
		userCardBean.swipeCard(new Station("Chelsea", Sets.newHashSet()), UserTravelMode.Bus);
		userCardBean.swipeCard(new Station("Earl's Court", Sets.newHashSet(Zone.ONE, Zone.TWO)), null);
		System.out.println("User card balance after 328 bus from Earl’s Court to Chelsea journey :  " + userCardBean.getBalance());

		//• Tube: Earl’s Court to Hammersmith
		userCardBean.swipeCard(new Station("Earl's Court", Sets.newHashSet(Zone.ONE, Zone.TWO)), UserTravelMode.Tube);
		userCardBean.swipeCard(new Station("HammerSmith", Sets.newHashSet(Zone.TWO)), null);
		System.out.println("User card balance after Tube: Earl’s Court to Hammersmith journey :   " + userCardBean.getBalance());

	}

}
