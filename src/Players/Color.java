package src.Players;

public enum Color {
    RED("Red"),
    YELLOW("Yellow");

    private String label;

    public String getLabel() {
        return label;
    }

    private Color(String label) {
        this.label = label;
    }
}
