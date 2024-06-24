package com.kh.reMerge.feed.model.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Feed {
	private int feedNo;
	private String feedContent;
	private String feedWriter;
	private Date createDate;
	private String originName;
	private String changeName;
	private String status;
	private String feedLocation;
}
