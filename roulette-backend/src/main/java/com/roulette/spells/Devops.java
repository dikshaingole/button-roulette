package com.roulette.spells;

import java.util.List;

import org.springframework.stereotype.Component;

import com.roulette.BaseSpell;
import com.roulette.model.SpellResponse;

@Component
public class Devops extends BaseSpell {

	private static final boolean RANDOM_MODE = Boolean.FALSE;

	private static final String SINGLE_EMOJI = "🚀";
	private static final String SINGLE_MESSAGE = "Automate everything. Sleep peacefully.";

	private static final List<String[]> QUOTES = List.of(new String[] { "🚀", "Shipped to prod. Pray." },
			new String[] { "✅", "Pipeline green. Don't touch it." }, new String[] { "🐳", "Docker image: 4GB. Nice." },
			new String[] { "🔥", "It works in staging. Staging lies." },
			new String[] { "☸️", "12 K8s nodes. App uses 0.3 of one." }, new String[] { "💀", "Merged. Site is down." },
			new String[] { "📉", "Zero downtime deploy. Downtime: 4 mins." },
			new String[] { "🕵️", "Secret committed in 2021. Still there." },
			new String[] { "🤡", "DO NOT RUN ON FRIDAYS. Running on Friday." },
			new String[] { "🔄", "Rollback completed. Nobody noticed." });

	@Override
	public SpellResponse cast() {

		if (!RANDOM_MODE) {
			return response(SINGLE_EMOJI, SINGLE_MESSAGE);
		}

		return randomResponse(QUOTES);
	}
}