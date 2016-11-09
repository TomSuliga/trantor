package org.suliga;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.suliga.trantor.service.earthquake.EarthquakeService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrantorApplicationTests {

	@Autowired
	private EarthquakeService earthquakeService;
	
	@Test
	public void contextLoads() {
		Assert.assertTrue(true);
	}
	
	@Test
	public void earthquakeService() {
		Assert.assertNotNull(earthquakeService);
	}
}
