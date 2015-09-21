package br.com.cast.turmaformacao.mytaskmanager.model.entities;

public enum Color {


    RED("#F44336"),
    PINK("#E91E63"),
    PURPLE("#9C27B0"),
    DEEP_PURPLE("#673AB7"),
    INDIGO("#3F51B5"),
    BLUE("#2196F3"),
    LIGHT_BLUE("#03A9F4"),
    CYAN("#00BCD4"),
    TEAL("#009688"),
    GREEN("#4CAF50"),
    LIGHT_GREEN("#8BC34A"),
    LIME("#CDDC39"),
    YELLOW("#FFEB3B"),
    AMBER("#FFC107"),
    ORANGE("#FF9800"),
    DEEP_ORANGE("#FF5722"),
    BROWN("#795548"),
    GREY("#9E9E9E"),
    BLUE_GREY("#607D8B"),
    BLACK("#000000"),
    WHITE("#FFFFFF");

    private String hex;

    Color(String hex){
        this.hex = hex;
    }

    public String getHex(){
        return this.hex;
    }
}
