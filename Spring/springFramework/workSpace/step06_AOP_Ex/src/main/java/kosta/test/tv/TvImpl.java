package kosta.test.tv;

import org.springframework.stereotype.Component;

import kosta.test.service.Player;

@Component("tv")
public class TvImpl implements Player {

	public void start(int volumn) {
		System.out.println("TvImpl start(int volumn) 호출");
	}

	public String pause() {
		System.out.println("TvImpl pause() 호출");
		return "TvImpl 리턴값!!";
	}

	public void stop() {
		System.out.println("TvImpl stop() 호출");
	}
}