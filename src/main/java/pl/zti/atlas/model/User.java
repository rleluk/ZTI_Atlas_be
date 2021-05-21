package pl.zti.atlas.model;

import lombok.Setter;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

@ToString
public class User {

    @Id
    @Getter @Setter
    private String id;

    @Getter @Setter
    private String name;

    public User(String id, String name) {
        this.id = id;
        this.name = name;
    }

}
