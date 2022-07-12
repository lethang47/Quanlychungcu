<?php
$connect = mysqli_connect("localhost","root","","quanlychungcu");
mysqli_query($connect, "SET NAMES 'utf8'");

$query = "SELECT * FROM formulation";
$data = mysqli_query($connect, $query);
class User{
	function User($id, $content, $id_apartment, $date){
		$this->Id = $id;
		$this->Content = $content;
		$this->Id_apartment = $id_apartment;
		$this->Date = $date;
	}
}

$mangUser = array();
while($row = mysqli_fetch_assoc($data)){
	array_push($mangUser, new User($row['id'], $row['content'], $row['id_apartment'], $row['date']));	
}
echo json_encode($mangUser);
?>
