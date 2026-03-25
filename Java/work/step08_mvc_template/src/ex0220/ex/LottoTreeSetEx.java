package ex0220.ex;

import java.util.Collections;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

public class LottoTreeSetEx {
	public static void main(String[] args) {
		// 1. 내림차순 정렬 조건을 가진 TreeSet 생성
		// Collections.reverseOrder(): 내림차순(가장 큰 숫자부터 정렬)
		Set<Integer> lottoNumbers = new TreeSet<>(Collections.reverseOrder());

		Random rd = new Random();
		
		// 2. 번호가 딱 6개가 모일 때까지 무한 반복
		while (lottoNumbers.size() < 6) {
			// Math.random()을 사용하여 1부터 45 사이의 랜덤 숫자 생성
			// int number = (int)(Math.random() * 45) + 1;

			int number = rd.nextInt(45) + 1;
			
			// TreeSet에 추가(중복된 숫자는 추가 X)
			lottoNumbers.add(number);
		}

		// 3. 결과 출력
		System.out.println("이번 주 로또 번호(내림차순): " + lottoNumbers);
	}
}