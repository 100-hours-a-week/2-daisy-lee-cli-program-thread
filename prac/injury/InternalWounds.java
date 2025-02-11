package prac.injury;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum InternalWounds {
    HEART(1, "심장", 3500000),
    LUNG(2, "폐", 2500000),
    LIVER(3, "간", 2000000);
    
    private final int woundNumber;
    private final String woundName;
    private final int woundCost;

    // internalWoundMap - (key: woundNumber, value: InternalWounds)
    private static Map<Integer, String> internalWoundMap = Collections.unmodifiableMap(
            Stream.of(values()).collect(Collectors.toMap(InternalWounds::getWoundNumber, InternalWounds::name))
    );

    // woundNumber를 통해 InternalWounds enum 객체를 추출하는 함수
    public static InternalWounds of(final int woundNumber) {
        return InternalWounds.valueOf(internalWoundMap.get(woundNumber));
    }
    
    InternalWounds(int woundNumber, String woundName, int woundCost) {
        this.woundNumber = woundNumber;
        this.woundName = woundName;
        this.woundCost = woundCost;
    }

    public int getWoundNumber() {
        return woundNumber;
    }
    public String getWoundName() {
        return woundName;
    }
    public int getWoundCost() {
        return woundCost;
    }
}
