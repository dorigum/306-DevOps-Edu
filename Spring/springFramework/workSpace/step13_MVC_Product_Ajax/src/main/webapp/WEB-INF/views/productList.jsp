<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Product Ajax</title>
<style type="text/css">
body { font-family: Arial, sans-serif; }
table { width: 800px; border: 5px green solid; margin-bottom: 20px; }
td, th { text-align: center; border: 1px gray solid; padding: 6px; }
a { text-decoration: none; cursor: pointer; color: blue; }
button, input[type="button"], input[type="submit"], input[type="reset"] { cursor: pointer; }
.section { margin-bottom: 24px; }
.menu { margin: 20px 0; }
.menu button { margin-right: 8px; padding: 6px 12px; }
.panel { display: none; }
.panel.active { display: block; }
textarea { vertical-align: top; }
</style>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.6.1.min.js"></script>
<script type="text/javascript">
	const contextPath = "${pageContext.request.contextPath}";

	$(function() {
		selectAll();
		showPanel("readPanel");

		$(".menu button").click(function() {
			resetWorkForms();
			showPanel($(this).data("target"));
		});

		$("#insertForm").submit(function(e) {
			e.preventDefault();

			$.ajax({
				url: contextPath + "/products",
				type: "post",
				data: $(this).serialize(),
				success: function() {
					alert("등록되었습니다.");
					$("#insertForm")[0].reset();
					resetWorkForms();
					selectAll();
				},
				error: ajaxError
			});
		});

		$("#readForm").submit(function(e) {
			e.preventDefault();
			read($("#readCode").val());
		});

		$("#updateFindForm").submit(function(e) {
			e.preventDefault();
			showUpdateForm($("#updateFindCode").val());
		});

		$("#updateForm").submit(function(e) {
			e.preventDefault();

			let code = $("#updateCode").val();

			$.ajax({
				url: contextPath + "/products/" + code,
				type: "put",
				contentType: "application/json; charset=UTF-8",
				data: JSON.stringify({
					name: $("#updateName").val(),
					price: $("#updatePrice").val(),
					detail: $("#updateDetail").val()
				}),
				success: function() {
					alert("수정되었습니다.");
					resetWorkForms();
					selectAll();
					read(code);
					showPanel("readPanel");
				},
				error: ajaxError
			});
		});

		$("#deleteForm").submit(function(e) {
			e.preventDefault();
			del($("#deleteCode").val());
		});
	});

	function showPanel(panelId) {
		$(".panel").removeClass("active");
		$("#" + panelId).addClass("active");
	}

	function selectAll() {
		$.ajax({
			url: contextPath + "/products",
			type: "get",
			dataType: "json",
			success: function(result) {
				let str = "";

				$.each(result, function(index, product) {
					str += "<tr>";
					str += "<td>" + (index + 1) + "</td>";
					str += "<td>" + product.code + "</td>";
					str += "<td><a onclick=\"read('" + product.code + "')\">" + product.name + "</a></td>";
					str += "<td>" + product.price.toLocaleString() + "원</td>";
					str += "<td>" + product.detail + "</td>";
					str += "<td><input type='button' value='삭제' onclick=\"readyDelete('" + product.code + "')\"></td>";
					str += "<td><input type='button' value='수정' onclick=\"showUpdateForm('" + product.code + "')\"></td>";
					str += "</tr>";
				});

				$("#productBody").html(str);
			},
			error: ajaxError
		});
	}

	function read(code) {
		$.ajax({
			url: contextPath + "/products/" + code,
			type: "get",
			dataType: "json",
			success: function(product) {
				$("#readCode").val(product.code);
				$("#detailCode").text(product.code);
				$("#detailName").text(product.name);
				$("#detailPrice").text(product.price.toLocaleString() + "원");
				$("#detailDetail").text(product.detail);
				showPanel("readPanel");
			},
			error: ajaxError
		});
	}

	function readyDelete(code) {
		$("#deleteCode").val(code);
		showPanel("deletePanel");
	}

	function del(code) {
		if (!confirm("정말 삭제하시겠습니까?")) {
			return;
		}

		$.ajax({
			url: contextPath + "/products/" + code,
			type: "delete",
			success: function() {
				alert("삭제되었습니다.");
				resetWorkForms();
				selectAll();
				clearDetail();
				showPanel("readPanel");
			},
			error: ajaxError
		});
	}

	function showUpdateForm(code) {
		$.ajax({
			url: contextPath + "/products/" + code,
			type: "get",
			dataType: "json",
			success: function(product) {
				$("#updateFindCode").val(product.code);
				$("#updateCode").val(product.code);
				$("#updateName").val(product.name);
				$("#updatePrice").val(product.price);
				$("#updateDetail").val(product.detail);
				showPanel("updatePanel");
			},
			error: ajaxError
		});
	}

	function clearDetail() {
		$("#detailCode").text("");
		$("#detailName").text("");
		$("#detailPrice").text("");
		$("#detailDetail").text("");
	}

	function resetWorkForms() {
		$("#readForm")[0].reset();
		$("#updateFindForm")[0].reset();
		$("#updateForm")[0].reset();
		$("#deleteForm")[0].reset();
		clearDetail();
	}

	function ajaxError(xhr) {
		if (xhr.responseJSON && xhr.responseJSON.message) {
			alert(xhr.responseJSON.message);
			return;
		}

		alert("요청 처리 중 오류가 발생했습니다.");
	}
