package cn.xuhuiqiang.creative.common.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Date;

public abstract class DateUtil {

	/***
	 * 
	 * @Title: localDateTimeConvertToDate 
	 * @Description: TODO   
	 * @param localDateTime
	 * @return 
	 * Date     
	 * @throws
	 */
	public static Date localDateTime2Date(LocalDateTime localDateTime) {
		return Date.from(localDateTime.toInstant(ZoneOffset.of("+8")));
	}

	/**
	 * 
	 * @Title: localDate2Date 
	 * @Description: TODO   
	 * @param localDate
	 * @return 
	 * Date     
	 * @throws
	 */
	public static Date localDate2Date(LocalDate localDate) {
		if (null == localDate) {
			return null;
		}
		ZonedDateTime zonedDateTime = localDate.atStartOfDay(ZoneId.systemDefault());
		return Date.from(zonedDateTime.toInstant());
	}
}
