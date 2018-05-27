package org.frameworkset.elasticsearch.bulk;


import com.frameworkset.orm.annotation.*;

/**
 * Created by CYC on 18-05-26.
 */
public class UpdateParams {

    @ESId(persistent = false)
    protected String id;

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    @ESParentId
    protected String parentId;
    protected String type;
    protected String index;
    @ESVersion
    protected int version;
    @ESVersionType
    protected String versionType;
    @ESRetryOnConflict
    protected int retryOnConflict;
    @ESRouting
    protected String routing;
    @ESDocAsUpsert
    protected boolean docAsUpsert;
    @ESSource
    protected boolean returnSource;

    public int getRetryOnConflict() {
        return retryOnConflict;
    }

    public void setRetryOnConflict(int retryOnConflict) {
        this.retryOnConflict = retryOnConflict;
    }

    public String getRouting() {
        return routing;
    }

    public void setRouting(String routing) {
        this.routing = routing;
    }

    public boolean isDocAsUpsert() {
        return docAsUpsert;
    }

    public void setDocAsUpsert(boolean docAsUpsert) {
        this.docAsUpsert = docAsUpsert;
    }

    public boolean isReturnSource() {
        return returnSource;
    }

    public void setReturnSource(boolean returnSource) {
        this.returnSource = returnSource;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getVersionType() {
        return versionType;
    }

    public void setVersionType(String versionType) {
        this.versionType = versionType;
    }


}
