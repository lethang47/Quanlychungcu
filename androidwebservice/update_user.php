<?php
$connect = mysqli_connect("localhost","root","","quanlychungcu");
mysqli_query($connect, "SET NAMES 'utf8'");

$id_user = $_POST['id_user'];
$name = $_POST['name'];
$email = $_POST['email'];
$phone_number = $_POST['sdt'];
$gender = $_POST['gender'];
$address = $_POST['address'];
$birthday = $_POST['birthday'];

$query = "UPDATE user SET name = '$name', email = '$email', phone_number = '$phone_number', gender = '$gender', address = '$address', birthday = '$birthday' WHERE id_user = '$id_user'";

if (mysqli_query($connect, $query)){
	echo "success";
} else {
	echo "error";
}
?>
