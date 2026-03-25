package ex0205_구도연;

public class Lotto {
    public static void main(String[] args) {
        // 1. 배열 생성
        int[] lotto = new int[6];

        // 2. 숫자 채우기(중복 체크 포함)
        setNumbers(lotto);

        // 3. 내림차순 정렬
        sortDesc(lotto);

        // 4. 출력하기
        printArray(lotto);
    }

 // --------------------------------------------------------------------------
 
    // 1. 중복값 체크하기
    public static boolean isDuplicate(int[] arr, int num) {
    	for(int i : arr) {
    		if(i == num) return true;
    	}
        return false; // 중복 아니면 false, 중복이면 true 리턴
    }

    // 2. 1~45번 중에서 6개의 랜덤 숫자 뽑기
    public static void setNumbers(int[] arr) {
        int count = 0; // 지금까지 뽑은 숫자 개수
        
        while(count < 6) {
             // 랜덤 숫자 뽑기
             // 중복값이 아니면 배열에 넣고 count 증가
        	int randomNum = (int)(Math.random() * 45) + 1;
        	
        	if(!isDuplicate(arr, randomNum)) {
        		arr[count] = randomNum;
        		count++;
        	}
        }
    }

    // 3. 내림차순 정렬(버블 정렬)
    public static void sortDesc(int[] arr) {
        // 앞, 뒤 숫자를 비교 -> 자리 바꿈
    	for(int i=0; i<arr.length-1; i++) {
    		for(int j=0; j<arr.length-1-i; j++) {
    			if(arr[i] < arr[j+1]) {
    				int temp = arr[j];
    				arr[j] = arr[j+1];
    				arr[j+1] = temp;
    			}
    		}
    	}
    }

    // 4. 출력하기
    public static void printArray(int[] arr) {
    	System.out.println("💰💰로또 번호 뽑기!!!💰💰 ");
    	
    	for(int num : arr) {
    		System.out.print(num + " ");
    	}
    }
}