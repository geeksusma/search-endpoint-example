package es.geeksusma.domain.contact;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Name {

    public final String first;
    public final String last;

    private Name(String first, String last) {
        this.first = first;
        this.last = last;
    }

    public static Name of(String first, String last) {
        return new Name(first, last);
    }

    public String getFullName() {
        return last + ", " + first;
    }

    @Override
    public String toString() {
        return "Name{" +
                "first='" + first + '\'' +
                ", last='" + last + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Name name = (Name) o;

        return new EqualsBuilder().append(first, name.first).append(last, name.last).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(first).append(last).toHashCode();
    }
}
