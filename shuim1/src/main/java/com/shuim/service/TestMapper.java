package com.shuim.service;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.shuim.domain.Test;

@Repository(value = "testMapper")
public interface TestMapper {
	
	ArrayList<Test> listTest();
	
}