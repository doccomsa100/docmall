package com.docmall.basic.user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LoginDTO {
	private String mbsp_id;
	private String mbsp_password;
}
