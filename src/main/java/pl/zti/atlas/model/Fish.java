package pl.zti.atlas.model;

import lombok.Setter;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

@ToString
public class Fish {

    @Id
    @Getter @Setter
    private String id;

    @Getter @Setter
    private String name;

    public Fish(String id, String name) {
        this.id = id;
        this.name = name;
    }

}
