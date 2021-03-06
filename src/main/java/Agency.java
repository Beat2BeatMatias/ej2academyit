public class Agency implements Comparable<Agency>{

    public enum Criterio{
        ADDRESS_LINE,
        AGENCY_CODE,
        DISTANCE,
    }

    public static Criterio criterio=Criterio.ADDRESS_LINE;
    private Address address;
    private String agency_code;
    private String correspondent_id;
    private String description;
    private String disabled;
    private String distance;
    private String id;
    private String payment_method_id;
    private String phone;
    private String site_id;
    private String terminal;


    public Agency (){

    }

    public Agency(Address address, String agency_code, String correspondent_id, String description, String disabled, String distance, String id, String payment_method_id, String phone, String site_id, String terminal) {
        this.address = address;
        this.agency_code = agency_code;
        this.correspondent_id = correspondent_id;
        this.description = description;
        this.disabled = disabled;
        this.distance = distance;
        this.id = id;
        this.payment_method_id = payment_method_id;
        this.phone = phone;
        this.site_id = site_id;
        this.terminal = terminal;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getAgency_code() {
        return agency_code;
    }

    public void setAgency_code(String agency_code) {
        this.agency_code = agency_code;
    }

    public String getCorrespondent_id() {
        return correspondent_id;
    }

    public void setCorrespondent_id(String correspondent_id) {
        this.correspondent_id = correspondent_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDisabled() {
        return disabled;
    }

    public void setDisabled(String disabled) {
        this.disabled = disabled;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPayment_method_id() {
        return payment_method_id;
    }

    public void setPayment_method_id(String payment_method_id) {
        this.payment_method_id = payment_method_id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSite_id() {
        return site_id;
    }

    public void setSite_id(String site_id) {
        this.site_id = site_id;
    }

    public String getTerminal() {
        return terminal;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }

    @Override
    public String toString() {
        return "Agency{" +
                "address=" + address +
                ", agency_code='" + agency_code + '\'' +
                ", correspondent_id='" + correspondent_id + '\'' +
                ", description='" + description + '\'' +
                ", disabled='" + disabled + '\'' +
                ", distance='" + distance + '\'' +
                ", id='" + id + '\'' +
                ", payment_method_id='" + payment_method_id + '\'' +
                ", phone='" + phone + '\'' +
                ", site_id='" + site_id + '\'' +
                ", terminal='" + terminal + '\'' +
                '}';
    }

    @Override
    public int compareTo(Agency o) {
        switch (criterio){
            case ADDRESS_LINE:
                return this.getAddress().getAdress_line().compareTo(o.getAddress().getAdress_line());
            case AGENCY_CODE:
                return this.agency_code.compareTo(o.agency_code);
            case DISTANCE:
                double distanciaThis = Double.parseDouble(this.distance);
                double distanciaThat = Double.parseDouble(o.distance);
                if (distanciaThis > distanciaThat){
                    return 1;
                }else if (distanciaThis < distanciaThat){
                    return -1;
                }else {
                    return 0;
                }
            default:
                return 0;
        }
    }
}
