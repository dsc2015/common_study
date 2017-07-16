package shardstudytype1;

import java.util.Date;

public class TreasureOrderExtensionRouter {

    private Long id;

    private Long beginIssueId;

    private Long endIssueId;

    private String dbTableName;

    private Date created;

    private Date modified;

    private Integer yn;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBeginIssueId() {
        return beginIssueId;
    }

    public void setBeginIssueId(Long beginIssueId) {
        this.beginIssueId = beginIssueId;
    }

    public Long getEndIssueId() {
        return endIssueId;
    }

    public void setEndIssueId(Long endIssueId) {
        this.endIssueId = endIssueId;
    }

    public String getDbTableName() {
        return dbTableName;
    }

    public void setDbTableName(String dbTableName) {
        this.dbTableName = dbTableName == null ? null : dbTableName.trim();
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    public Integer getYn() {
        return yn;
    }

    public void setYn(Integer yn) {
        this.yn = yn;
    }
}