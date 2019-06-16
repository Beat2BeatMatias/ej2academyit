import javax.persistence.Entity;


public class Address {

    private String address_line;
    private String city;
    private String country;
    private String location;
    private String other_info;
    private String state;
    private String zip_code;

    public Address(){

    }

    public Address(String adress_line, String city, String country, String location, String other_info, String state, String zip_code) {
        this.address_line = adress_line;
        this.city = city;
        this.country = country;
        this.location = location;
        this.other_info = other_info;
        this.state = state;
        this.zip_code = zip_code;
    }

    public String getAdress_line() {
        return address_line;
    }

    public void setAdress_line(String adress_line) {
        this.address_line = adress_line;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getOther_info() {
        return other_info;
    }

    public void setOther_info(String other_info) {
        this.other_info = other_info;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip_code() {
        return zip_code;
    }

    public void setZip_code(String zip_code) {
        this.zip_code = zip_code;
    }

    @Override
    public String toString() {
        return "Address{" +
                "adress_line='" + address_line + '\'' +
                ", City='" + city + '\'' +
                ", Country='" + country + '\'' +
                ", location='" + location + '\'' +
                ", other_info='" + other_info + '\'' +
                ", state='" + state + '\'' +
                ", zip_code='" + zip_code + '\'' +
                '}';
    }
}
