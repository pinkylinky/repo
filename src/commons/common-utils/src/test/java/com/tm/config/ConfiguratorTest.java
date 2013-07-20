package com.tm.config;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.Properties;

import junit.framework.Assert;

import org.junit.Test;

import com.tm.utils.DateUtils;
import com.tm.utils.PropertiesUtils;

public class ConfiguratorTest {
	
	@Test
	public void testName() throws Exception {
		Properties properties = PropertiesUtils.load("src\\test\\java\\test.properties");
		
		ConfigMetadata metadata = new ConfigMetadata();
		metadata.addParam(new ConfigParamMetadata("aaa", String.class, true));
		metadata.addParam(new ConfigParamMetadata("bbb", String.class, false));
		metadata.addParam(new ConfigParamMetadata("ccc", Integer.class, true));
		metadata.addParam(new ConfigParamMetadata("ddd", Date.class, true));
		Configuration config = Configurator.configure(properties, metadata);
		
		Assert.assertEquals("123", config.getStringProperty("aaa"));
		Assert.assertEquals("", config.getStringProperty("bbb"));
		Assert.assertEquals(888, config.getIntProperty("ccc").intValue());
		Assert.assertEquals(DateUtils.stringToDate("12.02.2000").getTime(), config.getDateProperty("ddd").getTime());
		
	}

}
