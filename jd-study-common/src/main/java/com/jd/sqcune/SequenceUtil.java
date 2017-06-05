package com.jd.sqcune;

import java.util.HashMap;
import java.util.Map;

/**指定了初始值和步长的序列增长器
 * @author dushuangcheng
 * @create 2017-06-05 11:01
 */
public class SequenceUtil {
    private Sequence defaultSequence;
    private Map<String,Sequence> sequenceMap=new HashMap<>();

    public Sequence getDefaultSequence() {
        return defaultSequence;
    }

    public void setDefaultSequence(Sequence defaultSequence) {
        this.defaultSequence = defaultSequence;
    }

    public Map<String, Sequence> getSequenceMap() {
        return sequenceMap;
    }

    public void setSequenceMap(Map<String, Sequence> sequenceMap) {
        this.sequenceMap = sequenceMap;
    }

    public long getSequence(String sequenceName){
        Sequence sequence=null ;
        if(sequenceMap!=null){
            sequence=sequenceMap.get(sequenceName);
        }
        if(sequence==null){
            return defaultSequence.get(sequenceName);
        }
        return sequence.get(sequenceName);
    }
}
