package ex0205.array;

public class ArgsExam {

	public static void main(String[] args) {
		System.out.println("-----시작하기-----");
		System.out.println("args = " + args);
		System.out.println("args.length = " + args.length);
		
//		for(int i=0; i<args.length; i++) {
//			// System.out.print(args[i] + " ");
//			System.out.println("args[" + i + "] = " + args[i]);
//		}
		// 만약 두 개의 숫자 값이 전달되었다고 가정하고 합을 출력하기
		System.out.println(args[0] + args[1]);
		
		int num1 = Integer.parseInt(args[0]);
		int num2 = Integer.parseInt(args[1]);
		
		System.out.println(num1 + num2);
	}
}
