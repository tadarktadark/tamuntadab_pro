package com.tdtd.tmtd;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tdtd.tmtd.model.service.IMainService;
import com.tdtd.tmtd.vo.ClassVo;
import com.tdtd.tmtd.vo.InstrVo;
import com.tdtd.tmtd.vo.SubjectTagVo;

/**
 * 메인 페이지 관련 컨트롤러
 * 
 * @author 문희애
 *
 */
@Controller
public class HomeController {

	@Autowired
	private IMainService service;

	/**
	 * 메인 페이지 로드시 실행
	 * 
	 * @return classes 모집중인 클래스 리스트 / instrs 인기 강사 리스트 / instrsReverse 인기 강사 리스트
	 *         (역순)
	 */
	@RequestMapping(value = "/main.do")
	public String home(Model model) {
		// 최신 모집 중 클래스 조회
		List<ClassVo> classes = service.getAllClass();
		// 강사 순방향 조회
		List<InstrVo> instrs = service.getIngiInstr();
		// 강사 역방향 조회
		List<InstrVo> instrsReverse = service.getIngiInstr();

		for (InstrVo instrVo : instrs) {
			String subjectsMajorTitle = instrVo.getSubjectsMajorTitle();
			String subjectsTitle = instrVo.getSubjectsTitle();
			subjectsMajorTitle = subjectsMajorTitle.replace("[", "").replace("]", "").replace("\"", "");
			subjectsTitle = subjectsTitle.replace("[", "").replace("]", "").replace("\"", "");

			instrVo.setSubjectsMajorTitle(subjectsMajorTitle);
			instrVo.setSubjectsTitle(subjectsTitle);
		}
		;

		for (InstrVo instrVo : instrsReverse) {
			String subjectsMajorTitle = instrVo.getSubjectsMajorTitle();
			String subjectsTitle = instrVo.getSubjectsTitle();
			subjectsMajorTitle = subjectsMajorTitle.replace("[", "").replace("]", "").replace("\"", "");
			subjectsTitle = subjectsTitle.replace("[", "").replace("]", "").replace("\"", "");

			instrVo.setSubjectsMajorTitle(subjectsMajorTitle);
			instrVo.setSubjectsTitle(subjectsTitle);
		}
		;
		// 역방향으로 바꿈
		Collections.reverse(instrsReverse);

		model.addAttribute("classes", classes);
		model.addAttribute("instrs", instrs);
		model.addAttribute("instrsReverse", instrsReverse);

		return "index";
	}

	/**
	 * 메인페이지 로드시 과목 slick slider 로드
	 * 
	 * @return subjects 과목 리스트
	 */
	@GetMapping("/subjectsSlider.do")
	@ResponseBody
	public List<SubjectTagVo> subjectsSlider() {
		List<SubjectTagVo> subjects = service.getAllSubjectTag();
		return subjects;
	}

	@RequestMapping(value = "/error.do")
	public String error() {
		return "";
	}

}
