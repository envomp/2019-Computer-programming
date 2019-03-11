package ee.taltech.iti0202.logger.level;

public final class Level {

    public static final Level OFF = new Level("OFF", Integer.MAX_VALUE);
    public static final Level SEVERE = new Level("SEVERE", 800);
    public static final Level ERROR = new Level("ERROR", 700);
    public static final Level WARNING = new Level("WARNING", 500);
    public static final Level INFO = new Level("INFO", 400);
    public static final Level DEBUG = new Level("DEBUG", 300);
    public static final Level ALL = new Level("ALL", Integer.MIN_VALUE);

    String name;
    int value;

    private Level(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

}
