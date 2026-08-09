package com.roulette.spells;

import java.util.List;

import org.springframework.stereotype.Component;

import com.roulette.BaseSpell;
import com.roulette.model.SpellResponse;

@Component
public class Java extends BaseSpell {

	private static final boolean RANDOM_MODE = Boolean.FALSE;

	private static final String SINGLE_EMOJI = "☕";
	private static final String SINGLE_MESSAGE = "Compiled successfully. Ship it.";

	private static final List<String[]> QUOTES = List.of(

			new String[] { "☕", "Works on my machine. JVM approved." },
			new String[] { "💥", "NullPointerException has entered the chat." },
			new String[] { "🚀", "Spring Boot started in 3.2 seconds." },
			new String[] { "📦", "Maven downloading dependency #847..." },
			new String[] { "🧠", "Garbage Collector cleaned up your mistakes." },
			new String[] { "🔒", "Immutable objects. Inner peace achieved." },
			new String[] { "⚠️", "Unchecked warning ignored successfully." },
			new String[] { "😅", "Just one more abstraction layer." },
			new String[] { "🎯", "Write once. Debug everywhere." },
			new String[] { "🍃", "Spring Boot found 127 beans and your bug." },
			new String[] { "🔍", "@Autowired succeeded. Mystery continues." },
			new String[] { "🌐", "Port 8080 is already in use." },
			new String[] { "📋", "Application started. Logs look suspiciously clean." },
			new String[] { "🔥", "Fixed one bug. Introduced three features." });

	@Override
	public SpellResponse cast() {

		if (!RANDOM_MODE) {
			return response(SINGLE_EMOJI, SINGLE_MESSAGE);
		}

		return randomResponse(QUOTES);
	}
}