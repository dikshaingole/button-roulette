package com.roulette.names;

import java.util.List;

import org.springframework.stereotype.Component;

import com.roulette.BaseSpell;
import com.roulette.model.SpellResponse;

@Component
public class Pranik extends BaseSpell {

	private static final boolean RANDOM_MODE = Boolean.FALSE;

	private static final String SINGLE_EMOJI = "⚡";
	private static final String SINGLE_MESSAGE = "Java by day. DevOps by necessity.";

	private static final List<String[]> QUOTES = List.of(new String[] { "☕", "Coffee consumed. Feature unlocked." },
			new String[] { "🚀", "Code deployed. Confidence deployed with it." },
			new String[] { "💻", "Java compiled. Life is good." },
			new String[] { "🐳", "Containerized another problem." },
			new String[] { "⚙️", "Automated it instead of doing it twice." },
			new String[] { "🔥", "Production survived today's deployment." },
			new String[] { "🧠", "Solved a bug by staring at logs." },
			new String[] { "🔍", "Root cause found. It was configuration." },
			new String[] { "🎯", "Small change. Massive impact." },
			new String[] { "😎", "Pipeline passed on the first attempt." },
			new String[] { "🏆", "Build passed. Screenshot taken immediately." },
			new String[] { "⚡", "Java by day. DevOps by necessity." });

	@Override
	public SpellResponse cast() {

		if (!RANDOM_MODE) {
			return response(SINGLE_EMOJI, SINGLE_MESSAGE);
		}

		return randomResponse(QUOTES);
	}
}