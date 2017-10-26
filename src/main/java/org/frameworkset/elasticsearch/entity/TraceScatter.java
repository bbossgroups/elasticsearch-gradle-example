package org.frameworkset.elasticsearch.entity;

import org.frameworkset.elasticsearch.entity.ESId;

public class TraceScatter extends ESId{
	private Long startTime;
	private Integer err;
	private Integer elapsed;


	public Long getStartTime() {
		return startTime;
	}

	public void setStartTime(Long startTime) {
		this.startTime = startTime;
	}

	public Integer getErr() {
		return err;
	}

	public void setErr(Integer err) {
		this.err = err;
	}

	public Integer getElapsed() {
		return elapsed;
	}

	public void setElapsed(Integer elapsed) {
		this.elapsed = elapsed;
	}
}
