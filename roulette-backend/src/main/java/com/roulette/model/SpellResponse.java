package com.roulette.model;

public class SpellResponse {

	private String spellName;
	private String emoji;
	private String result;

	public SpellResponse() {
	}

	public SpellResponse(String spellName, String emoji, String result) {
		this.spellName = spellName;
		this.emoji = emoji;
		this.result = result;
	}

	public String getSpellName() {
		return spellName;
	}

	public void setSpellName(String spellName) {
		this.spellName = spellName;
	}

	public String getEmoji() {
		return emoji;
	}

	public void setEmoji(String emoji) {
		this.emoji = emoji;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
}