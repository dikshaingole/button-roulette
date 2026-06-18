package com.roulette.spells;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.stereotype.Component;

import com.roulette.BaseSpell;
import com.roulette.model.SpellResponse;

@Component
public class Jenkins extends BaseSpell {

	private static final boolean RANDOM_MODE = Boolean.FALSE;

	private static final String SINGLE_EMOJI = "🤖";
	private static final String SINGLE_MESSAGE = "Build. Test. Deploy. Repeat.";

	private static final List<String[]> QUOTES = List.of(new String[] { "🤖", "Build started. Coffee deployed." },
			new String[] { "✅", "Pipeline passed. Nobody knows why." },
			new String[] { "❌", "Build failed. Check line 1,847." },
			new String[] { "🚀", "Deploying to production. Good luck." },
			new String[] { "⏳", "Waiting for agent... still waiting..." },
			new String[] { "🔄", "Restarted Jenkins. Fixed everything." },
			new String[] { "🐞", "Tests failed after working yesterday." },
			new String[] { "🔥", "One small commit. One giant outage." },
			new String[] { "📦", "Artifact archived for future confusion." },
			new String[] { "💀", "Build #666 completed unsuccessfully." },
			new String[] { "☕", "Pipeline running. Developer refreshing every 5 seconds." },
			new String[] { "📊", "100% build success. Suspicious." },
			new String[] { "⚙️", "One Jenkinsfile. Infinite possibilities." },
			new String[] { "🚨", "Production deployment approved by accident." },
			new String[] { "🎯", "Build succeeded on the second restart." });

	@Override
	public SpellResponse cast() {

		if (!RANDOM_MODE) {
			return response(SINGLE_EMOJI, SINGLE_MESSAGE);
		}

		return randomResponse(QUOTES);
	}
}