package prac.human;

public enum ExpertPersonality {
    VIOLENT(-10),    // 난폭한
    GENTLE(5);    // 점잖은

    private final int buffOrDebuff;

    ExpertPersonality(int buffOrDebuff) {
        this.buffOrDebuff = buffOrDebuff;
    }

    public static ExpertPersonality getRandomPersonality() {
        return values()[(int) (Math.random() * values().length)];
    }

    public int getBuffOrDebuff() {
        return buffOrDebuff;
    }
}
