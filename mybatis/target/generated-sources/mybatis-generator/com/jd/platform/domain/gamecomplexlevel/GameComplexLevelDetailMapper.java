package com.jd.platform.domain.gamecomplexlevel;

import com.jd.platform.domain.gamecomplexlevel.GameComplexLevelDetail;
import com.jd.platform.domain.gamecomplexlevel.GameComplexLevelDetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GameComplexLevelDetailMapper {
    int countByExample(GameComplexLevelDetailExample example);

    int deleteByExample(GameComplexLevelDetailExample example);

    int deleteByPrimaryKey(Long id);

    int insert(GameComplexLevelDetail record);

    int insertSelective(GameComplexLevelDetail record);

    List<GameComplexLevelDetail> selectByExample(GameComplexLevelDetailExample example);

    GameComplexLevelDetail selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") GameComplexLevelDetail record, @Param("example") GameComplexLevelDetailExample example);

    int updateByExample(@Param("record") GameComplexLevelDetail record, @Param("example") GameComplexLevelDetailExample example);

    int updateByPrimaryKeySelective(GameComplexLevelDetail record);

    int updateByPrimaryKey(GameComplexLevelDetail record);
}