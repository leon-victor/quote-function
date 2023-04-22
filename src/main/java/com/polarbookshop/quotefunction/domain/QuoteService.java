package com.polarbookshop.quotefunction.domain;

import java.util.List;
import java.util.Random;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.springframework.stereotype.Service;

@Service
public class QuoteService {

	private static final Random RANDOM = new Random();
	
	private static final List<Quote> QUOTES_LIST = List.of(
			new Quote("Content A", "Abigail", Genre.ADVENTURE),
			new Quote("Content B", "Beatrix", Genre.ADVENTURE),
			new Quote("Content C", "Casper", Genre.FANTASY),
			new Quote("Content D", "Dobby", Genre.FANTASY),
			new Quote("Content E", "Eileen", Genre.SCIENCE_FICTION),
			new Quote("Content F", "Flora", Genre.SCIENCE_FICTION)
	);

	public Flux<Quote> getAllQuotes() {
		return Flux.fromIterable(QUOTES_LIST);
	}

	public Mono<Quote> getRandomQuote() {
		return Mono.just(QUOTES_LIST.get(RANDOM.nextInt(QUOTES_LIST.size() - 1)));
	}

	public Mono<Quote> getRandomQuoteByGenre(Genre genre) {
		var quotesForGenre = QUOTES_LIST.stream()
				.filter(q -> q.genre().equals(genre))
				.toList();
		return Mono.just(quotesForGenre.get(RANDOM.nextInt(quotesForGenre.size() - 1)));
	}

}
