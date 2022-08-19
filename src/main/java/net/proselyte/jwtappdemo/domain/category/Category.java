package net.proselyte.jwtappdemo.domain.category;

import lombok.Data;
import net.proselyte.jwtappdemo.json.ISerializableViaUuid;
import net.proselyte.jwtappdemo.json.JsonEntityViaUuidDeserializer;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import java.util.UUID;

/**
 * Категория
 * Created by CommonName123 on 18.08.2022
 */
@Entity
@Data
@Table(name = "category")
public class Category implements ISerializableViaUuid {
    /**
     * Идентификатор
     */
    @Id
    @Column(columnDefinition = "uuid")
    private UUID id = UUID.randomUUID();

    /**
     * Название
     */
    @Column(columnDefinition = "varchar")
    private String name;

    /**
     * Описание
     */
    @Column(columnDefinition = "varchar")
    private String description;


    public static class UuidDesirializer extends JsonEntityViaUuidDeserializer<Category> {}

    public Category(){}

    public Category(UUID id) {
        this.id = id;
    }
}
