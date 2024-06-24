<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We"
	crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js"
></script>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
.btn-left {
	float: right;
	margin-right: 10px;
}

.upfliebtn:hover {
	background-color: #f5e4f3;
	border-color: #f5e4f3;
}
</style>
</head>
<body>
	<div class="content">
		<br> <br>
		<div class="innerOuter" align="center">
			<br>
			<h3>프로필 편집</h3>
			<!-- 프로필 사진 업로드 -->
				<form id="update-form" action="upprofile.us" method="post" enctype="multipart/form-data">


				<div>
					<input type="hidden" value="${loginUser.userId}" name="userId">
					${loginUser.userId} <br> <br>
					<!-- 프로필에 표시되는 이미지 -->
					<c:choose>
						<c:when test="${empty loginUser.profilePath}">
							<img id="profile"
								src="${pageContext.request.contextPath}${loginUser.profilePath}"
								width="40" height="40">
						</c:when>
					</c:choose>
					<br> <br> <label for="upfile" class="upfliebtn">사진
						변경</label> <input type="file" id="upfile" class="form-control-file border"
						name="upfile" style="display: none;">
				</div>


			<form id="update-form" action="update.us" method="post">
				<div>
					<h4>소개</h4>
					<textarea id="userMemo" name="userMemo" class="form-control"
						style="resize: none;" cols="40" rows="2"
						placeholder="150자까지 입력하세요.">${loginUser.userMemo}</textarea>
				</div>
				<br> <br>
				<div>
					<h4>이메일 변경하기</h4>
					<input type="email" id="email" name="email" class="form-control"
						value="${loginUser.email}">
				</div>
				<br> <br>
				<div class="btns" align="center">
					<button type="submit" class="btn btn-primary">완료</button>
					<button type="reset" class="btn btn-danger">취소</button>
				</div>
				<br> <br>
				<button type="button" class="btn btn-link" data-bs-toggle="modal"
					data-bs-target="#updatePwdForm">비밀번호 변경</button>
				<br> <br>
			</form>
		</div>
	</div>
	

<div>
	<button type="button" class="btn btn-danger" data-bs-toggle="modal"
		data-bs-target="#deleteForm">회원탈퇴</button>

</div>

<!-- 비밀번호 변경 Modal -->
<div class="modal" id="updatePwdForm">
	<div class="modal-dialog">
		<div class="modal-content">


			<div class="modal-header">
				<h4 class="modal-title">비밀번호 변경</h4>
				<button type="button" class="close" data-dismiss="modal">&times;</button>
			</div>

			<div class="modal-body" align="center">
				<form action="updatePwd.us" method="post">
					<input type="hidden" name="userId" value="${loginUser.userId }">

					<table>
						<tr>
							<td>현재 비밀번호</td>
							<td><input type="password" name="userPwd" required>
							</td>
						</tr>
						<tr>
							<td>변경할 비밀번호</td>
							<td><input type="password" name="updatePwd" required>
							</td>
						</tr>
						<tr>
							<td>변경할 비밀번호 확인</td>
							<td><input type="password" id="chkPwd" required></td>
						</tr>
					</table>
					<br>
					<button type="submit" class="btn btn-secondary"
						onclick="return checkPwd();">비밀번호 변경</button>
				</form>

				<input type="hidden" name="userId" value="${loginUser.userId }">
				<!--  Modal footer -->
				<div class="modal-footer">
					<button type="button" class="btn btn-danger" data-dismiss="modal">닫기</button>
				</div>
			</div>
		</div>
	</div>
</div>


	<script>
		function checkPwd() {
			var updatePwd = $("input[name=updatePwd]").val();
			var checkPwd = $("#chkPwd").val();

			if (updatePwd != checkPwd) {
				alert("변경할 비밀번호와 확인이 일치하지 않습니다.");
				return false;
			} else {
				return true;
			}
		}

		function deleteUser() {
			// Add functionality for deleting user if necessary
		}
	</script>
</body>
</html>