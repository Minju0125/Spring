package kr.or.ddit.lang;

import static org.junit.jupiter.api.Assertions.*;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.WordUtils;
import org.junit.jupiter.api.Test;

import kr.or.ddit.reflect.ReflectionTest;

/**
 * Reflection
 *    : 인스턴스로부터 타입, 구성요소, 특성들에 대한 정보를 역으로 추출하는 작업
 * java.lang.reflect.* 패키지로 지원됨
 *
 */
class ReflectionDesc {
   @Test
	void test3() {
		String columName = "ADRS_NO";
		String propName = WordUtils.capitalizeFully(columName, '_');
		System.out.println(propName);
		propName = StringUtils.replace(propName, "_", "");
		System.out.println(propName);
		propName =  WordUtils.uncapitalize(propName);
		System.out.println(propName);
		
   }
	
	
	//@Test
   void test2() throws IllegalArgumentException, IllegalAccessException, IntrospectionException, InvocationTargetException {
      Object obj = ReflectionTest.getObject();
      Class<?> objType = obj.getClass();
      Field[] fields = objType.getDeclaredFields();
      for(Field fld : fields) {
         String fldName = fld.getName();
         Class<?> fldType = fld.getType();
//         fld.setAccessible(true);
//         memId -> getMemId
//         memPass -> setMemPass
         PropertyDescriptor pd = new PropertyDescriptor(fldName, objType);
         Method getter = pd.getReadMethod();
         Method setter = pd.getWriteMethod();
//         obj.getMemId();
         if(fldName.endsWith("name")) {
            setter.invoke(obj, "변경한이름");
         }
         Object fldValue = getter.invoke(obj);
         System.out.printf("%s %s = %s;\n", fldType.getSimpleName(), fldName, fldValue);
      }
      
   }
   
//   @Test
   void test1(){
      Object obj = ReflectionTest.getObject();
      System.out.println(obj);
      Class<?> objType = obj.getClass();
      System.out.printf("객체 타입 : %s\n",objType.getName());
      Field[] fields = objType.getDeclaredFields();
      for(Field fld : fields) {
         String fldName = fld.getName();
         Class<?> fldType = fld.getType();
         System.out.printf("%s %s;\n", fldType.getSimpleName(), fldName);
      }
      
      Method[] methods = objType.getDeclaredMethods();
      for( Method mtd : methods) {
         String mtdName = mtd.getName();
         Parameter[] parameters = mtd.getParameters();
         Class<?> returnType = mtd.getReturnType();
         System.out.printf("%s %s(%s);\n",
               returnType.getSimpleName(),
               mtdName,
               Arrays.toString(parameters)
               );
      }
   }
}