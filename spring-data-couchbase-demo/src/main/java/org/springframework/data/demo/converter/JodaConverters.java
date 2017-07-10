package org.springframework.data.demo.converter;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.convert.WritingConverter;

import java.util.Arrays;
import java.util.List;
public class JodaConverters {
	public static List<Converter<?, ?>> listConverters() {
		return Arrays.asList(new LocalDateTimeToStringConverter(),
				new StringToLocalDateTimeConverter(),
				new LocalDateToStringConverter(),
				new StringToLocalDateConverter());
	}

	@WritingConverter
	public static class LocalDateToStringConverter implements Converter<LocalDate, String> {
		@Override
		public String convert(LocalDate localDate) {
			return localDate != null ? localDate.toString() : null;
		}
	}

	@ReadingConverter
	public static class StringToLocalDateConverter implements Converter<String, LocalDate> {
		@Override
		public LocalDate convert(String s) {
			return s != null ? LocalDate.parse(s) : null;
		}
	}

	@WritingConverter
	public static class LocalDateTimeToStringConverter implements Converter<LocalDateTime, String> {
		@Override
		public String convert(LocalDateTime localDateTime) {
			return localDateTime != null ? localDateTime.toString() : null;
		}
	}

	@ReadingConverter
	public static class StringToLocalDateTimeConverter implements Converter<String, LocalDateTime> {
		@Override
		public LocalDateTime convert(String s) {
			return s != null ? LocalDateTime.parse(s) : null;
		}
	}
}
