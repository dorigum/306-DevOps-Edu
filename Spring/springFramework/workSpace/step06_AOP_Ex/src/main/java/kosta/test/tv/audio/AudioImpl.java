package kosta.test.tv.audio;

import org.springframework.stereotype.Component;

import kosta.test.service.Player;

@Component("audio")
public class AudioImpl implements Player {
	public void start(int volumn) {
		System.out.println("AudioImpl start(int volumn) 호출");
	}

	public String pause() {
		System.out.println("AudioImpl pause() 호출");
		return "AudioImpl 리턴값!!";
	}

	public void stop() {
		System.out.println("AudioImpl stop() 호출");
	}
}