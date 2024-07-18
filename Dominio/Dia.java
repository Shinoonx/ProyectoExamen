package Dominio;

import com.fasterxml.jackson.databind.JsonNode;

public class Dia {
    private Menu vegetariano;
    private Menu economico;
    private Menu ejecutivo;
    private Menu baes;


    public Menu getVegetariano() {
        return vegetariano;
    }

    public void setVegetariano(Menu vegetariano) {
        this.vegetariano = vegetariano;
    }

    public Menu getEconomico() {
        return economico;
    }

    public void setEconomico(Menu economico) {
        this.economico = economico;
    }

    public Menu getEjecutivo() {
        return ejecutivo;
    }

    public void setEjecutivo(Menu ejecutivo) {
        this.ejecutivo = ejecutivo;
    }

    public Menu getBaes() {
        return baes;
    }

    public void setBaes(Menu baes) {
        this.baes = baes;
    }

}