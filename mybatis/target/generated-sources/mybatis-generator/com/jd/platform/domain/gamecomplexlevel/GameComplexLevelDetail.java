package com.jd.platform.domain.gamecomplexlevel;

import java.util.Date;

public class GameComplexLevelDetail {
    private Long id;

    private Long gameId;

    private Long complexLevelId;

    private String awardsName;

    private Byte awardLevel;

    private Byte awardWins;

    private String awardInfo;

    private Byte flag;

    private String ext;

    private Date modified;

    private Date created;

    private Byte yn;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    public Long getComplexLevelId() {
        return complexLevelId;
    }

    public void setComplexLevelId(Long complexLevelId) {
        this.complexLevelId = complexLevelId;
    }

    public String getAwardsName() {
        return awardsName;
    }

    public void setAwardsName(String awardsName) {
        this.awardsName = awardsName == null ? null : awardsName.trim();
    }

    public Byte getAwardLevel() {
        return awardLevel;
    }

    public void setAwardLevel(Byte awardLevel) {
        this.awardLevel = awardLevel;
    }

    public Byte getAwardWins() {
        return awardWins;
    }

    public void setAwardWins(Byte awardWins) {
        this.awardWins = awardWins;
    }

    public String getAwardInfo() {
        return awardInfo;
    }

    public void setAwardInfo(String awardInfo) {
        this.awardInfo = awardInfo == null ? null : awardInfo.trim();
    }

    public Byte getFlag() {
        return flag;
    }

    public void setFlag(Byte flag) {
        this.flag = flag;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext == null ? null : ext.trim();
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Byte getYn() {
        return yn;
    }

    public void setYn(Byte yn) {
        this.yn = yn;
    }
}