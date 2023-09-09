package com.tdtd.tmtd.vo;


import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@Getter
@Setter
@ToString
@NoArgsConstructor
public class YeyakVo {
	private String gayeId;	
	private String gayeGagaId;
	private String gayeAccountId;
	private String gayePhoneNumber;
	private String gayeYeyakDate;
	private String gayeStartTime;
	private int gayeHours;
	private String gayeState;
	private int gayeClasId;
	private String gayeGyeoljeType;
	private String gayeGyeoljeUser;
	
	public YeyakVo(String gayeGagaId, String gayeAccountId, String gayePhoneNumber, String gayeYeyakDate,
			String gayeStartTime, int gayeHours, int gayeClasId, String gayeGyeoljeType) {
		this.gayeGagaId = gayeGagaId;
		this.gayeAccountId = gayeAccountId;
		this.gayePhoneNumber = gayePhoneNumber;
		this.gayeYeyakDate = gayeYeyakDate;
		this.gayeStartTime = gayeStartTime;
		this.gayeHours = gayeHours;
		this.gayeClasId = gayeClasId;
		this.gayeGyeoljeType = gayeGyeoljeType;
	}
}
