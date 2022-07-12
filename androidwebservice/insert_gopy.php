<?php
$connect = mysqli_connect("localhost","root","","quanlychungcu");
mysqli_query($connect, "SET NAMES 'utf8'");

$id = "";
$content = $_POST['noidung'];
$id_apartment = $_POST['id_apartment'];
$date = date('Y-m-d');

$query = "INSERT INTO formulation VALUES('$id', '$content', '$id_apartment','$date')";
if (mysqli_query($connect, $query)){
	echo "success";
} else {
	echo "error";
}
?>
