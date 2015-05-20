package com.meishi.model;

public enum Rank {
	Rank1(1), Rank2(2), Rank3(3), Rank4(4), Rank5(5);

	private Integer rankValue;

	Rank(Integer rankValue) {
		this.rankValue = rankValue;
	}

}
