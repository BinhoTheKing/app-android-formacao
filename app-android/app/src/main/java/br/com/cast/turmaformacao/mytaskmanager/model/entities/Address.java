package br.com.cast.turmaformacao.mytaskmanager.model.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Address {

    @JsonProperty("cep")
    private String zipCode;

    @JsonProperty("tipoDeLogradouro")
    private String type;

    @JsonProperty("logradouro")
    private String street;

    @JsonProperty("bairro")
    private String neighborhood;

    @JsonProperty("cidade")
    private String city;

    @JsonProperty("estado")
    private String state;

    public Address() {
        super();
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Address address = (Address) o;

        if (!zipCode.equals(address.zipCode)) return false;
        if (!type.equals(address.type)) return false;
        if (!street.equals(address.street)) return false;
        if (!neighborhood.equals(address.neighborhood)) return false;
        if (!city.equals(address.city)) return false;
        return state.equals(address.state);

    }

    @Override
    public int hashCode() {
        int result = zipCode.hashCode();
        result = 31 * result + type.hashCode();
        result = 31 * result + street.hashCode();
        result = 31 * result + neighborhood.hashCode();
        result = 31 * result + city.hashCode();
        result = 31 * result + state.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Address{" +
                "zipCode='" + zipCode + '\'' +
                ", type='" + type + '\'' +
                ", street='" + street + '\'' +
                ", neighborhood='" + neighborhood + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}
