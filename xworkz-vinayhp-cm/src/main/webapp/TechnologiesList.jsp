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
<title>X-workz || Home</title>

<style>
body {
	margin: 0;
	padding: 0;
	font-family: Arial, sans-serif;
}

header {
	background-color: #333;
	color: #fff;
	padding: 20px;
	display: flex;
	align-items: center;
	justify-content: space-between;
}

header img {
	height: 50px;
}

nav ul {
	list-style: none;
	display: flex;
}

nav li {
	margin-left: 20px;
}

nav a {
	color: #fff;
	text-decoration: none;
	font-weight: bold;
}

nav img {
	height: 30px;
	width: 40px;
	border-radius: 50px;
}

main {
	padding: 50px;
}

nav a:hover {
	color: #ffc107;
	background-color: #555;
	border-radius: 5px;
}

.userId {
	background-color: red;
}

footer {
	background-color: #333;
	color: #fff;
	padding: 20px;
	text-align: center;
}
</style>
</head>
<body>
	<header>

		<img alt="Xworkz Logo" src="download?fileName=xworkz_logo.png" />
		<nav>
			<ul>
				<li><a href="index.jsp">Home</a></li>
				<li><a href="register">Register</a></li>
				<li><a href="signIn">SignIn</a></li>
				<li><a href="AboutUs.jsp">About Us</a></li>
				<li><a href="#">Services</a></li>
				<li><a href="ContactUs.jsp">Contact Us</a></li>
				<li><a class="userId">Welcome, ${dto.userId}</a></li>
				<li><img id="profilePic" src="" /></li>
				<!-- <span>Welcome, <span id="userId"></span></span> -->
			</ul>
		</nav>
	</header>

	<main>
		<h1 style="color: navy;" align="center">Technology(s) List</h1>
		<h2>Name: <a style="color: gray;"> ${dto.userId}</a></h2>
		<h2>Email: <a style="color: gray;"> ${dto.email}</a></h2>
		<h2>Total no. of technologies : <a style="color: gray;"> ${dtos.size()}</a></h2>
		<div class="container">
			<table style="width: 100%;" class="table table-striped">
				<thead>
					<tr>
						<th>Name</th>
						<th>Language</th>
						<th>Version</th>
						<th>Owner</th>
						<th>Support From</th>
						<th>Support To</th>
						<th>License</th>
						<th>OS Type</th>
						<th>Open Source</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${dtos}" var="d">
						<tr>
							<td>${d.name}</td>
							<td>${d.language}</td>
							<td>${d.version}</td>
							<td>${d.owner}</td>
							<td>${d.supportFrom}</td>
							<td>${d.supportTo}</td>
							<td>${d.license}</td>
							<td>${d.OSType}</td>
							<td>${d.openSource}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</main>

	<footer>
		<p>&copy; 2023 Xworkz ODC. All rights reserved.</p>
		<p>Developed by: VinayHp</p>
	</footer>

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

	<script type="text/javascript">
		var pic = localStorage.getItem("pic");
		console.log("localStorage : " + pic);
		if (pic == null) {
			var profilePicUrl = "https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_1280.png";
			document.getElementById("profilePic").src = profilePicUrl;
		}

		var ppic = document.getElementById("profilePic")
		console.log(ppic.src)
		ppic.src = "downloadPic?profilePic=" + pic
		console.log(ppic.src)
	</script>

</body>
</html>