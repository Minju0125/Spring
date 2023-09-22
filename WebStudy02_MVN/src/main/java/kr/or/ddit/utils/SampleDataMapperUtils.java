package kr.or.ddit.utils;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.WordUtils;

import kr.or.ddit.vo.AddressVO;

public class SampleDataMapperUtils {
	public static String snakeToCamel(String columName) {
		String propName = WordUtils.capitalizeFully(columName, '_');
//		System.out.println(propName);
		propName = StringUtils.replace(propName, "_", "");
//		System.out.println(propName);
		propName = WordUtils.uncapitalize(propName);
//		System.out.println(propName);
		return propName;
	}

	public static <T> T recordToVO(ResultSet rs, Class<T> resultType) throws SQLException {
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

//	StringBuffer sql = new StringBuffer();
//	
//	sql.append("INSERT INTO ADDRESSBOOK (                              ");
//	sql.append("	    ADRS_NO, MEM_ID, ADRS_NAME, ADRS_HP, ADRS_ADD  ");
//	sql.append("	) VALUES (                                         ");
//	sql.append("	   #{adrsNo},                                             ");
//	sql.append("	    #{memId},                                             ");
//	sql.append("	   #{adrsName},                                             ");
//	sql.append("	    #{adrsHp},                                             ");
//	sql.append("	    #{adrsAdd})     

	public static PreparedStatement generatePreparedstatement(Connection conn, String beforeSql, Object paramVO)
			throws SQLException {
		Pattern pattern = Pattern.compile("#\\{(\\w+)\\}"); // 영문자와 숫자를 동시에 잡을때 \w //영문ㅁ자나 숫자가 한글자 이상 반복되는 것들만 그룹으로 묶은 것
		Matcher matcher = pattern.matcher(beforeSql); // 정규식에 맞는 문자열 찾기
		List<String> propNames = new ArrayList<String>();
		StringBuffer afterSql = new StringBuffer();
		while (matcher.find()) {
			propNames.add(matcher.group(1));
			matcher.appendReplacement(afterSql, "?");
		}
		matcher.appendTail(afterSql);
		PreparedStatement pstmt = conn.prepareStatement(afterSql.toString());

		Class<?> paramType = paramVO.getClass();
		try {
			for (int i = 0; i < propNames.size(); i++) {
				String propName = propNames.get(i);
				PropertyDescriptor pd = new PropertyDescriptor(propName, paramType);
				Method getter = pd.getReadMethod();
				Object propValue = getter.invoke(paramVO);
				if(pd.getPropertyType().equals(int.class)) {
					pstmt.setInt(i+1, (Integer)propValue);
				}else {
					pstmt.setString(i + 1, (String)propValue);
				}
			}
			return pstmt;
		} catch (Exception e) {
			throw new SQLException(e);
		}
	}

}
