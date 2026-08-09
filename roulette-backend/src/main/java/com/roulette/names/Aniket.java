package com.roulette.names;

import org.springframework.stereotype.Component;

import com.roulette.BaseSpell;
import com.roulette.model.SpellResponse;

@Component
public class Aniket extends BaseSpell {

	private static final String SINGLE_EMOJI = "😎";
	private static final String SINGLE_MESSAGE = "Eat 5 Star Do Nothing.";

	@Override
	public SpellResponse cast() {
		return response(SINGLE_EMOJI, SINGLE_MESSAGE);

	}
}