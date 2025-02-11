package prac.injury;


import static prac.human.Patient.patient;

public class InternalWound extends Injury {
    public InternalWound() {
        super();
    }

    @Override
    public void woundList() {
        InternalWounds[] internalWoundArr = InternalWounds.values();
        int cost;
        String costStr;
        for(InternalWounds internalWound : internalWoundArr) {
            cost = internalWound.getWoundCost();
            costStr = String.format("%,d", cost);
            System.out.printf(" %d. %s (+ ₩" + costStr + ")", internalWound.getWoundNumber(), internalWound.getWoundName());
            System.out.println();
        }
    }

    @Override
    public String findHospital() {
        String hospital = null;
        int woundType = patient.getWoundType();
        hospital = switch (woundType) {
            case 1 -> "심장 센터";
            case 2 -> "호흡기 내과";
            case 3 -> "간 센터";
            default -> hospital;
        };
        return hospital;
    }
}
