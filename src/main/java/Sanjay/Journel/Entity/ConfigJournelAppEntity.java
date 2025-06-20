package Sanjay.Journel.Entity;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.annotation.Collation;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection = "config_journal_app")
@Data
@NoArgsConstructor
public class ConfigJournelAppEntity {
    private String key;
    private String value;
}
