package com.tdtd.tmtd.vo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ClassVo {

	private int clasId;
	private String clasTitle;
	private String clasLocation;
	private String clasStatus;
	private String clasMagamGihan;
	private int clasHuimangInwon;
	private int clasHyeonjaeInwon;
	private String clasSueopNaljja;
	private int clasChoisoSugangnyo;
	private int clasChoidaeSugangnyo;
	private String clasSeongbyeolJehan;
	private String clasNaiJehan;
	private String clasSubjectJeongbo;
	private String clasRegdate;
	private int clasMojipYeonjangHoitsu;
	private String clasContent;
	private String clasAccountId;
	private int clasSugangRyo;
	
	private List<ReviewVo> reviewVo;
}
