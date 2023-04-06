<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<!-- Required meta tags for bootstrap-->
<meta charset="ISO-8859-1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
<title>X-workz || Sign In</title>
</head>

<body>
	<header>
		<nav style="background-color: black;" class="row">
			<div class="container col">
				<a href="index.jsp"> <img
					src="https://tse4.mm.bing.net/th?id=OIP.sKD6cb_jUksj6usKnPC5awHaDt&pid=Api&P=0"
					width="400" height="100" />
				</a>
			</div>
			<div class="col p-3 my-3 bg-primary">
				<a href="register" style="color: white; font-size: x-large;">
					Register</a>
			</div>
			<div class="col p-3 my-3 bg-primary">
				<a href="signIn" style="color: white; font-size: x-large;"> Sign
					In</a>
			</div>
		</nav>
	</header>
	<marquee style="background-color: red;">SignIn and enjoy
		hassle free training</marquee>
	<h1 style="color: green;" align="center">${success}</h1>
	<h5 style="color: red;" align="center">${errors}</h5>
	<div class="container mt-5">
		<h1>Registration Form</h1>
		<form action="signIn" method="post">
			<div class="form-group">
				<label for="userId">UserId</label> <input type="text"
					class="form-control" id="userName" name="userId" value="${userId}"
					onchange="userNameValidation()"> <a
					id="userNameValidationMessage" style="color: red"></a><a
					id="userNameFromDB" style="color: red;"></a>
			</div>
			<div class="form-group">
				<label for="password">Password</label> <input type="password"
					class="form-control" id="password" name="password"
					onblur="passwordValidation()"> <a
					id="passwordValidationMessage" style="color: red"></a> <input
					type="checkbox" onclick="showPassword()"> Show Password
			</div>
			<button type="submit" class="btn btn-primary" id="signin"
				disabled="disabled">SignIn</button>
		</form>
	</div>
	<footer> Developed by: VinayHp </footer>
	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.14.3/dist/umd/popper.min.js"
		integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/js/bootstrap.min.js"
		integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
		crossorigin="anonymous"></script>
	<script>
		// Get the input elements
		const name = document.getElementById('userName');
		const password = document.getElementById('password');

		function showPassword() {
			if (password.type === "password") {
				password.type = "text";
			} else {
				password.type = "password";
			}
		}

		// Add event listeners to the input fields
		name.addEventListener('input', checkFields);
		password.addEventListener('input', checkFields);

		// Function to check if all fields are filled
		function checkFields() {
			if (name.value !== '' && password.value !== '') {
				signin.disabled = false;
			} else {
				signin.disabled = true;
			}
		}

		function userNameValidation() {
			var uservalue = name.value;
			console.log(uservalue);
			if (uservalue != null && uservalue != "" && uservalue.length > 3
					&& uservalue.length < 30) {
				console.log('userName is valid');
				document.getElementById('userNameValidationMessage').innerHTML = '';

				const xhr = new XMLHttpRequest();
				xhr.open("POST",
						"http://localhost:8082/xworkz-vinayhp-cm/userId/"
								+ uservalue);
				xhr.send();

				xhr.onload = function() {
					console.log(this);
					document.getElementById("userNameFromDB").innerHTML = this.responseText;
				}
			} else {
				console.log('invalid userName');
				document.getElementById('userNameValidationMessage').innerHTML = 'Plese enter valid name min 4 and max 30 character';
			}
		}

		function passwordValidation() {
			var password = document.getElementById('password');
			var confirmPassword = document
					.getElementById('userConfirmPassword');
			var passwordValue = password.value;
			var confirmPasswordvalue = confirmPassword.value;
			console.log(passwordValue);
			if (passwordValue != null && passwordValue != ""
					&& passwordValue.length > 4 && passwordValue.length < 12) {
				console.log('password is valid');
				document.getElementById('passwordValidationMessage').innerHTML = '';
			} else {
				console.log('password is invalid');
				document.getElementById('passwordValidationMessage').innerHTML = 'Plese enter valid password';
			}
		}
	</script>
</body>

</html>