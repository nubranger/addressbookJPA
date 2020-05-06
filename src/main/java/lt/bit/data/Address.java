package lt.bit.data;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "address")
    private String address;
    @Column(name = "city")
    private String city;
    @Column(name = "postal_code")
    private String postalCode;
    //2 gauti adresu lista per person situ net nereikejo
//    @ManyToOne(cascade = CascadeType.ALL)
    //15 @ManyToOne defaultas yra EAGER
//    @ManyToOne
//    @JoinColumn(name = "person_id")
    //2 gauti adresu lista per person situ net nereikejo
//    private Person person;

    //3
//    @ManyToOne
//    @JoinColumn(name = "person_id")
//    private Person person;

    //4
    @ManyToOne
    private Person person;


    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address1 = (Address) o;
        return id == address1.id &&
                Objects.equals(address, address1.address) &&
                Objects.equals(city, address1.city) &&
                Objects.equals(postalCode, address1.postalCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, address, city, postalCode);
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", postalCode='" + postalCode +
                '}';
    }

}
