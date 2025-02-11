package prac.human;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum HealerType {
    INTERN(1, "1년 미만 실습생", 100000, 30, 50),
    RESIDENT(2, "3년 이상 레지던트", 200000, 50, 60),
    EXPERT(3, "10년 이상 베테랑", 700000, 70, 80),
    ONESELF(4, "본인 (⚠ 권장하지 않습니다!)", 0, 0, 100);

    private final int healerNumber;
    private final String healerType;
    private final int healerCost;
    private final int healerMinProbability;
    private final int healerMaxProbability;

    // healerMap - (key: healerNumber, value: HealerType)
    private static Map<Integer, String> healerMap = Collections.unmodifiableMap(
            Stream.of(values()).collect(Collectors.toMap(HealerType::getHealerNumber, HealerType::name))
    );

    // healerNumber를 통해 HealerType enum 객체를 추출하는 함수
    public static HealerType of(final int healerNumber) {
        return HealerType.valueOf(healerMap.get(healerNumber));
    }

    HealerType(int healerNumber, String healerType, int healerCost, int healerMinProbability, int healerMaxProbability) {
        this.healerNumber = healerNumber;
        this.healerType = healerType;
        this.healerCost = healerCost;
        this.healerMinProbability = healerMinProbability;
        this.healerMaxProbability = healerMaxProbability;
    }

    public int getHealerNumber() {
        return healerNumber;
    }

    public String getHealerType() {
        return healerType;
    }

    public int getHealerCost() {
        return healerCost;
    }

    public int getHealerMinProbability() {
        return healerMinProbability;
    }

    public int getHealerMaxProbability() {
        return healerMaxProbability;
    }
}
