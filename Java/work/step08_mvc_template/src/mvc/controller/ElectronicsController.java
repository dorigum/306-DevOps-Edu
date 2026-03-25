package mvc.controller;

import java.util.List;

import mvc.dto.Electronics;
import mvc.exception.DuplicateModelNoException;
import mvc.exception.ElectronicsArrayBoundsException;
import mvc.service.ElectronicsService;
import mvc.service.ElectronicsServiceImpl;
import mvc.view.FailView;
import mvc.view.SuccessView;

/*
 * View와 Model 사이에서 중간 역할 
 * : 사용자의 요청을 받아서 그에 해당하는 서비스를 호출하고
 * 호출한 결과를 받아서 결과값에 따라 결과 뷰를 호출해준다.
 */
public class ElectronicsController {
	private ElectronicsService service = ElectronicsServiceImpl.getInstance();

	// 전체 검색
	public void selectAll() {
		// 서비스 호출하고 그 결과에 따라 성공 or 실패로 이동
		try {
			List<Electronics> list = service.selectAll();
			SuccessView.printAll(list);

		} catch (Exception e) {
			FailView.printErrorMessage("전체 검색 실패: " + e.getMessage());
		}
	}

	// 전자제품 등록
	public void insert(Electronics electronics) {
		// View에서 만들어준 electronics 객체를 그대로 Service로 전달
		// 서비스를 호출하고 그 결과에 따라서 성공 or 실패 뷰로 이동
		try {
			service.insert(electronics);
			SuccessView.printMessage("전자제품 정보가 등록되었습니다!");

		} catch (ElectronicsArrayBoundsException | DuplicateModelNoException e) {
			FailView.printErrorMessage("등록 실패: " + e.getMessage());
		}
	}

	/*
	 * 모델번호에 해당하는 전자제품 검색
	 * 
	 * @param modelNo
	 */
	public void searchByModelNo(int modelNo) {
		try {
			Electronics elec = service.searchByModelNo(modelNo);
			// 단일 객체를 출력하는 메소드가 SuccessView에 있다고 가정
			SuccessView.printElectronics(elec);

		} catch (Exception e) {
			FailView.printErrorMessage("검색 실패: " + e.getMessage());
		}
	}

	/*
	 * 모델번호에 해당하는 전자제품 수정하기
	 * 
	 * @param electronics
	 */
	public void update(Electronics electronics) {
		try {
			service.update(electronics);
			SuccessView.printMessage("전자제품 정보가 수정되었습니다!");

		} catch (Exception e) {
			FailView.printErrorMessage("수정 실패: " + e.getMessage());
		}
	}

	/*
	 * 모델번호에 해당하는 전자제품 삭제하기
	 * 
	 * @param electronics
	 */
	public void deleteModelNo(int modelNo) {
		try {
			service.deleteModelNo(modelNo);
			SuccessView.printMessage("전자제품 정보가 삭제되었습니다.");

		} catch (Exception e) {
			FailView.printErrorMessage("삭제 실패: " + e.getMessage());
		}
	}

	/*
	 * 가격을 기준으로 정렬하기 만약, 가격이 같으면 modelNo를 기준으로 정렬
	 * 
	 * @return
	 */
	public void selectSortByPrice() {
		try {
			List<Electronics> list = service.selectSortByPrice();
			SuccessView.printAll(list);

		} catch (Exception e) {
			FailView.printErrorMessage("정렬 실패: " + e.getMessage());
		}
	}
}