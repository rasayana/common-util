package app.config.domain;

import java.io.Serializable;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class Config implements Serializable {
	private static final long serialVersionUID = 1L;
    @Id private long id;
    private String name;
    private String value;
    private String description;
}
