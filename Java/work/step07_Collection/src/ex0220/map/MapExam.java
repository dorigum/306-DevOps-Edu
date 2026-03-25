package ex0220.map;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

public class MapExam {
//	Map<Integer, String> map = new HashMap<Integer, String>();
	Map<Integer, String> map = new TreeMap<Integer, String>();public MapExam() {
		// map에 추가
		map.put(30, "도연");
		map.put(10, "나영");
		map.put(40, "미미");
		map.put(30, "효리");
		map.put(10, "삼순");

		System.out.println("저장된 개수 = " + map.size());
		System.out.println("map = " + map); // map.toString()

		// map에 들어있는 <Key, Value> 꺼내기(Key와 Value의 한 쌍: Entry)
		// 모든 Key의 정보를 가져온다.
		Set<Integer> keySet = map.keySet(); // 모든 key 정보를 Set에 저장
		Iterator<Integer> it = keySet.iterator(); // key에 꺼낼 수 있는 준비

		while (it.hasNext()) { // 요소가 있다면(꺼낼 수 있는 값이 있다면)
			int key = it.next(); // 요소를 꺼낸다.
			String value = map.get(key); // 꺼낸 key에 해당하는 value 조회

			System.out.println(key + "와 " + value);
		}
		
		System.out.println("-----개선된 for문 사용-----");
		for(Integer key : map.keySet()) {
			String value = map.get(key);
			
			System.out.println(key + "와 " + value);
		}
		
		System.out.println("-----Entry 형태로 조회하기-----");
		Set<Entry<Integer, String>> set = map.entrySet();
		
		for(Entry<Integer, String> e :set) {
			System.out.println(e.getKey() + " = " + e.getValue());
		}
		
		// 삭제
		System.out.println("-----삭제하기-----");
		map.remove(10);
//		map.remove(200); // map에 없는 값은 아무런 변화없이 출력됨
		
		System.out.println("삭제 후 = " + map);
	}

	public static void main(String[] args) {
		new MapExam();
	}
}