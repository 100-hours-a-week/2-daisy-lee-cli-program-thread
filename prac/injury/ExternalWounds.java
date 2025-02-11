package prac.injury;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum ExternalWounds {
    BURN(1, "화상", 20000),
    CONTUSION(2, "타박상", 15000),
    FRACTURE(3, "골절상", 10000);

    private final int woundNumber;
    private final String woundName;
    private final int woundCost;

    // externalWoundMap - (key: woundNumber, value: ExternalWounds)
    private static Map<Integer, String> externalWoundMap = Collections.unmodifiableMap(
            Stream.of(values()).collect(Collectors.toMap(ExternalWounds::getWoundNumber, ExternalWounds::name))
    );

    // woundNumber를 통해 ExternalWounds enum 객체를 추출하는 함수
    public static ExternalWounds of(final int woundNumber) {
        return ExternalWounds.valueOf(externalWoundMap.get(woundNumber));
    }

    ExternalWounds(int woundNumber, String woundName, int woundCost) {
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
