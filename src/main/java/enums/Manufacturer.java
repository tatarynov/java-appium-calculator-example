package enums;

public enum Manufacturer {
    SAMSUNG("samsung"),
    GOOGLE("google"),
    XIAOMI("xiaomi");

    private String name;

    Manufacturer(String stringValue) {
        this.name = stringValue;
    }

    public String getName() {
        return name;
    }
}
