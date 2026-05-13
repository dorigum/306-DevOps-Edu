package web.mvc.dao;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ControllerAdvice;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import web.mvc.dto.ProductDTO;
import web.mvc.exception.ErrorCode;
import web.mvc.exception.MyErrorException;

@Repository
@RequiredArgsConstructor
@Slf4j
@ControllerAdvice
public class ProductDAOImpl implements ProductDAO {
	private final List<ProductDTO> list; // 영속성 = DB 역할(CRUD 작업)
	
	@PostConstruct
	public void init() {
		log.info("list = {}", list);
	}
	
	@Override
	public List<ProductDTO> select() {
		return list;
	}

	@Override
	public int insert(ProductDTO productDTO) throws MyErrorException {
		log.info("productDTO = {}", productDTO);
		
		if(productDTO.getCode().equals(insert(productDTO))) {
			throw new MyErrorException(ErrorCode.DUPLICATE_PRODUCT_CODE);
		}
		return 0;
		
	}

	@Override
	public int delete(String code) throws MyErrorException {
//		log.info("code = {}", code);
		return 0;
	}

	@Override
	public ProductDTO selectByCode(String code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByCode(ProductDTO productDTO) throws MyErrorException {
		// TODO Auto-generated method stub
		return 0;
	}

}
