package ex0223.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.Buffer;

import javax.swing.JOptionPane;

public class FileCopyBufferedExam {
	public FileCopyBufferedExam(String readFile, String writeFile) {
		long start = System.nanoTime();
		
		// readFile에 있는 내용을 읽어서 writeFile에 저장한다. (Buffered 사용)
		long end = System.nanoTime();
		
//		BufferedInputStream bis = null;
//		BufferedOutputStream bos = null;
		
		/*
		 * try {
		 * bis = new BufferedInputStream(new FileInputStream(readFile));
		 * bos = new BufferedOutputStream(new FileOutputStream(writeFile));
		 */
		
		try(BufferedInputStream bis = new BufferedInputStream(new FileInputStream(readFile));
			BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(writeFile));) {
			
			int i = 0;
			while((i = bis.read()) != -1) {
				bos.write(i); // 저장 = 쓰기
			}
			
			bos.flush(); // 버퍼 비움
			System.out.println("파일 복사 완료!!");
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		System.out.println(end - start);
	}
	
	public static void main(String[] args) {
		String readFile = JOptionPane.showInputDialog("읽을 파일명");
		String writeFile = JOptionPane.showInputDialog("저장할 파일명");
		
		new FileCopyBufferedExam(readFile, writeFile);
	}
}