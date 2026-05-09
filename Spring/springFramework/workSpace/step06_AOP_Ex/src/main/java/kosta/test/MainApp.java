package kosta.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import kosta.test.service.Player;

public class MainApp {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("springAop.xml");

		Player tv = context.getBean("tv", Player.class);
		tv.start(10);
		tv.pause();
		tv.stop();
		System.out.println("--------------------");

		Player audio = context.getBean("audio", Player.class);
		audio.start(20);
		audio.pause();
		audio.stop();
		System.out.println("--------------------");

		Player video = context.getBean("video", Player.class);
		video.start(20);
		video.pause();
		video.stop();

		context.close();
	}
}