package com.roulette.names;

import java.util.List;
import org.springframework.stereotype.Component;
import com.roulette.BaseSpell;
import com.roulette.model.SpellResponse;

@Component
public class Diksha extends BaseSpell {

	private static final String SINGLE_EMOJI = "👧";
	private static final String SINGLE_MESSAGE = "I Love You Pranik!";

	@Override
	public SpellResponse cast() {
		return response(SINGLE_EMOJI, SINGLE_MESSAGE);

	}
}