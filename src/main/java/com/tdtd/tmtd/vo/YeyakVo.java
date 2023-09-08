package com.tdtd.tmtd.vo;


import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@Getter
@Setter
@ToString
@NoArgsConstructor
public class YeyakVo {
	private String gaye_id;
	private String gaye_gaga_id;
	private String gaye_account_id;
	private String gaye_phone_number;
	private String gaye_yeyak_date;
	private String gaye_start_time;
	private int gaye_hours;
	private String gaye_state;
	private int gaye_clas_id;
	private String gaye_gyeolje_type;
	private String gaye_gyeolje_user;
}
