package pl.zti.atlas.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Fish {

    @Id
    private String id;
    private String name;
    private String species;
    private String waterType;
    private String protectionPeriod;
    private String protectionLength;
    private String description;
    private String imageUrl;

    public Fish(String id, String name, String species, String waterType, String protectionPeriod,
                String protectionLength, String description, String imageUrl) {
        this.id = id;
        this.name = name;
        this.species = species;
        this.waterType = waterType;
        this.protectionPeriod = protectionPeriod;
        this.protectionLength = protectionLength;
        this.description = description;
        this.imageUrl = imageUrl;
    }

}
