package kr.or.ddit.utils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.WordUtils;

public class SampleDataMapperUtils {
	public static String snakeToCamel(String columnName) {
		String columName = "ADRS_NO";
		String propName = WordUtils.capitalizeFully(columName, '_');
		System.out.println(propName);
		propName = StringUtils.replace(propName, "_", "");
		System.out.println(propName);
		propName = WordUtils.uncapitalize(propName);
		System.out.println(propName);
		return propName;
	}

	public static <T> T recordToVO(ResultSet rs, Class<?> resultType) throws Exception {
		try {
			// AddressVO vo = new AddressVO();
			Object vo = resultType.newInstance(); // 이걸 사용하면 알아서 기본 생성자
			// 프레임워크 내부에서 사용하는 건 항상 기본 생성자 있어야 (?)
			ResultSetMetaData rsmd = rs.getMetaData();
			int colCnt = rsmd.getColumnCount();
			for (int idx = 1; idx <= colCnt; idx++) {
				String columnName = rsmd.getColumnName(idx);
				String propName = SampleDataMapperUtils.snakeToCamel(columnName);
				PropertyDescriptor pd = new PropertyDescriptor(propName, resultType);
				Method setter = pd.getWriteMethod();
				Field property = resultType.getDeclaredField(propName);
				if (property.getType().equals(int.class)) {
					setter.invoke(vo, rs.getInt(columnName));
				} else {
					setter.invoke(vo, rs.getString(columnName));
				}
			}
			return (T) vo;
		} catch (Exception e) {
			throw new SQLException(e);
		}
	}

}
