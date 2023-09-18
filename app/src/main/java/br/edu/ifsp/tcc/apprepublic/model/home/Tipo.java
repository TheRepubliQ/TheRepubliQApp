package br.edu.ifsp.tcc.apprepublic.model.home;

public enum Tipo {

    REPUBLICA("Republica"),
    APARTAMENTO("Apartamento"),
    CASA("Casa"),
    PENSAO("Pensao"),
    KITNET("KitNet");

    private String description;

    private Tipo(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}
