package cz.tul.data;

public class Country {

    private String name;

    public Country(){}

    public Country(String name){
        this.name = name;
    }

    public String getCountryName() {
        return name;
    }

    public void setCountryName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Country otherCountry = (Country) obj;
        return name == otherCountry.name;
    }

    @Override
    public String toString() {
        return "Country [name=" + name+"]";
    }

}
