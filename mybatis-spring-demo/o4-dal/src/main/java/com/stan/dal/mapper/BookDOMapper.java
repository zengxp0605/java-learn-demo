package com.stan.dal.mapper;

public interface BookDOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BookDO record);

    int insertSelective(BookDO record);

    BookDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BookDO record);

    int updateByPrimaryKey(BookDO record);
}