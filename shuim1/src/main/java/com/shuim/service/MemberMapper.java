package com.shuim.service;

import org.springframework.stereotype.Repository;

import com.shuim.domain.MemberVO;

// 다른 곳에서 사용할 해당 맵퍼 클래스의 이름
@Repository(value = "memberMapper")
public interface MemberMapper {
	// MemberMapper.xml 에서 작성한 insert 문의 id와 입력할 값
	void addMember(MemberVO memberVO);
	
}