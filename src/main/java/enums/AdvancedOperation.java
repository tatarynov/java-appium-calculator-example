package enums;

public enum AdvancedOperation implements Operation {
    LEFT_PARENTHESES("lparen"),
    RIGHT_PARENTHESES("rparen"),
    SINE("fun_sin"),
    PI("const_pi");

    private String id;

    AdvancedOperation(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
