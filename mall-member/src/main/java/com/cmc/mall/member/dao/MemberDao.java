package com.cmc.mall.member.dao;

import com.cmc.mall.member.entity.MemberEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会员
 * 
 * @author chengmingchao
 * @email 502130705@qq.com
 * @date 2020-08-08 14:18:49
 */
@Mapper
public interface MemberDao extends BaseMapper<MemberEntity> {
	
}
