package org.frameworkset.elasticsearch.entity;

import org.frameworkset.elasticsearch.entity.ESBaseData;

import java.util.Date;

public class Traces extends ESBaseData{
    private Long traceId;



    private Date collectorAcceptTime;
    private Date starttimeDate;
    private String agentId;
    private String applicationName;
    private Long agentStarttime;
    private String transactionIdAgentId;
    private Long transactionIdAgentStarttime;
    private Long transactionIdTransactionSequence;
    private Long spanId;
    private Long parentSpanId;
    private Long startTime;
    private Integer elapsed;

    private String rpc;
    private Integer serviceType;
    private String endPoint;
    private String remoteAddr;

    private Integer flag;

    private Integer err;
    private String parentApplicationName;
    private Integer parentApplicationType;
    private String acceptorHost;
    private Integer apiId;
    private String exceptionInfo;
    private Integer applicationServiceType;
    private Integer loggingTransactionInfo;

    private String params;

    // 平均耗时
    private int avgElapsed;

    // 请求次数
    private int count;

    // 服务器类型名称
    private String serviceTypeName;

    public String getServiceTypeName() {
        return serviceTypeName;
    }

    public void setServiceTypeName(String serviceTypeName) {
        this.serviceTypeName = serviceTypeName;
    }

    public int getAvgElapsed() {
        return avgElapsed;
    }

    public void setAvgElapsed(int avgElapsed) {
        this.avgElapsed = avgElapsed;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Long getTraceId() {
        return traceId;
    }

    public void setTraceId(Long traceId) {
        this.traceId = traceId;
    }

    public Date getStarttimeDate() {
        return starttimeDate;
    }

    public void setStarttimeDate(Date starttimeDate) {
        this.starttimeDate = starttimeDate;
    }

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId == null ? null : agentId.trim();
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName == null ? null : applicationName.trim();
    }

    public Long getAgentStarttime() {
        return agentStarttime;
    }

    public void setAgentStarttime(Long agentStarttime) {
        this.agentStarttime = agentStarttime;
    }

    public String getTransactionIdAgentId() {
        return transactionIdAgentId;
    }

    public void setTransactionIdAgentId(String transactionIdAgentId) {
        this.transactionIdAgentId = transactionIdAgentId == null ? null : transactionIdAgentId.trim();
    }

    public Long getTransactionIdAgentStarttime() {
        return transactionIdAgentStarttime;
    }

    public void setTransactionIdAgentStarttime(Long transactionIdAgentStarttime) {
        this.transactionIdAgentStarttime = transactionIdAgentStarttime;
    }

    public Long getTransactionIdTransactionSequence() {
        return transactionIdTransactionSequence;
    }

    public void setTransactionIdTransactionSequence(Long transactionIdTransactionSequence) {
        this.transactionIdTransactionSequence = transactionIdTransactionSequence;
    }

    public Long getSpanId() {
        return spanId;
    }

    public void setSpanId(Long spanId) {
        this.spanId = spanId;
    }

    public Long getParentSpanId() {
        return parentSpanId;
    }

    public void setParentSpanId(Long parentSpanId) {
        this.parentSpanId = parentSpanId;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Integer getElapsed() {
        return elapsed;
    }

    public void setElapsed(Integer elapsed) {
        this.elapsed = elapsed;
    }

    public String getRpc() {
        return rpc;
    }

    public void setRpc(String rpc) {
        this.rpc = rpc == null ? null : rpc.trim();
    }

    public Integer getServiceType() {
        return serviceType;
    }

    public void setServiceType(Integer serviceType) {
        this.serviceType = serviceType;
    }

    public String getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint == null ? null : endPoint.trim();
    }

    public String getRemoteAddr() {
        return remoteAddr;
    }

    public void setRemoteAddr(String remoteAddr) {
        this.remoteAddr = remoteAddr == null ? null : remoteAddr.trim();
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public Integer getErr() {
        return err;
    }

    public void setErr(Integer err) {
        this.err = err;
    }

    public String getParentApplicationName() {
        return parentApplicationName;
    }

    public void setParentApplicationName(String parentApplicationName) {
        this.parentApplicationName = parentApplicationName == null ? null : parentApplicationName.trim();
    }

    public Integer getParentApplicationType() {
        return parentApplicationType;
    }

    public void setParentApplicationType(Integer parentApplicationType) {
        this.parentApplicationType = parentApplicationType;
    }

    public String getAcceptorHost() {
        return acceptorHost;
    }

    public void setAcceptorHost(String acceptorHost) {
        this.acceptorHost = acceptorHost == null ? null : acceptorHost.trim();
    }

    public Integer getApiId() {
        return apiId;
    }

    public void setApiId(Integer apiId) {
        this.apiId = apiId;
    }

    public String getExceptionInfo() {
        return exceptionInfo;
    }

    public void setExceptionInfo(String exceptionInfo) {
        this.exceptionInfo = exceptionInfo == null ? null : exceptionInfo.trim();
    }

    public Integer getApplicationServiceType() {
        return applicationServiceType;
    }

    public void setApplicationServiceType(Integer applicationServiceType) {
        this.applicationServiceType = applicationServiceType;
    }

    public Integer getLoggingTransactionInfo() {
        return loggingTransactionInfo;
    }

    public void setLoggingTransactionInfo(Integer loggingTransactionInfo) {
        this.loggingTransactionInfo = loggingTransactionInfo;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params == null ? null : params.trim();
    }
    public Date getCollectorAcceptTime() {
        return collectorAcceptTime;
    }

    public void setCollectorAcceptTime(Date collectorAcceptTime) {
        this.collectorAcceptTime = collectorAcceptTime;
    }
}