package web.mvc.dao;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import web.mvc.dto.ProductDTO;
import web.mvc.exception.ErrorCode;
import web.mvc.exception.MyErrorException;

@Repository
@RequiredArgsConstructor
public class ProductDAOImpl implements ProductDAO {
	private final List<ProductDTO> list; // 영속성 = DB 역할(CRUD 작업)

	@PostConstruct
	public void init() { }

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
			if (product.getCode().equals(code)) {
				return product;
			}
		}

		return null;
	}

	// 상품 수정하기
	// : 실제 list 안의 객체 변경 -> 찾아온 기존 객체의 값을 setter로 변경
	// ★★★dbProduct는 list 안에 들어있는 실제 객체를 참조하고 있기 때문
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