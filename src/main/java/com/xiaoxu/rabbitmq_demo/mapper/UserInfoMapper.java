package com.xiaoxu.rabbitmq_demo.mapper;

import com.xiaoxu.rabbitmq_demo.model.UserInfo;
import com.xiaoxu.rabbitmq_demo.mybatis.SensitiveStringTypeHandler;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserInfoMapper {
    @Insert("""
            INSERT INTO user_info(name, id_number, phone, address)
            VALUES(#{name,typeHandler=com.xiaoxu.rabbitmq_demo.mybatis.SensitiveStringTypeHandler},
                   #{idNumber,typeHandler=com.xiaoxu.rabbitmq_demo.mybatis.SensitiveStringTypeHandler},
                   #{phone,typeHandler=com.xiaoxu.rabbitmq_demo.mybatis.SensitiveStringTypeHandler},
                   #{address,typeHandler=com.xiaoxu.rabbitmq_demo.mybatis.SensitiveStringTypeHandler})
            """)
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(UserInfo userInfo);

    @Select("""
            SELECT id, name, id_number, phone, address
            FROM user_info
            WHERE id = #{id}
            """)
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "name", property = "name", typeHandler = SensitiveStringTypeHandler.class),
            @Result(column = "id_number", property = "idNumber", typeHandler = SensitiveStringTypeHandler.class),
            @Result(column = "phone", property = "phone", typeHandler = SensitiveStringTypeHandler.class),
            @Result(column = "address", property = "address", typeHandler = SensitiveStringTypeHandler.class)
    })
    UserInfo findById(Long id);
}
