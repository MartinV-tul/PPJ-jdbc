package cz.tul.data;

public class Town {
    private String name;
    private Country country;

    public Town(){
    }

    public Town(String name, Country country){
        this.name = name;
        this.country = country;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public Country getCountry() {
        return country;
    }

    public String getCountryName() {
        return country.getCountryName();
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((country == null) ? 0 : country.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj) return true;
        if(obj == null) return false;
        if(getClass() != obj.getClass()) return false;
        Town otherTown = (Town) obj;
        if(!name.equals(otherTown.name)) return false;
        return country.equals(otherTown.country);
    }

    @Override
    public String toString() {
        return "Town [name=" + name + ", country=" + country.toString()+"]";
    }
}
