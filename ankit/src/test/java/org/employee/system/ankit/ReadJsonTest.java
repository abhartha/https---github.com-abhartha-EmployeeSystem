package org.employee.system.ankit;


import static org.junit.Assert.assertEquals;

import org.employee.system.ankit.dataaccess.ReadJson;
import org.employee.system.ankit.utils.Constants;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;

public class ReadJsonTest {
	
	@Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();
	
	@Test
	public void ReadJsonTesting() {
		new ReadJson(Constants.PATH_OF_JSON_TEST);
		String output = systemOutRule.getLog();
		assertEquals("JSON Read Success.\n", output);
	}
	
	@Test
	public void ReadJsonTesting1() {
		new ReadJson("Wrong/path");
		String output = systemOutRule.getLog();
		if (output.contains("java.io.FileNotFoundException:")) {
			assert(true);
		} else {
			assert(false);
		}
	}
}
