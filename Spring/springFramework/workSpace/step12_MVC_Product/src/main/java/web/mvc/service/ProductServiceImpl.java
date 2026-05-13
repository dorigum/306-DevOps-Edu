package web.mvc.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ControllerAdvice;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import web.mvc.dao.ProductDAO;
import web.mvc.dto.ProductDTO;
import web.mvc.exception.ErrorCode;
import web.mvc.exception.MyErrorException;

@Service
@RequiredArgsConstructor
@Slf4j
@ControllerAdvice
public class ProductServiceImpl implements ProductService {

//	@ExceptionHandler(value = {DuplicateRequestException.class})
	private final ProductDAO productDAO;

	@Override
	public List<ProductDTO> select() {
		log.info("select = {}", select());
		
		return productDAO.select(); // 수정하기?
	}

	@Override
	public int insert(ProductDTO productDTO) throws MyErrorException {
		if(productDTO.getPrice() > 10000 || productDTO.getPrice() < 1000)
			throw new MyErrorException(ErrorCode.INVALID_PRICE);
		return 0;
	}

	@Override
	public int delete(String code) throws MyErrorException {
//		if(code.)
		return 0;
	}

	@Override
	public ProductDTO selectByCode(String code) throws MyErrorException {
		if(!code.equals(code))
			throw new MyErrorException(ErrorCode.INVALID_PRODUCT_CODE);
		return null;
	}

	@Override
	public int updateByCode(ProductDTO productDTO) throws MyErrorException {
//		if( != productDTO.getCode())
//			throw new MyErrorException(ErrorCode.FAILD_UPDATE);
		return 0;
	}

}
