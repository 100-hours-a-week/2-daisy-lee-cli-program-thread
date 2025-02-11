package prac.human;

public enum ResidentPersonality {
    NEPOTISM(-20),   // 섬세한
    DETAILED(10);  // 낙하산

    private final int buffOrDebuff;

    ResidentPersonality(int buffOrDebuff) {
        this.buffOrDebuff = buffOrDebuff;
    }

    public static ResidentPersonality getRandomPersonality() {
        return values()[(int) (Math.random() * values().length)];
    }

    public int getBuffOrDebuff() {
        return buffOrDebuff;
    }
}
