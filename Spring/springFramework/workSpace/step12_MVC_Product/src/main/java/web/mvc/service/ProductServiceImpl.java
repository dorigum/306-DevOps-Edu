package web.mvc.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import web.mvc.dao.ProductDAO;
import web.mvc.dto.ProductDTO;
import web.mvc.exception.ErrorCode;
import web.mvc.exception.MyErrorException;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

//	@ExceptionHandler(value = {DuplicateRequestException.class})
	private final ProductDAO productDAO;
	
//	private final static int MIN_PRICE = 1000;
//	private final static int MAX_PRICE = 10000;

	@Override
	public List<ProductDTO> select() {
		List<ProductDTO> list = productDAO.select();

		log.info("select = {}", list);

		return list;
	}

	@Override
	public int insert(ProductDTO productDTO) throws MyErrorException {
		if (productDTO.getPrice() > 10000 || productDTO.getPrice() < 1000)
			throw new MyErrorException(ErrorCode.INVALID_PRICE);

		return productDAO.insert(productDTO);
	}

	@Override
	public int delete(String code) throws MyErrorException {
		ProductDTO product = productDAO.selectByCode(code);

		if (product == null) {
			throw new MyErrorException(ErrorCode.INVALID_PRODUCT_CODE);
		}

		return productDAO.delete(code);
	}

	@Override
	// 상품 상세 보기
	public ProductDTO selectByCode(String code) throws MyErrorException {
		ProductDTO product = productDAO.selectByCode(code);

		if (product == null) {
			throw new MyErrorException(ErrorCode.INVALID_PRODUCT_CODE);
		}

		return product;
	}

	@Override
	// 상품 수정하기
	// 1. 가격 범위를 벗어났을 경우
	public int updateByCode(ProductDTO productDTO) throws MyErrorException {
		if (productDTO.getPrice() > 10000 || productDTO.getPrice() < 1000) {
			throw new MyErrorException(ErrorCode.INVALID_PRICE);
		}

		ProductDTO dbProduct = productDAO.selectByCode(productDTO.getCode());

		// 2. 상품 코드가 없는 경우
		if (dbProduct == null) {
			throw new MyErrorException(ErrorCode.INVALID_PRODUCT_CODE);
		}

		int result = productDAO.updateByCode(productDTO);

		// 3. 결과 값이 0인 경우
		if (result == 0) {
			throw new MyErrorException(ErrorCode.FAILD_UPDATE);
		}

		return result;
	}
}