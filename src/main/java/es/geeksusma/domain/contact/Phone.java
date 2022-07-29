package es.geeksusma.domain.contact;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Phone {

    public final String prefix;
    public final String number;

    private Phone(String prefix, String number) {
        this.prefix = prefix;
        this.number = number;
    }

    public static Phone of(String prefix, String number) {
        return new Phone(prefix, number);
    }

    @Override
    public String toString() {
        return "Phone{" +
                "prefix='" + prefix + '\'' +
                ", number='" + number + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Phone phone = (Phone) o;

        return new EqualsBuilder().append(prefix, phone.prefix).append(number, phone.number).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(prefix).append(number).toHashCode();
    }
}
