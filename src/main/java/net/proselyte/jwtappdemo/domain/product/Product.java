package net.proselyte.jwtappdemo.domain.product;

import lombok.Data;
import net.proselyte.jwtappdemo.domain.category.Category;
import net.proselyte.jwtappdemo.json.JsonEntityViaUuidSerializer;

import javax.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Продукт
 * Created by CommonName123 on 18.08.2022
 */
@Entity
@Data
@Table(name = "product")
public class Product {

    public Product() {
    }

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

    /**
     * Цена
     */
    private Double price;

    /**
     * Фото
     */
    private byte[] photo;

    /**
     * Категория
     */
    @ManyToOne
    @JoinColumn(columnDefinition = "uuid")
    @JsonSerialize(using = JsonEntityViaUuidSerializer.class)
    @JsonDeserialize(using = Category.UuidDesirializer.class)
    private Category category;

    /**
     * Дата добавления в каталог
     */
    private LocalDateTime date;

    /**
     * Статус(активен/не активен)
     */
    private Boolean status;


}
