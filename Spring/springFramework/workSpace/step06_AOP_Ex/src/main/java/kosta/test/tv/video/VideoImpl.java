package kosta.test.tv.video;

import org.springframework.stereotype.Component;

import kosta.test.service.Player;

@Component("video")
public class VideoImpl implements Player {
	public void start(int volumn) {
		System.out.println("VideoImpl start(int volumn) 호출");
	}

	public String pause() {
		System.out.println("VideoImpl pause() 호출");
		return "VideoImpl 리턴값!!";
	}

	public void stop() {
		System.out.println("VideoImpl stop() 호출");
	}
}
