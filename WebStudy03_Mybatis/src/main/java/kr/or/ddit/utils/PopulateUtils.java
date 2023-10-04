package kr.or.ddit.utils;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.Temporal;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.beanutils.converters.AbstractConverter;
import org.apache.commons.lang3.StringUtils;

public class PopulateUtils {
   public static void populate(Object bean, Map<String, ? extends Object> properties) {
      Converter converter = new AbstractConverter() {
         
         @Override
         protected Class<?> getDefaultType() {
            return Temporal.class;
         }
         
         @Override
         protected <T> T convertToType(Class<T> type, Object paramValue) throws Throwable {
            String text = (String)paramValue;
            if(StringUtils.isNoneBlank(text)) {
//               return LocalDate.parse((String)paramValue);
               return (T) type.getDeclaredMethod("parse", CharSequence.class).invoke(null, text);
            }else {
               return null;
            }
         }
      };
      
      ConvertUtils.register(converter, LocalDate.class);
      ConvertUtils.register(converter, LocalDateTime.class);
      
      try {
         
         BeanUtils.populate(bean, properties);
      } catch (IllegalAccessException | InvocationTargetException e) {
         throw new RuntimeException(e);
      }
   }
}