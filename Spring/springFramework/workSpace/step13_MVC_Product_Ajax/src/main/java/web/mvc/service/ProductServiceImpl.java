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

	private final ProductDAO productDAO;

	@Override
	public List<ProductDTO> select() {
		List<ProductDTO> list = productDAO.select();
		log.info("select = {}", list);
		return list;
	}

	@Override
	public int insert(ProductDTO productDTO) throws MyErrorException {
		if (isBlank(productDTO.getCode())) {
			throw new MyErrorException(ErrorCode.INVALID_PRODUCT_CODE);
		}

		if (productDTO.getPrice() > 10000 || productDTO.getPrice() < 1000) {
			throw new MyErrorException(ErrorCode.INVALID_PRICE);
		}

		return productDAO.insert(productDTO);
	}

	@Override
	public int delete(String code) throws MyErrorException {
		if (isBlank(code)) {
			throw new MyErrorException(ErrorCode.INVALID_PRODUCT_CODE);
		}

		ProductDTO product = productDAO.selectByCode(code);

		if (product == null) {
			throw new MyErrorException(ErrorCode.INVALID_PRODUCT_CODE);
		}

		return productDAO.delete(code);
	}

	@Override
	public ProductDTO selectByCode(String code) throws MyErrorException {
		if (isBlank(code)) {
			throw new MyErrorException(ErrorCode.INVALID_PRODUCT_CODE);
		}

		ProductDTO product = productDAO.selectByCode(code);

		if (product == null) {
			throw new MyErrorException(ErrorCode.INVALID_PRODUCT_CODE);
		}

		return product;
	}

	@Override
	public int updateByCode(ProductDTO productDTO) throws MyErrorException {
		if (isBlank(productDTO.getCode())) {
			throw new MyErrorException(ErrorCode.INVALID_PRODUCT_CODE);
		}

		if (productDTO.getPrice() > 10000 || productDTO.getPrice() < 1000) {
			throw new MyErrorException(ErrorCode.INVALID_PRICE);
		}

		ProductDTO dbProduct = productDAO.selectByCode(productDTO.getCode());

		if (dbProduct == null) {
			throw new MyErrorException(ErrorCode.INVALID_PRODUCT_CODE);
		}

		int result = productDAO.updateByCode(productDTO);

		if (result == 0) {
			throw new MyErrorException(ErrorCode.FAILD_UPDATE);
		}

		return result;
	}

	private boolean isBlank(String value) {
		return value == null || value.trim().isEmpty();
	}
}
