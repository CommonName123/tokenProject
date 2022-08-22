package jwtappdemo.json;

import java.util.UUID;

public interface ISerializableViaUuid {

    void setId(UUID id);

    UUID getId();
}
