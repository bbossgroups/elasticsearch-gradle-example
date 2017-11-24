package org.frameworkset.elasticsearch.entity;


import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 数据Json返回封装类
 * @author xiyt
 *
 * @param <T>
 */
public class JsonDataResult<T> implements Serializable {

	// 开始时间
	private long startTime;
	// 结束时间
	private long endTime;

	// 返回数据信息
	private T data;

	private List<Map<String, Object>> dateHistogram;

	/**
	 * 每页展示记录条数
	 */
	private int pageSize = 100;

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public long getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(long totalSize) {
		this.totalSize = totalSize;
	}

	private long totalSize;

	public JsonDataResult() {
		super();
	}

	public JsonDataResult(T t) {
		this();
		this.data = t;
	}

	public T getData() {
		return data;
	}

	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	public long getEndTime() {
		return endTime;
	}

	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}

	public void setData(T data) {

		this.data = data;
	}

	public List<Map<String, Object>> getDateHistogram() {
		return dateHistogram;
	}

	public void setDateHistogram(List<Map<String, Object>> dateHistogram) {
		this.dateHistogram = dateHistogram;
	}
}
