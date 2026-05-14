package web.mvc.dao;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import web.mvc.dto.ProductDTO;
import web.mvc.exception.ErrorCode;
import web.mvc.exception.MyErrorException;

@Repository
@RequiredArgsConstructor
public class ProductDAOImpl implements ProductDAO {
	private final List<ProductDTO> list;

	@Override
	public List<ProductDTO> select() {
		return list;
	}

	@Override
	public int insert(ProductDTO productDTO) throws MyErrorException {
		ProductDTO dbProduct = selectByCode(productDTO.getCode());

		if (dbProduct != null) {
			throw new MyErrorException(ErrorCode.DUPLICATE_PRODUCT_CODE);
		}

		list.add(productDTO);
		return 1;
	}

	@Override
	public int delete(String code) throws MyErrorException {
		ProductDTO product = selectByCode(code);

		if (product == null) {
			return 0;
		}

		list.remove(product);
		return 1;
	}

	@Override
	public ProductDTO selectByCode(String code) {
		for (ProductDTO product : list) {
			if (Objects.equals(product.getCode(), code)) {
				return product;
			}
		}

		return null;
	}

	@Override
	public int updateByCode(ProductDTO productDTO) throws MyErrorException {
		ProductDTO dbProduct = selectByCode(productDTO.getCode());

		if (dbProduct == null) {
			return 0;
		}

		dbProduct.setName(productDTO.getName());
		dbProduct.setPrice(productDTO.getPrice());
		dbProduct.setDetail(productDTO.getDetail());

		return 1;
	}
}
