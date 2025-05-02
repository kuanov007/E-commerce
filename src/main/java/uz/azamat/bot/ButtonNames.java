package uz.azamat.bot;

public enum ButtonNames {
    ADD_PRODUCT("Add product"),
    DELETE_PRODUCT("Delete product"),
    UPDATE_PRODUCT("Update product"),
    BUY_PRODUCT("Buy product"),
    HISTORY("History"),
    BACK("Back");

    private final String string;

    ButtonNames(String string) {
        this.string = string;
    }

    public String getString() {
        return string;
    }
}
