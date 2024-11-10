package org.dts.oyster.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.dts.oyster.enums.Zone;

import java.util.Set;


@Data
@AllArgsConstructor
public class Station {
    private String name;
    private Set<Zone> zones;
}
