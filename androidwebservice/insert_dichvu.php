<?php
$connect = mysqli_connect("localhost","root","","quanlychungcu");
mysqli_query($connect, "SET NAMES 'utf8'");

$id = "";
$name = $_POST['tendichvu'];
$price_by_month = $_POST['giadichvu'];
$registration_date = date('Y-m-d');
$term = $_POST['thoihan'];
$discount = $_POST['giamgia'];
$id_user = $_POST['id_nguoidung'];

$query = "INSERT INTO service VALUES('$id', '$name', '$price_by_month', '$registration_date', '$term', '$discount', '$id_user')";
if (mysqli_query($connect, $query)){
	echo "success";
} else {
	echo "error";
}
?>