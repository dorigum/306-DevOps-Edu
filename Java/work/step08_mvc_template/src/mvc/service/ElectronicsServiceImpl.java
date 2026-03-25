package mvc.service;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import mvc.dto.Electronics;
import mvc.exception.DuplicateModelNoException;
import mvc.exception.ElectronicsArrayBoundsException;
import mvc.exception.SearchNotFoundException;

// 전자제품에 관련된 기능을 담당할 클래스
public class ElectronicsServiceImpl implements ElectronicsService {
	private static ElectronicsService instance = new ElectronicsServiceImpl();
	private static final int MAX_SIZE = 10;

	List<Electronics> list = new ArrayList<Electronics>();

	/*
	 * 외부에서 객체 생성 X InitInfo.properties파일을 로딩하여 List에 추가하여 초기치 데이터를 만든다.
	 */
	private ElectronicsServiceImpl() { // private로 생성: 싱글톤 패턴 적용
		System.out.println("**private constructor init.....");

		ResourceBundle rb = ResourceBundle.getBundle("InitInfo"); // InitInfo.properties

		for (String key : rb.keySet()) {
			String value = rb.getString(key); // 100 = 100,선풍기,35000,삼성 선풍기
			String data[] = value.split(",");

			System.out.println(key + " = " + value);

			list.add(new Electronics(Integer.parseInt(data[0]), data[1], Integer.parseInt(data[2]), data[3]));
		}

		System.out.println(list);
	}

	// --------------------------------------------------------------------------------
	public static ElectronicsService getInstance() {
		return instance;
	}

	@Override
	public void insert(Electronics electronics) throws ElectronicsArrayBoundsException, DuplicateModelNoException {
		// 1. 최대 개수 초과 검사
		if (list.size() >= MAX_SIZE) {
			throw new ElectronicsArrayBoundsException("더 이상 전자제품을 등록할 수 없습니다. (최대 " + MAX_SIZE + "개)");
		}

		// 2. 모델 번호 중복 검사
//		for(Electronics e : list) {
//			if (e.getModelNo() == electronics.getModelNo()) {
//				throw new DuplicateModelNoException("이미 등록된 모델 번호입니다: " + electronics.getModelNo());
//			}
//		}
		try {
			this.searchByModelNo(electronics.getModelNo());

			throw new DuplicateModelNoException(electronics.getModelNo() + "은(는) 이미 등록된 모델 번호입니다.");

		} catch (SearchNotFoundException e) { // 중복된 모델번호 X : 등록 가능
			list.add(electronics);
		}

		// 3. 검사를 통과했다면 리스트에 추가
		list.add(electronics);
	}

	// --------------------------------------------------------------------------------
	// 1. 제품 전체 검색하기
	@Override
	public List<Electronics> selectAll() {
		return list;
	}

	// 2. 모델 번호로 검색하기
	@Override
	public Electronics searchByModelNo(int modelNo) throws SearchNotFoundException {
		// 리스트를 순회하며 번호가 일치하는 객체 찾기
		for (Electronics e : list) {
			if (e.getModelNo() == modelNo) {
				return e; // 찾으면 바로 반환
			}
		}
		// 반복문이 다 돌 때까지 못 찾았다면 예외 발생
		throw new SearchNotFoundException(modelNo + "은(는) 없는 모델번호로 검색할 수 없습니다.");
	}

	// --------------------------------------------------------------------------------
	// 3. 제품정보 수정하기
	@Override
	public void update(Electronics electronics) throws SearchNotFoundException {
		// (1) searchByModelNo를 재사용해서 해당 객체를 찾아오기
		// 만약 없으면 여기서 자동으로 예외가 터져서 밖으로 던져짐
		Electronics target = searchByModelNo(electronics.getModelNo());

		// (2) 찾은 객체의 정보(설명)를 매개변수로 넘어온 새 정보로 덮어씌우기
		target.setModelDetail(electronics.getModelDetail());
	}

	// --------------------------------------------------------------------------------
	// 4. 제품정보 삭제하기
	@Override
	public void deleteModelNo(int modelNo) throws SearchNotFoundException {
		// (1) 동일하게 searchByModelNo를 활용해 삭제할 객체 찾기
		Electronics target = searchByModelNo(modelNo);

		// (2) 리스트에서 해당 객체를 삭제
		list.remove(target);
	}

	// --------------------------------------------------------------------------------
	// 5. 제품을 가격 오름차순으로 정렬하기(가격이 같을 경우, 모델 번호로 오름차순 정렬)
	@Override
	public List<Electronics> selectSortByPrice() {
		// 1. 원본 데이터 보호를 위해 복사본 리스트 생성
		List<Electronics> sortedList = new ArrayList<Electronics>(list);

		// 2. 정렬 기준(Comparator) 정의 및 정렬
		sortedList.sort((e1, e2) -> {
			// 가격이 같을 경우: 모델 번호 기준 오름차순(앞의 값 - 뒤의 값)
			if (e1.getModelPrice() == e2.getModelPrice()) {
				return e1.getModelNo() - e2.getModelNo();
			}
			// 가격이 다를 경우: 가격 기준 오름차순
			return e1.getModelPrice() - e2.getModelPrice();
		});
		
		// 여기 부분 다시 체크!!!!!!!!!!!!!!!!
		// Functional Interface를 람다식으로 표현
		// Collections.sort(sortList, (a, b) ->
		//					a.getModelPrice
		return sortedList;
	}
} // 클래스 끝