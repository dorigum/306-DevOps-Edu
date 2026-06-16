package kosta.web.ai.tools;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileStorageTools {
    @Tool(description = "시험 문제를 로컬 폴더에 파일로 저장합니다.")
    public String saveQuizContent(@ToolParam(description = "시험문제") String quizContent) {
        System.out.println("FileStorageTools LLM을 통해 Spring AI가 호출하였습니다.");
        System.out.println("quizContent = " + quizContent);
        System.out.println("--------------------------------------------");

        String returnMessage = "";

        try {
            String filePath = this.saveQuizToFile(quizContent);
            returnMessage = "성공적으로 저장되었습니다: " + filePath;
        } catch (Exception e) {
            returnMessage = "저장 실패: " + e.getMessage();
        }

        return returnMessage;
    }

    public String saveQuizToFile(String content) throws IOException {

        // 현재 날짜/시간을 기반으로 파일명 생성
        // 예: 20260613-143015.txt
        String fileName = new SimpleDateFormat("yyyyMMdd-HHmmss")
                .format(new Date()) + ".txt";

        // 상대 경로 사용
        // 실행 중인 애플리케이션의 작업 디렉토리 아래
        // quiz 폴더에 파일을 생성하도록 경로 설정

        // => /home/user/my-project/quiz/20260613-143015.txt
        Path path = Paths.get("quiz", fileName);

        // quiz 디렉토리가 존재하지 않으면 생성
        // 이미 존재하면 아무 작업도 하지 않음
        Files.createDirectories(path.getParent());

        // 파일 내용 저장
        // CREATE : 파일이 없으면 새로 생성
        // TRUNCATE_EXISTING : 파일이 이미 있으면 기존 내용을 모두 지우고 새로 작성
        Files.writeString(
                path,
                content,
                StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING
        );

        // 생성된 파일의 절대 경로 반환
        // 예: /home/user/my-project/quiz/20260613-143015.txt
        return path.toAbsolutePath().toString();
    }
}