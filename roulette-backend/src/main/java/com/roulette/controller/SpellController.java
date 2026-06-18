package com.roulette.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.roulette.model.SpellResponse;
import com.roulette.service.SpellEngine;

@RestController
public class SpellController {

	private final SpellEngine spellEngine;

	public SpellController(SpellEngine spellEngine) {
		this.spellEngine = spellEngine;
	}

	@GetMapping("/api/cast")
	public SpellResponse castSpell() {
		return spellEngine.castSpell();
	}
}