package com.shuim.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.shuim.domain.MemberVO;

// 다른 곳에서 사용할 해당 서비스 클래스의 이름
@Service(value = "memberService")
public class MemberService {
	
	// Context-mybatis.xml 에서 스캔된 맵퍼의 어노테이션 선언
    @Resource(name = "memberMapper")	
	private MemberMapper memberMapper;
	
    // addMember 메소드의 기능 작성
	public void addMember (MemberVO memberVO){
		this.memberMapper.addMember(memberVO);
	}
}
