package magic_api.magic.controllers;

import magic_api.magic.models.Deck;
import magic_api.magic.services.DeckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/api/decks")
public class DeckController {

    @Autowired
    private DeckService deckService;

    @PostMapping("/create")
    public Mono<ResponseEntity<Deck>> createDeck(@RequestParam String commanderName, @RequestBody List<String> cardNames) {
        return deckService.createDeck(commanderName, cardNames)
                .map(deck -> ResponseEntity.ok(deck))
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Deck>> getDeckById(@PathVariable String id) {
        return deckService.getDeckById(id)
                .map(deck -> ResponseEntity.ok(deck))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
