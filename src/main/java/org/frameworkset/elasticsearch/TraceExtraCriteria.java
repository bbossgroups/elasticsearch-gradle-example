package org.frameworkset.elasticsearch;

import java.util.Date;
import java.util.List;

/**
 * Created by huxin on 2017/5/13.
 */
public class TraceExtraCriteria  {
    /**
     * 按条件查询时，是否是精确查找
     */
    private boolean exactSearch = false;

    private boolean traceScore = false;

    public boolean isTraceScore() {
        return traceScore;
    }

    public void setTraceScore(boolean traceScore) {
        this.traceScore = traceScore;
    }

    /**
     * 查询字段
     */
    private String[] searchFields;

    //查询条件


    private String queryCondition;
    //查询状态
    private String queryStatus;

    // 排序前几名
    private int topN;
    // 开始时间
    private long startTime;
    // 结束时间
    private long endTime;

    // 开始时间
    private Date startDate;
    // 结束时间
    private Date endDate;
    // 排序集合
    private List<String> orderByList ;
    // 限制集合最小值
    private int minLimit;
    // 限制集合最大值
    private int maxLimit;
    // 排序方式
    private String sortType;
    // 应用名称
    private String application;


    private Long lastStartTime;
    private Long lastElapsed;
    private Integer lastScore;
    private String lastId;
    // rpc
    private String rpc;
    // 代理
    private String agent;
    // 参数
    private String param;
    // SQL
    private String sql;
    // RPC状坊
    private String state;
    /**
     * 每页展示记录条数
     */
    private int pageSize = 100;
    private String orderBy;
    private String queryAction;

    public Integer getLastScore() {
        return lastScore;
    }

    public void setLastScore(Integer lastScore) {
        this.lastScore = lastScore;
    }

    public String getQueryCondition() {
        return queryCondition;
    }

    public void setQueryCondition(String queryCondition) {
        this.queryCondition = queryCondition;
    }

    public String getQueryStatus() {
        return queryStatus;
    }

    public void setQueryStatus(String queryStatus) {
        this.queryStatus = queryStatus;
    }

    public Long getLastStartTime() {
        return lastStartTime;
    }

    public void setLastStartTime(Long lastStartTime) {
        this.lastStartTime = lastStartTime;
    }
    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
    public String getRpc() {
        return rpc;
    }

    public boolean isExactSearch() {
        return exactSearch;
    }

    public void setExactSearch(boolean exactSearch) {
        this.exactSearch = exactSearch;
    }

    public String[] getSearchFields() {
        return searchFields;
    }

    public void setSearchFields(String[] searchFields) {
        this.searchFields = searchFields;
    }

    public void setRpc(String rpc) {
        this.rpc = rpc;
    }

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    public int getTopN() {
        return topN;
    }

    public void setTopN(int topN) {
        this.topN = topN;
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public List<String> getOrderByList() {
        return orderByList;
    }

    public void setOrderByList(List<String> orderByList) {
        this.orderByList = orderByList;
    }

    public int getMinLimit() {
        return minLimit;
    }

    public void setMinLimit(int minLimit) {
        this.minLimit = minLimit;
    }

    public int getMaxLimit() {
        return maxLimit;
    }

    public void setMaxLimit(int maxLimit) {
        this.maxLimit = maxLimit;
    }

    public String getSortType() {
        return sortType;
    }

    public void setSortType(String sortType) {
        this.sortType = sortType;
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getLastId() {
        return lastId;
    }

    public void setLastId(String lastId) {
        this.lastId = lastId;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public Long getLastElapsed() {
        return lastElapsed;
    }

    public void setLastElapsed(Long lastElapsed) {
        this.lastElapsed = lastElapsed;
    }

    public String getQueryAction() {
        return queryAction;
    }

    public void setQueryAction(String queryAction) {
        this.queryAction = queryAction;
    }
}
