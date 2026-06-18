package com.roulette.service;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.stereotype.Service;

import com.roulette.Spell;
import com.roulette.model.SpellResponse;

@Service
public class SpellEngine {

	private final List<Spell> spells;

	public SpellEngine(List<Spell> spells) {
		this.spells = spells;
	}

	public SpellResponse castSpell() {

		Spell spell = spells.get(ThreadLocalRandom.current().nextInt(spells.size()));

		return spell.cast();
	}
}