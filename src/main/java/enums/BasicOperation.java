package enums;

public enum BasicOperation implements Operation {
    PLUS("op_add"),
    MINUS("op_sub"),
    DIVIDE("op_div"),
    MULTIPLY("op_mul");

    private String id;

    BasicOperation(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
