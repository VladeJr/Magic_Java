package magic_api.magic.services;

import magic_api.magic.models.Card;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;

@Service
public class ScryfallClient {

	private final WebClient webClient = WebClient.create("https://api.scryfall.com");

	public Mono<Card> getCardByName(String name) {
		return webClient.get()
				.uri("/cards/named?exact=" + name)
				.retrieve()
				.bodyToMono(Card.class);
	}

	public Flux<Card> getCardsByColors(String colors) {
		return webClient.get()
				.uri("/cards/search?q=c%3A" + colors + "&unique=cards")
				.retrieve()
				.bodyToFlux(Card.class);
	}
}
