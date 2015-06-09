package com.meishi.model;

import java.io.Serializable;

public class Worker extends Person implements Comparable<Worker>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5197560301731485371L;

	protected Rank rank = Rank.Rank3;

	protected WorkerStatus status = WorkerStatus.READY;

	public WorkerStatus getStatus() {
		return status;
	}

	public void setStatus(WorkerStatus status) {
		this.status = status;
	}


	public Rank getRank() {
		return rank;
	}

	public void setRank(Rank rank) {
		this.rank = rank;
	}

	public int compareTo(Worker other) {
		return this.rank.ordinal() - other.rank.ordinal();
	}

}
