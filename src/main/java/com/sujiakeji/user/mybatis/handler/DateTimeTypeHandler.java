package com.sujiakeji.user.mybatis.handler;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import java.sql.*;

@MappedTypes(DateTime.class)
public class DateTimeTypeHandler implements TypeHandler {

    public void setParameter(PreparedStatement ps, int i, Object parameter, JdbcType jdbcType) throws SQLException {
        DateTime dt = (DateTime) parameter;
        if (dt != null) {
            ps.setTimestamp(i, new Timestamp(dt.getMillis()));
        } else {
            ps.setTimestamp(i, null);
        }
    }

    public Object getResult(ResultSet rs, String columnName) throws SQLException {
        Timestamp ts = rs.getTimestamp(columnName);
        if (ts != null) {
            return new DateTime(ts.getTime(), DateTimeZone.UTC);
        } else {
            return null;
        }
    }

    public Object getResult(ResultSet rs, int columnIndex) throws SQLException {
        Timestamp ts = rs.getTimestamp(columnIndex);
        if (ts != null) {
            return new DateTime(ts.getTime(), DateTimeZone.UTC);
        } else {
            return null;
        }
    }

    public Object getResult(CallableStatement cs, int columnIndex) throws SQLException {
        Timestamp ts = cs.getTimestamp(columnIndex);
        if (ts != null) {
            return new DateTime(ts.getTime(), DateTimeZone.UTC);
        } else {
            return null;
        }
    }

}
