package web.mvc.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import web.mvc.dto.ProductDTO;
import web.mvc.exception.ErrorCode;
import web.mvc.exception.MyErrorException;
import web.mvc.service.ProductService;

@Controller
public class ProductController {

	@Autowired
	private ProductService productService;

	@RequestMapping("/")
	public String main() {
		return "productList";
	}

	/*
	 * 전체 검색
	 */
	@ResponseBody
	@RequestMapping(value = "/products", method = RequestMethod.GET)
	public List<ProductDTO> selectAllAjax() {
		return productService.select();
	}
	
	/*
	 * GetMapping
	 * public ResponseEntity<?> selectAll() {
	 * 	log.info("전체 검색 call");
	 * 
	 */

	@ResponseBody
	@RequestMapping(value = "/products/{code}", method = RequestMethod.GET)
	public ProductDTO selectByCodeAjax(@PathVariable("code") String code) {
		return productService.selectByCode(code);
	}

	/*
	 * 상품 등록
	 */
	@ResponseBody
	@RequestMapping(value = "/products", method = RequestMethod.POST)
	public ResponseEntity<String> insert(HttpServletRequest request) {
		ProductDTO productDTO = new ProductDTO(param(request, "code"), param(request, "name"),
				parsePrice(param(request, "price")), param(request, "detail"));
		productService.insert(productDTO);
		return new ResponseEntity<>("insert success", HttpStatus.CREATED);
	}

	/*
	 * 상품 삭제
	 */
	@ResponseBody // ResponseBody 삭제하고 DeleteMapping으로 변경하기
	@RequestMapping(value = "/products/{code}", method = RequestMethod.DELETE)
	public String deleteAjax(@PathVariable("code") String code) {
		productService.delete(code);
		return "delete success";
	}

	/*
	 * 상품 수정
	 */
	@ResponseBody
	@RequestMapping(value = "/products/{code}", method = RequestMethod.PUT)
	public String updateAjax(@PathVariable("code") String code, @RequestBody ProductDTO productDTO) {
		productDTO.setCode(code);
		productService.updateByCode(productDTO);
		return "update success";
	}

	private String param(HttpServletRequest request, String name) {
		String value = request.getParameter(name);

		if (value != null) {
			return value.trim();
		}

		@SuppressWarnings("unchecked")
		Map<String, String[]> parameterMap = request.getParameterMap();

		for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
			if (name.equals(entry.getKey().trim()) && entry.getValue().length > 0) {
				return entry.getValue()[0].trim();
			}
		}

		return null;
	}

	private int parsePrice(String price) {
		try {
			return Integer.parseInt(price);
		} catch (NumberFormatException | NullPointerException e) {
			throw new MyErrorException(ErrorCode.INVALID_PRICE);
		}
	}
}
