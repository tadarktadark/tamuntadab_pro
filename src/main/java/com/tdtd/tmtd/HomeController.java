package com.tdtd.tmtd;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tdtd.tmtd.model.service.IMainService;
import com.tdtd.tmtd.vo.ClassVo;
import com.tdtd.tmtd.vo.InstrVo;
import com.tdtd.tmtd.vo.SubjectTagVo;

@Controller
public class HomeController {
	
	@Autowired
	private IMainService service;
	
	@RequestMapping(value = "/main.do")
	public String home(Model model) {
		List<SubjectTagVo> subjects = service.getAllSubjectTag();
		List<ClassVo> classes = service.getAllClass();
		List<InstrVo> instrs = service.getIngiInstr();
		List<InstrVo> instrsReverse = service.getIngiInstr();
		
		for (InstrVo instrVo : instrs) {
			String subjectsMajorTitle = instrVo.getSubjectsMajorTitle();
			String subjectsTitle = instrVo.getSubjectsTitle();
			subjectsMajorTitle = subjectsMajorTitle.replace("[", "").replace("]", "").replace("\"", "");
			subjectsTitle = subjectsTitle.replace("[", "").replace("]", "").replace("\"", "");
			
			instrVo.setSubjectsMajorTitle(subjectsMajorTitle);
			instrVo.setSubjectsTitle(subjectsTitle);
		};
		
		for (InstrVo instrVo : instrsReverse) {
			String subjectsMajorTitle = instrVo.getSubjectsMajorTitle();
			String subjectsTitle = instrVo.getSubjectsTitle();
			subjectsMajorTitle = subjectsMajorTitle.replace("[", "").replace("]", "").replace("\"", "");
			subjectsTitle = subjectsTitle.replace("[", "").replace("]", "").replace("\"", "");
			
			instrVo.setSubjectsMajorTitle(subjectsMajorTitle);
			instrVo.setSubjectsTitle(subjectsTitle);
		};
		
		Collections.reverse(instrsReverse);
		
		model.addAttribute("subjects", subjects);
		model.addAttribute("classes", classes);
		model.addAttribute("instrs", instrs);
		model.addAttribute("instrsReverse", instrsReverse);
		
		return "index";
	}
	@RequestMapping(value = "/error.do")
	public String error() {
		return "";
	}
	
}
