package org.frameworkset.elasticsearch.entity;

import org.frameworkset.elasticsearch.entity.LongAggHit;

public class TraceLongAggHit extends LongAggHit {
	private long success;
	private long failed;

	public long getFailed() {
		return failed;
	}

	public void setFailed(long failed) {
		this.failed = failed;
	}

	public long getSuccess() {
		return success;
	}

	public void setSuccess(long success) {
		this.success = success;
	}
}
