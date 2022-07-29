package es.geeksusma.domain.contact;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Contact {

    private final Name name;
    private final Phone phone;

    private Contact(Name name, Phone phone) {
        this.name = name;
        this.phone = phone;
    }

    public static Contact of(Name name, Phone phone) {
        return new Contact(name, phone);
    }

    public String getName() {
        return name.getFullName();
    }

    public String getPhone() {
        return phone.prefix + "-" + phone.number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Contact contact = (Contact) o;

        return new EqualsBuilder().append(name, contact.name).append(phone, contact.phone).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(name).append(phone).toHashCode();
    }

    @Override
    public String toString() {
        return "Contact{" +
                "name=" + name +
                ", phone=" + phone +
                '}';
    }
}
