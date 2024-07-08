package com.docmall.basic.review;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ReviewVo {

	// rev_code, mbsp_id, pro_num, rev_title, rev_content, rev_rate, rev_date
	private Long rev_code;
	private String mbsp_id;
	private int pro_num;
	private String rev_title;
	private String rev_content;
	private int rev_rate;
	private Date rev_date;
}
