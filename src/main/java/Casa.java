import javax.persistence.*;

@Entity
@Table(name="casa", schema = "restapi")
public class Casa {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    int puertas;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPuertas() {
        return puertas;
    }

    public void setPuertas(int puertas) {
        this.puertas = puertas;
    }
}
