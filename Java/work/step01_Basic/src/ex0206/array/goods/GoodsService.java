package ex0206.array.goods;

// 각 요청에 대한 로직(기능)을 담당할 클래스
// (등록, 전체 검색, 부분 검색, 수정, 삭제 등등.....)
public class GoodsService {
	// 상품을 관리할 배열 선언
	private Goods[] goodsArr = new Goods[10];
	private int count; // 0 배열방에 저장 객체의 개수 
	
	public void init(String[][] data) {
		for(int i=0; i<data.length; i++) {
			goodsArr[count++] = this.create(data[i]);
		}
		
		System.out.println("***초기화 완료***");
   } // 메소드 끝
	
	// Goods를 생성해서 값을 설정하고, 생성된 Goods를 리턴하는 메소드
	private Goods create(String[] row) { // {"A01" , "새우깡" , "2500" , "짜고 맛나다."}
		Goods gd = new Goods();
		gd.setCode(row[0]);
		gd.setName(row[1]);
		gd.setPrice(Integer.parseInt(row[2]));
		gd.setExplain(row[3]);
		
		return gd;
		}

   /*
     등록(등록실패 - 중복인 경우, 배열의 길이 벗어난 경우)
	 @return : 
	 0이면 상품 코드 중복,
	 1이면 등록 성공, 
	 -1이면 배열의 길이 벗어남
   */
   public int insert(Goods goods) {
	   // 1. 배열 길이 체크
	   if(goodsArr.length == count) return -1;
	   
	   // 2. 상품 코드 중복 체크
	   if(this.selectByCode(goods.getCode()) != null) return 0;
	   
	   // 3. 상품 등록 성공
	   goodsArr[count++] = goods;
	   return 1;
   }
   
   // 전체 상품 정보 조회하기
   public Goods[] selectAll( ) {
	   return goodsArr;//
   }

   /*
     상품 코드에 해당하는 상품 검색
	 @return : 만약 code에 해당하는 값이 있으면 Goods를 리턴하고
	           없으면 null 리턴
   */
   public Goods selectByCode(String code) {
	   for(int i=0; i<count; i++) {
		   Goods searchedGd = goodsArr[i];
		   
		   String c = searchedGd.getCode(); 
		   int converUpper = c.compareTo(code);
		   
		   if(searchedGd.getCode().equals(code)) {
			   return searchedGd;
		   }
	   }
	   // 상품이 존재하지 않으면 null 반환
	   return null;
   }

   /*
    상품코드에 해당하는 가격, 설명 수정하기 
	@return : true면 수정 완료, false면 수정 실패
   */
   public boolean update(Goods goods) { // 수정하려는 코드, 변경값 - 가격, 설명
	   for(int i=0; i<count; i++) {
		   if(goodsArr[i].getCode().equals(goods.getCode())) {
			   goodsArr[i].setName(goods.getName());
			   goodsArr[i].setPrice(goods.getPrice());
			   goodsArr[i].setExplain(goods.getExplain());
			   
			   return true; // 수정 완료
		   }
	   }
	   return false;
   }
   
   // 상품 정보 삭제
   /*
    * 삭제하려는 상품 코드를 입력 받아
    * index에 해당하는 정보를 찾고, 있으면 index(번지수)를 받는다.
    * index 이후부터 앞으로 하나씩 옮긴다.
    * count 변수 위치의 배열 공간을 null로 변경
    * count 감소
    * */
   public int delete(String code) {
	   /*
	    * int locate = this.findLocate(code);
	    * 
	    * if(locate == -1) return -1;
	    * 
	    * goodsArr[locate] = null // 위치를 null로 초기화
	    * 
	    * for(int i=locate; i<count-1; i++) {
	    * 	goodsArr[i] = goodsArr[i+1];
	    * }
	    * 
	    * goodsArr[--count] = null;
	    * 
	    * return 1;
	    * */
	   
	   int index = -1;
	   
	   for(int i=0; i<count; i++) {
		   if(goodsArr[i].getCode().equals(code)) {
			   index = i;
			   break;
		   }
	   }
	   
	   if(index == -1) return -1;
	   
	   for(int i=index; i<count-1; i++) {
		   goodsArr[i] = goodsArr[i+1];
	   }
	   
	   count--;
	   goodsArr[count] = null;
	   
	   return 1;
   }
}