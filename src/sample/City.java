package sample;

public class City {
    private String name;
    private int populatin;
    private String code2;
    private String code3;
    private String country;

    public City(String name, int populatin, String code2, String code3, String country) {
        this.name = name;
        this.populatin = populatin;
        this.code2 = code2;
        this.code3 = code3;
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public int getPopulatin() {
        return populatin;
    }

    public String getCode2() {
        return code2;
    }

    public String getCode3() {
        return code3;
    }

    public String getCountry() {
        return country;
    }
}