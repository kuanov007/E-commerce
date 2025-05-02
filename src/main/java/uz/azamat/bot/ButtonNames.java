package uz.azamat.bot;

public enum ButtonNames {
    //ADMIN UCHUN
    ADD_PRODUCT("Add product"),
    DELETE_PRODUCT("Delete product"),
    UPDATE_PRODUCT("Update product"),
    SHOW_ALL_PRODUCT("Show all product"),

    //USER UCHUN
    BUY_PRODUCT("Buy product"),
    HISTORY("History"),

    //IKKALASI UCHUN HAM
    BACK("Back");

    private final String string;

    ButtonNames(String string) {
        this.string = string;
    }

    public String getString() {
        return string;
    }
}
