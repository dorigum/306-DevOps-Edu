package web.mvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;
import web.mvc.dto.ProductDTO;
import web.mvc.service.ProductService;

@Controller
@Slf4j
public class ProductController {

	@Autowired
	private ProductService productService;

	/*
	 * 전체 검색
	 */
	@RequestMapping("/")
	public ModelAndView selectAll() {
		log.info("전체 검색 call");

		// 서비스 호출
		List<ProductDTO> list = productService.select();

		ModelAndView mv = new ModelAndView();
		mv.setViewName("productList"); // WEB-INF/views/productList.jsp 이동
		mv.addObject("productList", list);

		return mv;
	}

	/*
	 * 상품 등록 폼
	 */
	@RequestMapping("/{url}")
	public void url() {
	}

	/*
	 * 상품 등록 기능
	 */
	@RequestMapping(value = "/products", method = RequestMethod.POST) // insertForm.jsp가 POST /products 요청을 받아서
	public String insert(ProductDTO productDTO) { // Service의 insert()를 호출한 뒤, 목록 페이지로 다시 보냄
		log.info("상품 등록 call = {}", productDTO);

		productService.insert(productDTO);

		return "redirect:/";
	}

	/*
	 * "/read" 요청을 받는 메소드 : 상품의 상세 정보 조회하기
	 * 
	 * 1. 요청에서 code를 받는다. 2. Service에게 code로 상품을 찾아달라고 요청한다. 3. 찾은 product를
	 * read.jsp로 보낸다.
	 */
	@RequestMapping("/read")
	public ModelAndView read(String code) {
		log.info("상품 상세보기 call = {}", code);

		ProductDTO product = productService.selectByCode(code);

		ModelAndView mv = new ModelAndView();
		mv.setViewName("read");
		mv.addObject("product", product);

		return mv;
	}

	/*
	 * 상품 삭제
	 */
	@RequestMapping("/del/{code}")
	public String delete(@PathVariable String code) {
		log.info("상품 삭제 call = {}", code);

		productService.delete(code);

		return "redirect:/";
	}

	/*
	 * 상품 수정 폼
	 */
	@RequestMapping("/updateForm/{code}")
	public ModelAndView updateForm(@PathVariable String code) {
		ProductDTO product = productService.selectByCode(code);

		ModelAndView mv = new ModelAndView();
		mv.setViewName("updateForm");
		mv.addObject("product", product);

		return mv;
	}

	/*
	 * 상품 수정 기능
	 */
	@RequestMapping(value = "/products/{code}", method = RequestMethod.POST)
	public String update(@PathVariable String code, ProductDTO productDTO) {
		productDTO.setCode(code); // ★★★★★★

		productService.updateByCode(productDTO);
		
		return "redirect:/read?code=" + code;
	}
}