</script>
</head>
<body>
	<h1>[ Product Ajax Page ]</h1>

	<div class="menu">
		<button type="button" data-target="readPanel">상세조회</button>
		<button type="button" data-target="insertPanel">상품등록</button>
		<button type="button" data-target="updatePanel">상품수정</button>
		<button type="button" data-target="deletePanel">상품삭제</button>
	</div>

	<div class="section">
		<table id="listTable">
			<caption>
				<h2>상품 목록</h2>
			</caption>
			<thead>
				<tr>
					<th>번호</th>
					<th>상품코드</th>
					<th>상품이름</th>
					<th>상품가격</th>
					<th>상품설명</th>
					<th>삭제하기</th>
					<th>수정하기</th>
				</tr>
			</thead>
			<tbody id="productBody"></tbody>
		</table>
	</div>

	<div id="readPanel" class="section panel">
		<h3>상품 상세조회</h3>
		<form id="readForm">
			<table>
				<tr>
					<th>상품코드</th>
					<td>
						<input type="text" id="readCode">
						<input type="submit" value="조회하기">
					</td>
				</tr>
			</table>
		</form>
		<table>
			<tr>
				<th>상품코드</th>
				<td id="detailCode"></td>
			</tr>
			<tr>
				<th>상품이름</th>
				<td id="detailName"></td>
			</tr>
			<tr>
				<th>상품가격</th>
				<td id="detailPrice"></td>
			</tr>
			<tr>
				<th>상품설명</th>
				<td id="detailDetail"></td>
			</tr>
		</table>
	</div>

	<div id="insertPanel" class="section panel">
		<h3>상품 등록</h3>
		<form id="insertForm">
			<table>
				<tr>
					<th>상품코드</th>
					<td><input type="text" name="code"></td>
				</tr>
				<tr>
					<th>상품이름</th>
					<td><input type="text" name="name"></td>
				</tr>
				<tr>
					<th>상품가격</th>
					<td><input type="number" name="price"></td>
				</tr>
				<tr>
					<th>상품설명</th>
					<td><textarea name="detail" rows="5" cols="30"></textarea></td>
				</tr>
				<tr>
					<th colspan="2">
						<input type="submit" value="등록하기">
						<input type="reset" value="취소하기">
					</th>
				</tr>
			</table>
		</form>
	</div>

	<div id="updatePanel" class="section panel">
		<h3>상품 수정</h3>
		<form id="updateFindForm">
			<table>
				<tr>
					<th>상품코드</th>
					<td>
						<input type="text" id="updateFindCode">
						<input type="submit" value="수정할 상품 찾기">
					</td>
				</tr>
			</table>
		</form>
		<form id="updateForm">
			<input type="hidden" id="updateCode" name="code">
			<table>
				<tr>
					<th>상품이름</th>
					<td><input type="text" id="updateName" name="name"></td>
				</tr>
				<tr>
					<th>상품가격</th>
					<td><input type="number" id="updatePrice" name="price"></td>
				</tr>
				<tr>
					<th>상품설명</th>
					<td><textarea id="updateDetail" name="detail" rows="5" cols="30"></textarea></td>
				</tr>
				<tr>
					<th colspan="2">
						<input type="submit" value="수정하기">
						<input type="reset" value="취소하기">
					</th>
				</tr>
			</table>
		</form>
	</div>

	<div id="deletePanel" class="section panel">
		<h3>상품 삭제</h3>
		<form id="deleteForm">
			<table>
				<tr>
					<th>상품코드</th>
					<td>
						<input type="text" id="deleteCode">
						<input type="submit" value="삭제하기">
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>
