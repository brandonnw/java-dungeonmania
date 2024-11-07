package dungeonmania.entities.buildables;

public class Sceptre extends Buildable {
    public static final int DEFAULT_DURABILITY = 1;

    private int duration;

    public Sceptre(int durability, int duration) {
        super(null, durability);
        this.duration = duration;
    }

    public int getDuration() {
        return duration;
    }
}
