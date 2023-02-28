package com.sample.camel.converter;

import org.apache.camel.Converter;
import org.apache.camel.Exchange;
import org.apache.camel.NoTypeConversionAvailableException;
import org.apache.camel.TypeConversionException;
import org.apache.camel.TypeConverter;

@Converter
public class StringConverter implements TypeConverter{

	@Override
	public boolean allowNull() {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

	@Override
	public <T> T convertTo(Class<T> type, Object value) throws TypeConversionException {
		// TODO 自動生成されたメソッド・スタブ
		return type.cast(value);
	}

	@Override
	public <T> T convertTo(Class<T> type, Exchange exchange, Object value) throws TypeConversionException {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public <T> T mandatoryConvertTo(Class<T> type, Object value)
			throws TypeConversionException, NoTypeConversionAvailableException {
		// TODO 自動生成されたメソッド・スタブ
		return type.cast(value);
	}

	@Override
	public <T> T mandatoryConvertTo(Class<T> type, Exchange exchange, Object value)
			throws TypeConversionException, NoTypeConversionAvailableException {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public <T> T tryConvertTo(Class<T> type, Object value) {
		// TODO 自動生成されたメソッド・スタブ
		return type.cast(value);
	}

	@Override
	public <T> T tryConvertTo(Class<T> type, Exchange exchange, Object value) {
		// TODO 自動生成されたメソッド・スタブ
		return type.cast(value);
	}
	
	
	
}