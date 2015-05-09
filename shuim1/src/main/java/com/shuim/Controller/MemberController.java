package com.shuim.Controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.xstream.XStreamMarshaller;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.View;

import com.shuim.domain.MemberVO;
import com.shuim.service.MemberService;
import com.shuim.util.XmlResult;
import com.thoughtworks.xstream.XStream;


// 컨트롤러를 어노테이션 하지 않는 경우 Spring에서 인식하지 않아, 해당 컨트롤러에서 작성한 URL로 연결되지 않는다.
@Controller(value = "memberController")
@RequestMapping("/member")
public class MemberController {
    private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Resource(name="memberService")
	private MemberService memberService;
	
	@Resource(name = "xstreamMarshaller")
	private XStreamMarshaller xstreamMarshaller;
	
	@Resource(name = "xmlView")
	private View xmlView;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@RequestMapping(value="/login.do", method=RequestMethod.GET)
	public String login(Model model){
		logger.info("==================> start login");
		return "member/login";
	}
	
	@RequestMapping(value="/registerMember_ok.do", method=RequestMethod.POST)
	public View procRegisterMember(@ModelAttribute("memberVO") MemberVO memberVO, Model model){
		logger.info("================> start procRegisterMember");
		XStream xst = xstreamMarshaller.getXStream();
		xst.alias("result", XmlResult.class);
		XmlResult xml = new XmlResult();
		
		try {
			memberVO.setPasswd(passwordEncoder.encodePassword(memberVO.getPasswd().toString(), null));
			this.memberService.addMember(memberVO);
			xml.setMessage("회원 가입 종료");
			xml.setError(false);
		} catch(Exception e){
			xml.setMessage(e.getMessage());
			xml.setError(true);
		}
		model.addAttribute("xmlData", xml);
		return xmlView;
	}
}
