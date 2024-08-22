package magic_api.magic.services;

import magic_api.magic.models.Card;
import magic_api.magic.models.Deck;
import magic_api.magic.repositories.DeckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeckService {

    @Autowired
    private DeckRepository deckRepository;

    @Autowired
    private ScryfallClient scryfallClient;

    public Mono<Deck> createDeck(String commanderName, List<String> cardNames) {
        return scryfallClient.getCardByName(commanderName)
                .flatMap(commander -> {
                    List<Mono<Card>> cardMonos = cardNames.stream()
                            .map(scryfallClient::getCardByName)
                            .collect(Collectors.toList());

                    return Mono.zip(cardMonos, objects -> List.of(objects))
                            .flatMap(cards -> {
                                Deck deck = new Deck();
                                deck.setCommanderName(commander.getName());
                                deck.setCards(cards);
                                return deckRepository.save(deck);
                            });
                });
    }

    public Mono<Deck> getDeckById(String id) {
        return deckRepository.findById(id);
    }
}
