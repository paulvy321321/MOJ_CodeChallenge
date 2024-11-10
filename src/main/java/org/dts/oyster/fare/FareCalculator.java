package org.dts.oyster.fare;

import lombok.Data;
import org.dts.oyster.model.UserJourney;


import java.util.Comparator;
import java.util.function.Predicate;

@Data
public class FareCalculator {
    private RuleSystem ruleSystem;

    private Comparator<Rule> ruleComparator = (Rule rule1, Rule rule2) -> {
        if (rule1.getAmount() < rule2.getAmount()) {
            return -1;
        } else if (rule1.getAmount() > rule2.getAmount()) {
            return 1;
        } else {
            return 0;
        }
    };

    public Double calculate(UserJourney journey) {
        Predicate<Rule> rulePredicate = (rule) -> rule.check(journey);
        Rule applicable = ruleSystem.getRules().stream().filter(rulePredicate).min(ruleComparator).get();

        return applicable.getAmount();
    }
}
