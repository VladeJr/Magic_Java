package magic_api.magic.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "decks")
public class Deck {

    @Id
    private String id;
    private String commanderName;
    private List<Object> cards;

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCommanderName() {
        return commanderName;
    }

    public void setCommanderName(String commanderName) {
        this.commanderName = commanderName;
    }

    public List<Object> getCards() {
        return cards;
    }

    public void setCards(List<Object> cards) {
        this.cards = cards;
    }
}
