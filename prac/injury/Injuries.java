package prac.injury;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// enum이 복수형태 이기 때문에 Injury.java와 분리
public enum Injuries{
    EXTERNAL(1, "외상", 5000),
    INTERNAL(2, "내상", 8000);

    private final int injuryNumber;
    private final String injuryName;
    private final int injuryCost;

    // injuryMap - (key: injuryNumber, value: Injuries)
    private static Map<Integer, String> injuryMap = Collections.unmodifiableMap(
            Stream.of(values()).collect(Collectors.toMap(Injuries::getInjuryNumber, Injuries::name))
    );

    // injuryNumber를 통해 Injuries enum 객체를 추출하는 함수
    public static Injuries of(final int injuryNumber) {
        return Injuries.valueOf(injuryMap.get(injuryNumber));
    }

    Injuries(int injuryNumber, String injuryName, int injuryCost) {
        this.injuryNumber = injuryNumber;
        this.injuryName = injuryName;
        this.injuryCost = injuryCost;
    }

    public int getInjuryNumber() {
        return injuryNumber;
    }
    public String getInjuryName() { return injuryName; }
    public int getInjuryCost() { return injuryCost; }
}
