package com.wyd.satokendemospringboot.demos.config.mybatis.type.handlers;

import com.wyd.satokendemospringboot.demos.constants.IEnum;
import com.wyd.satokendemospringboot.demos.constants.TestIEum;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.EnumOrdinalTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes(value = {TestIEum.class})
public class MyEnumTypeHandler<E extends Enum> extends BaseTypeHandler<E> {
    private final Class<E> type;
    private final E[] enums;

    public MyEnumTypeHandler(Class<E> type) {
        if (type == null) {
            throw new IllegalArgumentException("Type argument cannot be null");
        } else {
            this.type = type;
            this.enums = type.getEnumConstants();
            if (this.enums == null) {
                throw new IllegalArgumentException(type.getSimpleName() + " does not represent an enum type.");
            }
            if (!IEnum.class.isAssignableFrom(type)){
                throw new IllegalArgumentException(type.getSimpleName() + " does not implements IEnum.");
            }
        }
    }

    public void setNonNullParameter(PreparedStatement ps, int i, E parameter, JdbcType jdbcType) throws SQLException {
        // 将枚举类中的字典值（value）sql语句中
        IEnum iEnum = (IEnum) parameter;
        Integer ordinal = iEnum.getValue();
        ps.setInt(i, ordinal);
    }

    public E getNullableResult(ResultSet rs, String columnName) throws SQLException {
        int ordinal = rs.getInt(columnName);
        return ordinal == 0 && rs.wasNull() ? null : this.toOrdinalEnum(ordinal);
    }

    public E getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        int ordinal = rs.getInt(columnIndex);
        return ordinal == 0 && rs.wasNull() ? null : this.toOrdinalEnum(ordinal);
    }

    public E getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        int ordinal = cs.getInt(columnIndex);
        return ordinal == 0 && cs.wasNull() ? null : this.toOrdinalEnum(ordinal);
    }

    private E toOrdinalEnum(int ordinal) {
        for (E e : this.enums) {
            IEnum ie = (IEnum) e;
            if (ie.getValue() == ordinal) return e;
        }
        // 如果没找到字典值（value）对应的枚举类，则报错
        throw new IllegalArgumentException("Cannot convert " + ordinal + " to " + this.type.getSimpleName() + " by ordinal value.");
    }
}
