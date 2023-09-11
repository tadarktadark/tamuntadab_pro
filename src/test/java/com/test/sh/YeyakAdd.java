package com.test.sh;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.gson.Gson;
import com.tdtd.tmtd.model.service.IYeyakService;
import com.tdtd.tmtd.vo.GangeuisilVo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class YeyakAdd {

	@Autowired
	private IYeyakService service;
	
	@Test
	public void dummy() {
		int n = 0;
		for(int i=1; i<283; i++) {
			if(i<10) {
				n += service.dummy("GAGA0000"+i);				
			} else if(i<100) {
				n += service.dummy("GAGA000"+i);								
			} else {
				n += service.dummy("GAGA00"+i);												
			}
		}
		assertEquals(282, n);
		int m = service.dummy2();
		assertEquals(4, m);
	}

}
