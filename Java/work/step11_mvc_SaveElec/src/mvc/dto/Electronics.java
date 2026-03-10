package mvc.dto;

import java.io.Serializable;

/*
 * 전자 제품의 속성을 관리하는 객체 
 */
public class Electronics implements Serializable {
	// 직렬화 버전 관리를 위한 고유 ID(선택 사항이지만 권장)
	private static final long serialVersionUID = 1L;

	private int modelNo;
	private String modelName;
	private int modelPrice;
	private String modelDetail;

	// 생성자 3개
	public Electronics() { }

	// 모델번호에 해당하는 전자제품 수정할 때 사용할 생성자
	public Electronics(int modelNo, String modelDetail) {
		this.modelNo = modelNo;
		this.modelDetail = modelDetail;
	}

	public Electronics(int modelNo, String modelName, int modelPrice, String modelDetail) {
		this(modelNo, modelDetail); // 생성자 구현부 첫번째 줄에서 다른 생성자 호출

		this.modelName = modelName;
		this.modelPrice = modelPrice;
	}
	
	// -------------------------------------------------------------------------------
	// set, get 작성
	// setXxx() : public 리턴 void, 인수 필수
	public void setModelNo(int modelNo) {
		this.modelNo = modelNo;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public void setModelPrice(int modelPrice) {
		this.modelPrice = modelPrice;
	}

	public void setModelDetail(String modelDetail) {
		this.modelDetail = modelDetail;
	}

	// -------------------------------------------------------------------------------
	// getXxx() : public 리턴 필수, 인수 없음
	public int getModelNo() {
		return modelNo;
	}

	public String getModelName() {
		return modelName;
	}

	public int getModelPrice() {
		return modelPrice;
	}

	public String getModelDetail() {
		return modelDetail;
	}

	// -------------------------------------------------------------------------------
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();

		builder.append("Electronics [modelNo = ");
		builder.append(modelNo);
		builder.append(", modelName = ");
		builder.append(modelName);
		builder.append(", modelPrice = ");
		builder.append(modelPrice);
		builder.append(", modelDetail = ");
		builder.append(modelDetail);
		builder.append("]");

		return builder.toString();
	}
}