package com.xiaoxu.rabbitmq_demo.mybatis;

import com.xiaoxu.rabbitmq_demo.crypto.Encryptor;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.springframework.stereotype.Component;

@Component
@MappedTypes(String.class)
@MappedJdbcTypes(JdbcType.VARCHAR)
public class SensitiveStringTypeHandler extends BaseTypeHandler<String> {
    private final Encryptor encryptor;

    public SensitiveStringTypeHandler(Encryptor encryptor) {
        this.encryptor = encryptor;
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, String parameter, JdbcType jdbcType)
            throws SQLException {
        ps.setString(i, encryptor.encrypt(parameter));
    }

    @Override
    public String getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return encryptor.decrypt(rs.getString(columnName));
    }

    @Override
    public String getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return encryptor.decrypt(rs.getString(columnIndex));
    }

    @Override
    public String getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return encryptor.decrypt(cs.getString(columnIndex));
    }
}
