package br.edu.ifsp.tcc.apprepublic.model.home;

public enum Tipo {

    REPUBLICA("Republica"),
    APARTAMENTO("Apartamento"),
    CASA("Casa"),
    PENSAO("Pensão"),
    KITNET("KitNet");

    private String description;

    private Tipo(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}
