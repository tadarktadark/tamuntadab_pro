package com.tdtd.tmtd.vo;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@Getter
@Setter
@ToString
@NoArgsConstructor
public class GangeuisiilVo {
	private String gacoId;
	private String gacoName;
	private String gacoSido;
	private String gacoSigungu;
	private String gacoJuso;
	private long gacoLat;
	private long gacoLon;
	private String gacoOpen;
	private String gacoClose;
	private String gagaId;
	private String gagaName;
	private int gagaMax;
	private int gagaHourPrice;
}
