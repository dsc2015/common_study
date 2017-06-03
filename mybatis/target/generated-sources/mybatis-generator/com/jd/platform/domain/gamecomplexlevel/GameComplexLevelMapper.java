package com.jd.platform.domain.gamecomplexlevel;

import com.jd.platform.domain.gamecomplexlevel.GameComplexLevel;
import com.jd.platform.domain.gamecomplexlevel.GameComplexLevelExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GameComplexLevelMapper {
    int countByExample(GameComplexLevelExample example);

    int deleteByExample(GameComplexLevelExample example);

    int deleteByPrimaryKey(Long id);

    int insert(GameComplexLevel record);

    int insertSelective(GameComplexLevel record);

    List<GameComplexLevel> selectByExample(GameComplexLevelExample example);

    GameComplexLevel selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") GameComplexLevel record, @Param("example") GameComplexLevelExample example);

    int updateByExample(@Param("record") GameComplexLevel record, @Param("example") GameComplexLevelExample example);

    int updateByPrimaryKeySelective(GameComplexLevel record);

    int updateByPrimaryKey(GameComplexLevel record);
}