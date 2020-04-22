package org.frameworkset.elasticsearch.byquery;

import java.util.Date;
import java.util.List;


public class TraceExtraCriteria {
  private List<String> channelApplications;
  private Date startTime;
  private Date endTime;

  public List<String> getChannelApplications() {
    return channelApplications;
  }

  public void setChannelApplications(List<String> channelApplications) {

    this.channelApplications = channelApplications;
  }

  public Date getStartTime() {
    return startTime;
  }

  public void setStartTime(Date startTime) {
    this.startTime = startTime;
  }

  public Date getEndTime() {
    return endTime;
  }

  public void setEndTime(Date endTime) {
    this.endTime = endTime;
  }
}
