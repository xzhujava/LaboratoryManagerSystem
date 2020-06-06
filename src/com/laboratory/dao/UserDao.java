package com.laboratory.dao;

import com.laboratory.entity.Menu;
import com.laboratory.entity.User;

import java.util.List;

/**
 * @author 张栓
 * @version 1.0
 * @date 2020/6/5 18:35
 */
public interface UserDao {
    /**
     * 查询所有学生
     * @param sql
     * @return
     * @throws Exception
     */
    List<User> findStudent(String sql) throws Exception;

    /**
     * 获取主键id值
     * @param sql
     * @return
     * @throws Exception
     */
    String getId(String sql) throws Exception;
}
