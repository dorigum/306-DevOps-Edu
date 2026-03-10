package ex0223.io;

import java.io.File;

import javax.swing.JOptionPane; // GUI

public class FileExam {
	public FileExam() throws Exception {
		String path = JOptionPane.showInputDialog("파일 경로는?");

		System.out.println(path);

		File file = new File(path);

		if (file.exists()) { // 파일이 존재한다면
			System.out.println(path + "은(는) 있습니다^^");

			if (file.isDirectory()) { // 폴더인 경우
				System.out.println("폴더 안에 정보를 확인해볼게요!!");
				
				String fileNames[] = file.list(); // 단순히 정보를 확인하고 싶을 때
				// String fileNames[] = file.listFiles(); 
				
				for (String fname : fileNames) {
					System.out.println(fname);
				}

			} else { // 파일인 경우
				System.out.println("***파일의 정보를 확인해볼게요!!***");
				System.out.println("file.canRead() = " + file.canRead());
				System.out.println("file.canWrite() = " + file.canWrite());
				System.out.println("file.length() = " + file.length());
				System.out.println("file.getName() = " + file.getName());
				System.out.println("file.getAbsolutePath() = " + file.getAbsolutePath());
				System.out.println("file.lastModified() = " + file.lastModified());
			}

		} else {
			System.out.println(path + "가 없으니 생성할게요.");
			// 파일 생성
			file.createNewFile();

			// 폴더 생성
			// file.mkdir();
		}
	}

	public static void main(String[] args) throws Exception {
		new FileExam();
	}
}