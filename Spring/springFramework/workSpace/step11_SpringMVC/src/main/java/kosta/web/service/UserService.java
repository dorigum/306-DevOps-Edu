package kosta.web.service;

import kosta.web.exception.BasicException;

public interface UserService {
   /*
    * 나이 체크
    */
	void ageCheck(int age)throws BasicException;
	
	/*
	 * 아이디 중복체크
	 */
	void idCheck(String id) throws BasicException;
}
