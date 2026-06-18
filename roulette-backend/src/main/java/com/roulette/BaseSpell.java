package com.roulette;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import com.roulette.model.SpellResponse;

public abstract class BaseSpell implements Spell {

	protected SpellResponse response(String emoji, String message) {

		return new SpellResponse(getClass().getSimpleName(), emoji, message);
	}

	protected SpellResponse randomResponse(List<String[]> quotes) {

		String[] quote = quotes.get(ThreadLocalRandom.current().nextInt(quotes.size()));

		return response(quote[0], quote[1]);
	}
}