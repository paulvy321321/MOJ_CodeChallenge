package org.dts.oyster.fare;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class RuleSystem {
    private Double maxFare;

    private Set<Rule> rules = new HashSet<>();

    public void loadRule(Rule rule) {
        rules.add(rule);
    }

}
