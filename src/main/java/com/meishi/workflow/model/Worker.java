package com.meishi.workflow.model;

import java.io.Serializable;

public class Worker extends Person implements Comparable<Worker>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5197560301731485371L;

	protected Integer rank = 3;
	
	protected WorkerStatus status = WorkerStatus.READY;

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	public WorkerStatus getStatus() {
		return status;
	}

	public void setStatus(WorkerStatus status) {
		this.status = status;
	}

	@Override
	public int compareTo(Worker o) {
		return this.rank - o.rank;
	}

	@Override
	public String toString() {
		return "Worker " + super.toString() + "[idCard=" + identity + ", rank=" + rank + ", status=" + status + "]";
	}
	
	
	
}
