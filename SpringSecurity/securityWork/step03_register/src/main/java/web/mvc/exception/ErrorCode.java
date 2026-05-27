package web.mvc.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode { // enum은 'Enumeration' 의 약자로, 열거/목록이라는 뜻
    DUPLICATED(HttpStatus.BAD_REQUEST, "Duplicate Id", "아이디가 중복입니다."),
    WRONG_PASS(HttpStatus.BAD_REQUEST, "Wrong Password", "비밀번호 오류입니다."),

    NOTFOUND_NO(HttpStatus.NOT_FOUND, "Not Found Board SearchById", "글 번호를 확인하세요."),
    NOTFOUND_BOARD(HttpStatus.BAD_REQUEST, "Not Found Board All", "전체 게시물을 조회할 수 없습니다."),

    UPDATE_FAILED(HttpStatus.BAD_REQUEST, "Update fail", "수정할 수 없습니다."),
    DELETE_FAILED(HttpStatus.BAD_REQUEST, "Delete fail", "삭제할 수 없습니다.");

    private final HttpStatus httpStatus;
    private final String title;
    private final String message;
}