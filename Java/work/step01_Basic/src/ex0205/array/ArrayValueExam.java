package ex0205.array;

class ArrayValue{
	//정수형 10개 저장하는 배열선언(선언과 동시에 임의값 지정)
	int[] arr = new int[10];
	// int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
	
   // printArrayvalue 메소드 작성
   // 메소드 안에서 위에 선언된 배열방의 값을 출력한다.
	public void printArrayvalue() {
		System.out.println("arr = " + arr);
		
		int len = arr.length;
		System.out.println("배열의 길이 = " + len);
		
		for(int i=0; i<len; i++) {
			System.out.print(arr + " ");
			
		}
		
		// 배열 값 변경
		arr[5] = 100;
		System.out.println("---변경 후---");
			
		for(int i=0; i<len; i++) {
			System.out.println("arr[" + i + "] = " + arr[i]);
		}
		
		System.out.println("---향상된 for 문---");
		for(int i : arr) {
			System.out.println(i);
		}
	}
}

// ---------------------------------------------------------
class ArrayValueExam{
	public static void main(String args []){
        // ArrayValue에 있는 printArrayvalue 메소드 호출
		ArrayValue av = new ArrayValue();
		av.printArrayvalue();
		// new ArrayValue().printArrayvalue(); -> 간략하지만 하나 밖에 호출할 수 없다.
		
	}
}