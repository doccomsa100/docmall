package com.docmall.basic.admin;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AdminVo {

	private String admin_id;
	private String admin_pw;
	private Date admin_visit_date;
}
