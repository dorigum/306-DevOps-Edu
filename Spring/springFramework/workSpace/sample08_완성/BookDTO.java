package sample08;



import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(exclude = {"writer","date"})
//@Data //Equivalent to @Getter @Setter @RequiredArgsConstructor @ToString @EqualsAndHashCode. 
@Component
@Scope("prototype")
public class BookDTO {
  @Value("springDI")
  private String subject;
  
  @Value("희정")
  //@Getter
  private String writer;
  
  @Value("25000")
  private int price;
  
  @Value("2026-05-08")
  private String date;
  
  
}
