package ni.jug.meetup.entity;

import javax.persistence.MappedSuperclass;
import java.util.Objects;

@MappedSuperclass
public abstract class SurrogateIdentifier {

    public abstract Integer getId();

    public abstract void setId(Integer id);

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null)
            return false;
        if (!getClass().equals(o.getClass()))
            return false;
        SurrogateIdentifier key = (SurrogateIdentifier) o;
        return Objects.equals(getId(), key.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
