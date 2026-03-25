package mvc.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

import mvc.dto.Electronics;
import mvc.exception.DuplicateModelNoException;
import mvc.exception.ElectronicsArrayBoundsException;
import mvc.exception.SearchNotFoundException;

/*
 * 전자제품에 관련된 기능을 담당할 클래스
 */
public class ElectronicsServiceImpl implements ElectronicsService {
	private static ElectronicsService instance = new ElectronicsServiceImpl();
	private static final int MAX_SIZE = 6;
	private static final String FILE_NAME = ("src/mvc" + "electronics.txt"); // 저장할 파일 이름 지정

	List<Electronics> list = new ArrayList<Electronics>();

	/*
	 * 외부에서 객체 생성 X InitInfo.properties 파일을 로딩하여 List에 추가하여 초기치 데이터를 만든다.
	 */
	private ElectronicsServiceImpl() {
//		System.out.println("user.dir = " + System.getProperty("user.dir"));
//		System.out.println("user.home = " + System.getProperty("user.home"));
//		
//		String path = System.getProperty("user.dir") + "/save.txt";
		
		File file = new File(FILE_NAME);

		if (file.exists() && file.length() > 0) {
			// 1. 텍스트 파일(문자 스트림)에서 한 줄씩 읽어오기
			try (BufferedReader br = new BufferedReader(new FileReader(file))) {
				String line;
				
				while ((line = br.readLine()) != null) {
					// 텍스트 파일에 "100,선풍기,35000,삼성 선풍기" 형태로 저장된 것을 쪼갭니다.
					String[] data = line.split(",");
					
					if (data.length == 4) {
						Electronics elec = new Electronics(Integer.parseInt(data[0]), data[1],
								Integer.parseInt(data[2]), data[3]);
						list.add(elec);
					}
				}
				
				System.out.println("기존 데이터를 텍스트 파일에서 성공적으로 불러왔습니다.");

			} catch (Exception e) {
				System.out.println("파일 읽기 실패: " + e.getMessage());
			}

		} else {
			// 2. 파일이 없다면 기존처럼 properties에서 초기 데이터 세팅 (이 부분은 기존 코드와 100% 동일)
			ResourceBundle rb = ResourceBundle.getBundle("InitInfo");

			for (String key : rb.keySet()) {
				String value = rb.getString(key);
				String data[] = value.split(",");
				Electronics elec = new Electronics(Integer.parseInt(data[0]), data[1], Integer.parseInt(data[2]),
						data[3]);
				list.add(elec);
			}
			
			System.out.println("초기 데이터를 properties에서 불러왔습니다.");
		}
	}

	// --------------------------------------------------------------------------
	public static ElectronicsService getInstance() {
		return instance;
	}

	@Override
	public void insert(Electronics electronics) throws ElectronicsArrayBoundsException, DuplicateModelNoException {

		if (list.size() == MAX_SIZE) {
			throw new ElectronicsArrayBoundsException("더이상 등록할 수 없습니다.");
		}

		try {
			// 모델번호 중복여부 체크
			this.searchByModelNo(electronics.getModelNo());

			throw new DuplicateModelNoException(electronics.getModelNo() + "중복이므로 등록할 수 없습니다.");

		} catch (SearchNotFoundException e) {
			// 예외가 발생했다는 것은 중복이 아니다.
			list.add(electronics);
		}
	}

	@Override
	public List<Electronics> selectAll() {
		return list;
	}

	@Override
	public Electronics searchByModelNo(int modelNo) throws SearchNotFoundException {
		for (Electronics elec : list) {
			if (elec.getModelNo() == modelNo) {
				return elec;
			}
		}

		// 찾는 정보가 없다!!!
		throw new SearchNotFoundException(modelNo + "은(는) 없는 모델번호로 검색할 수 없습니다.");
	}

	/*
	 * 모델번호에 해당하는 전자제품 수정하기(설명만 수정)
	 * 
	 * @param electronics
	 * 
	 * @return
	 */
	public void update(Electronics electronics) throws SearchNotFoundException { // 모델번호, 설명
		// 인수로 전달된 모델번호에 해당하는 전자제품이 있는지 검색한다.

		Electronics dbElectronics = this.searchByModelNo(electronics.getModelNo());

		// 수정
		dbElectronics.setModelDetail(electronics.getModelDetail());
	}

	@Override
	public void delete(int modelNo) throws SearchNotFoundException {
		Electronics dbElectronics = this.searchByModelNo(modelNo);

		list.remove(dbElectronics);

	}

	// ---------------------------------------------------------------------------------
	/*
	 * class A implements Comparator<Electronics>{
	 * 
	 * @Override public int compare(Electronics a, Electronics b) { // TODO
	 * Auto-generated method stub return a.getModelPrice() == b.getModelPrice() ?
	 * b.getModelNo()-a.getModelNo() : a.getModelPrice()-b.getModelPrice(); } }
	 */

	// ---------------------------------------------------------------------------------
	@Override
	public List<Electronics> selectSortByPrice() {

		// 정렬을 할 자료구조
		List<Electronics> sortList = new ArrayList<Electronics>(list);

		Collections.sort(sortList, (a, b) -> a.getModelPrice() == b.getModelPrice() ? b.getModelNo() - a.getModelNo()
				: a.getModelPrice() - b.getModelPrice());

		return sortList;
	}

	// ---------------------------------------------------------------------------------
	/*
	 * 프로그램 종료 시 리스트를 파일로 저장하는 메소드
	 */
	@Override
	public void saveObject() {
		// PrintWriter와 FileWriter를 사용해 텍스트 형태로 파일에 쓰기
		try (PrintWriter pw = new PrintWriter(new FileWriter("electronics.txt"))) {
			
			for (Electronics elec : list) {
				// 객체의 속성들을 콤마(,)로 연결해서 한 줄씩 기록합니다.
				pw.println(elec.getModelNo() + "," + elec.getModelName() + "," + 
				           elec.getModelPrice() + "," + elec.getModelDetail());
			}
		
		} catch (Exception e) {
			System.out.println("파일 저장 실패: " + e.getMessage());
		}
	}
} // 클래스 끝
