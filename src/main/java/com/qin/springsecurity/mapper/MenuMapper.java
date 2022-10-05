package com.qin.springsecurity.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qin.springsecurity.pojo.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MenuMapper extends BaseMapper<Menu> {
    List<String> selectPermsByUserId(@Param("id") Long id);
}
