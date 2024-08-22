package magic_api.magic.repositories;

import magic_api.magic.models.Deck;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface DeckRepository extends ReactiveMongoRepository<Deck, String> {
}
