package kosta.web.service;

import org.springframework.stereotype.Service;

import kosta.web.exception.BasicException;
import kosta.web.exception.ErrorInfo;
import lombok.extern.slf4j.Slf4j;

@Service // id = "userServiceImpl"
@Slf4j
public class UserServiceImpl implements UserService {

	@Override
	public void ageCheck(int age) throws BasicException {
		log.info("age = {}", age);

		if (age <= 18)
			throw new BasicException(ErrorInfo.INVALID_AGE);
	}

	@Override
	public void idCheck(String id) throws BasicException {
		// ±â´É
		log.info("id = {}", id);

		if ("jang".equals(id))
			throw new BasicException(ErrorInfo.INVALID_ID);
	}
}