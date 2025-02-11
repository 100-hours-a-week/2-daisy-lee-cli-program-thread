package prac.human;

public enum InternPersonality {
    CLUMSY(-5),         // 서툰
    METICULOUS(10);    // 꼼꼼한

    private final int buffOrDebuff;

    InternPersonality(int buffOrDebuff) {
        this.buffOrDebuff = buffOrDebuff;
    }

    public static InternPersonality getRandomPersonality() {
        return values()[(int) (Math.random() * values().length)];
    }

    public int getBuffOrDebuff() {
        return buffOrDebuff;
    }
}
