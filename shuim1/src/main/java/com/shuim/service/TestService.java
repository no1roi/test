package com.shuim.service;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.shuim.domain.Test;
@Service(value = "testService")
public class TestService {
	
    @Resource(name = "testMapper")	
	private TestMapper testMapper;
	

	public ArrayList<Test> listTest() {
		
		return testMapper.listTest();
	}
}
